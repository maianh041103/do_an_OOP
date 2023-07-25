
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.tableChuongTrinhKhungView;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import lib.ConnectDatabase;

public class tableChuongTrinhKhungController implements ActionListener{
    
    private tableChuongTrinhKhungView TableChuongTrinhKhungView ;

    public tableChuongTrinhKhungController(tableChuongTrinhKhungView TableChuongTrinhKhungView) {
        this.TableChuongTrinhKhungView = TableChuongTrinhKhungView;
    }
    
    //Lay ra du lieu mon hoc bang ma mon
    public ArrayList<Object> getMonByMaMon(String maMon){
        ArrayList<Object>arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_mon, so_tin_chi, so_tiet_li_thuyet, so_tiet_thuc_hanh FROM mon_hoc "
                    + "WHERE ma_mon = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                arr.add(rs.getString("ten_mon"));
                arr.add(rs.getInt("so_tin_chi"));
                arr.add(rs.getInt("so_tiet_li_thuyet"));
                arr.add(rs.getInt("so_tiet_thuc_hanh"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    public void getDataTableCTKhung(String khoa,int hocKi){
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon FROM chuong_trinh_khung WHERE ten_khoa = ? AND hoc_ki = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, khoa);
            stt.setInt(2, hocKi);
            ResultSet rs = stt.executeQuery();
            int i = 1;
            while (rs.next()) {
                String maMon = rs.getString("ma_mon");
                ArrayList<Object> arr = getMonByMaMon(maMon);
                Vector<Object>data = new Vector<>();
                data.add(i);
                data.add(maMon);
                data.add(arr.get(0));
                data.add(arr.get(1));
                data.add(arr.get(2));
                data.add(arr.get(3));
                TableChuongTrinhKhungView.getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        String khoa = TableChuongTrinhKhungView.getCbbKhoa().getSelectedItem().toString();
        int hocKi = TableChuongTrinhKhungView.getCbbHocKy().getSelectedIndex()+1;
        if(src.equals("cbbKhoa")){
            khoa = TableChuongTrinhKhungView.getCbbKhoa().getSelectedItem().toString();
            TableChuongTrinhKhungView.getModel().setRowCount(0);
            getDataTableCTKhung(khoa, hocKi);
        }
        else if(src.equals("cbbHocKy")){
            hocKi = TableChuongTrinhKhungView.getCbbHocKy().getSelectedIndex()+1;
            TableChuongTrinhKhungView.getModel().setRowCount(0);
            getDataTableCTKhung(khoa, hocKi);
        }
    }
    
}
