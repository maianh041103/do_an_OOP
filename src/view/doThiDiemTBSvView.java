package view;

import controller.doThiDiemTBSvController;
import controller.doThiTBMonController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;

public class doThiDiemTBSvView extends JPanel implements MouseListener, ActionListener {
    
    private Font font;
    private JLabel lblHocKi, lblNam;
    private JComboBox<String> cbbHocKy, cbbNam;
    private ChartPanel chartPanel;
    private doThiDiemTBSvController doThiDiemTBSv = new doThiDiemTBSvController();
    private TextField txtMSV;
    private JLabel lblMSV;
    private buttonView btnFind;

    public doThiDiemTBSvView() {
        this.setLayout(null);
        setUpViewPanel();
        setUpCharPanel();

    }

    public void setUpViewPanel() {
        font = new Font("sansserif", 1, 15);

        this.setBackground(new Color(250, 250, 250));

        lblMSV = new JLabel("Mã sinh viên");
        lblMSV.setBounds(220, 580, 80, 25);
        
        txtMSV = new TextField();
        txtMSV.setBounds(300, 580, 100, 25);
        txtMSV.setBackground(new Color(250,250,250));
        txtMSV.setForeground(new Color(51, 154, 240));
        
        lblNam = new JLabel("Năm");
        lblNam.setBounds(650, 580, 40, 25);

        cbbNam = new JComboBox<>();
        cbbNam.setBounds(690, 580, 100, 25);
        cbbNam.setMaximumRowCount(3);
        cbbNam.setActionCommand("cbbNam");

        for (int i = LocalDate.now().getYear() - 1; i >= LocalDate.now().getYear() - 10; i--) {
            cbbNam.addItem(i + "-" + (int) (i + 1));
        }
        cbbNam.addActionListener((e) -> {
            chartPanel.setVisible(false);
            setUpCharPanel();
        });

        lblHocKi = new JLabel("Học kỳ");
        lblHocKi.setBounds(460, 580, 50, 25);

        cbbHocKy = new JComboBox<>();
        cbbHocKy.setMaximumRowCount(3);
        cbbHocKy.setBounds(510, 580, 100, 25);
        cbbHocKy.setActionCommand("cbbHocKy");
        cbbHocKy.addItem("Học Kỳ " + 1);
        cbbHocKy.addItem("Học Kỳ " + 2);
        cbbHocKy.addItem("Học Kỳ " + 3);
        cbbHocKy.addActionListener((e) -> {
            chartPanel.setVisible(false);
            setUpCharPanel();
        });

        btnFind = new buttonView();
        btnFind.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(doThiDiemTBSvView.class.getResource("/icon/icons8-find-20.png"))));
        btnFind.setBackground(Color.white);
        btnFind.setActionCommand("Find");
        btnFind.setBounds(880, 580, 50, 25);
        btnFind.addActionListener((e) -> {
            chartPanel.setVisible(false);
            setUpCharPanel();
        });
        
        this.add(lblMSV);
        this.add(txtMSV);
        this.add(lblNam);
        this.add(cbbNam);
        this.add(lblHocKi);
        this.add(cbbHocKy);
        this.add(btnFind);
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
        chartPanel = new ChartPanel(doThiDiemTBSv.createChart(txtMSV.getText(),cbbHocKy.getSelectedIndex() + 1, nam));
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
                            "Môn học : " + category.toString()
                            + "\nĐiểm tb : " + value.toString());
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

