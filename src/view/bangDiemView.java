package view;

import Table.TableHeader;
import controller.bangDiemController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomLabel;
import model.PanelAction;
import model.PanelDat;
import model.PanelNiem;
import model.PanelTrong;
import model.TableActionCellEditer;
import model.TableActionCellRender;
import model.TableActionEvent;
import scroll.ScrollPaneWin11;

public class bangDiemView extends JFrame {

    private JPanel pn, pnTongKet;
    private CustomLabel lblTitle;
    private JLabel lblTenSV, lblMaSV, lblNgaySinh, lblLopQL, lblKhoa, lblHeDT, lblHocKi;
    private JTextField txtTenSV, txtMaSV, txtNgaySinh, txtLopQL, txtKhoa, txtHeDT;
    private JLabel lblTbHe10, lblTbHe4, lblTichLuyHe10, lblTichLuyHe4, lblTinDki, lblTinTichLuy, lblXepLoai, lblXepLoaiTichLuy;
    private JTextField txtTbHe10, txtTbHe4, txtTichLuyHe10, txtTichLuyHe4, txtTinDki, txtTinTichLuy, txtXepLoai, txtXepLoaiTichLuy;
    private JComboBox<String> cbbHocKy;
    private JTable tableBangDiem;
    private buttonView btnBack;
    private Font font;
    private DefaultTableModel model;
    private ScrollPaneWin11 scroll;
    private String maSV;
    private bangDiemController BangDiemController = new bangDiemController(this);
    
    public bangDiemView(String maSV) {
        this.maSV = maSV;
        setUpViewFrame();
        setUpViewTitle(maSV);
        setUpViewThongTin();
        setTable(maSV);
        int namBD = BangDiemController.getNamNhapHoc(BangDiemController.getDataSinhVienByMaSV(maSV).get(2).toString());
        setTongKet(1,namBD);
        this.setVisible(true);
    }

    public void setUpViewFrame() {
        this.setSize(1120, 700);
        this.setLocationRelativeTo(null);
        pn = new JPanel();
        pn.setBackground(new Color(245, 245, 245));
        pn.setLayout(null);
        this.add(pn);
        font = new Font("Aria", Font.BOLD, 12);
    }

