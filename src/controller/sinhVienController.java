
package controller;

import Table.StatusTypeStudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lib.ConnectDatabase;
import model.sinhVienModel;
import view.sinhVienView;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class sinhVienController implements ActionListener{
    
    private sinhVienView SinhVienView;
    private sinhVienModel SinhVienModel;
    int i = 1;
    
    public sinhVienController(sinhVienView SinhVienView){
        this.SinhVienView = SinhVienView;
    }
    ///////////////////////////////////////////////////////
    //Lay du lieu dien vao cbbMaLopHP
    
    public ArrayList<String> getDataMaLopHP(){
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_quan_li FROM lop_quan_li ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ma_lop_quan_li"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return arr;
    }
    
    //Get Data Mon
    public void getDataSinhVien(){
            try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM quan_li_sinh_vien ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            
            while(rs.next()){
                Vector<Object>data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_lop_quan_li"));
                data.add(rs.getString("ma_sinh_vien"));
                data.add(rs.getString("ten_sinh_vien"));
                String tmp = rs.getString("ngay_sinh");
                String date = tmp.substring(8, 10)+"/"+tmp.substring(5, 7)+"/"+tmp.substring(0, 4);
                data.add(date);
                boolean gt = rs.getBoolean("gioi_tinh");
                if(gt==false){
                    data.add("Nam");
                }
                else{
                    data.add("Nữ");
                }
                data.add(rs.getString("dia_chi"));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if(trangThai == 0){
                    data.add(StatusTypeStudent.PROGRESSING.toString());
                }
                else if(trangThai == 1)
                    data.add(StatusTypeStudent.COMPLETE.toString());
                else
                    data.add(StatusTypeStudent.RESERVE.toString());
               
                SinhVienView.getTableSinhVienView().getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getDataSinhVienByLopQL(String maLopQL){
            try {
                
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM quan_li_sinh_vien "
                    + "WHERE ma_lop_quan_li = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                Vector<Object>data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_lop_quan_li"));
                data.add(rs.getString("ma_sinh_vien"));
                data.add(rs.getString("ten_sinh_vien"));
                String tmp = rs.getString("ngay_sinh");
                String date = tmp.substring(8, 10)+"/"+tmp.substring(5, 7)+"/"+tmp.substring(0, 4);
                data.add(date);
                boolean gt = rs.getBoolean("gioi_tinh");
                if(gt==false){
                    data.add("Nam");
                }
                else{
                    data.add("Nữ");
                }
                data.add(rs.getString("dia_chi"));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if(trangThai == 0){
                    data.add(StatusTypeStudent.PROGRESSING.toString());
                }
                else if(trangThai == 1)
                    data.add(StatusTypeStudent.COMPLETE.toString());
                else
                    data.add(StatusTypeStudent.RESERVE.toString());
               
                SinhVienView.getTableSinhVienView().getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertSinhVienTable(){
        SinhVienView.getTableSinhVienView().getModel().setRowCount(0);
        getDataSinhVienByLopQL(SinhVienView.getTableSinhVienView().getMaLopQL().toString());
    }
    
    //Them du lieu vao database
    public int insertSinhVienDatabase(sinhVienModel t){
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "INSERT INTO quan_li_sinh_vien(ma_sinh_vien,ten_sinh_vien,ngay_sinh,gioi_tinh,dia_chi,trang_thai,ma_lop_quan_li) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, t.getMaSv());
            stt.setString(2,t.getTenSv());
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
    
    public void reset(){
        SinhVienView.getTxtDiaChi().setText("");
        SinhVienView.getTxtMaSV().setText("");
        SinhVienView.getTxtTenSV().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Insert")){
            String maLopQL = SinhVienView.getCbbLopQL().getSelectedItem().toString();
            String maSV = SinhVienView.getTxtMaSV().getText();
            String tenSV = SinhVienView.getTxtTenSV().getText();
            String nam = SinhVienView.getCbbNam().getSelectedItem().toString();
            String thang = SinhVienView.getCbbThang().getSelectedItem().toString();
            String ngay = SinhVienView.getCbbNgay().getSelectedItem().toString();
            String tmp = nam+"-"+thang+"-"+ngay;
            Date date = Date.valueOf(tmp);
            boolean gt = SinhVienView.getGioiTinh();
            String diaChi = SinhVienView.getTxtDiaChi().getText();
            String ans = SinhVienView.getCbbTrangThai().getSelectedItem().toString();
            int trangThai ;
            //1 la hoan thanh 0 la chua hoan thanh 2 la bao luu
            if(ans.equals(StatusTypeStudent.COMPLETE.toString()))
                trangThai = 1;
            else if(ans.equals(StatusTypeStudent.PROGRESSING.toString()))
                trangThai = 0;
            else
                trangThai = 2;
            
            
            if(maSV.equals("")||tenSV.equals("")||diaChi.equals("")){
                JOptionPane.showMessageDialog(SinhVienView.getTableSinhVienView(), "Nhập Lỗi!", maSV, JOptionPane.ERROR_MESSAGE);
            }
            else{
                SinhVienModel = new sinhVienModel(maSV, tenSV, date, gt, diaChi, trangThai, maLopQL);
                insertSinhVienDatabase(SinhVienModel);
                insertSinhVienTable();
                reset();
                SinhVienView.setVisible(false);
            }
        }
        
    }
    
}
