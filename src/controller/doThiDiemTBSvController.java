
package controller;


import java.awt.Color;
import java.util.ArrayList;
import java.sql.*;
import lib.ConnectDatabase;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class doThiDiemTBSvController {

    public doThiDiemTBSvController() {
    }

    public ArrayList<ArrayList<Object>> getDataMonByMaSV(String maSV, int hocKy, int nam){
        ArrayList<ArrayList<Object>> arr = new ArrayList<ArrayList<Object>>();
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT ten_mon , diem_qt, diem_thi , lop_hoc_phan_has_sinh_vien.ma_lop_hoc_phan "
                    + "FROM (( lop_hoc_phan_has_sinh_vien "
                    + "INNER JOIN quan_li_lop_hoc_phan ON lop_hoc_phan_has_sinh_vien.ma_lop_hoc_phan = quan_li_lop_hoc_phan.ma_lop_hoc_phan) "
                    + "INNER JOIN mon_hoc ON quan_li_lop_hoc_phan.ma_mon = mon_hoc.ma_mon) "
                    + "WHERE ma_sinh_vien = ? AND hoc_ki = ? AND nam = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maSV);
            stt.setInt(2, hocKy);
            stt.setInt(3, nam);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(rs.getString("ten_mon"));
                double dqt = rs.getDouble("diem_qt");
                double dThi = rs.getDouble("diem_thi");
                double dtk = dqt*0.3+dThi*0.7;
                tmp.add(dtk);
                tmp.add(diemTbLopHP(rs.getString("lop_hoc_phan_has_sinh_vien.ma_lop_hoc_phan")));
                arr.add(tmp);
                
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    //Lay ra diem tb cua lop hoc phan do
    public double diemTbLopHP(String maLopHP){
        double diem = 0;
        int count = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT diem_qt , diem_thi FROM lop_hoc_phan_has_sinh_vien WHERE ma_lop_hoc_phan = ?";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, maLopHP);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                double dqt = rs.getDouble("diem_qt");
                double dThi = rs.getDouble("diem_thi");
                diem += dqt*0.3 + dThi*0.7;
                count++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            System.out.println(e);
        } 
        diem /= count;
        return diem;
    }

   public JFreeChart createChart(String maSV,int hocKy ,int nam ) {

      // Tạo dữ liệu
      CategoryDataset dataset = createDataset(maSV, hocKy, nam);
      CategoryDataset dataset2 = createDataset2(maSV, hocKy, nam);

      // Vẽ line chart
      NumberAxis axis2 = new NumberAxis("Lượng sản phẩm");
      CategoryPlot plot = new CategoryPlot();
      plot.setRangeAxis(axis2);
      plot.setDataset(dataset2);
      plot.mapDatasetToRangeAxis(1, 1);
      LineAndShapeRenderer renderer2 = new DefaultCategoryItemRenderer();
      renderer2.setSeriesPaint(0,Color.YELLOW);
      plot.setRenderer(renderer2);
      
      // Vẽ bar chart
      CategoryAxis categoryAxis = new CategoryAxis();
      NumberAxis numberAxis = new NumberAxis();
      BarRenderer renderer = new LayeredBarRenderer();
      
      renderer.setSeriesPaint(0, new Color(250, 108, 81));
      renderer.setMaximumBarWidth(0.05);
      plot.setDomainAxis(categoryAxis);
      plot.setRangeGridlinesVisible(false);
      plot.setRangeAxis(1, numberAxis);
      plot.setDataset(1,dataset);
      plot.setRenderer(1,renderer);

      // Tạo biểu đồ kết hợp
      JFreeChart chart = new JFreeChart(plot);
      chart.setBackgroundPaint(Color.white);
      //chart.removeLegend();  // xoá legend 
      ValueAxis range = plot.getRangeAxis();
      //range.setVisible(false); // ẩn giá trị trên cột bên trái
      range.setRange(0,10);
      range = plot.getRangeAxis(1);
      range.setVisible(false);  //ẩn giá trị trên cột bên phải
      return chart;
   }

   private CategoryDataset createDataset(String maSV, int hocKy, int nam) {

      // Tạo dữ liệu
      ArrayList<ArrayList<Object>> arr = getDataMonByMaSV(maSV, hocKy, nam);
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      for(ArrayList tmp: arr) {
          dataset.addValue(Double.parseDouble(tmp.get(1).toString()), "", tmp.get(0).toString());
      }
      return dataset;
   }

   private CategoryDataset createDataset2(String maSV, int hocKy, int nam) {

      // Tạo dữ liệu
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      ArrayList<ArrayList<Object>> arr = getDataMonByMaSV(maSV, hocKy, nam);
      for(ArrayList tmp: arr) {
          dataset.addValue(Double.parseDouble(tmp.get(2).toString()), "", tmp.get(0).toString());
      }
      return dataset;
   }

}

