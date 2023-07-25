
package view;

import Table.StatusTypeStudent;
import controller.sinhVienController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.*;
import model.CustomLabel;

public class sinhVienView extends JFrame{
    private CustomLabel lblTitle;
    private JLabel lblLopQL,lblMaSV,lblTenSV,lblNgaySinh,lblGioiTinh,lblDiaChi,lblTrangThai;
    private TextField txtMaSV,txtTenSV,txtDiaChi;
    private JComboBox<String>cbbTrangThai,cbbLopQL;
    private JComboBox<Integer>cbbNgay,cbbThang,cbbNam;
    private JRadioButton btnNu,btnNam;
    private JPanel pn;
    private buttonView btnInsert;
    private tableSinhVienView TableSinhVienView;
    
    
    public sinhVienView(tableSinhVienView TableSinhVienView){
        this.TableSinhVienView = TableSinhVienView;
        setUpViewPanel();
        setUpViewTitle();
        setUpViewData();
        setUpViewButton();
        addAction();
        addItemNS();
        addItemMaLop(TableSinhVienView.getMaLopQL().toString());
        this.setVisible(true);
    }
    public sinhVienController SinhVienController = new sinhVienController(this);
    
    public void setUpViewPanel(){
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        pn = new JPanel();
        pn.setBounds(0,0,500,600);
        pn.setBackground(new Color(255,255,255));
        pn.setLayout(null);
        this.add(pn);
    }
    
    public void setUpViewTitle(){
        lblTitle = new CustomLabel();
        lblTitle.setText("Nhập Sinh Viên");
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/student.png"))));
        lblTitle.setBounds(30,10,150,30);
        lblTitle.setFont(new Font("sansserif", 1, 15));
        lblTitle.setBorder(null);
    }
    
