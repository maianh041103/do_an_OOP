package view;

import Table.StatusType;
import controller.lopHPController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import model.CustomLabel;

public class lopHPView extends JFrame {

    private CustomLabel lblTitle;
    private JLabel lblMaMon, lblMaLopHP, lblTenLopHP, lblGiangVien, lblTrangThai;
    private TextField txtMaLopHP, txtTenLopHP, txtGiangVien;
    private JComboBox<String> cbbTrangThai, cbbMaMon;
    private JPanel pn;
    private buttonView btnInsert;
    private tableLopHPView TableLopHPView;
    private int thang = LocalDate.now().getMonthValue();
    private int hocKi;
    private int nam = LocalDate.now().getYear();

    public int getHocKi() {
        if (thang >= 1 && thang <= 5) {
            hocKi = 2;
        } else if (thang >= 6 && thang <= 8) {
            hocKi = 3;
        } else {
            hocKi = 1;
        }
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public lopHPView(tableLopHPView TableLopHPView) {
        this.TableLopHPView = TableLopHPView;
        setUpViewPanel();
        setUpViewTitle();
        setUpViewData();
        setUpViewButton();
        addAction();

    }

    public lopHPController LopHPController = new lopHPController(this);

    public void setUpViewPanel() {
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        pn = new JPanel();
        pn.setBounds(0, 0, 500, 600);
        pn.setBackground(new Color(255, 255, 255));
        pn.setLayout(null);
        this.add(pn);
    }

    public void setUpViewTitle() {
        lblTitle = new CustomLabel();
        lblTitle.setText("Nhập Lớp Học Phần");
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/course.png"))));
        lblTitle.setBounds(30, 10, 150, 30);
        lblTitle.setFont(new Font("sansserif", 1, 15));
        lblTitle.setBorder(null);
    }

    public void setUpViewData() {
        lblMaMon = new JLabel("Mã Môn");
        lblMaMon.setBounds(30, 70, 80, 30);
        lblMaMon.setBorder(null);
        lblMaLopHP = new JLabel("Mã Lớp HP");
        lblMaLopHP.setBounds(30, 140, 80, 30);
        lblMaLopHP.setBorder(null);
        lblTenLopHP = new JLabel("Tên Lớp HP");
        lblTenLopHP.setBounds(30, 210, 80, 30);
        lblTenLopHP.setBorder(null);
        lblGiangVien = new JLabel("Giảng Viên");
        lblGiangVien.setBounds(30, 280, 80, 30);
        lblGiangVien.setBorder(null);
        lblTrangThai = new JLabel("Trạng Thái");
        lblTrangThai.setBounds(30, 360, 80, 30);
        lblTrangThai.setBorder(null);

        cbbMaMon = new JComboBox<>();
        cbbMaMon.setBounds(110, 70, 300, 30);
        cbbMaMon.setBackground(Color.white);
        cbbMaMon.setActionCommand("cbbMaMon");
        cbbMaMon.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));
        ArrayList<String> arr = LopHPController.getDataMaMon();
        for (String tmp : arr) {
            cbbMaMon.addItem(tmp);
        }

        txtMaLopHP = new TextField();
        txtMaLopHP.setBounds(110, 140, 300, 30);

        txtTenLopHP = new TextField();
        txtTenLopHP.setBounds(110, 210, 300, 30);

        txtGiangVien = new TextField();
        txtGiangVien.setBounds(110, 280, 300, 30);

        cbbTrangThai = new JComboBox<>();
        cbbTrangThai.setBounds(110, 350, 300, 30);
        cbbTrangThai.addItem(StatusType.COMPLETE.toString());
        cbbTrangThai.addItem(StatusType.PROGRESSING.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));

        pn.add(lblMaMon);
        pn.add(lblMaLopHP);
        pn.add(lblTenLopHP);
        pn.add(lblGiangVien);
        pn.add(lblTrangThai);

        pn.add(cbbMaMon);
        pn.add(txtMaLopHP);
        pn.add(txtTenLopHP);
        pn.add(txtGiangVien);
        pn.add(cbbTrangThai);

    }

    public void setUpViewButton() {
        btnInsert = new buttonView();
        btnInsert.setText("Insert");
        btnInsert.setBounds(190, 450, 120, 30);

        pn.add(btnInsert);
    }

    public void addAction() {
        btnInsert.addActionListener(LopHPController);
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public lopHPController getLopHPController() {
        return LopHPController;
    }

    public void setLopHPController(lopHPController LopHPController) {
        this.LopHPController = LopHPController;
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

    public JLabel getLblMaLopHP() {
        return lblMaLopHP;
    }

    public void setLblMaLopHP(JLabel lblMaLopHP) {
        this.lblMaLopHP = lblMaLopHP;
    }

    public JLabel getLblTenLopHP() {
        return lblTenLopHP;
    }

    public void setLblTenLopHP(JLabel lblTenLopHP) {
        this.lblTenLopHP = lblTenLopHP;
    }

    public JLabel getLblGiangVien() {
        return lblGiangVien;
    }

    public void setLblGiangVien(JLabel lblGiangVien) {
        this.lblGiangVien = lblGiangVien;
    }

    public JLabel getLblTrangThai() {
        return lblTrangThai;
    }

    public void setLblTrangThai(JLabel lblTrangThai) {
        this.lblTrangThai = lblTrangThai;
    }

    public TextField getTxtMaLopHP() {
        return txtMaLopHP;
    }

    public void setTxtMaLopHP(TextField txtMaLopHP) {
        this.txtMaLopHP = txtMaLopHP;
    }

    public TextField getTxtTenLopHP() {
        return txtTenLopHP;
    }

    public void setTxtTenLopHP(TextField txtTenLopHP) {
        this.txtTenLopHP = txtTenLopHP;
    }

    public TextField getTxtGiangVien() {
        return txtGiangVien;
    }

    public void setTxtGiangVien(TextField txtGiangVien) {
        this.txtGiangVien = txtGiangVien;
    }

    public JComboBox<String> getCbbTrangThai() {
        return cbbTrangThai;
    }

    public void setCbbTrangThai(JComboBox<String> cbbTrangThai) {
        this.cbbTrangThai = cbbTrangThai;
    }

    public JComboBox<String> getCbbMaMon() {
        return cbbMaMon;
    }

    public void setCbbMaMon(JComboBox<String> cbbMaMon) {
        this.cbbMaMon = cbbMaMon;
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

    public tableLopHPView getTableLopHPView() {
        return TableLopHPView;
    }

    public void setTableLopHPView(tableLopHPView TableLopHPView) {
        this.TableLopHPView = TableLopHPView;
    }

}
