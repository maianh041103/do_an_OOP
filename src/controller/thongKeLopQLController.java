package controller;

import Table.StatusType;
import Table.StatusTypeStudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.thongKeLopQLView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import lib.ConnectDatabase;

public class thongKeLopQLController implements ActionListener {

    private thongKeLopQLView ThongKeLopQLView;

    public thongKeLopQLController(thongKeLopQLView ThongKeLopQLView) {
        this.ThongKeLopQLView = ThongKeLopQLView;
    }

    //Lay ra ma lop quan ly dien vao cbb
    public ArrayList<String> getMaLopQL() {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_quan_li FROM lop_quan_li ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ma_lop_quan_li"));
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    //Lay ra ma sinh vien bang ma lop quan ly
    public ArrayList<ArrayList<Object>> getDataSVByMaLopQL(String maLopQL) {
        ArrayList<ArrayList<Object>> arr = new ArrayList<ArrayList<Object>>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien, ten_sinh_vien, trang_thai FROM quan_li_sinh_vien "
                    + "WHERE ma_lop_quan_li = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopQL);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(rs.getString("ma_sinh_vien"));
                tmp.add(rs.getString("ten_sinh_vien"));
                tmp.add(rs.getInt("trang_thai"));
                arr.add(tmp);
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    //Lay ra thong tin sinh vien de dien vao bang by ma sinh vien
    public ArrayList<Object> getDiemAndSoTC(String maSV) {
        ArrayList<Object> arr = new ArrayList<>();
        int stcTL = 0;
        int stcDK = 0;
        double diemTBHe4 = 0;
        int count = 0;
        HashMap<String, Integer> map = new HashMap<>();
        double diemTb;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT mon_hoc.ma_mon , so_tin_chi , diem_qt, diem_thi "
                    + "FROM (( lop_hoc_phan_has_sinh_vien "
                    + "INNER JOIN quan_li_lop_hoc_phan ON lop_hoc_phan_has_sinh_vien.ma_lop_hoc_phan = quan_li_lop_hoc_phan.ma_lop_hoc_phan) "
                    + "INNER JOIN mon_hoc ON quan_li_lop_hoc_phan.ma_mon = mon_hoc.ma_mon) "
                    + "WHERE ma_sinh_vien = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                count++;
                double dqt = rs.getInt("diem_qt");
                double dThi = rs.getInt("diem_thi");
                diemTb = dqt * 0.3 + dThi * 0.7;
                if (diemTb < 4.0) {
                    diemTBHe4 += 0;
                } else if (diemTb >= 4 && diemTb < 5) {
                    diemTBHe4 += 1;
                } else if (diemTb >= 5 && diemTb < 6) {
                    diemTBHe4 += 1.5;
                } else if (diemTb >= 6 && diemTb < 6.5) {
                    diemTBHe4 += 2;
                } else if (diemTb >= 6.5 && diemTb < 7) {
                    diemTBHe4 += 2.5;
                } else if (diemTb >= 7 && diemTb < 8) {
                    diemTBHe4 += 3;
                } else if (diemTb >= 8 && diemTb < 8.5) {
                    diemTBHe4 += 3.5;
                } else {
                    diemTBHe4 += 4;
                }
                stcDK += rs.getInt("so_tin_chi");
                if (diemTb >= 4) {
                    if (!map.containsKey(rs.getString("ma_mon"))) {
                        map.put(rs.getString("ma_mon"), rs.getInt("so_tin_chi"));
                        stcTL += rs.getInt("so_tin_chi");
                    }
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        diemTBHe4 /= count;
        arr.add(diemTBHe4);
        arr.add(stcDK);
        arr.add(stcTL);
        arr.add(xepLoaiTichLuy(diemTBHe4));
        return arr;
    }

    //Xep loai hoc luc tich luy
    public String xepLoaiTichLuy(double diem) {
        String xepLoai = "";
        if (diem < 1) {
            xepLoai = "Kém";
        } else if (diem < 2) {
            xepLoai = "Yếu";
        } else if (diem < 2.5) {
            xepLoai = "Trung Bình";
        } else if (diem < 3.2) {
            xepLoai = "Khá";
        } else if (diem < 3.6) {
            xepLoai = "Giỏi";
        } else if (diem <= 4){
            xepLoai = "Xuất Sắc";
        } else {
            xepLoai = "";
        }
        return xepLoai;
    }

    //Lay du lieu len bang
    public void getDataTable(String maLopQL) {
        ArrayList<ArrayList<Object>> maVaTenSV = getDataSVByMaLopQL(maLopQL);
        for (int i = 0; i < maVaTenSV.size(); i++) {
            Vector<Object> data = new Vector<>();
            data.add(i + 1);
            data.add(maVaTenSV.get(i).get(0));
            data.add(maVaTenSV.get(i).get(1));
            ArrayList<Object> arr = getDiemAndSoTC(maVaTenSV.get(i).get(0).toString());
            data.add(Double.parseDouble(String.format("%.2f", Double.parseDouble(arr.get(0).toString()))));
            data.add(Integer.parseInt(arr.get(1).toString()));
            data.add(Integer.parseInt(arr.get(2).toString()));
            data.add(arr.get(3).toString());
            int trangThai = Integer.parseInt(maVaTenSV.get(i).get(2).toString());
            if (trangThai == 0) {
                data.add(StatusTypeStudent.PROGRESSING.toString());
            } else if (trangThai == 1) {
                data.add(StatusTypeStudent.COMPLETE.toString());
            } else {
                data.add(StatusTypeStudent.RESERVE.toString());
            }
            ThongKeLopQLView.getModel().addRow(data);
        }
    }

    ////////////////////////////Sap xep
    public void sapXepTheoTen() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(ThongKeLopQLView.getModel());
        sorter.setComparator(2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String arr1[] = o1.split("\\s+");
                String arr2[] = o2.split("\\s+");
                String res1 = arr1[arr1.length - 1];
                String res2 = arr2[arr2.length - 1];
                for (int i = 0; i < arr1.length - 1; i++) {
                    res1 += arr1[i];
                }
                for (int i = 0; i < arr2.length - 1; i++) {
                    res2 += arr2[i];
                }
                return res1.compareTo(res2);
            }
        });
        ThongKeLopQLView.getTable().setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    public void sapXepTheoDiemTb() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(ThongKeLopQLView.getModel());
        sorter.setComparator(3, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o1 < o2) {
                    return -1;
                } else {
                    return 1;
                }
            }

        });
        ThongKeLopQLView.getTable().setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    public void sapXepTheoSTC() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(ThongKeLopQLView.getModel());
        sorter.setComparator(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        ThongKeLopQLView.getTable().setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        String maLop = ThongKeLopQLView.getCbbMaLopQL().getSelectedItem().toString();
        if (src.equals("cbbLopQL")) {
            //maLop = ThongKeLopQLView.getCbbMaLopQL().getSelectedItem().toString();
            ThongKeLopQLView.getModel().setRowCount(0);
            getDataTable(maLop);
        } else if (src.equals("cbbSapXep")) {
            int index = ThongKeLopQLView.getCbbSapXep().getSelectedIndex();
            if (index == 0) {
                ThongKeLopQLView.getModel().setRowCount(0);
                getDataTable(maLop);
                sapXepTheoTen();
            } else if (index == 1) {
                ThongKeLopQLView.getModel().setRowCount(0);
                getDataTable(maLop);
                sapXepTheoDiemTb();

            } else {
                ThongKeLopQLView.getModel().setRowCount(0);
                getDataTable(maLop);
                sapXepTheoSTC();
            }
        }
    }
}
