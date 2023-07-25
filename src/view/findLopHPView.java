package view;

import Table.TableHeader;
import controller.findLopHPController;
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
import model.PanelDat;
import model.PanelNiem;
import model.PanelTrong;
import scroll.ScrollPaneWin11;

public class findLopHPView extends JPanel {

    private Font font;
    private CustomLabel lblTitle, lblIcon;
    private JLabel lblMaLop;
    private TextField txtMaLop;
    private buttonView btnFind;
    private JPanel pnInfor;
    private JLabel lblMaMon, lblTenLopHP, lblGiangVien, lblTrangThai, lblSiSo, lblDiemTB, lblHocKi;
    private JTextField txtMaMon, txtTenLopHP, txtGiangVien, txtTrangThai, txtSiSo, txtDiemTB, txtHocKi;
    private JTable tableSinhVien;
    private DefaultTableModel model;
    private ScrollPaneWin11 scroll;

    private findLopHPController FindLopHPController = new findLopHPController(this);

    public findLopHPView() {
        setUpViewPanel();
        setUpViewTitle();
        setUpViewFind();
        setUpButton();

    }

    public void setUpViewPanel() {
        this.setBackground(Color.white);
        this.setLayout(null);

        font = new Font("Arial", Font.BOLD, 24);
    }

