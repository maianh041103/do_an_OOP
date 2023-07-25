
package view;

import Table.TableHeader;
import controller.tableChuongTrinhKhungController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomLabel;
import scroll.ScrollPaneWin11;

public class tableChuongTrinhKhungView extends JPanel{
    private JPanel pnTitle,pnTieuChi,pnTable;
    private Font font;
    private CustomLabel lblIcon;
    private JLabel lblKhoa,lblHocKy;
    private JComboBox<String> cbbKhoa,cbbHocKy;
    private JTable tableKhung;
    private DefaultTableModel model;
    private ScrollPaneWin11 scroll;
    private tableChuongTrinhKhungController TableChuongTrinhKhungController = new tableChuongTrinhKhungController(this);

    public tableChuongTrinhKhungView() {
        setUpViewPanel();
        setUpViewTable();
        
        this.setVisible(true);
    }
    
    public void setUpViewPanel(){
        font = new Font("sansserif", 1, 15);
        
        this.setLayout(null);
        this.setBackground(new Color(250,250,250));
        
        pnTitle = new JPanel();
        pnTitle.setBounds(0, 0, 1200, 50);
        pnTitle.setLayout(null);
        pnTitle.setBackground(new Color(250,250,250));
        
        lblIcon = new CustomLabel();
        lblIcon.setFont(font);
        lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(tableChuongTrinhKhungView.class.getResource("/icon/frame-24.png"))));
        lblIcon.setText("  Chương Trình Khung");
        lblIcon.setBounds(30, 20, 300, 30);
        pnTitle.add(lblIcon);
        
        pnTieuChi = new JPanel();
        pnTieuChi.setBounds(0,50,1200,60);
        pnTieuChi.setLayout(null);
        pnTieuChi.setBackground(new Color(250,250,250));
        
        lblKhoa = new JLabel("Khoa");
        lblKhoa.setBounds(150, 30, 80, 30);
        lblKhoa.setFont(font);
        
        cbbKhoa = new JComboBox<>();
        cbbKhoa.setMaximumRowCount(2);
        cbbKhoa.setBounds(230, 30, 200, 30);
        cbbKhoa.addItem("CNTT");
        cbbKhoa.addItem("Kinh Tế");
        cbbKhoa.setBackground(new Color(250,250,250));
        cbbKhoa.setActionCommand("cbbKhoa");
        cbbKhoa.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));
        cbbKhoa.addActionListener(TableChuongTrinhKhungController);
        
        lblHocKy = new JLabel("Học Kỳ");
        lblHocKy.setBounds(700, 30, 80, 30);
        lblHocKy.setFont(font);
        
        cbbHocKy = new JComboBox<>();
        cbbHocKy.setMaximumRowCount(2);
        cbbHocKy.setBounds(780, 30, 200, 30);
        cbbHocKy.setBackground(new Color(250,250,250));
        cbbHocKy.setActionCommand("cbbHocKy");
        cbbHocKy.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));
        for(int i=1;i<=10;i++){
            cbbHocKy.addItem("Học Kỳ "+i);
        }
        cbbHocKy.addActionListener(TableChuongTrinhKhungController);
        
        pnTieuChi.add(lblKhoa);
        pnTieuChi.add(cbbKhoa);
        pnTieuChi.add(lblHocKy);
        pnTieuChi.add(cbbHocKy);
        
        pnTable = new JPanel();
        pnTable.setBounds(0, 120, 1200, 580);
        pnTable.setBackground(new Color(250,250,250));
        pnTable.setLayout(null);
        
        this.add(pnTitle);
        this.add(pnTieuChi);
        
    }
    
    public void setUpViewTable(){
        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object>col = new Vector<>();
        col.add("STT");
        col.add("Mã Môn");
        col.add("Tên Môn");
        col.add("STC");
        col.add("Số Tiết LT");
        col.add("Số Tiết TH");
        
        tableKhung = new JTable(); 
        tableKhung.setBackground(new Color(250,250,250));
        tableKhung.setShowHorizontalLines(true);  // hien thi duong ke ngang
        tableKhung.setGridColor(new Color(230, 230, 230));
        tableKhung.setRowHeight(40); // chieu cao cua 1 hang
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableKhung.setDefaultRenderer(Object.class, centerRenderer);

        tableKhung.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        tableKhung.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi
               
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        
        tableKhung.setModel(new DefaultTableModel(row,col));
        model = (DefaultTableModel)tableKhung.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableKhung);
        scroll.setBounds(20, 30, 1070, 450);
        
        tableKhung.setRowHeight(40);
        TableColumnModel tableColumnModel = tableKhung.getColumnModel();
        
        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth()/6 - 100);
        
        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth()/6 + 110);
        
        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth()/6 + 110);
        
        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth()/6 - 40);
        
        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth()/6 - 40);
        
        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth()/6 - 40);
        
        
        pnTable.add(scroll); 
        TableChuongTrinhKhungController.getDataTableCTKhung("CNTT", 1);
        
        this.add(pnTable);
    }

    public JPanel getPnTitle() {
        return pnTitle;
    }

    public void setPnTitle(JPanel pnTitle) {
        this.pnTitle = pnTitle;
    }

    public JPanel getPnTieuChi() {
        return pnTieuChi;
    }

    public void setPnTieuChi(JPanel pnTieuChi) {
        this.pnTieuChi = pnTieuChi;
    }

    public JPanel getPnTable() {
        return pnTable;
    }

    public void setPnTable(JPanel pnTable) {
        this.pnTable = pnTable;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public CustomLabel getLblIcon() {
        return lblIcon;
    }

    public void setLblIcon(CustomLabel lblIcon) {
        this.lblIcon = lblIcon;
    }

    public JLabel getLblKhoa() {
        return lblKhoa;
    }

    public void setLblKhoa(JLabel lblKhoa) {
        this.lblKhoa = lblKhoa;
    }

    public JLabel getLblHocKy() {
        return lblHocKy;
    }

    public void setLblHocKy(JLabel lblHocKy) {
        this.lblHocKy = lblHocKy;
    }

    public JComboBox<String> getCbbKhoa() {
        return cbbKhoa;
    }

    public void setCbbKhoa(JComboBox<String> cbbKhoa) {
        this.cbbKhoa = cbbKhoa;
    }

    public JComboBox<String> getCbbHocKy() {
        return cbbHocKy;
    }

    public void setCbbHocKy(JComboBox<String> cbbHocKy) {
        this.cbbHocKy = cbbHocKy;
    }

    public JTable getTableKhung() {
        return tableKhung;
    }

    public void setTableKhung(JTable tableKhung) {
        this.tableKhung = tableKhung;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public ScrollPaneWin11 getScroll() {
        return scroll;
    }

    public void setScroll(ScrollPaneWin11 scroll) {
        this.scroll = scroll;
    }
    
    
}
