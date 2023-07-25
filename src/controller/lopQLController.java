
package controller;

import Table.StatusType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.lopQLModel;
import view.lopQLView;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import lib.ConnectDatabase;
import view.parentView;
import view.tableLopQLView;

public class lopQLController implements ActionListener{
    
    private lopQLView LopQLView;
    private lopQLModel LopQLModel;

    public lopQLController(lopQLView LopQLView) {
        this.LopQLView = LopQLView;
        
    }
    
    //Get Data Mon
    public void getDataLopQL(){
        try {
            try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM lop_quan_li ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
           
            int i = 1;
            while(rs.next()){
                Vector<Object>data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_lop_quan_li"));
                data.add(rs.getString("ten_khoa"));
                data.add(rs.getString("gvcn"));
                data.add(rs.getString("hoc_ki"));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if(trangThai == 0){
                    data.add("PROCESSING");     
                }
                else
                    data.add("COMPLETE");
               
                LopQLView.getTableLopQLView().getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Update bang sau khi them
    public void insertLopQLTable(){
        LopQLView.getTableLopQLView().getModel().setRowCount(0);
        getDataLopQL();
    }
    
    //Them du lieu vao database
    public int insertLopQLDatabase(lopQLModel t){
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "INSERT INTO lop_quan_li(ma_lop_quan_li,ten_khoa,gvcn,hoc_ki,trang_thai) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, t.getMaLopQL());
            stt.setString(2,t.getTenKhoa());
            stt.setString(3, t.getGvcn());
            stt.setString(4, t.getHocKi());
            stt.setInt(5, t.getTrangThai());
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public void reset(){
        LopQLView.getTxtGvcn().setText("");
        LopQLView.getTxtMaLopQL().setText("");
        LopQLView.getTxtTenKhoa().setText("");
    }
   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Insert")){
            String maLop = LopQLView.getTxtMaLopQL().getText();
            String tenKhoa = LopQLView.getTxtTenKhoa().getText();
            String gvcn = LopQLView.getTxtGvcn().getText();
            String hocKi = LopQLView.getCbbNamHoc().getSelectedItem().toString();
            String tmp = LopQLView.getCbbTrangThai().getSelectedItem().toString();
            int trangThai;
            // 1 la hoan thanh 0 la chua hoan thanh
            if(tmp.equals(StatusType.COMPLETE.toString())){
                trangThai = 1;
            }
            else{
                trangThai = 0;
            }
            if(maLop.equals("")||tenKhoa.equals("")||gvcn.equals("")||hocKi.equals("")){
                JOptionPane.showMessageDialog(LopQLView.getTableLopQLView(), "Nhập Lỗi!", maLop, JOptionPane.ERROR_MESSAGE);
            }
            else{
                LopQLModel = new lopQLModel(maLop,tenKhoa,gvcn,hocKi,trangThai);
                insertLopQLDatabase(LopQLModel);
                insertLopQLTable();
                reset();
                LopQLView.setVisible(false);
                insertLopQLTable();
            }
        }
    }
}

