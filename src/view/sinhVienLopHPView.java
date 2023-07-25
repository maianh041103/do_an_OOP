
package view;

import controller.sinhVienLopHPController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.*;
import model.CustomLabel;


public class sinhVienLopHPView extends JFrame{
    private CustomLabel lblTitle;
    private JLabel lblMaSV,lblTenSV,lblMaLopQL;
    private JComboBox<String>cbbMaSV,cbbMaLopQL;
    private TextField txtTenSV;
    private JPanel pn;
    private buttonView btnInsert;
    private tableSinhVienLopHPView TableSinhVienLopHPView;
    
    public sinhVienLopHPView(tableSinhVienLopHPView TableSinhVienLopHPView){
        this.TableSinhVienLopHPView = TableSinhVienLopHPView;
        setUpViewPanel();
        setUpViewTitle();
        setUpViewData();
        setUpViewButton();
        addAction();
        addItem();
        this.setVisible(true);
    }
    public sinhVienLopHPController SinhVienLopHPController = new sinhVienLopHPController(this);
    
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
        lblTitle.setBounds(30,20,150,30);
        lblTitle.setFont(new Font("sansserif", 1, 15));
        lblTitle.setBorder(null);
        
        pn.add(lblTitle);
    }
    
    public void setUpViewData(){
        lblMaLopQL = new JLabel("Lớp QL ");
        lblMaLopQL.setBounds(30, 120, 100, 30);
        
        lblMaSV = new JLabel("Mã Sinh Viên ");
        lblMaSV.setBounds(30, 200, 100, 30);
        
        lblTenSV = new JLabel("Tên Sinh Viên ");
        lblTenSV.setBounds(30, 280, 100, 30);
        
        cbbMaLopQL = new JComboBox<>();
        cbbMaLopQL.setBounds(130,120,270,30);
        cbbMaLopQL.setBackground(Color.white);
        cbbMaLopQL.setActionCommand("cbbMaLopQL");
        cbbMaLopQL.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        cbbMaLopQL.addActionListener(SinhVienLopHPController);
        
        cbbMaSV = new JComboBox<>();
        cbbMaSV.setBounds(130,200,270,30);
        cbbMaSV.setBackground(Color.white);
        cbbMaSV.setActionCommand("cbbMaSV");
        cbbMaSV.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        cbbMaSV.addActionListener(SinhVienLopHPController);
        
        txtTenSV = new TextField();
        txtTenSV.setBounds(130, 280, 270, 30);
        
        
        pn.add(lblMaSV);
        pn.add(cbbMaSV);
        pn.add(lblTenSV);
        pn.add(lblMaLopQL);
        pn.add(txtTenSV);
        pn.add(cbbMaLopQL);
    }
    
    public void setUpViewButton(){
        btnInsert = new buttonView();
        btnInsert.setText("Insert");
        btnInsert.setBounds(190,470,120,30);
        
        pn.add(btnInsert);
    }
    
    public void addAction(){
        btnInsert.addActionListener(SinhVienLopHPController);
    }
    
    public void addItem(){
        ArrayList<String> arr1 = SinhVienLopHPController.getDataMaLopQL();
        for(String tmp : arr1){
            cbbMaLopQL.addItem(tmp);
        }
    }

    public tableSinhVienLopHPView getTableSinhVienLopHPView() {
        return TableSinhVienLopHPView;
    }

    public void setTableSinhVienLopHPView(tableSinhVienLopHPView TableSinhVienLopHPView) {
        this.TableSinhVienLopHPView = TableSinhVienLopHPView;
    }

    public JLabel getLblTitle() {
        return lblTitle;
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

    public JLabel getLblMaLopQL() {
        return lblMaLopQL;
    }

    public void setLblMaLopQL(JLabel lblMaLopQL) {
        this.lblMaLopQL = lblMaLopQL;
    }

    public JComboBox<String> getCbbMaSV() {
        return cbbMaSV;
    }

    public void setCbbMaSV(JComboBox<String> cbbMaSV) {
        this.cbbMaSV = cbbMaSV;
    }

    public JComboBox<String> getCbbMaLopQL() {
        return cbbMaLopQL;
    }

    public void setCbbMaLopQL(JComboBox<String> cbbMaLopQL) {
        this.cbbMaLopQL = cbbMaLopQL;
    }

    public TextField getTxtTenSV() {
        return txtTenSV;
    }

    public void setTxtTenSV(TextField txtTenSV) {
        this.txtTenSV = txtTenSV;
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

    public sinhVienLopHPController getSinhVienLopHPController() {
        return SinhVienLopHPController;
    }

    public void setSinhVienLopHPController(sinhVienLopHPController SinhVienLopHPController) {
        this.SinhVienLopHPController = SinhVienLopHPController;
    }
    
    
}
