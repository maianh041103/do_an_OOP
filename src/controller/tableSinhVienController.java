package controller;

import Table.StatusTypeStudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import lib.ConnectDatabase;
import view.tableSinhVienView;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.sinhVienModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.sinhVienView;

public class tableSinhVienController implements ActionListener {

    private tableSinhVienView TableSinhVienView;

    public tableSinhVienController(tableSinhVienView TableSinhVienView) {
        this.TableSinhVienView = TableSinhVienView;
    }

    //Lay du lieu mon do len bang
    public void getDataSinhVienByLopQL(String maLopQL) {
        try {

            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM quan_li_sinh_vien "
                    + "WHERE ma_lop_quan_li = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            int i = 1;
            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_lop_quan_li"));
                data.add(rs.getString("ma_sinh_vien"));
                data.add(rs.getString("ten_sinh_vien"));
                String tmp = rs.getString("ngay_sinh");
                String date = tmp.substring(8, 10) + "/" + tmp.substring(5, 7) + "/" + tmp.substring(0, 4);
                data.add(date);
                boolean gt = rs.getBoolean("gioi_tinh");
                if (gt == false) {
                    data.add("Nam");
                } else {
                    data.add("Nữ");
                }
                data.add(rs.getString("dia_chi"));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if (trangThai == 0) {
                    data.add(StatusTypeStudent.PROGRESSING.toString());
                } else if (trangThai == 1) {
                    data.add(StatusTypeStudent.COMPLETE.toString());
                } else {
                    data.add(StatusTypeStudent.RESERVE.toString());
                }

                TableSinhVienView.getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Lay ra ma sinh vien
    public ArrayList<String> getMaSV() {
        ArrayList<String> arr = new ArrayList<>();
        try {

            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM quan_li_sinh_vien ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            int i = 1;
            while (rs.next()) {
                arr.add(rs.getString("ma_sinh_vien"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Lay ra trang thai cua lop quan li
    public int getTrangThaiLopQL(String maLopQL) {
        int trangThai = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT trang_thai FROM lop_quan_li WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                trangThai = rs.getInt("trang_thai");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangThai;
    }

    public void updateTable() {
        TableSinhVienView.getModel().setRowCount(0);
        getDataSinhVienByLopQL(TableSinhVienView.getMaLopQL());
    }

    //Xoa du lieu ra khoi bang
    public void deleteTable(int row) {
        if (row != -1) {
            TableSinhVienView.getModel().removeRow(row);
        }
    }

    //Xoa du lieu ra khoi database
    public int deleteDatabase(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM quan_li_sinh_vien WHERE ma_sinh_vien = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableSinhVienView.getModel().getValueAt(row, 2).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    //Sua du lieu trong database
    public int editDatabase(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "UPDATE quan_li_sinh_vien "
                        + "SET ten_sinh_vien = ? , ngay_sinh = ? , gioi_tinh = ? , dia_chi = ? , trang_thai = ? , ma_lop_quan_li = ? "
                        + "WHERE ma_sinh_vien = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableSinhVienView.getModel().getValueAt(row, 3).toString());
                String tmp = TableSinhVienView.getModel().getValueAt(row, 4).toString();
                String date = tmp.substring(6, 10) + "-" + tmp.substring(3, 5) + "-" + tmp.substring(0, 2);
                stt.setDate(2, Date.valueOf(date));
                stt.setBoolean(3, Boolean.parseBoolean(TableSinhVienView.getModel().getValueAt(row, 5).toString()));
                stt.setString(4, TableSinhVienView.getModel().getValueAt(row, 6).toString());
                String trangThai = TableSinhVienView.getModel().getValueAt(row, 7).toString();
                if (trangThai.equals(StatusTypeStudent.PROGRESSING.toString())) {
                    stt.setInt(5, 0);
                } else if (trangThai.equals(StatusTypeStudent.COMPLETE.toString())) {
                    stt.setInt(5, 1);
                } else {
                    stt.setInt(5, 2);
                }
                stt.setString(6, TableSinhVienView.getModel().getValueAt(row, 1).toString());
                stt.setString(7, TableSinhVienView.getModel().getValueAt(row, 2).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

///////////////////////////////////////////// Xet xem sinh vien tot nghiep chua

    // Lay ra so tin chi
    public int getSTCByMaMon(String maMon) {
        int stc = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT so_tin_chi FROM mon_hoc "
                    + "WHERE ma_mon = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                stc = rs.getInt("so_tin_chi");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stc;
    }

    public int getTongTinChiHT(String maSV) {
        int stc = 0;
        HashMap<String, Integer> map = new HashMap<>();
        double diemTb ;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT mon_hoc.ma_mon , so_tin_chi , diem_qt, diem_thi "
                    + "FROM (( lop_hoc_phan_has_sinh_vien "
                    + "INNER JOIN quan_li_lop_hoc_phan ON lop_hoc_phan_has_sinh_vien.ma_lop_hoc_phan = quan_li_lop_hoc_phan.ma_lop_hoc_phan) "
                    + "INNER JOIN mon_hoc ON quan_li_lop_hoc_phan.ma_mon = mon_hoc.ma_mon) "
                    + "WHERE ma_sinh_vien = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                double dqt = rs.getInt("diem_qt");
                double dThi = rs.getInt("diem_thi");
                diemTb = dqt * 0.3 + dThi * 0.7;
                if(diemTb >= 4){
                    if(!map.containsKey(rs.getString("ma_mon"))){
                        map.put(rs.getString("ma_mon"), rs.getInt("so_tin_chi"));
                        stc += rs.getInt("so_tin_chi");
                    }
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stc;
    }

    //Lay ra tong so tin chi sinh vien phai dat duoc
    public int tongTinChiYeuCau() {
        int tongTC = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon FROM chuong_trinh_khung ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                tongTC += getSTCByMaMon(rs.getString("ma_mon"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongTC;
    }

    //Lay ra sinh vien co soTC du dieu kien de tot nghiep
    public ArrayList<String> getMaSVComplete() {
        int tongTinChi = tongTinChiYeuCau();
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM quan_li_sinh_vien ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                if (getTongTinChiHT(rs.getString("ma_sinh_vien")) == tongTinChi) {
                    arr.add(rs.getString("ma_sinh_vien"));
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Chuyen trang thai cua sinh vien ve COMPLETE
    public int updateTrangThaiSV() {
        int ketQua = 0;
        ArrayList<String> maSV = getMaSVComplete();
        for (String tmp : maSV) {
            System.out.println(tmp);
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "UPDATE quan_li_sinh_vien "
                        + "SET trang_thai = ? "
                        + "WHERE ma_sinh_vien = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, 1);
                stt.setString(2, tmp);
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

//////////////////////////////////////// 
    //Them thong tin 1 sinh vien vao database
    public int insertSinhVienDatabase(sinhVienModel t) {
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "INSERT INTO quan_li_sinh_vien(ma_sinh_vien,ten_sinh_vien,ngay_sinh,gioi_tinh,dia_chi,trang_thai,ma_lop_quan_li) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, t.getMaSv());
            stt.setString(2, t.getTenSv());
            stt.setDate(3, t.getNgaySinh());
            stt.setBoolean(4, t.isGioiTinh());
            stt.setString(5, t.getDiaChi());
            stt.setInt(6, t.getTrangThai());
            stt.setString(7, t.getMaLopQL());
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // Nhap du lieu tu file excel
    public void getDataFromExcel(String maLopQL) {

        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportWorkBook = null;

        String currentDirectoryPath = "D:\\";
        JFileChooser excelFileChooserImport = new JFileChooser(currentDirectoryPath);

        //Chon file la file excel
        FileNameExtensionFilter name = new FileNameExtensionFilter("EXCEL FILE", "xlsx", "xls", "xslm");
        excelFileChooserImport.setFileFilter(name);

        int excelChooser = excelFileChooserImport.showOpenDialog(null);

        int count = 0; // Dem so sinh vien trung

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                File excelFile = excelFileChooserImport.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportWorkBook = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportWorkBook.getSheetAt(0);

                for (int i = 0; i <= excelSheet.getLastRowNum(); i++) {

                    Vector<Object> data = new Vector<>();
                    XSSFRow excelRow = excelSheet.getRow(i);
                    data.add(TableSinhVienView.getModel().getRowCount() + 1);
                    data.add(maLopQL);

                    String ns = "";
                    for (int j = 0; j <= excelRow.getLastCellNum(); j++) {
                        XSSFCell cell = excelRow.getCell(j);
                        if (cell != null) {

                            String cellValue;
                            CellType cellType = cell.getCellType();

                            if (cellType == CellType.STRING) {
                                cellValue = cell.getStringCellValue();
                            } else if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                                java.util.Date utilDate = cell.getDateCellValue();
                                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                                String tmp = String.valueOf(sqlDate);
                                ns = tmp;
                                cellValue = tmp.substring(8, 10) + "/" + tmp.substring(5, 7) + "/" + tmp.substring(0, 4);
                            } else if (cellType == CellType.NUMERIC) {
                                cellValue = String.valueOf(Math.round(cell.getNumericCellValue()));
                                if (j == 0) {
                                    cellValue = String.format("%07d", Integer.parseInt(String.valueOf(cellValue)));
                                }
                                if (j == 5) {
                                    if (cellValue.equals("1")) {
                                        cellValue = StatusTypeStudent.COMPLETE.toString();
                                    } else if (cellValue.equals("0")) {
                                        cellValue = StatusTypeStudent.PROGRESSING.toString();
                                    } else {
                                        cellValue = StatusTypeStudent.RESERVE.toString();
                                    }
                                }

                            } else if (cellType == CellType.BOOLEAN) {
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                            } else if (cellType == CellType.FORMULA) {
                                cellValue = cell.getCellFormula();
                            } else if (cellType == CellType.BLANK) {
                                cellValue = "";
                            } else {
                                cellValue = "";
                            }
                            data.add(cellValue);
                        }
                    }
                    boolean check = false;
                    ArrayList<String> maSV = getMaSV();
                    for (String ans : maSV) {
                        if (ans.equals(data.get(2))) {
                            check = true;
                            count++;
                        }
                    }

                    if (check == false) {
                        TableSinhVienView.getModel().addRow(data);
                        String gioitinh = data.get(5).toString();
                        boolean gt;
                        if (gioitinh.equals("Nữ")) {
                            gt = true;
                        } else {
                            gt = false;
                        }
                        int trangThai;
                        if (data.get(7).equals(StatusTypeStudent.COMPLETE.toString())) {
                            trangThai = 1;
                        } else if (data.get(7).equals(StatusTypeStudent.PROGRESSING.toString())) {
                            trangThai = 0;
                        } else {
                            trangThai = 2;
                        }
                        sinhVienModel a = new sinhVienModel(data.get(2).toString(), data.get(3).toString(), Date.valueOf(ns), gt, data.get(6).toString(), trangThai, maLopQL);

                        insertSinhVienDatabase(a);
                    }
                }
                if (count > 0) {
                    JOptionPane.showMessageDialog(TableSinhVienView, "Danh sách vừa thêm có " + count + " thông tin \n sinh viên trùng mã sinh viên ", "Thêm thông tin sinh viên vào lớp QL", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void exportExcel() {
        //Chon cho luu file
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;

        JFileChooser excelFileChooser = new JFileChooser("D:\\");

        excelFileChooser.setDialogTitle("Save As");

        FileNameExtensionFilter name = new FileNameExtensionFilter("EXCEL FILE", "xls", "xlsx");
        excelFileChooser.setFileFilter(name);

        int excelChooser = excelFileChooser.showSaveDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                int result = JOptionPane.showConfirmDialog(excelFileChooser, "Bạn có muốn ghi đè lên file cũ ?", "Xuất dữ liệu ra excel", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    FileInputStream fis = new FileInputStream(excelFileChooser.getSelectedFile());
                    excelJTableExporter = new XSSFWorkbook(fis);
                    XSSFSheet excelSheet = excelJTableExporter.getSheetAt(0);
                    // Sheet sheet = excelJTableExporter.getSheetAt(0);
                    int tmp = excelSheet.getLastRowNum();
                    for (int i = 0; i < TableSinhVienView.getModel().getRowCount(); i++) {

                        XSSFRow excelRow = excelSheet.createRow(i + tmp + 1);
                        for (int j = 1; j < TableSinhVienView.getModel().getColumnCount() - 1; j++) {
                            XSSFCell excelCell = excelRow.createCell(j);
                            excelCell.setCellValue(TableSinhVienView.getModel().getValueAt(i, j).toString());
                        }
                    }
                    excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile());
                    excelBOU = new BufferedOutputStream(excelFOU);
                    excelJTableExporter.write(excelBOU);
                } else {
                    excelJTableExporter = new XSSFWorkbook();
                    XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");

                    for (int i = 0; i < TableSinhVienView.getModel().getRowCount(); i++) {
                        XSSFRow excelRow = excelSheet.createRow(i);
                        for (int j = 0; j < TableSinhVienView.getModel().getColumnCount() - 1; j++) {
                            XSSFCell excelCell = excelRow.createCell(j);
                            excelCell.setCellValue(TableSinhVienView.getModel().getValueAt(i, j).toString());

                        }
                    }

                    excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx"); // tao ra cac file khac nhau
                    excelBOU = new BufferedOutputStream(excelFOU);
                    excelJTableExporter.write(excelBOU);
                }

                JOptionPane.showMessageDialog(excelFileChooser, "Xuất dữ liệu thành công !", "Xuất dữ liệu ra excel", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelFOU != null) {
                        excelFOU.close();
                    }
                    if (excelJTableExporter != null) {
                        excelJTableExporter.close();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();

        if (src.equals("Insert")) {
            sinhVienView SinhVienView = new sinhVienView(TableSinhVienView);
            SinhVienView.setVisible(true);
        } else if (src.equals("Back")) {
            TableSinhVienView.setVisible(false);
        } else if (src.equals("Import")) {
            getDataFromExcel(TableSinhVienView.getMaLopQL());
        } else if (src.equals("Export")) {
            exportExcel();
        }
    }

}
