
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import lib.ConnectDatabase;
import view.findLopHPView;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class findLopHPController implements ActionListener{
    
    private findLopHPView FindLopHPView;

    public findLopHPController(findLopHPView FindLopHPView) {
        this.FindLopHPView = FindLopHPView;
    }
    
    //Lay thong tin lop hoc phan bang ma mon
    public ArrayList<Object> getDataLopHPByMa(String maLop){
        ArrayList<Object> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon, ten_lop_hoc_phan, giang_vien, hoc_ki, nam , trang_thai FROM quan_li_lop_hoc_phan "
                    + "WHERE ma_lop_hoc_phan = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLop);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ma_mon"));
                arr.add(rs.getString("ten_lop_hoc_phan"));
                arr.add(rs.getString("giang_vien"));
                int trangThai = rs.getInt("trang_thai");
                if(trangThai == 0){
                    arr.add("Đang Tiến Hành");
                }
                else{
                    arr.add("Hoàn Thành");
                }
                arr.add(rs.getInt("hoc_ki"));
                arr.add(rs.getInt("nam"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arr;
    }
    
    //Lay ra si so cua lop hoc phan
    public int getSiSo(String maLop){
        int siSo = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_lop_hoc_phan = ? ";
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
    
    //Tinh diem trung binh cau 1 lop hoc phan
    public double getDiemTB(String maLopHP) {
        double res = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT diem_qt, diem_thi FROM lop_hoc_phan_has_sinh_vien WHERE ma_lop_hoc_phan = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                double dqt = rs.getDouble("diem_qt");
                double dthi = rs.getDouble("diem_thi");
                res += dqt*0.3+dthi*0.7;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        res /= getSiSo(maLopHP)*1.0;
        res = (Math.round(res * 100.0) / 100.0);
        return res;
    }
    
    //Lay ra ma sinh vien bang ma lop hoc phan
    public ArrayList<String> getMaSVByMaLop(String maLop){
        ArrayList<String>arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_lop_hoc_phan = ? ";
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
    
    //Lay ra thong tin sinh vien 
    public ArrayList<Object> getDataSVByMaSV(String maSV){
        ArrayList<Object>arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_sinh_vien, ngay_sinh, ma_lop_quan_li FROM quan_li_sinh_vien "
                    + "WHERE ma_sinh_vien = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ten_sinh_vien"));
                arr.add(rs.getDate("ngay_sinh"));
                arr.add(rs.getString("ma_lop_quan_li"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    //Lay ra diem cua tung sinh vien
    public ArrayList<Double> getDiemByMaSV(String maSV,String maLopHP){
        ArrayList<Double>arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT diem_qt, diem_thi FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_sinh_vien = ? AND ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            stt.setString(2, maLopHP);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getDouble("diem_qt"));
                arr.add(rs.getDouble("diem_thi"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    //Lay du lieu len bang
    public void getDataTable(String maLop){
        ArrayList<String> arr = getMaSVByMaLop(maLop);
        int i = 1;
        for(String tmp : arr){
            ArrayList<Object> infor = getDataSVByMaSV(tmp);
            ArrayList<Double> diem = getDiemByMaSV(tmp,maLop);
            Vector<Object>data = new Vector<>();
            data.add(i);
            data.add(tmp);
            data.add(infor.get(0));        
            String ns = infor.get(1).toString();
            String date = ns.substring(8, 10)+"/"+ns.substring(5, 7)+"/"+ns.substring(0, 4);
            data.add(date);
            data.add(infor.get(2));
            double dqt = diem.get(0);
            data.add(dqt);
            double diemThi = diem.get(1);
            data.add(diemThi);
            double dtk = dqt*0.3+diemThi*0.7;        
            data.add((Math.round(dtk * 100.0) / 100.0));        
            double diemHe4;
            String diemChu;
            if(dtk<4.0){
                diemHe4 = 0;
                diemChu = "F";
            }else if(dtk>=4&&dtk<5){
                diemHe4 = 1;
                diemChu = "D";
            }else if(dtk>=5 && dtk<6){
                diemHe4 = 1.5;
                diemChu = "D+";
            }else if(dtk>=6&&dtk<6.5){
                diemHe4 = 2;
                diemChu = "C";
            }else if(dtk>=6.5 && dtk<7){
                diemHe4 = 2.5;
                diemChu = "C+";
            }else if(dtk>=7 && dtk <8){
                diemHe4 = 3;
                diemChu = "B";
            }else if(dtk>=8 && dtk<8.5){
                diemHe4 = 3.5;
                diemChu = "B+";
            }else{
                diemHe4 = 4;
                diemChu = "A";
            }
            data.add(diemHe4);
            data.add(diemChu);
            String dat;
            if(dtk>=4){
                dat = "Đạt";
            }
            else
                dat = "Không Đạt";
            data.add(dat);
            FindLopHPView.getModel().addRow(data);
            i++;
        }
    }
    
    public void deleteTable(String maLopHP){
        FindLopHPView.getModel().setRowCount(0);
        FindLopHPView.setUpDataTable(maLopHP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Find")){
            String maLopHP = FindLopHPView.getTxtMaLop().getText().toString();
            ArrayList<Object> arr = getDataLopHPByMa(maLopHP);
            if(arr.isEmpty()){
                JOptionPane.showMessageDialog(FindLopHPView, "Mã lớp học phần không hợp lệ !", "Tìm kiếm lớp học phần", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                FindLopHPView.setUpInfor();
                FindLopHPView.setTable();
                deleteTable(maLopHP);
            }
        }
    }
    
}
