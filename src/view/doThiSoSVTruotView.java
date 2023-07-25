
package view;

import controller.doThiSoSVTruotController;
import controller.doThiTBMonController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;

/**
 *
 * @author TRIỆU TOÀN
 */
public class doThiSoSVTruotView extends JPanel implements MouseListener, ActionListener{

    private Font font;
    private JLabel lblHocKi, lblNam;
    private JComboBox<String> cbbHocKy, cbbNam;
    private ChartPanel chartPanel;
    private doThiSoSVTruotController doThiSoSVTruotController = new doThiSoSVTruotController();

    public doThiSoSVTruotView() {
        this.setLayout(null);
        setUpViewPanel();
        setUpCharPanel();

    }

    public void setUpViewPanel() {
        font = new Font("sansserif", 1, 15);

        this.setBackground(new Color(250, 250, 250));

        lblNam = new JLabel("Năm");
        lblNam.setBounds(610, 590, 40, 25);

        cbbNam = new JComboBox<>();
        cbbNam.setBounds(650, 590, 100, 25);
        cbbNam.setMaximumRowCount(3);
        cbbNam.setActionCommand("cbbNam");

        for (int i = LocalDate.now().getYear() - 1; i >= LocalDate.now().getYear() - 10; i--) {
            cbbNam.addItem(i + "-" + (int) (i + 1));
        }
        cbbNam.addActionListener((e) -> {
            chartPanel.setVisible(false);
            chartPanel = new ChartPanel(doThiSoSVTruotController.createChart(cbbHocKy.getSelectedIndex() + 1, cbbNam.getSelectedIndex() + 2020));
            chartPanel.setPreferredSize(new Dimension(1100, 520));
            setChartMouseListener();
            chartPanel.setBounds(20, 40, 1050, 520);
            this.add(chartPanel);
        });

        lblHocKi = new JLabel("Học kỳ");
        lblHocKi.setBounds(410, 590, 50, 25);

        cbbHocKy = new JComboBox<>();
        cbbHocKy.setMaximumRowCount(3);
        cbbHocKy.setBounds(460, 590, 100, 25);
        cbbHocKy.setActionCommand("cbbHocKy");
        cbbHocKy.addItem("Học Kỳ " + 1);
        cbbHocKy.addItem("Học Kỳ " + 2);
        cbbHocKy.addItem("Học Kỳ " + 3);
        cbbHocKy.addActionListener((e) -> {
            chartPanel.setVisible(false);
            chartPanel = new ChartPanel(doThiSoSVTruotController.createChart(cbbHocKy.getSelectedIndex() + 1, cbbNam.getSelectedIndex() + 2020));
            chartPanel.setPreferredSize(new Dimension(1100, 520));
            setChartMouseListener();
            chartPanel.setBounds(20, 40, 1050, 520);
            this.add(chartPanel);
        });

        this.add(lblNam);
        this.add(cbbNam);
        this.add(lblHocKi);
        this.add(cbbHocKy);
    }

    public void setUpCharPanel() {
        int hk = cbbHocKy.getSelectedIndex() + 1;
        int nam;
        String tmp = cbbNam.getSelectedItem().toString();
        if (hk == 1) {
            nam = Integer.parseInt(tmp.substring(0, 4));
        } else {
            nam = Integer.parseInt(tmp.substring(0, 4)) + 1;
        }
        chartPanel = new ChartPanel(doThiSoSVTruotController.createChart(cbbHocKy.getSelectedIndex() + 1, nam));
        chartPanel.setPreferredSize(new Dimension(1100, 520));
        setChartMouseListener();
        chartPanel.setBounds(20, 40, 1050, 520);
        this.add(chartPanel);
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public void setChartMouseListener() {
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                ChartEntity entity = event.getEntity();
                if (entity instanceof CategoryItemEntity) {
                    CategoryItemEntity item = (CategoryItemEntity) entity;
                    Comparable category = item.getColumnKey();
                    Number value = item.getDataset().getValue(item.getRowKey(), item.getColumnKey());
                    JOptionPane.showMessageDialog(chartPanel.getParent(),
                            "Môn : " + category.toString()
                            + "\nSinh viên trượt: " + value.toString()+"%");
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {
            }

        });
    }

    public JComboBox<String> getCbbHocKy() {
        return cbbHocKy;
    }

    public JComboBox<String> getCbbNam() {
        return cbbNam;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
