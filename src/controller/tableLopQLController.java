package controller;

import Table.StatusType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.tableLopQLView;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import lib.ConnectDatabase;
import view.lopQLView;

public class tableLopQLController implements ActionListener {

    private tableLopQLView TableLopQLView;
    private lopQLView LopQLView;

    public tableLopQLController(tableLopQLView TableLopQLView) {
        this.TableLopQLView = TableLopQLView;
    }

    public void getDataLopQL() {

        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM lop_quan_li ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();

            int i = 1;
            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_lop_quan_li"));
                data.add(rs.getString("ten_khoa"));
                data.add(rs.getString("gvcn"));
                data.add(rs.getString("hoc_ki"));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if (trangThai == 0) {
                    data.add("PROCESSING");

                } else {
                    data.add("COMPLETE");
                }

                TableLopQLView.getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateTable() {
        TableLopQLView.getModel().setRowCount(0);
        getDataLopQL();
    }

    //Xoa du lieu ra khoi bang
    public void deleteTable(int row) {
        if (row != -1) {
            TableLopQLView.getModel().removeRow(row);
        }
    }

    //Xoa du lieu sinh vien thuoc 1 lop quan ly
    public int deleteSinhVienDatabaseByMaLopQL(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM quan_li_sinh_vien WHERE ma_lop_quan_li = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableLopQLView.getModel().getValueAt(row, 1).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    //Xoa du lieu ra khoi database
    public int deleteDatabase(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM lop_quan_li WHERE ma_lop_quan_li = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableLopQLView.getModel().getValueAt(row, 1).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    //Sua du lieu trong database
    public int editDatabase(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "UPDATE lop_quan_li "
                        + "SET ten_khoa = ? , gvcn = ? , hoc_ki = ? , trang_thai = ? "
                        + "WHERE ma_lop_quan_li = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableLopQLView.getModel().getValueAt(row, 2).toString());
                stt.setString(2, TableLopQLView.getModel().getValueAt(row, 3).toString());
                stt.setString(3, TableLopQLView.getModel().getValueAt(row, 4).toString());
                String trangThai = TableLopQLView.getModel().getValueAt(row, 5).toString();
                if (trangThai.equals(StatusType.COMPLETE.toString())) {
                    stt.setInt(4, 1);
                } else {
                    stt.setInt(4, 0);
                }
                stt.setString(5, TableLopQLView.getModel().getValueAt(row, 1).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }
    
    //Lay ra ma lop hoc phan da hoan thanh 10 ki hoc
    public ArrayList<String> getMaLopHPComplete(){
        ArrayList<String>arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_quan_li,hoc_ki FROM lop_quan_li ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();

            while (rs.next()) {
                String hocKi = rs.getString("hoc_ki");
                int namBD = Integer.parseInt(hocKi.substring(0, 4));
                if(LocalDate.now().getYear() - namBD >= 6){
                    arr.add(rs.getString("ma_lop_quan_li"));
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Neu lop quan ly da qua 10 ki thi COMPLETE
    public int updateTrangThaiLopQL(String maLopQL) {
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "UPDATE lop_quan_li "
                    + "SET trang_thai = ? "
                    + "WHERE ma_lop_quan_li = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setInt(1, 1);
            stt.setString(2,maLopQL );
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
        System.out.println(src);
        if (src.equals("Insert")) {

            LopQLView = new lopQLView(TableLopQLView);
            LopQLView.setVisible(true);
        }
    }

    public lopQLView getLopQLView() {
        return LopQLView;
    }

    public void setLopQLView(lopQLView LopQLView) {
        this.LopQLView = LopQLView;
    }

}
