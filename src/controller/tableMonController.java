
package controller;

import Table.StatusType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import lib.ConnectDatabase;
import model.monModel;
import view.monView;
import view.tableMonView;

public class tableMonController implements ActionListener{
    
    private tableMonView TableMonView;

    public tableMonController(tableMonView TableMonView) {
        this.TableMonView = TableMonView;
    }
    
    //Lay du lieu mon do len bang
    public void getDataMon(){
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM mon_hoc ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
           int i = 1;
            while(rs.next()){
                Vector<Object>data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_mon"));
                data.add(rs.getString("ten_mon"));
                data.add(Integer.parseInt(rs.getString("so_tin_chi")));
                data.add(Integer.parseInt(rs.getString("so_tiet_li_thuyet")));
                data.add(Integer.parseInt(rs.getString("so_tiet_thuc_hanh")));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if(trangThai == 0){
                    data.add("PROCESSING");
                    
                }
                else
                    data.add("COMPLETE");
               
                TableMonView.getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Lay ra trang thai cua mon by ma mon
    public int getTrangThaiByMaMon(String maMon){
        int trangThai = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT trang_thai FROM mon_hoc WHERE ma_mon = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                trangThai = rs.getInt("trang_thai");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        }
        return trangThai;
    }
    
    //Update trang thai lop hoc phan thanh COMPLETE neu mon o trang thai COMPLETE
    public int editLopHPDatabase(String maMon){
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "UPDATE quan_li_lop_hoc_phan "
                    + "SET trang_thai = ? "
                    + "WHERE ma_mon = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setInt(1, 1);
            stt.setString(2, maMon);
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public void updateTable(){
        TableMonView.getModel().setRowCount(0);
        getDataMon();
    }
    
    //Xoa du lieu ra khoi bang
    public void deleteTable(int row){
        if(row != -1){
            TableMonView.getModel().removeRow(row);
        }
    }
    
    //Lay ra cac ma lop hoc phan theo ma mon
    public ArrayList<String> getMaLopHPByMaMon(int row){
        ArrayList<String>arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan WHERE ma_mon = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, TableMonView.getModel().getValueAt(row, 1).toString());
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
    
    //Xoa du lieu sinh vien ra khoi lop hoc phan
    public int deleteSinhVienDatabaseByMaLopHP(String maLopHP){
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "DELETE FROM lop_hoc_phan_has_sinh_vien WHERE ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    //Xoa du lieu lop hoc phan ra khoi database khi xoa mon hoc
    public int deleteLopHPDatabaseByMaMon(int row){
        int ketQua = 0;
        if(row != -1){
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM quan_li_lop_hoc_phan WHERE ma_mon = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableMonView.getModel().getValueAt(row, 1).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
                System.out.println(TableMonView.getModel().getValueAt(row, 1).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }
    
    //Xoa du lieu ra khoi database
    public int deleteDatabase(int row){
        int ketQua = 0;
        if(row != -1){
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM mon_hoc WHERE ma_mon = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableMonView.getModel().getValueAt(row, 1).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }
    
    //Xoa mon hoc ra khoi chuong trinh khung
    public int deleteCtKhungDatabase(int row){
        int ketQua = 0;
        if(row != -1){
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM chuong_trinh_khung WHERE ma_mon = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableMonView.getModel().getValueAt(row, 1).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }
    
    //Sua du lieu trong database
    public int editDatabase(int row){
        int ketQua = 0;
        if(row != -1){
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "UPDATE mon_hoc "
                        + "SET ten_mon = ? , so_tin_chi = ? , so_tiet_li_thuyet = ? , so_tiet_thuc_hanh = ? , trang_thai = ? "
                        + "WHERE ma_mon = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableMonView.getModel().getValueAt(row, 2).toString());
                stt.setInt(2, Integer.parseInt(TableMonView.getModel().getValueAt(row, 3).toString()));
                stt.setInt(3, Integer.parseInt(TableMonView.getModel().getValueAt(row, 4).toString()));
                stt.setInt(4, Integer.parseInt(TableMonView.getModel().getValueAt(row, 5).toString()));
                String trangThai = TableMonView.getModel().getValueAt(row, 6).toString();
                if(trangThai.equals(StatusType.COMPLETE)){
                    stt.setInt(5, 1);
                }
                else{
                    stt.setInt(5, 0);
                }
                stt.setString(6, TableMonView.getModel().getValueAt(row, 1).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
          
        if(src.equals("Insert")){
            monView MonView = new monView(TableMonView);
            MonView.setVisible(true);
        }
       
    }
    
}
