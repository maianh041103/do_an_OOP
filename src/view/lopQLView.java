
package view;

import Table.StatusType;
import controller.lopQLController;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import model.CustomLabel;

public class lopQLView extends JFrame{
    private CustomLabel lblTitle;
    private JLabel lblMaLopQL,lblTenKhoa,lblGvcn,lblNamHoc,lblTrangThai;
    private TextField txtMaLopQL,txtTenKhoa,txtGvcn;
    private JComboBox<String>cbbTrangThai,cbbNamHoc;
    private JPanel pn;
    private buttonView btnInsert;
    private tableLopQLView TableLopQLView;
    

    public lopQLView(tableLopQLView TableLopQLView) {
        this.TableLopQLView = TableLopQLView;
        setUpViewPanel();
        setUpViewTitle();
        setUpViewData();
        setUpViewButton();
        addAction();
        
        this.setVisible(true);
    }
    
    public lopQLController LopQLController = new lopQLController(this);
    
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
        lblTitle.setText("Nhập Lớp Quản Lý");
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/book.png"))));
        lblTitle.setBounds(30,10,150,30);
        lblTitle.setFont(new Font("sansserif", 1, 15));
        lblTitle.setBorder(null);
    }
    
    public void setUpViewData(){
        lblMaLopQL = new JLabel("Mã Lớp QL");
        lblMaLopQL.setBounds(30, 70, 80, 30);
        lblMaLopQL.setBorder(null);
        
        lblTenKhoa = new JLabel("Tên Khoa");
        lblTenKhoa.setBounds(30, 140, 80, 30);
        lblTenKhoa.setBorder(null);
        
        lblGvcn = new JLabel("GVCN");
        lblGvcn.setBounds(30, 210, 80, 30);
        lblGvcn.setBorder(null);
        
        lblNamHoc = new JLabel("Năm Bắt Đầu");
        lblNamHoc.setBounds(30, 280, 80, 30);
        lblNamHoc.setBorder(null);
        
        lblTrangThai = new JLabel("Trạng Thái");
        lblTrangThai.setBounds(30, 360, 80, 30);
        lblTrangThai.setBorder(null);
        
        txtMaLopQL = new TextField();
        txtMaLopQL.setBounds(110, 70, 300, 30);
        
        txtTenKhoa = new TextField();
        txtTenKhoa.setBounds(110, 140, 300, 30);
        
        txtGvcn = new TextField();
        txtGvcn.setBounds(110, 210, 300, 30);
        
        cbbNamHoc = new JComboBox();
        cbbNamHoc.setBounds(110, 280, 300, 30);
        cbbNamHoc.setBackground(Color.white);
        cbbNamHoc.setActionCommand("cbbNamHoc");
        cbbNamHoc.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        for(int i=LocalDate.now().getYear();i>=2010;i--){
            String tmp = i+"-"+(int)(i+1);
            cbbNamHoc.addItem(tmp);
        }
        
        cbbTrangThai = new JComboBox();
        cbbTrangThai.setBounds(110,360,300,30);
        cbbTrangThai.addItem(StatusType.COMPLETE.toString());
        cbbTrangThai.addItem(StatusType.PROGRESSING.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        pn.add(lblMaLopQL);
        pn.add(lblTenKhoa);
        pn.add(lblGvcn);
        pn.add(lblNamHoc);
        pn.add(lblTrangThai);
        pn.add(txtMaLopQL);
        pn.add(txtTenKhoa);
        pn.add(txtGvcn);
        pn.add(cbbNamHoc);
        pn.add(cbbTrangThai);
    }
    
    public void setUpViewButton(){
        btnInsert = new buttonView();
        btnInsert.setText("Insert");
        btnInsert.setBounds(190,450,120,30);
        
        pn.add(btnInsert);
    }
    
    public void addAction(){
        btnInsert.addActionListener(LopQLController);
    }

    public CustomLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(CustomLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JLabel getLblMaLopQL() {
        return lblMaLopQL;
    }

    public void setLblMaLopQL(JLabel lblMaLopQL) {
        this.lblMaLopQL = lblMaLopQL;
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

    public JLabel getLblNamHoc() {
        return lblNamHoc;
    }

    public void setLblNamHoc(JLabel lblNamHoc) {
        this.lblNamHoc = lblNamHoc;
    }

    public JLabel getLblTrangThai() {
        return lblTrangThai;
    }

    public void setLblTrangThai(JLabel lblTrangThai) {
        this.lblTrangThai = lblTrangThai;
    }

    public view.TextField getTxtMaLopQL() {
        return txtMaLopQL;
    }

    public void setTxtMaLopQL(view.TextField txtMaLopQL) {
        this.txtMaLopQL = txtMaLopQL;
    }

    public view.TextField getTxtTenKhoa() {
        return txtTenKhoa;
    }

    public void setTxtTenKhoa(view.TextField txtTenKhoa) {
        this.txtTenKhoa = txtTenKhoa;
    }

    public view.TextField getTxtGvcn() {
        return txtGvcn;
    }

    public void setTxtGvcn(view.TextField txtGvcn) {
        this.txtGvcn = txtGvcn;
    }

    public JComboBox<String> getCbbTrangThai() {
        return cbbTrangThai;
    }

    public void setCbbTrangThai(JComboBox<String> cbbTrangThai) {
        this.cbbTrangThai = cbbTrangThai;
    }

    public JComboBox<String> getCbbNamHoc() {
        return cbbNamHoc;
    }

    public void setCbbNamHoc(JComboBox<String> cbbNamHoc) {
        this.cbbNamHoc = cbbNamHoc;
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

    public tableLopQLView getTableLopQLView() {
        return TableLopQLView;
    }

    public void setTableLopQLView(tableLopQLView TableLopQLView) {
        this.TableLopQLView = TableLopQLView;
    }

    public lopQLController getLopQLController() {
        return LopQLController;
    }

    public void setLopQLController(lopQLController LopQLController) {
        this.LopQLController = LopQLController;
    }
    
    
}
