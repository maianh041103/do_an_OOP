package controller;

import Table.StatusType;
import Table.StatusTypeStudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import lib.ConnectDatabase;
import view.sinhVienLopHPView;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.sinhVienLopHPModel;

public class sinhVienLopHPController implements ActionListener {

    private sinhVienLopHPView SinhVienLopHPView;
    private sinhVienLopHPModel SinhVienLopHPModel;

    public sinhVienLopHPController(sinhVienLopHPView SinhVienLopHPView) {
        this.SinhVienLopHPView = SinhVienLopHPView;
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

    //Lay ra ma lop quan ly cho vao cbb
    public ArrayList<String> getDataMaLopQL() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_quan_li FROM lop_quan_li WHERE trang_thai = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, StatusType.PROGRESSING.toString());
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ma_lop_quan_li"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        }
        return arr;
    }

    //Lay ra danh sach cac sinh vien theo ma lop quan ly cho vao cbb
    public ArrayList<String> getDataMaSVByMaLopQL(String maLopQL) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM quan_li_sinh_vien WHERE ma_lop_quan_li = ? AND trang_thai = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            stt.setString(2, StatusTypeStudent.PROGRESSING.toString());
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ma_sinh_vien"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        }
        return arr;
    }

    //Lay ra ten sinh vien tu ma sinh vien
    public String getTenSVByMaSV(String maSV) {
        String ten = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_sinh_vien FROM quan_li_sinh_vien WHERE ma_sinh_vien = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                ten = rs.getString("ten_sinh_vien");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ten;
    }

    //Lay ra ten va ma lop quan ly bang ma sinh vien
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

    //Lay ra du lieu thong tin de them vao bang
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

    //Them sinh vien vao bang
    public void insertSinhVienTable() {
        SinhVienLopHPView.getTableSinhVienLopHPView().getModel().setRowCount(0);
        Vector<Vector<Object>> ans = getDataSinhVienByLopHP(SinhVienLopHPView.getTableSinhVienLopHPView().getMaLopHP());
        for (Vector<Object> tmp : ans) {
            SinhVienLopHPView.getTableSinhVienLopHPView().getModel().addRow(tmp);
            SinhVienLopHPView.getTableSinhVienLopHPView().getModel().fireTableDataChanged();
        }
        System.out.println(SinhVienLopHPView.getTableSinhVienLopHPView().getModel().getRowCount());
    }

    //Them du lieu vao database
    public int insertSinhVienLopHPDatabase(sinhVienLopHPModel t) {
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "INSERT INTO lop_hoc_phan_has_sinh_vien(ma_sinh_vien,ma_lop_hoc_phan,diem_qt,diem_thi) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, t.getMaSV());
            stt.setString(2, t.getMaLopHP());
            stt.setDouble(3, t.getDqt());
            stt.setDouble(4, t.getDiemThi());
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("cbbMaLopQL")) {
            SinhVienLopHPView.getCbbMaSV().removeAllItems();
            String maLopQL = SinhVienLopHPView.getCbbMaLopQL().getSelectedItem().toString();
            ArrayList<String> arr = getDataMaSVByMaLopQL(maLopQL);
            for (String tmp : arr) {
                SinhVienLopHPView.getCbbMaSV().addItem(tmp);
            }
        }
        if (src.equals("cbbMaSV")) {
            if (SinhVienLopHPView.getCbbMaSV().getSelectedItem().toString() != null) {
                String ten = SinhVienLopHPView.getCbbMaSV().getSelectedItem().toString();
                SinhVienLopHPView.getTxtTenSV().setText(getTenSVByMaSV(ten));
            }
        }
        if (src.equals("Insert")) {
            String maLopHP = SinhVienLopHPView.getTableSinhVienLopHPView().getMaLopHP();
            String maSV = SinhVienLopHPView.getCbbMaSV().getSelectedItem().toString();
            ArrayList<String> arr = SinhVienLopHPView.getTableSinhVienLopHPView().getTableSinhVienLopHPController().getTenSVAndMaLopQL(maSV);
            String tenSV = arr.get(0);
            String maLopQL = arr.get(1);
            boolean check = false;
            for (int i = 0; i < SinhVienLopHPView.getTableSinhVienLopHPView().getModel().getRowCount(); i++) {
                Vector<Object> data = SinhVienLopHPView.getTableSinhVienLopHPView().getModel().getDataVector().get(i);
                if (maSV.equals(data.get(1))) {
                    check = true;
                    JOptionPane.showMessageDialog(SinhVienLopHPView, "Sinh Viên " + maSV + " đã có trong danh sách", tenSV, JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (check == false) {
                ArrayList<String> maMonDaHoc = getDataMaMonSVDaHoc(maSV);
                for (String ans : maMonDaHoc) {
                    if (ans.equals(getDataMaMonByMaLopHP(maLopHP))) {
                        check = true;
                    }
                }
                if (check == true) {
                    JOptionPane.showMessageDialog(SinhVienLopHPView, "Sinh Viên " + maSV + " đã học môn " + getDataMaMonByMaLopHP(maLopHP), tenSV, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    SinhVienLopHPModel = new sinhVienLopHPModel(maLopHP, maSV, tenSV, maLopQL, 0, 0);
                    insertSinhVienLopHPDatabase(SinhVienLopHPModel);
                    insertSinhVienTable();
                    SinhVienLopHPView.getTableSinhVienLopHPView().getModel().fireTableDataChanged();
                }
            }
            SinhVienLopHPView.setVisible(false);
        }
    }

}
