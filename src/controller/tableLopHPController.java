package controller;

import Table.StatusType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lib.ConnectDatabase;
import view.tableLopHPView;
import java.sql.*;
import java.util.Vector;
import view.lopHPView;

public class tableLopHPController implements ActionListener {

    private tableLopHPView TableLopHPView;
    private lopHPView LopHPView;

    public tableLopHPController(tableLopHPView TableLopHPView) {
        LopHPView = new lopHPView(TableLopHPView);
        this.TableLopHPView = TableLopHPView;
    }

    //Lay ra trang thai cua mon bang ma mon
    public int getTrangThaiByMaMon(String maMon) {
        int trangThai = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT trang_thai FROM mon_hoc WHERE ma_mon = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                trangThai = rs.getInt("trang_thai");
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        }
        return trangThai;
    }

    public void getDataLopHP() {
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM quan_li_lop_hoc_phan ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();

            int i = 1;
            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_mon"));
                data.add(rs.getString("ma_lop_hoc_phan"));
                data.add(rs.getString("ten_lop_hoc_phan"));
                data.add(rs.getString("giang_vien"));
                int hK = rs.getInt("hoc_ki");
                int nam = rs.getInt("nam");

                String hocKy;
                if (hK == 1) {
                    hocKy = "HK" + hK + " " + nam + "-" + (int) (nam + 1);
                } else {
                    hocKy = "HK" + hK + " " + (int) (nam - 1) + "-" + nam;
                }
                data.add(hocKy);

                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if (trangThai == 0) {
                    data.add("PROCESSING");
                } else {
                    data.add("COMPLETE");
                }

                TableLopHPView.getModel().addRow(data);
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

                TableLopHPView.getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Xoa thong tin cua sinh vien trong cung 1 lop hoc phan
    public int deleteSVDatabaseByMaLopQL(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM lop_hoc_phan_has_sinh_vien WHERE ma_lop_hoc_phan = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableLopHPView.getModel().getValueAt(row, 2).toString());
                ketQua = stt.executeUpdate();
                ConnectDatabase.closeConnection(connect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    public void updateTable() {
        TableLopHPView.getModel().setRowCount(0);
        getDataLopHP();
    }

    //Xoa du lieu ra khoi bang
    public void deleteTable(int row) {
        if (row != -1) {
            TableLopHPView.getModel().removeRow(row);
        }
    }

    //Xoa du lieu ra khoi database
    public int deleteDatabase(int row) {
        int ketQua = 0;
        if (row != -1) {
            try {
                Connection connect = ConnectDatabase.getConnection();
                String sql = "DELETE FROM quan_li_lop_hoc_phan WHERE ma_lop_hoc_phan = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableLopHPView.getModel().getValueAt(row, 2).toString());
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
                String sql = "UPDATE quan_li_lop_hoc_phan "
                        + "SET ma_mon = ? , ten_lop_hoc_phan = ? , giang_vien = ? ,hoc_ki = ? , nam = ? , trang_thai = ? "
                        + "WHERE ma_lop_hoc_phan = ?";
                PreparedStatement stt = connect.prepareStatement(sql);
                stt.setString(1, TableLopHPView.getModel().getValueAt(row, 1).toString());
                stt.setString(2, TableLopHPView.getModel().getValueAt(row, 3).toString());
                stt.setString(3, TableLopHPView.getModel().getValueAt(row, 4).toString());
                String hocKy = TableLopHPView.getModel().getValueAt(row, 5).toString();
                int hk = Integer.parseInt(hocKy.substring(2, 3));
                int nam = Integer.parseInt(hocKy.substring(4, 8));
                stt.setInt(4, hk);
                if (hk == 1) {
                    stt.setInt(5, nam);
                } else {
                    stt.setInt(5, nam + 1);
                }
                String trangThai = TableLopHPView.getModel().getValueAt(row, 5).toString();
                if (trangThai.equals(StatusType.COMPLETE.toString())) {
                    stt.setInt(6, 1);
                } else {
                    stt.setInt(6, 0);
                }
                stt.setString(7, TableLopHPView.getModel().getValueAt(row, 2).toString());
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
        if (src.equals("Insert")) {
            LopHPView.setVisible(true);
        }
    }

    public lopHPView getLopHPView() {
        return LopHPView;
    }

    public void setLopHPView(lopHPView LopHPView) {
        this.LopHPView = LopHPView;
    }

}