    public void setUpViewTitle() {
        lblTitle = new CustomLabel();
        lblTitle.setText("  Tìm Kiếm Lớp Học Phần");
        lblTitle.setFont(font);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(findLopHPView.class.getResource("/icon/find.png"))));
        lblTitle.setForeground(new Color(51, 154, 240));
        lblTitle.setBounds(30, 10, 500, 40);
        this.add(lblTitle);
    }

    public void setUpViewFind() {
        lblIcon = new CustomLabel();
        lblIcon.setBounds(30, 60, 250, 250);
        lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/online-course.png"))));

        lblMaLop = new JLabel("Mã Lớp Học Phần");
        lblMaLop.setBounds(320, 70, 130, 30);
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

    public void setUpButton() {
        btnFind = new buttonView();
        btnFind.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/icons8-find-20.png"))));
        btnFind.setBounds(900, 70, 100, 30);
        btnFind.setBackground(Color.white);
        btnFind.addActionListener(FindLopHPController);
        btnFind.setActionCommand("Find");
        this.add(btnFind);
    }

    public void setUpInfor() {
        String maLopHP = txtMaLop.getText();

        pnInfor = new JPanel();
        pnInfor.setLayout(null);
        pnInfor.setBackground(Color.white);
        pnInfor.setBounds(300, 100, getWidth() - 300, 210);

        ArrayList<Object> arr = FindLopHPController.getDataLopHPByMa(maLopHP);

        lblMaMon = new JLabel("Mã Môn : ");
        lblMaMon.setBounds(0, 25, 100, 30);
        lblMaMon.setFont(new Font("Arial", Font.BOLD, 14));
        lblMaMon.setForeground(new Color(51, 154, 240));

        txtMaMon = new JTextField();
        txtMaMon.setBackground(Color.WHITE);
        txtMaMon.setBorder(null);
        txtMaMon.setBounds(100, 25, 200, 30);
        txtMaMon.setFont(new Font("Arial", Font.BOLD, 14));
        txtMaMon.setForeground(new Color(51, 154, 240));
        txtMaMon.setText(arr.get(0).toString());

        lblTenLopHP = new JLabel("Tên Lớp : ");
        lblTenLopHP.setBounds(400, 25, 100, 30);
        lblTenLopHP.setFont(new Font("Arial", Font.BOLD, 14));
        lblTenLopHP.setForeground(new Color(51, 154, 240));

        txtTenLopHP = new JTextField();
        txtTenLopHP.setBackground(Color.WHITE);
        txtTenLopHP.setBorder(null);
        txtTenLopHP.setBounds(500, 25, 200, 30);
        txtTenLopHP.setFont(new Font("Arial", Font.BOLD, 14));
        txtTenLopHP.setForeground(new Color(51, 154, 240));
        txtTenLopHP.setText(arr.get(1).toString());

        lblGiangVien = new JLabel("Giảng Viên : ");
        lblGiangVien.setBounds(0, 80, 100, 30);
        lblGiangVien.setFont(new Font("Arial", Font.BOLD, 14));
        lblGiangVien.setForeground(new Color(51, 154, 240));

        txtGiangVien = new JTextField();
        txtGiangVien.setBackground(Color.WHITE);
        txtGiangVien.setBorder(null);
        txtGiangVien.setBounds(100, 80, 200, 30);
        txtGiangVien.setFont(new Font("Arial", Font.BOLD, 14));
        txtGiangVien.setForeground(new Color(51, 154, 240));
        txtGiangVien.setText(arr.get(2).toString());

        lblSiSo = new JLabel("Sĩ Số : ");
        lblSiSo.setBounds(400, 80, 100, 30);
        lblSiSo.setFont(new Font("Arial", Font.BOLD, 14));
        lblSiSo.setForeground(new Color(51, 154, 240));

        txtSiSo = new JTextField();
        txtSiSo.setBackground(Color.WHITE);
        txtSiSo.setBorder(null);
        txtSiSo.setBounds(500, 80, 200, 30);
        txtSiSo.setFont(new Font("Arial", Font.BOLD, 14));
        txtSiSo.setForeground(new Color(51, 154, 240));
        txtSiSo.setText(FindLopHPController.getSiSo(maLopHP) + "");

        lblTrangThai = new JLabel("Trạng Thái : ");
        lblTrangThai.setBounds(0, 135, 100, 30);
        lblTrangThai.setFont(new Font("Arial", Font.BOLD, 14));
        lblTrangThai.setForeground(new Color(51, 154, 240));

        txtTrangThai = new JTextField();
        txtTrangThai.setBackground(Color.WHITE);
        txtTrangThai.setBorder(null);
        txtTrangThai.setBounds(100, 135, 200, 30);
        txtTrangThai.setFont(new Font("Arial", Font.BOLD, 14));
        txtTrangThai.setForeground(new Color(51, 154, 240));
        txtTrangThai.setText(arr.get(3).toString());

        lblDiemTB = new JLabel("Điểm TB : ");
        lblDiemTB.setBounds(400, 135, 100, 30);
        lblDiemTB.setFont(new Font("Arial", Font.BOLD, 14));
        lblDiemTB.setForeground(new Color(51, 154, 240));

        txtDiemTB = new JTextField();
        txtDiemTB.setBackground(Color.WHITE);
        txtDiemTB.setBorder(null);
        txtDiemTB.setBounds(500, 135, 100, 30);
        txtDiemTB.setFont(new Font("Arial", Font.BOLD, 14));
        txtDiemTB.setForeground(new Color(51, 154, 240));
        txtDiemTB.setText(String.valueOf(FindLopHPController.getDiemTB(maLopHP)));

        lblHocKi = new JLabel("Học Kỳ : ");
        lblHocKi.setBounds(0, 185, 100, 30);
        lblHocKi.setFont(new Font("Arial", Font.BOLD, 14));
        lblHocKi.setForeground(new Color(51, 154, 240));

        txtHocKi = new JTextField();
        txtHocKi.setBackground(Color.WHITE);
        txtHocKi.setBorder(null);
        txtHocKi.setBounds(100, 185, 200, 30);
        txtHocKi.setFont(new Font("Arial", Font.BOLD, 14));
        txtHocKi.setForeground(new Color(51, 154, 240));
        int hk = Integer.parseInt(arr.get(4).toString());
        int nam = Integer.parseInt(arr.get(5).toString());
        String hocKy;
        if (hk == 1) {
            hocKy = "HK" + hk + " " + nam + "-" + (int) (nam + 1);
        } else {
            hocKy = "HK" + hk + " " + (int) (nam - 1) + "-" + nam;
        }
        txtHocKi.setText(hocKy);

        pnInfor.add(lblMaMon);
        pnInfor.add(txtMaMon);
        pnInfor.add(lblTenLopHP);
        pnInfor.add(txtTenLopHP);
        pnInfor.add(lblGiangVien);
        pnInfor.add(txtGiangVien);
        pnInfor.add(lblSiSo);
        pnInfor.add(txtSiSo);
        pnInfor.add(lblTrangThai);
        pnInfor.add(txtTrangThai);
        pnInfor.add(lblDiemTB);
        pnInfor.add(txtDiemTB);
        pnInfor.add(lblHocKi);
        pnInfor.add(txtHocKi);

        this.add(pnInfor);

        pnInfor.revalidate();
        pnInfor.repaint();
    }

    public void setTable() {

        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object> col = new Vector<>();
        col.add("STT");
        col.add("Mã SV");
        col.add("Tên SV");
        col.add("Ngày Sinh");
        col.add("Lớp QL");
        col.add("ĐQT");
        col.add("ĐKT");
        col.add("Điểm Tổng Kết");
        col.add("Thang Điểm 4");
        col.add("Điểm chữ");
        col.add("Đạt");

        tableSinhVien = new JTable();
        tableSinhVien.setBackground(Color.WHITE);
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

        tableSinhVien.setModel(new DefaultTableModel(row, col));
        model = (DefaultTableModel) tableSinhVien.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableSinhVien);
        scroll.setBounds(20, 350, getWidth() - 50, 250);

        tableSinhVien.setRowHeight(40);
        TableColumnModel tableColumnModel = tableSinhVien.getColumnModel();

        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth() / 11 - 55);

        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth() / 11);

        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth() / 11 + 105);

        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth() / 11 + 30);

        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth() / 11 + 10);

        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth() / 11 - 20);

        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth() / 11 - 20);

        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth() / 11);

        TableColumn column8 = tableColumnModel.getColumn(8);
        column8.setPreferredWidth(scroll.getWidth() / 11);

        TableColumn column9 = tableColumnModel.getColumn(9);
        column9.setPreferredWidth(scroll.getWidth() / 11);

        TableColumn column10 = tableColumnModel.getColumn(10);
        column10.setPreferredWidth(scroll.getWidth() / 11 - 50);

        tableSinhVien.getColumnModel().getColumn(10).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Object valueAtColumn9 = table.getModel().getValueAt(row, 9);
                if (valueAtColumn9 != null && valueAtColumn9.toString().equals("")) {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    PanelTrong trong = new PanelTrong();
                    if (isSelected == false && row % 2 == 0) {
                        trong.setBackground(Color.WHITE);
                    } else {
                        trong.setBackground(com.getBackground());
                    }
                    return trong;
                } else if (valueAtColumn9 != null && valueAtColumn9.toString().equals("F")) {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    PanelNiem niem = new PanelNiem();
                    if (isSelected == false && row % 2 == 0) {
                        niem.setBackground(Color.WHITE);
                    } else {
                        niem.setBackground(com.getBackground());
                    }
                    return niem;
                } else {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    PanelDat dat = new PanelDat();
                    if (isSelected == false && row % 2 == 0) {
                        dat.setBackground(Color.WHITE);
                    } else {
                        dat.setBackground(com.getBackground());
                    }
                    return dat;
                }
            }
        });

        this.add(scroll);
    }

    public void setUpDataTable(String maLop) {
        FindLopHPController.getDataTable(maLop);
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

    public TextField getTxtMaLop() {
        return txtMaLop;
    }

    public void setTxtMaLop(TextField txtMaLop) {
        this.txtMaLop = txtMaLop;
    }

    public CustomLabel getLblIcon() {
        return lblIcon;
    }

    public void setLblIcon(CustomLabel lblIcon) {
        this.lblIcon = lblIcon;
    }

    public buttonView getBtnFind() {
        return btnFind;
    }

    public void setBtnFind(buttonView btnFind) {
        this.btnFind = btnFind;
    }

    public JPanel getPnInfor() {
        return pnInfor;
    }

    public void setPnInfor(JPanel pnInfor) {
        this.pnInfor = pnInfor;
    }

    public JLabel getLblMaMon() {
        return lblMaMon;
    }

    public void setLblMaMon(JLabel lblMaMon) {
        this.lblMaMon = lblMaMon;
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

    public JLabel getLblSiSo() {
        return lblSiSo;
    }

    public void setLblSiSo(JLabel lblSiSo) {
        this.lblSiSo = lblSiSo;
    }

    public JLabel getLblDiemTB() {
        return lblDiemTB;
    }

    public void setLblDiemTB(JLabel lblDiemTB) {
        this.lblDiemTB = lblDiemTB;
    }

    public JTextField getTxtMaMon() {
        return txtMaMon;
    }

    public void setTxtMaMon(JTextField txtMaMon) {
        this.txtMaMon = txtMaMon;
    }

    public JTextField getTxtTenLopHP() {
        return txtTenLopHP;
    }

    public void setTxtTenLopHP(JTextField txtTenLopHP) {
        this.txtTenLopHP = txtTenLopHP;
    }

    public JTextField getTxtGiangVien() {
        return txtGiangVien;
    }

    public void setTxtGiangVien(JTextField txtGiangVien) {
        this.txtGiangVien = txtGiangVien;
    }

    public JTextField getTxtTrangThai() {
        return txtTrangThai;
    }

    public void setTxtTrangThai(JTextField txtTrangThai) {
        this.txtTrangThai = txtTrangThai;
    }

    public JTextField getTxtSiSo() {
        return txtSiSo;
    }

    public void setTxtSiSo(JTextField txtSiSo) {
        this.txtSiSo = txtSiSo;
    }

    public JTextField getTxtDiemTB() {
        return txtDiemTB;
    }

    public void setTxtDiemTB(JTextField txtDiemTB) {
        this.txtDiemTB = txtDiemTB;
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
