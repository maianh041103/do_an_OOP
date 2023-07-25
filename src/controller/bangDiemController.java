package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import lib.ConnectDatabase;
import view.bangDiemView;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class bangDiemController implements ActionListener {

    private bangDiemView BangDiemView;

    public bangDiemController(bangDiemView BangDiemView) {
        this.BangDiemView = BangDiemView;
    }

    //Lay ra thong tin sinh vien theo ma sinh vien 
    public ArrayList<Object> getDataSinhVienByMaSV(String maSV) {
        ArrayList<Object> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_sinh_vien, ngay_sinh, ma_lop_quan_li FROM quan_li_sinh_vien "
                    + "WHERE ma_sinh_vien = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ten_sinh_vien"));
                arr.add(Date.valueOf(rs.getString("ngay_sinh")));
                arr.add(rs.getString("ma_lop_quan_li"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Lay ra thong tin nam nhap hoc cua moi lop quan ly theo ma lop
    public int getNamNhapHoc(String maLopQL) {

        String hk = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT hoc_ki FROM lop_quan_li "
                    + "WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                hk = rs.getString("hoc_ki");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int nam = Integer.parseInt(hk.substring(0, 4));
        return nam;
    }

    //Lay ra ten khoa theo ma lop quan ly
    public String getKhoaByMaLopQL(String maLopQL) {
        String tenKhoa = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_khoa FROM lop_quan_li "
                    + "WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                tenKhoa = rs.getString("ten_khoa");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenKhoa;
    }

    ////////////////////////////////////////////Thong tin dien vao bang diem
    //Lay ra ma lop HP theo ma SV , hoc ky , nam theo hoc
    public ArrayList<String> getMaLopHPByMaSV(String maSV, int hocKy, int nam) {
        //Lay ra ma lop hp ma sinh vien theo hoc
        ArrayList<String> arr1 = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_sinh_vien = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr1.add(rs.getString("ma_lop_hoc_phan"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Lay ra ma lop hp ma dung ki va nam
        ArrayList<String> arr2 = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan "
                    + "WHERE hoc_ki = ? AND nam = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setInt(1, hocKy);
            stt.setInt(2, nam);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr2.add(rs.getString("ma_lop_hoc_phan"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> arr = new ArrayList<>();
        for (String tmp1 : arr1) {
            for (String tmp2 : arr2) {
                if (tmp1.equals(tmp2)) {
                    arr.add(tmp1);
                }
            }
        }
        return arr;
    }

    //Lay ra maMon theo ma lop hp
    public String getMaMonByMaLopHP(String maLopHP) {
        String maMon = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon FROM quan_li_lop_hoc_phan "
                    + "WHERE ma_lop_hoc_phan = ? ";
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

    //Lay ra ten mon, so tin chi theo ma mon
    public ArrayList<Object> getTenMonByMaMon(String maMon) {
        ArrayList<Object> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_mon, so_tin_chi FROM mon_hoc "
                    + "WHERE ma_mon = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ten_mon"));
                arr.add(rs.getInt("so_tin_chi"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Lay ra dqt,dthi bang maSV,maLopHP, theo hoc ki, nam
    public ArrayList<Double> getDiemByMaLopHpMaSv(String maLopHP, String maSV) {
        ArrayList<Double> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT diem_qt, diem_thi FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_sinh_vien = ? AND ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            stt.setString(2, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(Double.parseDouble(rs.getString("diem_qt").toString()));
                arr.add(Double.parseDouble(rs.getString("diem_thi").toString()));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    //////////////////////////////////////////////////////////////////////////////
    //Xu ly phan bi trung mon hoc

    //Lấy ra mã lớp học phần của 1 sinh viên có học kì nhỏ hơn thời điểm hiện tại
    public ArrayList<String> getMaLopHPBeforeByMaSV(String maSV, int hocKy, int nam) {
        ArrayList<String> arr1 = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_sinh_vien = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr1.add(rs.getString("ma_lop_hoc_phan"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Lay ra ma lop hp ma dung ki va nam
        ArrayList<String> arr2 = new ArrayList<>();
        if (hocKy == 1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan "
                        + "WHERE nam < ? OR (hoc_ki = ? AND nam = ?) ";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, nam);
                stt.setInt(2, 2);
                stt.setInt(3, nam);

                ResultSet rs = stt.executeQuery();
                while (rs.next()) {
                    arr2.add(rs.getString("ma_lop_hoc_phan"));
                }
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (hocKy == 2) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan "
                        + "WHERE nam <= ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, nam - 1);
                ResultSet rs = stt.executeQuery();
                while (rs.next()) {
                    arr2.add(rs.getString("ma_lop_hoc_phan"));
                }
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (hocKy == 3) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan "
                        + "WHERE nam < ? OR (hoc_ki <> ? AND nam = ?) ";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, nam);
                stt.setInt(2, 1);
                stt.setInt(3, nam);
                ResultSet rs = stt.executeQuery();
                while (rs.next()) {
                    arr2.add(rs.getString("ma_lop_hoc_phan"));
                }
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ArrayList<String> arr = new ArrayList<>();
        for (String tmp1 : arr1) {
            for (String tmp2 : arr2) {
                if (tmp1.equals(tmp2)) {
                    arr.add(tmp1);
                }
            }
        }
        return arr;
    }

    //Lay ra danh sach cac mon hoc sau khi da bo di phan mon bi trung
    public ArrayList<String> getMaLopHpUpdate(String maSV, int hocKi, int nam) {
        ArrayList<String> maLopHpUpdate = new ArrayList<String>(); // chi so 0 luu maHP ki hien tai, chi so 1 luu maHP ki truoc do
        ArrayList<String> maLopHpDaDK = getMaLopHPBeforeByMaSV(maSV, hocKi, nam);
        ArrayList<String> maLopHpDKiHienTai = getMaLopHPByMaSV(maSV, hocKi, nam);
        HashMap<String,String> map = new HashMap<>(); // luu ma mon , ma lop hoc phan
        for (String tmp : maLopHpDKiHienTai){
            map.put(getMaMonByMaLopHP(tmp), tmp);
        }
        for(String tmp: maLopHpDaDK){
            map.put(getMaMonByMaLopHP(tmp), tmp);
        }
        Set<Map.Entry<String,String>> entrySet = map.entrySet();
        for(Map.Entry<String,String> entry : entrySet){
            maLopHpUpdate.add(entry.getValue());
        }
        return maLopHpUpdate;
    }

    ///////////////////////////////////////////////////////////////////////////////
    //Them du lieu len bang diem
    public void getDataBangDiem(String maSV, int hocKi, int nam) {
        ArrayList<String> arr = getMaLopHPByMaSV(maSV, hocKi, nam);
        int i = 1;
        for (String maLopHP : arr) {
            Vector<Object> data = new Vector<>();
            data.add(i);
            String maMon = getMaMonByMaLopHP(maLopHP);
            data.add(maMon);
            ArrayList<Object> tmp = getTenMonByMaMon(maMon);
            data.add(tmp.get(0));
            data.add(maLopHP);
            data.add(tmp.get(1));
            ArrayList<Double> diem = getDiemByMaLopHpMaSv(maLopHP, maSV);
            double dqt = diem.get(0);
            double diemThi = diem.get(1);
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
            BangDiemView.getModel().addRow(data);
            i++;
        }
    }

    //:ay ra diem trung binh theo hoc ky he 10
    public double diemHocKyHe10() {
        double res = 0;
        for (int i = 0; i < BangDiemView.getModel().getRowCount(); i++) {
            res += Double.parseDouble(BangDiemView.getModel().getValueAt(i, 7).toString());
        }
        return (Math.round((res / BangDiemView.getModel().getRowCount()) * 100)) / 100.0;
    }

    //Lay ra diem trung binh theo hoc ky he 4
    public double diemHocKyHe4() {
        double res = 0;
        for (int i = 0; i < BangDiemView.getModel().getRowCount(); i++) {
            res += Double.parseDouble(BangDiemView.getModel().getValueAt(i, 8).toString());
        }
        return (Math.round((res / BangDiemView.getModel().getRowCount()) * 100)) / 100.0;
    }

    //Lấy ra mã lớp học phần của 1 sinh viên tính đến thời điểm hiện tại
    public ArrayList<String> getMaLopHPTlByMaSV(String maSV, int hocKy, int nam) {

        ArrayList<String> arr1 = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_sinh_vien = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr1.add(rs.getString("ma_lop_hoc_phan"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> arr2 = new ArrayList<>();
        if (hocKy == 1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan "
                        + "WHERE nam <= ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, nam);
                ResultSet rs = stt.executeQuery();
                while (rs.next()) {
                    arr2.add(rs.getString("ma_lop_hoc_phan"));
                }
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (hocKy == 2) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan "
                        + "WHERE nam < ? OR (hoc_ki <> ? AND nam = ?) ";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, nam);
                stt.setInt(2, 1);
                stt.setInt(3, nam);
                ResultSet rs = stt.executeQuery();
                while (rs.next()) {
                    arr2.add(rs.getString("ma_lop_hoc_phan"));
                }
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (hocKy == 3) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan "
                        + "WHERE nam < ? OR (hoc_ki = ? AND nam = ?) ";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setInt(1, nam);
                stt.setInt(2, 2);
                stt.setInt(3, nam);

                ResultSet rs = stt.executeQuery();
                while (rs.next()) {
                    arr2.add(rs.getString("ma_lop_hoc_phan"));
                }
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ArrayList<String> arr = new ArrayList<>();
        for (String tmp1 : arr1) {
            for (String tmp2 : arr2) {
                if (tmp1.equals(tmp2)) {
                    arr.add(tmp1);
                }
            }
        }
        return arr;
    }

    //Lay ra diem tich luy he 10
    public double diemTichLuyHe10(String maSV, int hocKy, int nam) {
        double diemTichLuyHe10 = 0;
        ArrayList<String> maLopHP = getMaLopHpUpdate(maSV, hocKy, nam);
        for (String tmp : maLopHP) {
            ArrayList<Double> diem = getDiemByMaLopHpMaSv(tmp, maSV);
            double dqt = diem.get(0);
            double diemThi = diem.get(1);
            diemTichLuyHe10 += dqt * 0.3 + diemThi * 0.7;
        }
        diemTichLuyHe10 /= maLopHP.size();
        return (Math.round(diemTichLuyHe10 * 100)) / 100.0;
    }

    //lay ra diem tich luy he 4
    public double diemTichLuyHe4(String maSV, int hocKy, int nam) {
        double diemTichLuyHe4 = 0;
        ArrayList<String> maLopHP = getMaLopHpUpdate(maSV, hocKy, nam);
        for (String tmp : maLopHP) {
            ArrayList<Double> diem = getDiemByMaLopHpMaSv(tmp, maSV);
            double dqt = diem.get(0);
            double diemThi = diem.get(1);
            double dtk = dqt * 0.3 + diemThi * 0.7;
            double diemHe4;
            if (dtk < 4.0) {
                diemHe4 = 0;
            } else if (dtk >= 4 && dtk < 5) {
                diemHe4 = 1;
            } else if (dtk >= 5 && dtk < 6) {
                diemHe4 = 1.5;
            } else if (dtk >= 6 && dtk < 6.5) {
                diemHe4 = 2;
            } else if (dtk >= 6.5 && dtk < 7) {
                diemHe4 = 2.5;
            } else if (dtk >= 7 && dtk < 8) {
                diemHe4 = 3;
            } else if (dtk >= 8 && dtk < 8.5) {
                diemHe4 = 3.5;
            } else {
                diemHe4 = 4;
            }
            diemTichLuyHe4 += diemHe4;
        }

        diemTichLuyHe4 /= maLopHP.size();
        return (Math.round(diemTichLuyHe4 * 100)) / 100.0;
    }

    //Tong so tin da dki
    public int tongTinDangKi(String maSV, int hocKy, int nam) {
        int tongTin = 0;
        ArrayList<String> maLopHP = getMaLopHPTlByMaSV(maSV, hocKy, nam);
        for (String tmp : maLopHP) {
            String maMon = getMaMonByMaLopHP(tmp);
            ArrayList<Object> arr = getTenMonByMaMon(maMon);
            tongTin += Integer.parseInt(arr.get(1).toString());
        }
        return tongTin;
    }

    //Tong tin tich luy ( gom nhung mon khong bi diem F)
    public int tongTinTichLuy(String maSV, int hocKy, int nam) {
        int tongTin = 0;
        ArrayList<String> maLopHP = getMaLopHpUpdate(maSV, hocKy, nam);
        for (String tmp : maLopHP) {
            ArrayList<Double> diem = getDiemByMaLopHpMaSv(tmp, maSV);
            double dqt = diem.get(0);
            double diemThi = diem.get(1);
            double dtk = dqt * 0.3 + diemThi * 0.7;
            if (dtk >= 4) {
                String maMon = getMaMonByMaLopHP(tmp);
                ArrayList<Object> arr = getTenMonByMaMon(maMon);
                tongTin += Integer.parseInt(arr.get(1).toString());
            }
        }
        
        return tongTin;
    }

    //Xep loai hoc luc cua ki
    public String xepLoaiTheoKy() {
        String xepLoai = "";
        double diem = diemHocKyHe4();
        if (diem < 1) {
            xepLoai = "Kém";
        } else if (diem < 2) {
            xepLoai = "Yếu";
        } else if (diem < 2.5) {
            xepLoai = "Trung Bình";
        } else if (diem < 3.2) {
            xepLoai = "Khá";
        } else if (diem < 3.6) {
            xepLoai = "Giỏi";
        } else {
            xepLoai = "Xuất Sắc";
        }
        return xepLoai;
    }

    //Xep loai hoc luc tich luy
    public String xepLoaiTichLuy(String maSV, int hocKy, int nam) {
        String xepLoai = "";
        double diem = diemTichLuyHe4(maSV, hocKy, nam);
        if (diem < 1) {
            xepLoai = "Kém";
        } else if (diem < 2) {
            xepLoai = "Yếu";
        } else if (diem < 2.5) {
            xepLoai = "Trung Bình";
        } else if (diem < 3.2) {
            xepLoai = "Khá";
        } else if (diem < 3.6) {
            xepLoai = "Giỏi";
        } else {
            xepLoai = "Xuất Sắc";
        }
        return xepLoai;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("cbbHocKy")) {
            String tmp = BangDiemView.getCbbHocKy().getSelectedItem().toString();
            int hk = Integer.parseInt(tmp.substring(2, 3));
            int nam;
            if (hk == 1) {
                nam = Integer.parseInt(tmp.substring(4, 8));
            } else {
                nam = Integer.parseInt(tmp.substring(4, 8)) + 1;
            }
            BangDiemView.getModel().setRowCount(0);
            getDataBangDiem(BangDiemView.getMaSV(), hk, nam);

            BangDiemView.setTongKet(hk, nam);
        }
    }

}
