
package view;

import Table.StatusType;
import controller.monController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import model.CustomLabel;

public class monView extends JFrame{
    private CustomLabel lblTitle;
    private JLabel lblMaMon,lblTenMon,lblSoTC,lblSoTietLT,lblSoTietTH,lblTrangThai;
    private TextField txtMaMon,txtTenMon,txtSoTC,txtSoTietLT,txtSoTietTH;
    private JComboBox<String>cbbTrangThai;
    private JPanel pn;
    private buttonView btnInsert;
    private tableMonView TableMonView;
    
    
    public monView(tableMonView TableMonView){
        this.TableMonView = TableMonView;
        setUpViewPanel();
        setUpViewTitle();
        setUpViewData();
        setUpViewButton();
        addAction();
        this.setVisible(true);
    }
    public monController MonController = new monController(this);
    
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
        lblTitle.setText("Nhập Môn Học");
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/book.png"))));
        lblTitle.setBounds(30,10,150,30);
        lblTitle.setFont(new Font("sansserif", 1, 15));
        lblTitle.setBorder(null);
    }
    
    public void setUpViewData(){
        lblMaMon = new JLabel("Mã Môn");
        lblMaMon.setBounds(30,70,80,30);
        lblMaMon.setBorder(null);
        lblTenMon = new JLabel("Tên Môn");
        lblTenMon.setBounds(30,130 , 80, 30);
        lblTenMon.setBorder(null);
        lblSoTC = new JLabel("Số Tín Chỉ");
        lblSoTC.setBounds(30, 190, 80, 30);
        lblSoTC.setBorder(null);
        lblSoTietLT = new JLabel("Số Tiết LT");
        lblSoTietLT.setBounds(30, 250, 80, 30);
        lblSoTietLT.setBorder(null);
        lblSoTietTH = new JLabel("Số Tiết TH");
        lblSoTietTH.setBounds(30, 310, 80, 30);
        lblSoTietTH.setBorder(null);
        lblTrangThai = new JLabel("Trạng Thái");
        lblTrangThai.setBounds(30, 360, 80, 30);
        lblTrangThai.setBorder(null);
        
        txtMaMon = new TextField();
        txtMaMon.setBounds(110,70,300,30);
        txtTenMon = new TextField();
        txtTenMon.setBounds(110,130,300,30);
        txtSoTC = new TextField();
        txtSoTC.setBounds(110,190, 300, 30);
        txtSoTietLT = new TextField();
        txtSoTietLT.setBounds(110, 250, 300, 30);
        txtSoTietTH = new TextField();
        txtSoTietTH.setBounds(110, 310, 300, 30);
        
        cbbTrangThai = new JComboBox();
        cbbTrangThai.setBounds(110,365,300,30);
        cbbTrangThai.addItem(StatusType.COMPLETE.toString());
        cbbTrangThai.addItem(StatusType.PROGRESSING.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        pn.add(lblTitle);
        pn.add(lblMaMon);
        pn.add(lblTenMon);
        pn.add(lblSoTC);
        pn.add(lblSoTietLT);
        pn.add(lblSoTietTH);
        pn.add(lblTrangThai);
        
        pn.add(txtMaMon);
        pn.add(txtTenMon);
        pn.add(txtSoTC);
        pn.add(txtSoTietLT);
        pn.add(txtSoTietTH);
        pn.add(cbbTrangThai);
    }
    
    public void setUpViewButton(){
        btnInsert = new buttonView();
        btnInsert.setText("Insert");
        btnInsert.setBounds(190,450,120,30);
        
        pn.add(btnInsert);
    }
    
    public void addAction(){
        btnInsert.addActionListener(MonController);
    }

    public tableMonView getTableMonView() {
        return TableMonView;
    }

    public void setTableMonView(tableMonView TableMonView) {
        this.TableMonView = TableMonView;
    }

    
    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JLabel getLblMaMon() {
        return lblMaMon;
    }

    public void setLblMaMon(JLabel lblMaMon) {
        this.lblMaMon = lblMaMon;
    }

    public JLabel getLblTenMon() {
        return lblTenMon;
    }

    public void setLblTenMon(JLabel lblTenMon) {
        this.lblTenMon = lblTenMon;
    }

    public JLabel getLblSoTC() {
        return lblSoTC;
    }

    public void setLblSoTC(JLabel lblSoTC) {
        this.lblSoTC = lblSoTC;
    }

    public JLabel getLblSoTietLT() {
        return lblSoTietLT;
    }

    public void setLblSoTietLT(JLabel lblSoTietLT) {
        this.lblSoTietLT = lblSoTietLT;
    }

    public JLabel getLblSoTietTH() {
        return lblSoTietTH;
    }

    public void setLblSoTietTH(JLabel lblSoTietTH) {
        this.lblSoTietTH = lblSoTietTH;
    }

    public JLabel getLblTrangThai() {
        return lblTrangThai;
    }

    public void setLblTrangThai(JLabel lblTrangThai) {
        this.lblTrangThai = lblTrangThai;
    }

    public TextField getTxtMaMon() {
        return txtMaMon;
    }

    public void setTxtMaMon(TextField txtMaMon) {
        this.txtMaMon = txtMaMon;
    }

    public TextField getTxtTenMon() {
        return txtTenMon;
    }

    public void setTxtTenMon(TextField txtTenMon) {
        this.txtTenMon = txtTenMon;
    }

    public TextField getTxtSoTC() {
        return txtSoTC;
    }

    public void setTxtSoTC(TextField txtSoTC) {
        this.txtSoTC = txtSoTC;
    }

    public TextField getTxtSoTietLT() {
        return txtSoTietLT;
    }

    public void setTxtSoTietLT(TextField txtSoTietLT) {
        this.txtSoTietLT = txtSoTietLT;
    }

    public TextField getTxtSoTietTH() {
        return txtSoTietTH;
    }

    public void setTxtSoTietTH(TextField txtSoTietTH) {
        this.txtSoTietTH = txtSoTietTH;
    }

    public JComboBox<String> getCbbTrangThai() {
        return cbbTrangThai;
    }

    public void setCbbTrangThai(JComboBox<String> cbbTrangThai) {
        this.cbbTrangThai = cbbTrangThai;
    }
    
    
}
