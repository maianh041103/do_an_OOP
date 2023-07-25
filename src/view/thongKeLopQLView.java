
package view;

import Table.TableHeader;
import controller.thongKeLopQLController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomLabel;
import scroll.ScrollPaneWin11;

public class thongKeLopQLView extends JPanel{
    private JPanel pnTsble;
    private JLabel lblTitle,lblMaLopQL,lblSapXep;
    private JComboBox<String> cbbMaLopQL,cbbSapXep;
    private Font font;
    private JTable table;
    private ScrollPaneWin11 scroll;
    private DefaultTableModel model;
    private thongKeLopQLController ThongKeLopQLController = new thongKeLopQLController(this);
    
    public thongKeLopQLView() {
        setUpViewPanel();
        setUpViewTable();
        setUpViewTitle();
        this.setVisible(true);
    }
    
    public void setUpViewPanel(){
        this.setLayout(null);
        this.setBackground(new Color(250, 250, 250));
        
        font = new Font("Arial", Font.BOLD, 24);
        
        
    }
    
    public void setUpViewTitle(){
        lblTitle = new CustomLabel();
        lblTitle.setText("  Thống Kê Theo Lớp Quản Lý ");
        lblTitle.setFont(font);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/chart-32.png"))));
        lblTitle.setForeground(new Color(51, 154, 240));
        lblTitle.setBounds(30, 10, 400, 40);
        
        lblMaLopQL = new JLabel("Mã lớp ");
        lblMaLopQL.setBounds(80, 80, 80, 30);
        lblMaLopQL.setFont(new Font("Arial", Font.BOLD, 18));
        lblMaLopQL.setForeground(new Color(51, 154, 240));
        
        cbbMaLopQL = new JComboBox<>();
        cbbMaLopQL.setBounds(160, 80, 100, 30);
        cbbMaLopQL.setBackground(Color.white);
        cbbMaLopQL.setActionCommand("cbbLopQL");
        cbbMaLopQL.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));
        cbbMaLopQL.setMaximumRowCount(2);
        cbbMaLopQL.addActionListener(ThongKeLopQLController);
        ArrayList<String>arr = ThongKeLopQLController.getMaLopQL();
        for(String tmp : arr){
            cbbMaLopQL.addItem(tmp);
        }
        
        lblSapXep = new JLabel("Sắp xếp ");
        lblSapXep.setBounds(600, 80, 80, 30);
        lblSapXep.setFont(new Font("Arial", Font.BOLD, 18));
        lblSapXep.setForeground(new Color(51, 154, 240));
        
        cbbSapXep = new JComboBox<>();
        cbbSapXep.setBounds(680, 80, 150, 30);
        cbbSapXep.setBackground(Color.white);
        cbbSapXep.setActionCommand("cbbSapXep");
        cbbSapXep.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));
        cbbSapXep.setMaximumRowCount(2);
        cbbSapXep.addItem("Theo Tên");
        cbbSapXep.addItem("Điểm tích lũy");
        cbbSapXep.addItem("STC tích lũy");
        cbbSapXep.addActionListener(ThongKeLopQLController);
  
        this.add(lblTitle);
        this.add(lblMaLopQL);
        this.add(cbbMaLopQL);
        this.add(lblSapXep);
        this.add(cbbSapXep);
    }
    
    public void setUpViewTable(){
        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object>col = new Vector<>();
        col.add("STT");
        col.add("Mã Sinh Viên");
        col.add("Tên Sinh Viên");
        col.add("Điểm Tích Lũy");
        col.add("STC Đăng Kí");
        col.add("STC Tích Lũy");
        col.add("Xếp loại");
        col.add("Trạng Thái");
        
        table = new JTable(); 
        table.setBackground( Color.WHITE);
        table.setShowHorizontalLines(true);  // hien thi duong ke ngang
        table.setGridColor(new Color(230, 230, 230));
        table.setRowHeight(40); // chieu cao cua 1 hang
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        table.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi
               
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        
        table.setModel(new DefaultTableModel(row,col));
        model = (DefaultTableModel)table.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(table);
        scroll.setBounds(20, 150, 1070, 400);
        
        table.setRowHeight(40);
        TableColumnModel tableColumnModel = table.getColumnModel();
        
        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth()/8 - 90);
        
        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth()/8 );
        
        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth()/8 + 100);
        
        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth()/8 - 15);
        
        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth()/8 - 15);
        
        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth()/8 - 15);
        
        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth()/8 - 15 );
        
        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth()/8 + 50 );
        
        this.add(scroll); 
    }

    public JPanel getPnTsble() {
        return pnTsble;
    }

    public void setPnTsble(JPanel pnTsble) {
        this.pnTsble = pnTsble;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JLabel getLblMaLopQL() {
        return lblMaLopQL;
    }

    public void setLblMaLopQL(JLabel lblMaLopQL) {
        this.lblMaLopQL = lblMaLopQL;
    }

    public JLabel getLblSapXep() {
        return lblSapXep;
    }

    public void setLblSapXep(JLabel lblSapXep) {
        this.lblSapXep = lblSapXep;
    }

    public JComboBox<String> getCbbMaLopQL() {
        return cbbMaLopQL;
    }

    public void setCbbMaLopQL(JComboBox<String> cbbMaLopQL) {
        this.cbbMaLopQL = cbbMaLopQL;
    }

    public JComboBox<String> getCbbSapXep() {
        return cbbSapXep;
    }

    public void setCbbSapXep(JComboBox<String> cbbSapXep) {
        this.cbbSapXep = cbbSapXep;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public ScrollPaneWin11 getScroll() {
        return scroll;
    }

    public void setScroll(ScrollPaneWin11 scroll) {
        this.scroll = scroll;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public thongKeLopQLController getThongKeLopQLController() {
        return ThongKeLopQLController;
    }

    public void setThongKeLopQLController(thongKeLopQLController ThongKeLopQLController) {
        this.ThongKeLopQLController = ThongKeLopQLController;
    }
    
    
    
}