    public void setUpViewData(){
        lblLopQL = new JLabel("Mã Lớp QL");
        lblLopQL.setBounds(30,70,80,30);
        lblLopQL.setBorder(null);
        
        lblMaSV = new JLabel("Mã SV");
        lblMaSV.setBounds(30,130 , 80, 30);
        lblMaSV.setBorder(null);
        
        lblTenSV = new JLabel("Tên SV");
        lblTenSV.setBounds(30, 190, 80, 30);
        lblTenSV.setBorder(null);
        
        lblNgaySinh = new JLabel("Ngày Sinh");
        lblNgaySinh.setBounds(30, 250, 80, 30);
        lblNgaySinh.setBorder(null);
        
        lblGioiTinh = new JLabel("Giới Tính");
        lblGioiTinh.setBounds(30, 310, 80, 30);
        lblGioiTinh.setBorder(null);
        
        lblDiaChi = new JLabel("Địa Chỉ");
        lblDiaChi.setBounds(30, 370, 80, 30);
        lblDiaChi.setBorder(null);
        
        lblTrangThai = new JLabel("Trạng Thái");
        lblTrangThai.setBounds(30, 430, 80, 30);
        lblTrangThai.setBorder(null);
        
        cbbLopQL = new JComboBox<>();
        cbbLopQL.setBounds(110,70,300,30);
        cbbLopQL.setBackground(Color.white);
        cbbLopQL.setActionCommand("cbbMaLopQL");
        cbbLopQL.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        txtMaSV = new TextField();
        txtMaSV.setBounds(110,130,300,30);
        
        txtTenSV = new TextField();
        txtTenSV.setBounds(110,190,300,30);
        
        cbbNgay = new JComboBox<>();
        cbbNgay.setBounds(110,250,100,30);
        cbbNgay.setBackground(Color.white);
        cbbNgay.setActionCommand("cbbNgay");
        cbbNgay.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        cbbThang = new JComboBox<>();
        cbbThang.setBounds(230,250,100,30);
        cbbThang.setBackground(Color.white);
        cbbThang.setActionCommand("cbbThang");
        cbbThang.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        cbbNam = new JComboBox<>();
        cbbNam.setBounds(350,250,100,30);
        cbbNam.setBackground(Color.white);
        cbbNam.setActionCommand("cbbNam");
        cbbNam.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        btnNu = new JRadioButton("Nữ");
        btnNu.setBounds(110, 310, 100, 30);
        btnNu.addActionListener(SinhVienController);
        btnNu.setActionCommand("Nu");
        
        btnNam = new JRadioButton("Nam");
        btnNam.setBounds(250, 310, 100, 30);
        btnNam.addActionListener(SinhVienController);
        btnNam.setActionCommand("Nam");
       
        txtDiaChi = new TextField();
        txtDiaChi.setBounds(110, 370, 300, 30);
       
        cbbTrangThai = new JComboBox();
        cbbTrangThai.setBounds(110,430,300,30);
        cbbTrangThai.addItem(StatusTypeStudent.COMPLETE.toString());
        cbbTrangThai.addItem(StatusTypeStudent.PROGRESSING.toString());
        cbbTrangThai.addItem(StatusTypeStudent.RESERVE.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        pn.add(lblTitle);
        pn.add(lblLopQL);
        pn.add(lblMaSV);
        pn.add(lblTenSV);
        pn.add(lblNgaySinh);
        pn.add(lblGioiTinh);
        pn.add(lblDiaChi);
        pn.add(lblTrangThai);
        
        pn.add(cbbLopQL);
        pn.add(txtMaSV);
        pn.add(txtTenSV);
        pn.add(cbbNgay);
        pn.add(cbbThang);
        pn.add(cbbNam);
        pn.add(btnNu);
        pn.add(btnNam);
        pn.add(txtDiaChi);
        pn.add(cbbTrangThai);
    }
    
    public void setUpViewButton(){
        btnInsert = new buttonView();
        btnInsert.setText("Insert");
        btnInsert.setBounds(190,500,120,30);
        
        pn.add(btnInsert);
    }
    
    public void addAction(){
        btnInsert.addActionListener(SinhVienController);
    }
    
    public void addItemMaLop(String maLopQL){
        cbbLopQL.addItem(maLopQL);
    }
    
    public void addItemNS(){
        Date currentDate = new Date(System.currentTimeMillis());
        int year = currentDate.toLocalDate().getYear();
        
        for(int i=year;i >= year - 100;i--){
            cbbNam.addItem(i);
        }
        for(int i=1;i<=12;i++){
            cbbThang.addItem(i);
        }
        for(int i=1;i<=31;i++){
            cbbNgay.addItem(i);
        }
    }
    
    //Nam 0 nu 1
    public boolean getGioiTinh(){
        if(btnNu.isSelected())
            return true;
        else
            return false;
    }
    

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JLabel getLblLopQL() {
        return lblLopQL;
    }

    public void setLblLopQL(JLabel lblLopQL) {
        this.lblLopQL = lblLopQL;
    }

    public JLabel getLblMaSV() {
        return lblMaSV;
    }

    public void setLblMaSV(JLabel lblMaSV) {
        this.lblMaSV = lblMaSV;
    }

    public JLabel getLblTenSV() {
        return lblTenSV;
    }

    public void setLblTenSV(JLabel lblTenSV) {
        this.lblTenSV = lblTenSV;
    }

    public JLabel getLblNgaySinh() {
        return lblNgaySinh;
    }

    public void setLblNgaySinh(JLabel lblNgaySinh) {
        this.lblNgaySinh = lblNgaySinh;
    }

    public JLabel getLblGioiTinh() {
        return lblGioiTinh;
    }

    public void setLblGioiTinh(JLabel lblGioiTinh) {
        this.lblGioiTinh = lblGioiTinh;
    }

    public JLabel getLblDiaChi() {
        return lblDiaChi;
    }

    public void setLblDiaChi(JLabel lblDiaChi) {
        this.lblDiaChi = lblDiaChi;
    }

    public JLabel getLblTrangThai() {
        return lblTrangThai;
    }

    public void setLblTrangThai(JLabel lblTrangThai) {
        this.lblTrangThai = lblTrangThai;
    }

    public TextField getTxtMaSV() {
        return txtMaSV;
    }

    public void setTxtMaSV(TextField txtMaSV) {
        this.txtMaSV = txtMaSV;
    }

    public TextField getTxtTenSV() {
        return txtTenSV;
    }

    public void setTxtTenSV(TextField txtTenSV) {
        this.txtTenSV = txtTenSV;
    }

    public TextField getTxtDiaChi() {
        return txtDiaChi;
    }

    public void setTxtDiaChi(TextField txtDiaChi) {
        this.txtDiaChi = txtDiaChi;
    }

    public JComboBox<String> getCbbTrangThai() {
        return cbbTrangThai;
    }

    public void setCbbTrangThai(JComboBox<String> cbbTrangThai) {
        this.cbbTrangThai = cbbTrangThai;
    }

    public JComboBox<String> getCbbLopQL() {
        return cbbLopQL;
    }

    public void setCbbLopQL(JComboBox<String> cbbLopQL) {
        this.cbbLopQL = cbbLopQL;
    }

    public JComboBox<Integer> getCbbNgay() {
        return cbbNgay;
    }

    public void setCbbNgay(JComboBox<Integer> cbbNgay) {
        this.cbbNgay = cbbNgay;
    }

    public JComboBox<Integer> getCbbThang() {
        return cbbThang;
    }

    public void setCbbThang(JComboBox<Integer> cbbThang) {
        this.cbbThang = cbbThang;
    }

    public JComboBox<Integer> getCbbNam() {
        return cbbNam;
    }

    public void setCbbNam(JComboBox<Integer> cbbNam) {
        this.cbbNam = cbbNam;
    }

    public JRadioButton getBtnNu() {
        return btnNu;
    }

    public void setBtnNu(JRadioButton btnNu) {
        this.btnNu = btnNu;
    }

    public JRadioButton getBtnNam() {
        return btnNam;
    }

    public void setBtnNam(JRadioButton btnNam) {
        this.btnNam = btnNam;
    }

    public JPanel getPn() {
        return pn;
    }

    public void setPn(JPanel pn) {
        this.pn = pn;
    }

    public buttonView getBtnInsert() {
        return btnInsert;
    }

    public void setBtnInsert(buttonView btnInsert) {
        this.btnInsert = btnInsert;
    }

    public tableSinhVienView getTableSinhVienView() {
        return TableSinhVienView;
    }

    public void setTableSinhVienView(tableSinhVienView TableSinhVienView) {
        this.TableSinhVienView = TableSinhVienView;
    }
    

}
