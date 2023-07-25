
package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lib.ConnectDatabase;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author TRIỆU TOÀN
 */
public class doThiSoSVTruotController {

    public doThiSoSVTruotController() {
    }
    
    public ArrayList<String> getMaMon(int hocKy, int nam) {
        ArrayList<String> listMaMon = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_mon FROM quan_li_lop_hoc_phan WHERE hoc_ki = ? AND nam = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, hocKy);
            stmt.setInt(2, nam);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listMaMon.add(rs.getString("ma_mon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMaMon;
    }
    
    public String getTenMonByMaMon(String maMon) {
        String ten_mon = "";
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_mon FROM mon_hoc WHERE ma_mon = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                ten_mon = rs.getString("ten_mon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ten_mon;
    }
    
    public ArrayList<String> getDataMaLopHP(String maMon, int hocKy, int nam) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_lop_hoc_phan FROM quan_li_lop_hoc_phan WHERE ma_mon = ? AND hoc_ki = ? AND nam = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maMon);
            stt.setInt(2, hocKy);
            stt.setInt(3, nam);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString("ma_lop_hoc_phan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    public int getSiSoLopHP(String maLopHP) {
        int siSo = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ma_sinh_vien FROM lop_hoc_phan_has_sinh_vien "
                    + "WHERE ma_lop_hoc_phan = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                siSo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return siSo;
    }
    
    public double getSoPhanTramSVTruotofLopHP(String maLopHP) {
        double pct = 0;
        ArrayList<Double> arr = new ArrayList<>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT diem_qt, diem_thi FROM lop_hoc_phan_has_sinh_vien WHERE ma_lop_hoc_phan = ? ";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                double dqt = rs.getDouble("diem_qt");
                double dthi = rs.getDouble("diem_thi");
                double tb = dqt * 0.3 + dthi * 0.7;
                if(tb < 4.0) {
                    pct += 1;
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pct = (pct / getSiSoLopHP(maLopHP))*100;
        return pct;
    }
    
    public double getSoPhanTramSVTruotofMon(String maMon, int hocKy, int nam) {
        double phanTramTruot = 0;
        ArrayList<String> arr = getDataMaLopHP(maMon, hocKy, nam);
        for (int i = 0; i < arr.size();i++) {
            phanTramTruot += getSoPhanTramSVTruotofLopHP(arr.get(i));
        }
        phanTramTruot = phanTramTruot/arr.size();
        return Double.parseDouble(String.format("%.2f", phanTramTruot));
    }
    
    public DefaultCategoryDataset createDataset(int hocKy, int nam) {
        ArrayList<String> columnKey = (ArrayList) getMaMon(hocKy, nam);
        String maMon = "";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < columnKey.size(); i++) {
            maMon = columnKey.get(i);
            dataset.addValue(getSoPhanTramSVTruotofMon(maMon, hocKy, nam), "", getTenMonByMaMon(maMon));
        }
        return dataset;
    }
    
    public JFreeChart createChart(int hocKy, int nam) {
        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        JFreeChart areaChart = ChartFactory.createAreaChart(
                "",
                "", "%",
                createDataset(hocKy, nam), PlotOrientation.VERTICAL, false, true, false);
        Paint paint = new ChartColor(102, 178, 255);
        areaChart.setBackgroundPaint(Color.white);

        AreaRenderer renderer = (AreaRenderer) areaChart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, new GradientPaint(0, 800, new Color(250, 108, 81), 0, 0, Color.white));//màu cho dòng
        //renderer.setSeriesOutlinePaint(0, Color.RED);
        CategoryPlot plot = areaChart.getCategoryPlot();
        ValueAxis range = plot.getRangeAxis();
        range.setRange(0, 100);//set value min max cho trục y
        plot.setBackgroundPaint(Color.WHITE);//màu cho đồ thị
        plot.setOutlineVisible(true); // dòng bao bên ngoài đồ thị
        plot.setRenderer(renderer);
        
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 12)); // set font cho label ở hàng
        domainAxis.setLabelFont(new Font("Arial", Font.PLAIN, 14)); // set font cho kiểu của hàng

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 12)); // set font cho số ở cột
        rangeAxis.setLabelFont(new Font("Arial", Font.PLAIN, 14)); // set font cho kiểu của cột
        
        return areaChart;
    }

}
