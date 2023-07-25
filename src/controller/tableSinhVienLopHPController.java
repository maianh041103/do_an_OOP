package controller;

import Table.StatusTypeStudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import lib.ConnectDatabase;
import view.tableSinhVienLopHPView;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.sinhVienLopHPModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.sinhVienLopHPView;

public class tableSinhVienLopHPController implements ActionListener {

    private tableSinhVienLopHPView TableSinhVienLopHPView;

    public tableSinhVienLopHPController(tableSinhVienLopHPView TableSinhVienLopHPView) {
        this.TableSinhVienLopHPView = TableSinhVienLopHPView;
    }

    //Lay ra trang thai cua lop hoc phan bang ma lop hoc phan
    public int getTrangThaiByMaLopHP(String maLopHP) {
        int trangThai = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT trang_thai FROM quan_li_lop_hoc_phan WHERE ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                trangThai = rs.getInt("trang_thai");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        }
        return trangThai;
    }

    //Lay ra ten sinh vien va ma lop quan ly
    public ArrayList<String> getTenSVAndMaLopQL(String maSV) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_sinh_vien, ma_lop_quan_li FROM quan_li_sinh_vien "
                    + "WHERE ma_sinh_vien = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ten_sinh_vien"));
                arr.add(rs.getString("ma_lop_quan_li"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Lay ra vector cac dong trong table
    public Vector<Vector<Object>> getDataSinhVienByLopHP(String maLopQL) {
        Vector<Vector<Object>> ans = new Vector<Vector<Object>>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            int i = 1;
            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_sinh_vien"));
                ArrayList<String> arr = getTenSVAndMaLopQL(rs.getString("ma_sinh_vien"));
                data.add(arr.get(0));
                data.add(arr.get(1));
                double dqt = rs.getDouble("diem_qt");
                data.add(dqt);
                double diemThi = rs.getDouble("diem_thi");
                data.add(diemThi);
                double dtk = dqt * 0.3 + diemThi * 0.7;
                data.add((Math.round(dtk * 100.0) / 100.0));
                double diemHe4;
                String diemChu;
                if (dtk < 4.0) {
                    diemHe4 = 0;
                    diemChu = "F";
                } else if (dtk >= 4 && dtk < 5) {
                    diemHe4 = 1;
                    diemChu = "D";
                } else if (dtk >= 5 && dtk < 6) {
                    diemHe4 = 1.5;
                    diemChu = "D+";
                } else if (dtk >= 6 && dtk < 6.5) {
                    diemHe4 = 2;
                    diemChu = "C";
                } else if (dtk >= 6.5 && dtk < 7) {
                    diemHe4 = 2.5;
                    diemChu = "C+";
                } else if (dtk >= 7 && dtk < 8) {
                    diemHe4 = 3;
                    diemChu = "B";
                } else if (dtk >= 8 && dtk < 8.5) {
                    diemHe4 = 3.5;
                    diemChu = "B+";
                } else {
                    diemHe4 = 4;
                    diemChu = "A";
                }
                data.add(diemHe4);
                data.add(diemChu);
                String dat;
                if (dtk >= 4) {
                    dat = "Đạt";
                } else {
                    dat = "Không Đạt";
                }
                data.add(dat);
                ans.add(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public void updateTable() {
        TableSinhVienLopHPView.getModel().setRowCount(0);
        Vector<Vector<Object>> ans = getDataSinhVienByLopHP(TableSinhVienLopHPView.getMaLopHP());
        TableSinhVienLopHPView.getModel().fireTableDataChanged();
        for (Vector<Object> tmp : ans) {
            TableSinhVienLopHPView.getModel().addRow(tmp);
        }
    }

    //Xoa du lieu ra khoi bang
    public void deleteTable(int row) {
        if (row != -1) {
            TableSinhVienLopHPView.getModel().removeRow(row);
            TableSinhVienLopHPView.getModel().fireTableCellUpdated(TableSinhVienLopHPView.getModel().getRowCount() - 1, TableSinhVienLopHPView.getModel().getColumnCount());
        }
    }

    //Xoa du lieu ra khoi database
    public int deleteDatabase(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM lop_hoc_phan_has_sinh_vien WHERE ma_sinh_vien = ? AND ma_lop_hoc_phan = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableSinhVienLopHPView.getModel().getValueAt(row, 1).toString());
                stt.setString(2, TableSinhVienLopHPView.getMaLopHP());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }
    
    public boolean checkEditDiem(int row){
        boolean check = true;
        if(row != - 1){
            if(Double.valueOf(TableSinhVienLopHPView.getModel().getValueAt(row, 4).toString())<0 || Double.valueOf(TableSinhVienLopHPView.getModel().getValueAt(row, 4).toString()) > 10 )
                check = false;
            }
        return check;
    }

    //Sua du lieu trong database
    public int editDatabase(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "UPDATE lop_hoc_phan_has_sinh_vien "
                        + "SET diem_qt = ? , diem_thi = ? "
                        + "WHERE ma_sinh_vien = ? AND ma_lop_hoc_phan = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setDouble(1, Double.parseDouble(TableSinhVienLopHPView.getModel().getValueAt(row, 4).toString()));
                stt.setDouble(2, Double.parseDouble(TableSinhVienLopHPView.getModel().getValueAt(row, 5).toString()));
                stt.setString(3, TableSinhVienLopHPView.getModel().getValueAt(row, 1).toString());
                stt.setString(4, TableSinhVienLopHPView.getMaLopHP());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    //Lay ra ma SV cung lop HP
    public ArrayList<String> getMaSVByMaLopHP(String maLopHP) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM lop_hoc_phan_has_sinh_vien WHERE ma_lop_hoc_phan = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ma_sinh_vien"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Them du lieu vao database
    public int insertSinhVienLopHPDatabase(sinhVienLopHPModel t) {
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "INSERT INTO lop_hoc_phan_has_sinh_vien(ma_sinh_vien,ma_lop_hoc_phan,diem_qt,diem_thi,trang_thai) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, t.getMaSV());
            stt.setString(2, t.getMaLopHP());
            stt.setDouble(3, t.getDqt());
            stt.setDouble(4, t.getDiemThi());
            stt.setInt(5, 1);
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    //Xu li su kien cho nut lock
    //Set trang thai cua sinh vien ve 1
    public int setTrangThai(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "UPDATE lop_hoc_phan_has_sinh_vien "
                        + "SET trang_thai = ? "
                        + "WHERE ma_sinh_vien = ? AND ma_lop_hoc_phan = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, 1);
                stt.setString(2, TableSinhVienLopHPView.getModel().getValueAt(row, 1).toString());
                stt.setString(3, TableSinhVienLopHPView.getMaLopHP());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    //Lay ra trang thai bang ma sinh vien va ma lop hoc phan
    public int getTrangThaiByMaLopHpAndMaSV(String maLopHP, String maSV) {
        int trangThai = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT trang_thai FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_lop_hoc_phan = ? AND ma_sinh_vien = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            stt.setString(2, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                trangThai = rs.getInt("trang_thai");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        }
        return trangThai;
    }

    //set Table
    public void setTable(String maLopHP) {
        for (int i = 0; i < TableSinhVienLopHPView.getModel().getRowCount(); i++) {
            int trangThai = getTrangThaiByMaLopHpAndMaSV(maLopHP, TableSinhVienLopHPView.getModel().getValueAt(i, 1).toString());
            if (trangThai == 1) {
                TableSinhVienLopHPView.getModel().setCellEditable(i, 4, false);
                if (Double.parseDouble(TableSinhVienLopHPView.getModel().getValueAt(i, 5).toString()) > 0) {
                    TableSinhVienLopHPView.getModel().setCellEditable(i, 5, false);
                }
            } else {
                TableSinhVienLopHPView.getModel().setCellEditable(i, 4, true);
                TableSinhVienLopHPView.getModel().setCellEditable(i, 5, true);
            }
        }
    }

    //Lay ra ma mon tu ma lop hoc phan
    public String getDataMaMonByMaLopHP(String maLopHP) {
        String maMon = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon FROM quan_li_lop_hoc_phan WHERE ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                maMon = rs.getString("ma_mon");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maMon;
    }

    //Lay ra ma mon ma sinh vien da hoc va da qua mon
    public ArrayList<String> getDataMaMonSVDaHoc(String maSV) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan, diem_qt, diem_thi, trang_thai FROM lop_hoc_phan_has_sinh_vien WHERE ma_sinh_vien = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                double dqt = rs.getDouble("diem_qt");
                double dThi = rs.getDouble("diem_thi");
                double dtk = dqt * 0.3 + dThi * 0.7;
                int trangThai = rs.getInt("trang_thai");
                //sinh vien da co diem >= C thi khong duoc dang ki
                // sinh vien dang theo hoc 1 lop ma chua hoan thanh thi cung k duoc dang ki
                if (dtk >= 6) {
                    arr.add(getDataMaMonByMaLopHP(rs.getString("ma_lop_hoc_phan")));
                } else if (trangThai == 0) {
                    arr.add(getDataMaMonByMaLopHP(rs.getString("ma_lop_hoc_phan")));
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    ////////////////////////////////////////////////Xu ly excel
    public void getDataFromExcel(String maLopHP) {

        if (TableSinhVienLopHPView.getTableSinhVienLopHP().isEditing()) {
            TableSinhVienLopHPView.getTableSinhVienLopHP().getCellEditor().stopCellEditing();
        }

        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportWorkBook = null;

        String currentDirectoryPath = "D:\\";
        JFileChooser excelFileChooserImport = new JFileChooser(currentDirectoryPath);

        FileNameExtensionFilter name = new FileNameExtensionFilter("EXCEL FILE", "xlsx", "xls", "xslm");
        excelFileChooserImport.setFileFilter(name);

        int excelChooser = excelFileChooserImport.showOpenDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            int cnt = 0;
            int count1 = 0;
            try {
                File excelFile = excelFileChooserImport.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportWorkBook = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportWorkBook.getSheetAt(0);

                for (int i = 0; i <= excelSheet.getLastRowNum(); i++) {

                    Vector<Object> data = new Vector<>();
                    XSSFRow excelRow = excelSheet.getRow(i);
                    data.add(TableSinhVienLopHPView.getModel().getRowCount() + 1);
                    double dqt = 0;
                    double diemThi = 0;

                    for (int j = 0; j <= excelRow.getLastCellNum(); j++) {
                        XSSFCell cell = excelRow.getCell(j);

                        if (cell != null) {
                            if (j == 0) {
                                data.add(String.format("%07d", Integer.parseInt(String.valueOf(String.valueOf(Math.round(cell.getNumericCellValue()))))));
                            } else if (j == 3) {
                                dqt = Double.parseDouble(cell.toString());

                            } else if (j == 4) {
                                diemThi = Double.parseDouble(cell.toString());

                            } else {
                                data.add(cell);
                            }
                        }
                    }
                    data.add(dqt);
                    data.add(diemThi);
                    double dtk = dqt * 0.3 + diemThi * 0.7;
                    data.add((Math.round(dtk * 100.0) / 100.0));
                    double diemHe4;
                    String diemChu;
                    if (dtk < 4.0) {
                        diemHe4 = 0;
                        diemChu = "F";
                    } else if (dtk >= 4 && dtk < 5) {
                        diemHe4 = 1;
                        diemChu = "D";
                    } else if (dtk >= 5 && dtk < 6) {
                        diemHe4 = 1.5;
                        diemChu = "D+";
                    } else if (dtk >= 6 && dtk < 6.5) {
                        diemHe4 = 2;
                        diemChu = "C";
                    } else if (dtk >= 6.5 && dtk < 7) {
                        diemHe4 = 2.5;
                        diemChu = "C+";
                    } else if (dtk >= 7 && dtk < 8) {
                        diemHe4 = 3;
                        diemChu = "B";
                    } else if (dtk >= 8 && dtk < 8.5) {
                        diemHe4 = 3.5;
                        diemChu = "B+";
                    } else {
                        diemHe4 = 4;
                        diemChu = "A";
                    }
                    data.add(diemHe4);
                    data.add(diemChu);
                    String dat;
                    if (dtk >= 4) {
                        dat = "Đạt";
                    } else {
                        dat = "Không Đạt";
                    }
                    data.add(dat);

                    boolean check = false;
                    ArrayList<String> maSV = getMaSVByMaLopHP(maLopHP);
                    for (String ans : maSV) {
                        if (ans.equals(data.get(1))) {
                            check = true;
                            cnt++;
                        }
                    }
                    
                    
                    ArrayList<String> maMonDaHoc = getDataMaMonSVDaHoc(data.get(1).toString());
                    for (String ans : maMonDaHoc) {
                        if (ans.equals(getDataMaMonByMaLopHP(maLopHP))) {
                            check = true;
                            count1 ++;
                        }
                    }
                    if(check == false) {
                        
                        TableSinhVienLopHPView.getModel().addRow(data);
                        TableSinhVienLopHPView.getModel().fireTableDataChanged();
                        sinhVienLopHPModel SinhVienLopHPModel = new sinhVienLopHPModel(maLopHP, data.get(1).toString(), data.get(2).toString(), data.get(3).toString(), dqt, diemThi);
                        insertSinhVienLopHPDatabase(SinhVienLopHPModel);
                    }
                }
                if(count1 > 0){
                    JOptionPane.showMessageDialog(excelFileChooserImport, "Danh sách sinh viên vừa thêm \n có " + count1 + " sinh viên đã đăng kí môn học !", "Thêm sinh viên vào lớp HP", JOptionPane.INFORMATION_MESSAGE);
                }
                if (cnt > 0) {
                    JOptionPane.showMessageDialog(excelFileChooserImport, "Danh sách sinh viên vừa thêm \n có " + cnt + " sinh viên trùng !", "Thêm sinh viên vào lớp HP", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

//Lay du lieu ra file excel
    public void exportExcel() {
        //Chon cho luu file
        if (TableSinhVienLopHPView.getTableSinhVienLopHP().isEditing()) {
            TableSinhVienLopHPView.getTableSinhVienLopHP().getCellEditor().stopCellEditing();
        }

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
                    for (int i = 0; i < TableSinhVienLopHPView.getModel().getRowCount(); i++) {

                        XSSFRow excelRow = excelSheet.createRow(i + tmp + 1);
                        for (int j = 1; j < TableSinhVienLopHPView.getModel().getColumnCount() - 1; j++) {
                            XSSFCell excelCell = excelRow.createCell(j);
                            excelCell.setCellValue(TableSinhVienLopHPView.getModel().getValueAt(i, j).toString());
                        }
                    }
                    excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile());
                    excelBOU = new BufferedOutputStream(excelFOU);
                    excelJTableExporter.write(excelBOU);
                } else {
                    excelJTableExporter = new XSSFWorkbook();
                    XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");

                    for (int i = 0; i < TableSinhVienLopHPView.getModel().getRowCount(); i++) {
                        XSSFRow excelRow = excelSheet.createRow(i);
                        for (int j = 0; j < TableSinhVienLopHPView.getModel().getColumnCount() - 1; j++) {
                            XSSFCell excelCell = excelRow.createCell(j);
                            excelCell.setCellValue(TableSinhVienLopHPView.getModel().getValueAt(i, j).toString());

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

    ////////////////////////////////////////////////////////////////////////
    //Chuyen trang thai cua tat ca sinh vien thuoc lop hoc phan do ve 1
    public int setTrangThaiSVByLopHP() {
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "UPDATE lop_hoc_phan_has_sinh_vien "
                    + "SET trang_thai = ? "
                    + "WHERE ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setInt(1, 1);
            stt.setString(2, TableSinhVienLopHPView.getMaLopHP());
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    //Dua trang thai lop hoc phan ve 1
    public int setTrangThaiLopHP() {
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "UPDATE quan_li_lop_hoc_phan "
                    + "SET trang_thai = ? "
                    + "WHERE ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setInt(1, 1);
            stt.setString(2, TableSinhVienLopHPView.getMaLopHP());
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketQua;
    }
    
    public int getRowSelectedByMaLop(String maLopHP){
        int row = 0;
        for(int i=0;i<TableSinhVienLopHPView.getTableLopHPView().getModel().getRowCount();i++){
            if(TableSinhVienLopHPView.getTableLopHPView().getModel().getValueAt(i,2).toString().equals(maLopHP))
                row = i;
        }
        return row;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();

        if (src.equals("Insert")) {
            if (TableSinhVienLopHPView.getTableSinhVienLopHP().isEditing()) {
                TableSinhVienLopHPView.getTableSinhVienLopHP().getCellEditor().stopCellEditing();
            }
            sinhVienLopHPView SinhVienLopHPView = new sinhVienLopHPView(TableSinhVienLopHPView);
            TableSinhVienLopHPView.getModel().fireTableDataChanged();
            SinhVienLopHPView.setVisible(true);
        } else if (src.equals("Back")) {
            TableSinhVienLopHPView.setVisible(false);
        } else if (src.equals("Import")) {
            if (TableSinhVienLopHPView.getTableSinhVienLopHP().isEditing()) {
                TableSinhVienLopHPView.getTableSinhVienLopHP().getCellEditor().stopCellEditing();
            }
            getDataFromExcel(TableSinhVienLopHPView.getMaLopHP());
            TableSinhVienLopHPView.getModel().fireTableDataChanged();
        } else if (src.equals("Export")) {
            if (TableSinhVienLopHPView.getTableSinhVienLopHP().isEditing()) {
                TableSinhVienLopHPView.getTableSinhVienLopHP().getCellEditor().stopCellEditing();
            }
            exportExcel();
        } else if (src.equals("Lock")) {
            int result = JOptionPane.showConfirmDialog(TableSinhVienLopHPView.getTableSinhVienLopHP(), "Bạn có chắc chắn muốn lưu", "Lưu thông tin ", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (TableSinhVienLopHPView.getTableSinhVienLopHP().isEditing()) {
                    TableSinhVienLopHPView.getTableSinhVienLopHP().getCellEditor().stopCellEditing();
                }
                setTrangThaiSVByLopHP();
                setTrangThaiLopHP();
                TableSinhVienLopHPView.getTableLopHPView().getModel().setValueAt("COMPLETE", getRowSelectedByMaLop(TableSinhVienLopHPView.getMaLopHP()), 6);
                //TableSinhVienLopHPView.getTableLopHPView().getModel().setRowCount(0);
               // TableSinhVienLopHPView.getTableLopHPView().getTableLopHPController().getDataLopHP();
                setTable(TableSinhVienLopHPView.getMaLopHP());

            }

        }

    }

}
