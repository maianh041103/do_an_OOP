
package view;

import Table.TableHeader;
import controller.findLopQLController;
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

public class findLopQLView extends JPanel{
    private JPanel pnInfor;
    private CustomLabel lblIcon,lblTitle;
    private JLabel lblMaLop,lblTenKhoa,lblGvcn,lblHocKi,lblSiSo,lblTrangThai;
    private TextField txtMaLop;
    private JTextField txtTenKhoa,txtGvcn,txtHocKi,txtSiSo,txtTrangThai;
    private Font font;
    private buttonView btnFind;
    private JTable tableSinhVien;
    private DefaultTableModel model;
    private ScrollPaneWin11 scroll;
    private findLopQLController FindLopQLController = new findLopQLController(this);

    public findLopQLView() {
        setUpViewPanel();
        setUpViewTitle();
        setUpViewFind();
        setUpButton();
        
        this.setVisible(true);
    }
    
    public void setUpViewPanel(){
        this.setBackground(Color.white);
        this.setLayout(null);
        
        font = new Font("Arial", Font.BOLD, 24);
    }
    
    public void setUpViewTitle(){
        lblTitle = new CustomLabel();
        lblTitle.setText("  Tìm Kiếm Lớp Quản Lý");
        lblTitle.setFont(font);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/find.png"))));
        lblTitle.setForeground(new Color(51, 154, 240));
        lblTitle.setBounds(30, 10, 500, 40);
        this.add(lblTitle);
    }
    
    public void setUpViewFind(){
        lblIcon = new CustomLabel();
        lblIcon.setBounds(30, 60, 250, 250);
        lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/class-group.png"))));
        
        lblMaLop = new JLabel("Mã Lớp Quản Lý");
        lblMaLop.setBounds(320,70,130,30);
        lblMaLop.setFont(new Font("Arial", Font.BOLD, 14));
        lblMaLop.setForeground(new Color(51, 154, 240));
        
        txtMaLop = new TextField();
        txtMaLop.setBounds(480, 70, 300, 30);
        txtMaLop.setFont(new Font("Arial", Font.BOLD, 14));
        txtMaLop.setForeground(new Color(51, 154, 240));
        
        this.add(lblMaLop);
        this.add(txtMaLop);
        this.add(lblIcon);
    }
    
    public void setUpButton(){
        btnFind = new buttonView();
        btnFind.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/icons8-find-20.png"))));
        btnFind.setBounds(900, 70, 100, 30);
        btnFind.setBackground(Color.white);
        btnFind.addActionListener(FindLopQLController);
        btnFind.setActionCommand("Find");
        this.add(btnFind);
    }
    
    public void setUpInfor(){
        String maLop = txtMaLop.getText().toString();
        
        pnInfor = new JPanel();
        pnInfor.setLayout(null);
        pnInfor.setBackground(Color.white);
        pnInfor.setBounds(300, 100, getWidth() - 300, 210);
        
        ArrayList<Object>arr = FindLopQLController.getDataLopQLByMa(maLop);
        
        lblTenKhoa = new JLabel("Khoa : ");
        lblTenKhoa.setBounds(0, 30, 100, 30);
        lblTenKhoa.setFont(new Font("Arial", Font.BOLD, 14));
        lblTenKhoa.setForeground(new Color(51, 154, 240));
        
        txtTenKhoa = new JTextField();
        txtTenKhoa.setBackground(Color.WHITE);
        txtTenKhoa.setBorder(null);
        txtTenKhoa.setBounds(100, 30, 200, 30);
        txtTenKhoa.setFont(new Font("Arial", Font.BOLD, 14));
        txtTenKhoa.setForeground(new Color(51, 154, 240));
        txtTenKhoa.setText(arr.get(0).toString());
        
        lblHocKi = new JLabel("Năm Bắt Đầu : ");
        lblHocKi.setBounds(400, 30, 120, 30);
        lblHocKi.setFont(new Font("Arial", Font.BOLD, 14));
        lblHocKi.setForeground(new Color(51, 154, 240));
        
        txtHocKi = new JTextField();
        txtHocKi.setBackground(Color.WHITE);
        txtHocKi.setBorder(null);
        txtHocKi.setBounds(520, 30, 200, 30);
        txtHocKi.setFont(new Font("Arial", Font.BOLD, 14));
        txtHocKi.setForeground(new Color(51, 154, 240));
        txtHocKi.setText(arr.get(2).toString());
        
        
        lblGvcn = new JLabel("GVCN : ");
        lblGvcn.setBounds(0, 90, 100, 30);
        lblGvcn.setFont(new Font("Arial", Font.BOLD, 14));
        lblGvcn.setForeground(new Color(51, 154, 240));
        
        txtGvcn = new JTextField();
        txtGvcn.setBackground(Color.WHITE);
        txtGvcn.setBorder(null);
        txtGvcn.setBounds(100, 90, 200, 30);
        txtGvcn.setFont(new Font("Arial", Font.BOLD, 14));
        txtGvcn.setForeground(new Color(51, 154, 240));
        txtGvcn.setText(arr.get(1).toString());
        
        
        lblSiSo = new JLabel("Sĩ Số : ");
        lblSiSo.setBounds(400, 90, 100, 30);
        lblSiSo.setFont(new Font("Arial", Font.BOLD, 14));
        lblSiSo.setForeground(new Color(51, 154, 240));
        
        txtSiSo = new JTextField();
        txtSiSo.setBackground(Color.WHITE);
        txtSiSo.setBorder(null);
        txtSiSo.setBounds(520, 90, 200, 30);
        txtSiSo.setFont(new Font("Arial", Font.BOLD, 14));
        txtSiSo.setForeground(new Color(51, 154, 240));
        txtSiSo.setText(FindLopQLController.getSiSo(maLop)+"");
        
        
        lblTrangThai = new JLabel("Trạng Thái : ");
        lblTrangThai.setBounds(0, 150, 100, 30);
        lblTrangThai.setFont(new Font("Arial", Font.BOLD, 14));
        lblTrangThai.setForeground(new Color(51, 154, 240));
        
        txtTrangThai = new JTextField();
        txtTrangThai.setBackground(Color.WHITE);
        txtTrangThai.setBorder(null);
        txtTrangThai.setBounds(100, 150, 200, 30);
        txtTrangThai.setFont(new Font("Arial", Font.BOLD, 14));
        txtTrangThai.setForeground(new Color(51, 154, 240));
        txtTrangThai.setText(arr.get(3).toString());
    
        pnInfor.add(lblTenKhoa);
        pnInfor.add(txtTenKhoa);
        pnInfor.add(lblGvcn);
        pnInfor.add(txtGvcn);
        pnInfor.add(lblHocKi);
        pnInfor.add(txtHocKi);
        pnInfor.add(lblSiSo);
        pnInfor.add(txtSiSo);
        pnInfor.add(lblTrangThai);
        pnInfor.add(txtTrangThai);
   
        this.add(pnInfor);
        
        pnInfor.revalidate();
        pnInfor.repaint();
    }
    
