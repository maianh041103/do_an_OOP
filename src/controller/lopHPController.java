package controller;

import Table.StatusType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lib.ConnectDatabase;
import model.lopHPModel;
import view.lopHPView;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class lopHPController implements ActionListener {

    private lopHPView LopHPView;
    private lopHPModel LopHPModel;
    private int count = 1;

    public lopHPController(lopHPView LopHPView) {
        this.LopHPView = LopHPView;
    }
    
    //Lay du lieu ma lop hoc phan tu ma mon
    public ArrayList<String> getDataMaLopHPByMaMon(String maMon){
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan WHERE ma_mon = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1,maMon );
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ma_lop_hoc_phan"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Lay du lieu ma mon tu database
    public ArrayList<String> getDataMaMon() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon FROM mon_hoc WHERE trang_thai = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, StatusType.PROGRESSING.toString());
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ma_mon"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Get Data Mon
    public void getDataLopHP() {
        int i = 1;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM quan_li_lop_hoc_phan ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();

            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_mon"));
                data.add(rs.getString("ma_lop_hoc_phan"));
                data.add(rs.getString("ten_lop_hoc_phan"));
                data.add(rs.getString("giang_vien"));
                int hocKi = rs.getInt("hoc_ki");
                int nam = rs.getInt("nam");
                String hocKy;
                if(hocKi == 1)
                    hocKy = "HK"+ hocKi + " " + nam+"-"+(int)(nam+1);
                else
                    hocKy = "HK"+ hocKi + " " + (int)(nam-1)+"-"+nam;
                data.add(hocKy);
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if (trangThai == 0) {
                    data.add("PROCESSING");
                } else {
                    data.add("COMPLETE");
                }

                LopHPView.getTableLopHPView().getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getDataLopHPByMaMon(String maMon) {
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM quan_li_lop_hoc_phan WHERE ma_mon = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();

            int i = 1;
            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_mon"));
                data.add(rs.getString("ma_lop_hoc_phan"));
                data.add(rs.getString("ten_lop_hoc_phan"));
                data.add(rs.getString("giang_vien"));
                int hk = rs.getInt("hoc_ki");
                int nam = rs.getInt("nam");
                String hocKy;
                if (hk == 1) {
                    hocKy = "HK" + hk + " " + nam + "-" + (int) (nam + 1);
                } else {
                    hocKy = "HK" + hk + " " + (int) (nam - 1) + "-" + nam;
                }
                data.add(hocKy);
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if (trangThai == 0) {
                    data.add("PROCESSING");
                } else {
                    data.add("COMPLETE");
                }

                LopHPView.getTableLopHPView().getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertLopHPTable() {
        LopHPView.getTableLopHPView().getModel().setRowCount(0);
        getDataLopHP();
    }
    
    public void insertLopHPTableByMaMon(String maMon){
        LopHPView.getTableLopHPView().getModel().setRowCount(0);
        getDataLopHPByMaMon(maMon);
    }

    //Them du lieu vao database
    public int insertLopHPDatabase(lopHPModel t) {
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "INSERT INTO quan_li_lop_hoc_phan(ma_mon,ma_lop_hoc_phan,ten_lop_hoc_phan,giang_vien,trang_thai,hoc_ki,nam) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, t.getMaMon());
            stt.setString(2, t.getMaLopHP());
            stt.setString(3, t.getTenLopHP());
            stt.setString(4, t.getGiangVien());
            stt.setInt(5, t.getTrangThai());
            stt.setInt(6, t.getHocKi());
            stt.setInt(7, t.getNam());

            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public void reset(){
        LopHPView.getTxtMaLopHP().setText("");
        LopHPView.getTxtGiangVien().setText("");
        LopHPView.getTxtTenLopHP().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Insert")) {
            String maMon = LopHPView.getCbbMaMon().getSelectedItem().toString();
            String maLopHP = LopHPView.getTxtMaLopHP().getText().toString();
            String tenLopHP = LopHPView.getTxtTenLopHP().getText().toString();
            String giangVien = LopHPView.getTxtGiangVien().getText().toString();
            String tmp = LopHPView.getCbbTrangThai().getSelectedItem().toString();
            int hocKi = LopHPView.getHocKi();
            int nam = LopHPView.getNam();
            int trangThai;
            // 1 la hoan thanh 0 la chua hoan thanh
            if (tmp.equals(StatusType.COMPLETE.toString())) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
            boolean check = false;
            ArrayList<String>dayMaLopHp = getDataMaLopHPByMaMon(maMon);
            for(String ans : dayMaLopHp){
                if(ans.equals(maLopHP)){
                    check = true;
                }
            }
            if (maMon.equals("") || maLopHP.equals("") || tenLopHP.equals("") || giangVien.equals("")) {
                JOptionPane.showMessageDialog(LopHPView.getTableLopHPView(), "Nhập Lỗi!", maLopHP, JOptionPane.ERROR_MESSAGE);
            } else if(check == true){
                JOptionPane.showMessageDialog(LopHPView.getTableLopHPView(), "Mã lớp học phần bị trùng!", maLopHP, JOptionPane.ERROR_MESSAGE);
            }else {
                LopHPModel = new lopHPModel(maMon, maLopHP, tenLopHP, giangVien,hocKi,nam, trangThai);
                insertLopHPDatabase(LopHPModel);
                if(count==1)
                    insertLopHPTable();
                else
                    insertLopHPTableByMaMon(maMon);
                reset();
                LopHPView.setVisible(false);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    } 

}
