
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.findStudentView;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;
import lib.ConnectDatabase;

public class findStudentController implements ActionListener{
    private findStudentView FindStudentView;

    public findStudentController(findStudentView FindStudentView) {
        this.FindStudentView = FindStudentView;
    }
    
    //Lay ra thong tin sinh vien theo ma sinh vien 
    public ArrayList<Object> getDataSinhVienByMaSV(String maSV){
        ArrayList<Object> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_sinh_vien, ngay_sinh, dia_chi, ma_lop_quan_li FROM quan_li_sinh_vien "
                    + "WHERE ma_sinh_vien = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ten_sinh_vien"));
                arr.add(Date.valueOf(rs.getString("ngay_sinh")));
                arr.add(rs.getString("dia_chi"));
                arr.add(rs.getString("ma_lop_quan_li"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arr;
    }
    
    //Lay ra ten khoa theo ma lop quan ly
    public String getKhoaByMaLopQL(String maLopQL){
        String tenKhoa = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_khoa FROM lop_quan_li "
                    + "WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                tenKhoa = rs.getString("ten_khoa");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tenKhoa;
    }
    
    
    //Thong tin dien vao bang diem
    
    //Lay ra ma lop HP theo ma SV theo hoc
    public ArrayList<String> getMaLopHPByMaSV(String maSV){
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_sinh_vien = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ma_lop_hoc_phan"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return arr;
    }
    
    //Lay ra ma lop hp k bi trung ma mon
    public ArrayList<String> getMaLopHpByMaSVUpdate(String maSV) {
        ArrayList<String> maLopHpUpdate = new ArrayList<String>();
        ArrayList<String> maLopHp = getMaLopHPByMaSV(maSV);
        HashMap<String, String> map = new HashMap<>(); // luu ma mon , ma lop hoc phan
        for (String ans : maLopHp) {
            map.put(getMaMonByMaLopHP(ans), ans);
        }
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            maLopHpUpdate.add(entry.getValue());
        }
        return maLopHpUpdate;
    }
    
    //Lay ra maMon theo ma lop hp
    public String getMaMonByMaLopHP(String maLopHP){
        String maMon = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon FROM quan_li_lop_hoc_phan "
                    + "WHERE ma_lop_hoc_phan = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                maMon = rs.getString("ma_mon");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return maMon;
    }
    
    //Lay ra ten mon, so tin chi theo ma mon
    public ArrayList<Object> getTenMonByMaMon(String maMon){
        ArrayList<Object> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_mon, so_tin_chi FROM mon_hoc "
                    + "WHERE ma_mon = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ten_mon"));
                arr.add(rs.getString("so_tin_chi"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arr;
    }
    
    //Lay ra dqt,dthi bang maSV,maLopHP
    public ArrayList<Double> getDiemByMaLopHpMaSv(String maLopHP,String maSV){
        ArrayList<Double> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT diem_qt, diem_thi FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_sinh_vien = ? AND ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            stt.setString(2,maLopHP);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(Double.parseDouble(rs.getString("diem_qt").toString()));
                arr.add(Double.parseDouble(rs.getString("diem_thi").toString()));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arr;
    }
    
    //Lay ra diem tich luy
    public double diemHocKyHe4() {
        double res = 0;
        for (int i = 0; i < FindStudentView.getModel().getRowCount(); i++) {
            res += Double.parseDouble(FindStudentView.getModel().getValueAt(i, 8).toString());
        }
        return (Math.round((res / FindStudentView.getModel().getRowCount()) * 100)) / 100.0;
    }
    
    //Lay ra tong so tin chi tich luy
    public int tongTinChi() {
        int res = 0;
        for (int i = 0; i < FindStudentView.getModel().getRowCount(); i++) {
            if(Double.parseDouble(FindStudentView.getModel().getValueAt(i, 7).toString())>=4)
                res += Integer.parseInt(FindStudentView.getModel().getValueAt(i, 4).toString());
        }
        return res;
    }
    
    public void getDataBangDiem(String maSV){
        ArrayList<String>arr = getMaLopHpByMaSVUpdate(maSV);
        int i = 1;
        for(String maLopHP : arr){
            Vector<Object> data = new Vector<>();
            data.add(i);
            String maMon = getMaMonByMaLopHP(maLopHP);
            data.add(maMon);
            ArrayList<Object> tmp = getTenMonByMaMon(maMon);
            data.add(tmp.get(0));
            data.add(maLopHP);
            data.add(tmp.get(1));
            ArrayList<Double>diem = getDiemByMaLopHpMaSv(maLopHP, maSV);
            double dqt = diem.get(0);
            double diemThi = diem.get(1);
            data.add(dqt);
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
                FindStudentView.getModel().addRow(data);
                i++;
        }
    }
    
    public void deleteTable(String maLopHP){
        FindStudentView.getModel().setRowCount(0);
        FindStudentView.setUpDataTable(maLopHP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Find")){
            String maSV = FindStudentView.getTxtMaSV().getText().toString();
            ArrayList<Object> arr = getDataSinhVienByMaSV(maSV);
            if(arr.isEmpty()){
                JOptionPane.showMessageDialog(FindStudentView, "Mã sinh viên không hợp lệ !", "Tìm kiếm sinh viên", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                FindStudentView.setTable();
                FindStudentView.setUpViewInfor();
                deleteTable(maSV);
            }
        }
    }
    
}