    void setUpViewTitle(String maSV) {
        lblTitle = new CustomLabel();
        lblTitle.setText("Bảng Điểm Sinh Viên " + maSV);
        lblTitle.setBackground(Color.white);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/transcript.png"))));
        lblTitle.setBounds(20, 20, 1120, 30);
        lblTitle.setFont(new Font("sansserif", 1, 15));

        btnBack = new buttonView();
        btnBack.setBounds(950, 0, 80, 30);
        btnBack.setText("Back");
        btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chiTietMonView.class.getResource("/icon/back.png"))));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }

        });
        lblTitle.setLayout(null);
        lblTitle.add(btnBack);

        pn.add(lblTitle);
    }

    public void setUpViewThongTin() {

        lblTenSV = new JLabel("Sinh Viên : ");
        lblTenSV.setBounds(20, 70, 80, 30);
        lblTenSV.setFont(font);

        lblMaSV = new JLabel("Mã SV : ");
        lblMaSV.setBounds(350, 70, 80, 30);
        lblMaSV.setFont(font);

        lblNgaySinh = new JLabel("Ngày Sinh : ");
        lblNgaySinh.setBounds(680, 70, 80, 30);
        lblNgaySinh.setFont(font);

        lblLopQL = new JLabel("Lớp : ");
        lblLopQL.setBounds(20, 120, 50, 30);
        lblLopQL.setFont(font);

        lblKhoa = new JLabel("Khoa : ");
        lblKhoa.setBounds(240, 120, 50, 30);
        lblKhoa.setFont(font);

        lblHeDT = new JLabel("Hệ Đào Tạo : ");
        lblHeDT.setBounds(460, 120, 80, 30);
        lblHeDT.setFont(font);

        lblHocKi = new JLabel("Học Kỳ : ");
        lblHocKi.setBounds(800, 120, 80, 30);
        lblHocKi.setFont(font);

        txtTenSV = new JTextField();
        txtTenSV.setBounds(100, 70, 200, 30);
        txtTenSV.setBorder(null);
        txtTenSV.setBackground(new Color(245, 245, 245));
        txtTenSV.setFont(font);
        ArrayList<Object> arr = BangDiemController.getDataSinhVienByMaSV(maSV);
        txtTenSV.setText(arr.get(0).toString());

        txtMaSV = new JTextField(maSV);
        txtMaSV.setBounds(430, 70, 200, 30);
        txtMaSV.setBorder(null);
        txtMaSV.setBackground(new Color(245, 245, 245));
        txtMaSV.setFont(font);

        txtNgaySinh = new JTextField();
        txtNgaySinh.setBounds(760, 70, 200, 30);
        txtNgaySinh.setBorder(null);
        txtNgaySinh.setBackground(new Color(245, 245, 245));
        txtNgaySinh.setFont(font);
        String tmp = arr.get(1).toString();
        String date = tmp.substring(8, 10) + "/" + tmp.substring(5, 7) + "/" + tmp.substring(0, 4);
        txtNgaySinh.setText(date);

        txtLopQL = new JTextField();
        txtLopQL.setBounds(70, 120, 150, 30);
        txtLopQL.setBorder(null);
        txtLopQL.setBackground(new Color(245, 245, 245));
        txtLopQL.setFont(font);
        txtLopQL.setText(arr.get(2).toString());

        txtKhoa = new JTextField();
        txtKhoa.setBounds(290, 120, 150, 30);
        txtKhoa.setBorder(null);
        txtKhoa.setBackground(new Color(245, 245, 245));
        txtKhoa.setFont(font);
        txtKhoa.setText(BangDiemController.getKhoaByMaLopQL(arr.get(2).toString()));

        txtHeDT = new JTextField("Đại học hệ chính quy");
        txtHeDT.setBounds(540, 120, 150, 30);
        txtHeDT.setBorder(null);
        txtHeDT.setBackground(new Color(245, 245, 245));
        txtHeDT.setFont(font);

        cbbHocKy = new JComboBox<>();
        cbbHocKy.setMaximumRowCount(2);
        cbbHocKy.setBounds(860, 120, 150, 30);
        cbbHocKy.setBackground(new Color(250, 250, 250));
        cbbHocKy.setActionCommand("cbbHocKy");
        cbbHocKy.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));
        int namBD = BangDiemController.getNamNhapHoc(arr.get(2).toString());
        for (int i = namBD; i < LocalDate.now().getYear(); i++) {
            for (int j = 1; j <= 3; j++) {
                String hk = "HK" + j + " " + i + "-" + (int) (i + 1);
                cbbHocKy.addItem(hk);
            }
        }
        cbbHocKy.addActionListener(BangDiemController);

        pn.add(lblTenSV);
        pn.add(lblMaSV);
        pn.add(lblNgaySinh);
        pn.add(lblLopQL);
        pn.add(lblKhoa);
        pn.add(lblHeDT);
        pn.add(lblHocKi);

        pn.add(txtMaSV);
        pn.add(txtTenSV);
        pn.add(txtLopQL);
        pn.add(txtKhoa);
        pn.add(txtNgaySinh);
        pn.add(txtHeDT);
        pn.add(cbbHocKy);
    }

    void setTable(String maSV) {

        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object> col = new Vector<>();
        col.add("STT");
        col.add("Mã Môn");
        col.add("Tên Môn Học");
        col.add("Lớp Học Phần");
        col.add("STC");
        col.add("ĐQT");
        col.add("ĐKT");
        col.add("Điểm Tổng");
        col.add("Thang Điểm 4");
        col.add("Điểm chữ");
        col.add("Đạt");

        tableBangDiem = new JTable();
        tableBangDiem.setBackground(Color.WHITE);
        tableBangDiem.setShowHorizontalLines(true);  // hien thi duong ke ngang
        tableBangDiem.setGridColor(new Color(230, 230, 230));
        tableBangDiem.setRowHeight(40); // chieu cao cua 1 hang

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableBangDiem.setDefaultRenderer(Object.class, centerRenderer);

        tableBangDiem.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        tableBangDiem.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi

                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });

        tableBangDiem.setModel(new DefaultTableModel(row, col));
        model = (DefaultTableModel) tableBangDiem.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableBangDiem);
        scroll.setBounds(20, 180, 1070, 250);

        tableBangDiem.setRowHeight(40);
        TableColumnModel tableColumnModel = tableBangDiem.getColumnModel();

        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth() / 11 - 60);

        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth() / 11 + 30);

        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth() / 11 + 110);

        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth() / 11 + 30);

        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column8 = tableColumnModel.getColumn(8);
        column8.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column9 = tableColumnModel.getColumn(9);
        column9.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column10 = tableColumnModel.getColumn(10);
        column10.setPreferredWidth(scroll.getWidth() / 11 - 50);

        ArrayList<Object> arr = BangDiemController.getDataSinhVienByMaSV(maSV);
        int namBD = BangDiemController.getNamNhapHoc(arr.get(2).toString());

        BangDiemController.getDataBangDiem(maSV, 1, namBD);

        tableBangDiem.getColumnModel().getColumn(10).setCellRenderer(new DefaultTableCellRenderer() {
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

        pn.add(scroll);

    }
    
    
    public void setTongKet(int hocKy,int nam){
        pnTongKet = new JPanel();
        pnTongKet.setBackground(new Color(245,245,245));
        pnTongKet.setLayout(null);
        pnTongKet.setBounds(0, 430, 1070, 270);
        
        lblTbHe10 = new JLabel("Điểm trung bình hệ 10 : ");
        lblTbHe10.setBounds(20, 20, 180, 30);
        
        txtTbHe10 = new JTextField();
        txtTbHe10.setBounds(200, 20, 100, 30);
        txtTbHe10.setBorder(null);
        txtTbHe10.setBackground(new Color(245, 245, 245));
        txtTbHe10.setFont(font);
        txtTbHe10.setText(String.valueOf(BangDiemController.diemHocKyHe10()));
        
        lblTbHe4 = new JLabel("Điểm trung bình hệ 4 : ");
        lblTbHe4.setBounds(600, 20, 180, 30);
        
        txtTbHe4 = new JTextField();
        txtTbHe4.setBounds(780, 20, 100, 30);
        txtTbHe4.setBorder(null);
        txtTbHe4.setBackground(new Color(245, 245, 245));
        txtTbHe4.setFont(font);
        txtTbHe4.setText(String.valueOf(BangDiemController.diemHocKyHe4()));
        
        
        lblTichLuyHe10 = new JLabel("Điểm tích lũy hệ 10 : ");
        lblTichLuyHe10.setBounds(20, 75, 180, 30);
        
        txtTichLuyHe10 = new JTextField();
        txtTichLuyHe10.setBounds(200, 75, 100, 30);
        txtTichLuyHe10.setBorder(null);
        txtTichLuyHe10.setBackground(new Color(245, 245, 245));
        txtTichLuyHe10.setFont(font);
        txtTichLuyHe10.setText(String.valueOf(BangDiemController.diemTichLuyHe10(maSV, hocKy, nam)));
        
        lblTichLuyHe4 = new JLabel("Điểm tích lũy hệ 4 : ");
        lblTichLuyHe4.setBounds(600, 75, 180, 30);
        
        txtTichLuyHe4 = new JTextField();
        txtTichLuyHe4.setBounds(780, 75, 100, 30);
        txtTichLuyHe4.setBorder(null);
        txtTichLuyHe4.setBackground(new Color(245, 245, 245));
        txtTichLuyHe4.setFont(font);
        txtTichLuyHe4.setText(String.valueOf(BangDiemController.diemTichLuyHe4(maSV, hocKy, nam)));
        
        
        lblTinDki = new JLabel("Tổng số tín chỉ đã đăng kí : ");
        lblTinDki.setBounds(20, 130, 180, 30);
        
        txtTinDki = new JTextField();
        txtTinDki.setBounds(200, 130, 100, 30);
        txtTinDki.setBorder(null);
        txtTinDki.setBackground(new Color(245, 245, 245));
        txtTinDki.setFont(font);
        txtTinDki.setText(String.valueOf(BangDiemController.tongTinDangKi(maSV, hocKy, nam)));
        
        lblTinTichLuy = new JLabel("Tổng số tín chỉ tích lũy : ");
        lblTinTichLuy.setBounds(600, 130, 180, 30);
        
        txtTinTichLuy = new JTextField();
        txtTinTichLuy.setBounds(780, 130, 100, 30);
        txtTinTichLuy.setBorder(null);
        txtTinTichLuy.setBackground(new Color(245, 245, 245));
        txtTinTichLuy.setFont(font);
        txtTinTichLuy.setText(String.valueOf(BangDiemController.tongTinTichLuy(maSV, hocKy, nam)));
        
        lblXepLoai = new JLabel("Xếp loại học lực học kỳ : ");
        lblXepLoai.setBounds(20, 185, 180, 30);
        
        txtXepLoai = new JTextField();
        txtXepLoai.setBounds(200, 185, 100, 30);
        txtXepLoai.setBorder(null);
        txtXepLoai.setBackground(new Color(245, 245, 245));
        txtXepLoai.setFont(font);
        txtXepLoai.setText(BangDiemController.xepLoaiTheoKy());
        
        lblXepLoaiTichLuy = new JLabel("Xếp loại học lực tích lũy : ");
        lblXepLoaiTichLuy.setBounds(600, 185, 180, 30);
        
        txtXepLoaiTichLuy = new JTextField();
        txtXepLoaiTichLuy.setBounds(780, 185, 100, 30);
        txtXepLoaiTichLuy.setBorder(null);
        txtXepLoaiTichLuy.setBackground(new Color(245, 245, 245));
        txtXepLoaiTichLuy.setFont(font);
        txtXepLoaiTichLuy.setText(BangDiemController.xepLoaiTichLuy(maSV, hocKy, nam));
        
        pnTongKet.add(lblTbHe10);
        pnTongKet.add(txtTbHe10);
        pnTongKet.add(lblTbHe4);
        pnTongKet.add(txtTbHe4);
        pnTongKet.add(lblTichLuyHe10);
        pnTongKet.add(txtTichLuyHe10);
        pnTongKet.add(lblTichLuyHe4);
        pnTongKet.add(txtTichLuyHe4);
        pnTongKet.add(lblTinDki);
        pnTongKet.add(txtTinDki);
        pnTongKet.add(lblTinTichLuy);
        pnTongKet.add(txtTinTichLuy);
        pnTongKet.add(lblXepLoai);
        pnTongKet.add(txtXepLoai);
        pnTongKet.add(lblXepLoaiTichLuy);
        pnTongKet.add(txtXepLoaiTichLuy);
        
        pnTongKet.revalidate();
        pnTongKet.repaint();
        
        pn.add(pnTongKet);
    }

    public JComboBox<String> getCbbHocKy() {
        return cbbHocKy;
    }

    public void setCbbHocKy(JComboBox<String> cbbHocKy) {
        this.cbbHocKy = cbbHocKy;
    }

    public JPanel getPn() {
        return pn;
    }

    public void setPn(JPanel pn) {
        this.pn = pn;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JLabel getLblTenSV() {
        return lblTenSV;
    }

    public void setLblTenSV(JLabel lblTenSV) {
        this.lblTenSV = lblTenSV;
    }

    public JLabel getLblMaSV() {
        return lblMaSV;
    }

    public void setLblMaSV(JLabel lblMaSV) {
        this.lblMaSV = lblMaSV;
    }

    public JLabel getLblNgaySinh() {
        return lblNgaySinh;
    }

    public void setLblNgaySinh(JLabel lblNgaySinh) {
        this.lblNgaySinh = lblNgaySinh;
    }

    public JLabel getLblLopQL() {
        return lblLopQL;
    }

    public void setLblLopQL(JLabel lblLopQL) {
        this.lblLopQL = lblLopQL;
    }

    public JLabel getLblKhoa() {
        return lblKhoa;
    }

    public void setLblKhoa(JLabel lblKhoa) {
        this.lblKhoa = lblKhoa;
    }

    public JLabel getLblHeDT() {
        return lblHeDT;
    }

    public void setLblHeDT(JLabel lblHeDT) {
        this.lblHeDT = lblHeDT;
    }

    public JTextField getTxtTenSV() {
        return txtTenSV;
    }

    public void setTxtTenSV(JTextField txtTenSV) {
        this.txtTenSV = txtTenSV;
    }

    public JTextField getTxtMaSV() {
        return txtMaSV;
    }

    public void setTxtMaSV(JTextField txtMaSV) {
        this.txtMaSV = txtMaSV;
    }

    public JTextField getTxtNgaySinh() {
        return txtNgaySinh;
    }

    public void setTxtNgaySinh(JTextField txtNgaySinh) {
        this.txtNgaySinh = txtNgaySinh;
    }

    public JTextField getTxtLopQL() {
        return txtLopQL;
    }

    public void setTxtLopQL(JTextField txtLopQL) {
        this.txtLopQL = txtLopQL;
    }

    public JTextField getTxtKhoa() {
        return txtKhoa;
    }

    public void setTxtKhoa(JTextField txtKhoa) {
        this.txtKhoa = txtKhoa;
    }

    public JTextField getTxtHeDT() {
        return txtHeDT;
    }

    public void setTxtHeDT(JTextField txtHeDT) {
        this.txtHeDT = txtHeDT;
    }

    public JTable getTableBangDiem() {
        return tableBangDiem;
    }

    public void setTableBangDiem(JTable tableBangDiem) {
        this.tableBangDiem = tableBangDiem;
    }

    public buttonView getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(buttonView btnBack) {
        this.btnBack = btnBack;
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

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public bangDiemController getBangDiemController() {
        return BangDiemController;
    }

    public void setBangDiemController(bangDiemController BangDiemController) {
        this.BangDiemController = BangDiemController;
    }

}
