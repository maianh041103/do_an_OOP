
package controller;

import Table.StatusType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import lib.ConnectDatabase;
import view.findLopQLView;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class findLopQLController implements ActionListener{
    private findLopQLView FindLopQLView;

    public findLopQLController(findLopQLView FindLopQLView) {
        this.FindLopQLView = FindLopQLView;
    }
    
    public ArrayList<Object> getDataLopQLByMa(String maLop){
        ArrayList<Object> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_khoa, gvcn, hoc_ki, trang_thai FROM lop_quan_li "
                    + "WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLop);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ten_khoa"));
                arr.add(rs.getString("gvcn"));
                arr.add(rs.getString("hoc_ki"));
                int trangThai = rs.getInt("trang_thai");
                if(trangThai == 0){
                    arr.add("Đang Tiến Hành");
                }
                else{
                    arr.add("Hoàn Thành");
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arr;
    }
    
    public int getSiSo(String maLop){
        int siSo = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM quan_li_sinh_vien "
                    + "WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLop);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                siSo++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return siSo;
    } 
    
    public ArrayList<String> getMaSVByMaLopQL(String maLop){
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM quan_li_sinh_vien "
                    + "WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLop);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ma_sinh_vien"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arr;
    }
    
    public ArrayList<Object> getDataSVByMaSV(String maSV){
        ArrayList<Object> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_sinh_vien , ngay_sinh , gioi_tinh , dia_chi , trang_thai FROM quan_li_sinh_vien "
                    + "WHERE ma_sinh_vien = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ten_sinh_vien"));
                String tmp = rs.getDate("ngay_sinh").toString();
                String date = tmp.substring(8, 10)+"/"+tmp.substring(5, 7)+"/"+tmp.substring(0, 4);
                arr.add(date);
                boolean gt = rs.getBoolean("gioi_tinh");
                if(gt==false){
                    arr.add("Nam");
                }
                else{
                    arr.add("Nữ");
                }
                arr.add(rs.getString("dia_chi"));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if(trangThai == 0){
                    arr.add("PROGRESSING");
                }
                else if(trangThai == 1){
                    arr.add("COMPLETE");
                }
                else{
                    arr.add("RESERVE");
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arr;
    }
    
    public void setUpDataTable(String maLop){
        ArrayList<String> arr = getMaSVByMaLopQL(maLop);
        int i = 1;
        for(String tmp : arr){
            ArrayList<Object>ans = getDataSVByMaSV(tmp);
            Vector<Object>data = new Vector<>();
            data.add(i);
            data.add(tmp);
            data.add(ans.get(0));
            data.add(ans.get(1));
            data.add(ans.get(2));
            data.add(ans.get(3));
            data.add(ans.get(4));
            i++;
            FindLopQLView.getModel().addRow(data);
        }
    }
    
    public void deleteTable(String maLopHP){
        FindLopQLView.getModel().setRowCount(0);
        FindLopQLView.setUpDataTable(maLopHP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Find")){ 
            String maLopQL = FindLopQLView.getTxtMaLop().getText().toString();
            ArrayList<Object> arr = getDataLopQLByMa(maLopQL);
            if(arr.isEmpty()){
                JOptionPane.showMessageDialog(FindLopQLView, "Mã lớp quản lý không hợp lệ !", "Tìm kiếm lớp quản lý", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                FindLopQLView.setTable();
                FindLopQLView.setUpInfor();
                deleteTable(maLopQL);
            }
        }
    }
    
}