    public void setTable(){
        
        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object>col = new Vector<>();
        col.add("STT");
        col.add("Mã SV");
        col.add("Tên SV");
        col.add("Ngày Sinh");
        col.add("Giới Tính");
        col.add("Địa Chỉ");
        col.add("Trạng Thái");
        
        tableSinhVien = new JTable(); 
        tableSinhVien.setBackground( Color.WHITE);
        tableSinhVien.setShowHorizontalLines(true);  // hien thi duong ke ngang
        tableSinhVien.setGridColor(new Color(230, 230, 230));
        tableSinhVien.setRowHeight(40); // chieu cao cua 1 hang
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSinhVien.setDefaultRenderer(Object.class, centerRenderer);

        tableSinhVien.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        tableSinhVien.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi
               
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        
        tableSinhVien.setModel(new DefaultTableModel(row,col));
        model = (DefaultTableModel)tableSinhVien.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableSinhVien);
        scroll.setBounds(20, 350, getWidth()- 50, 250);
        
        tableSinhVien.setRowHeight(40);
        TableColumnModel tableColumnModel = tableSinhVien.getColumnModel();
        
        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth()/7 - 110);
        
        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth()/7 );
        
        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth()/7 + 80);
        
        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth()/7 + 70);
        
        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth()/7 - 80);
        
        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth()/7 + 70);
        
        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth()/7 - 30);
        
        this.add(scroll); 
       
    }
    
    public void setUpDataTable(String maLop){
        FindLopQLController.setUpDataTable(maLop);
    }

    public JPanel getPnInfor() {
        return pnInfor;
    }

    public void setPnInfor(JPanel pnInfor) {
        this.pnInfor = pnInfor;
    }

    public JLabel getLblIcon() {
        return lblIcon;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JLabel getLblMaLop() {
        return lblMaLop;
    }

    public void setLblMaLop(JLabel lblMaLop) {
        this.lblMaLop = lblMaLop;
    }

    public JLabel getLblTenKhoa() {
        return lblTenKhoa;
    }

    public void setLblTenKhoa(JLabel lblTenKhoa) {
        this.lblTenKhoa = lblTenKhoa;
    }

    public JLabel getLblGvcn() {
        return lblGvcn;
    }

    public void setLblGvcn(JLabel lblGvcn) {
        this.lblGvcn = lblGvcn;
    }

    public JLabel getLblHocKi() {
        return lblHocKi;
    }

    public void setLblHocKi(JLabel lblHocKi) {
        this.lblHocKi = lblHocKi;
    }

    public JLabel getLblSiSo() {
        return lblSiSo;
    }

    public void setLblSiSo(JLabel lblSiSo) {
        this.lblSiSo = lblSiSo;
    }

    public JLabel getLblTrangThai() {
        return lblTrangThai;
    }

    public void setLblTrangThai(JLabel lblTrangThai) {
        this.lblTrangThai = lblTrangThai;
    }

    public TextField getTxtMaLop() {
        return txtMaLop;
    }

    public void setTxtMaLop(TextField txtMaLop) {
        this.txtMaLop = txtMaLop;
    }

    public JTextField getTxtTenKhoa() {
        return txtTenKhoa;
    }

    public void setTxtTenKhoa(JTextField txtTenKhoa) {
        this.txtTenKhoa = txtTenKhoa;
    }

    public JTextField getTxtGvcn() {
        return txtGvcn;
    }

    public void setTxtGvcn(JTextField txtGvcn) {
        this.txtGvcn = txtGvcn;
    }

    public JTextField getTxtHocKi() {
        return txtHocKi;
    }

    public void setTxtHocKi(JTextField txtHocKi) {
        this.txtHocKi = txtHocKi;
    }

    public JTextField getTxtSiSo() {
        return txtSiSo;
    }

    public void setTxtSiSo(JTextField txtSiSo) {
        this.txtSiSo = txtSiSo;
    }

    public JTextField getTxtTrangThai() {
        return txtTrangThai;
    }

    public void setTxtTrangThai(JTextField txtTrangThai) {
        this.txtTrangThai = txtTrangThai;
    }

    public buttonView getBtnFind() {
        return btnFind;
    }

    public void setBtnFind(buttonView btnFind) {
        this.btnFind = btnFind;
    }

    public JTable getTableSinhVien() {
        return tableSinhVien;
    }

    public void setTableSinhVien(JTable tableSinhVien) {
        this.tableSinhVien = tableSinhVien;
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
