
package view;

import Table.TableHeader;
import controller.findStudentController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
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

public class findStudentView extends JPanel{

    private JPanel pnInfor;
    private CustomLabel lblTitle,lblIcon;
    private JLabel lblMaSV,lblTenSV,lblLopQL,lblNgaySinh,lblKhoa,lblHeDaoTao,lblQueQuan,lblDiemTb,lblTinChi;
    private JTextField txtTenSV,txtLopQL,txtNgaySinh,txtKhoa,txtHeDaoTao,txtQueQuan,txtDiemTb,txtTinChi;
    private Font font;
    private TextField txtMaSV;
    private buttonView btnFind;
    private JTable tableBangDiem;
    private DefaultTableModel model; 
    private ScrollPaneWin11 scroll;
    private findStudentController FindStudentController = new findStudentController(this);
    
    public findStudentView() {
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
        lblTitle.setText("  Tìm Kiếm Sinh Viên");
        lblTitle.setFont(font);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/find.png"))));
        lblTitle.setForeground(new Color(51, 154, 240));
        lblTitle.setBounds(30, 10, 500, 40);
        this.add(lblTitle);
        
    }
    
    public void setUpViewFind(){
        lblIcon = new CustomLabel();
        lblIcon.setBounds(30, 60, 250, 250);
        lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/student (3).png"))));
        
        lblMaSV = new JLabel("Mã Sinh Viên");
        lblMaSV.setBounds(300,70,100,30);
        lblMaSV.setFont(new Font("Arial", Font.BOLD, 14));
        lblMaSV.setForeground(new Color(51, 154, 240));
        
        txtMaSV = new TextField();
        txtMaSV.setBounds(430, 70, 300, 30);
        txtMaSV.setFont(new Font("Arial", Font.BOLD, 14));
        txtMaSV.setForeground(new Color(51, 154, 240));
        
        this.add(lblMaSV);
        this.add(txtMaSV);
        this.add(lblIcon);
    }
    
    public void setUpButton(){
        btnFind = new buttonView();
        btnFind.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/icons8-find-20.png"))));
        btnFind.setBounds(850, 70, 100, 30);
        btnFind.setBackground(Color.white);
        btnFind.addActionListener(FindStudentController);
        btnFind.setActionCommand("Find");
        this.add(btnFind);
    }
    
    public void setUpViewInfor(){
        String maSV = txtMaSV.getText().toString();
        
        pnInfor = new JPanel();
        pnInfor.setLayout(null);
        pnInfor.setBackground(Color.white);
        pnInfor.setBounds(300, 100, getWidth() - 300, 210);
        
        lblTenSV = new JLabel("Họ Tên : ");
        lblTenSV.setBounds(0, 25, 150, 30);
        lblTenSV.setFont(new Font("Arial", Font.BOLD, 14));
        lblTenSV.setForeground(new Color(51, 154, 240));
        
        ArrayList<Object> arr = FindStudentController.getDataSinhVienByMaSV(maSV);
        
        txtTenSV = new JTextField();
        txtTenSV.setBackground(Color.WHITE);
        txtTenSV.setBorder(null);
        txtTenSV.setBounds(150, 25, 200, 30);
        txtTenSV.setFont(new Font("Arial", Font.BOLD, 14));
        txtTenSV.setForeground(new Color(51, 154, 240));
        txtTenSV.setText(arr.get(0).toString());
        
        lblLopQL = new JLabel("Lớp Học : ");
        lblLopQL.setBounds(400, 25, 150, 30);
        lblLopQL.setFont(new Font("Arial", Font.BOLD, 14));
        lblLopQL.setForeground(new Color(51, 154, 240));
        
        txtLopQL = new JTextField();
        txtLopQL.setBackground(Color.WHITE);
        txtLopQL.setBorder(null);
        txtLopQL.setBounds(550, 25, 200, 30);
        txtLopQL.setFont(new Font("Arial", Font.BOLD, 14));
        txtLopQL.setForeground(new Color(51, 154, 240));
        txtLopQL.setText(arr.get(3).toString());
        
        lblNgaySinh = new JLabel("Ngày Sinh : ");
        lblNgaySinh.setBounds(0, 80, 150, 30);
        lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 14));
        lblNgaySinh.setForeground(new Color(51, 154, 240));
        
        txtNgaySinh = new JTextField();
        txtNgaySinh.setBackground(Color.WHITE);
        txtNgaySinh.setBorder(null);
        txtNgaySinh.setBounds(150, 80, 200, 30);
        txtNgaySinh.setFont(new Font("Arial", Font.BOLD, 14));
        txtNgaySinh.setForeground(new Color(51, 154, 240));
        String tmp = arr.get(1).toString();
        String date = tmp.substring(8, 10)+"/"+tmp.substring(5, 7)+"/"+tmp.substring(0, 4);
        txtNgaySinh.setText(date);
        
        lblKhoa = new JLabel("Khoa : ");
        lblKhoa.setBounds(400, 80, 150, 30);
        lblKhoa.setFont(new Font("Arial", Font.BOLD, 14));
        lblKhoa.setForeground(new Color(51, 154, 240));
        
        txtKhoa = new JTextField();
        txtKhoa.setBackground(Color.WHITE);
        txtKhoa.setBorder(null);
        txtKhoa.setBounds(550, 80, 200, 30);
        txtKhoa.setFont(new Font("Arial", Font.BOLD, 14));
        txtKhoa.setForeground(new Color(51, 154, 240));
        txtKhoa.setText(FindStudentController.getKhoaByMaLopQL(arr.get(3).toString()));
        
        lblQueQuan = new JLabel("Quê Quán : ");
        lblQueQuan.setBounds(0, 135, 150, 30);
        lblQueQuan.setFont(new Font("Arial", Font.BOLD, 14));
        lblQueQuan.setForeground(new Color(51, 154, 240));
        
        txtQueQuan = new JTextField();
        txtQueQuan.setBackground(Color.WHITE);
        txtQueQuan.setBorder(null);
        txtQueQuan.setBounds(150, 135, 200, 30);
        txtQueQuan.setFont(new Font("Arial", Font.BOLD, 14));
        txtQueQuan.setForeground(new Color(51, 154, 240));
        txtQueQuan.setText(arr.get(2).toString());
        
        lblHeDaoTao = new JLabel("Hệ Đào Tạo : ");
        lblHeDaoTao.setBounds(400, 135, 150, 30);
        lblHeDaoTao.setFont(new Font("Arial", Font.BOLD, 14));
        lblHeDaoTao.setForeground(new Color(51, 154, 240));
        
        txtHeDaoTao = new JTextField("Đại học hệ chính quy");
        txtHeDaoTao.setBackground(Color.WHITE);
        txtHeDaoTao.setBorder(null);
        txtHeDaoTao.setBounds(550, 135, 200, 30);
        txtHeDaoTao.setFont(new Font("Arial", Font.BOLD, 14));
        txtHeDaoTao.setForeground(new Color(51, 154, 240));
        
        lblDiemTb = new JLabel("Điểm tích lũy hệ 4 : ");
        lblDiemTb.setBounds(0, 190, 150, 30);
        lblDiemTb.setFont(new Font("Arial", Font.BOLD, 14));
        lblDiemTb.setForeground(new Color(51, 154, 240));
        
        txtDiemTb = new JTextField();
        txtDiemTb.setBackground(Color.WHITE);
        txtDiemTb.setBorder(null);
        txtDiemTb.setBounds(150, 190, 200, 30);
        txtDiemTb.setFont(new Font("Arial", Font.BOLD, 14));
        txtDiemTb.setForeground(new Color(51, 154, 240));
        txtDiemTb.setText(String.valueOf(FindStudentController.diemHocKyHe4()));
        
        lblTinChi = new JLabel("Tổng tín chỉ tích lũy : ");
        lblTinChi.setBounds(400, 190, 150, 30);
        lblTinChi.setFont(new Font("Arial", Font.BOLD, 14));
        lblTinChi.setForeground(new Color(51, 154, 240));
        
        txtTinChi = new JTextField();
        txtTinChi.setBackground(Color.WHITE);
        txtTinChi.setBorder(null);
        txtTinChi.setBounds(550, 190, 200, 30);
        txtTinChi.setFont(new Font("Arial", Font.BOLD, 14));
        txtTinChi.setForeground(new Color(51, 154, 240));
        txtTinChi.setText(String.valueOf(FindStudentController.tongTinChi()));
        
        this.add(pnInfor);
        pnInfor.add(lblTenSV);
        pnInfor.add(txtTenSV);
        pnInfor.add(lblLopQL);
        pnInfor.add(txtLopQL);
        pnInfor.add(lblNgaySinh);
        pnInfor.add(txtNgaySinh);
        pnInfor.add(lblKhoa);
        pnInfor.add(txtKhoa);
        pnInfor.add(lblQueQuan);
        pnInfor.add(txtQueQuan);
        pnInfor.add(lblHeDaoTao);
        pnInfor.add(txtHeDaoTao);
        pnInfor.add(lblDiemTb);
        pnInfor.add(txtDiemTb);
        pnInfor.add(lblTinChi);
        pnInfor.add(txtTinChi);
        
        pnInfor.revalidate();
        pnInfor.repaint();
    }
    
    public void setTable(){
        
        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object>col = new Vector<>();
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
        tableBangDiem.setBackground( Color.WHITE);
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
        
        tableBangDiem.setModel(new DefaultTableModel(row,col));
        model = (DefaultTableModel)tableBangDiem.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableBangDiem);
        scroll.setBounds(20, 350, getWidth()- 50, 250);
        
        tableBangDiem.setRowHeight(40);
        TableColumnModel tableColumnModel = tableBangDiem.getColumnModel();
        
        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth()/11 - 60);
        
        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth()/11 + 30);
        
        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth()/11 + 110);
        
        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth()/11 + 30);
        
        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth()/11 - 10);
        
        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth()/11 - 10);
        
        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth()/11 - 10);
        
        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth()/11 - 10);
        
        TableColumn column8 = tableColumnModel.getColumn(8);
        column8.setPreferredWidth(scroll.getWidth()/11 - 10);
        
        TableColumn column9 = tableColumnModel.getColumn(9);
        column9.setPreferredWidth(scroll.getWidth()/11 - 10);   
        
        TableColumn column10 = tableColumnModel.getColumn(10);
        column10.setPreferredWidth(scroll.getWidth()/11 - 50);   
        
        
        tableBangDiem.getColumnModel().getColumn(10).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Object valueAtColumn9 = table.getModel().getValueAt(row, 9);
                if (valueAtColumn9 != null &&  valueAtColumn9.toString().equals("")) {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                    PanelTrong trong = new PanelTrong();
                    if(isSelected == false && row%2 == 0){
                        trong.setBackground(Color.WHITE);
                    }
                    else{
                        trong.setBackground(com.getBackground());
                    }
                    return trong;
                } 
                else if (valueAtColumn9 != null &&  valueAtColumn9.toString().equals("F")){
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    PanelNiem niem = new PanelNiem();
                    if(isSelected == false && row%2 == 0){
                        niem.setBackground(Color.WHITE);
                    }
                    else{
                        niem.setBackground(com.getBackground());
                    }
                    return niem;
                }
                else {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    PanelDat dat = new PanelDat();
                    if(isSelected == false && row%2 == 0){
                        dat.setBackground(Color.WHITE);
                    }
                    else{
                        dat.setBackground(com.getBackground());
                    }
                    return dat;                
                    }                
            }
        });
        
        
        
        FindStudentController.getDataBangDiem(txtMaSV.getText().toString());
        
        this.add(scroll); 
        
    }
    
    public void setUpDataTable(String maSV){
        FindStudentController.getDataBangDiem(maSV);
    }

    public JPanel getPnInfor() {
        return pnInfor;
    }

    public void setPnInfor(JPanel pnInfor) {
        this.pnInfor = pnInfor;
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

    public JLabel getLblIcon() {
        return lblIcon;
    }

    public JLabel getLblTenSV() {
        return lblTenSV;
    }

    public void setLblTenSV(JLabel lblTenSV) {
        this.lblTenSV = lblTenSV;
    }

    public JLabel getLblLopQL() {
        return lblLopQL;
    }

    public void setLblLopQL(JLabel lblLopQL) {
        this.lblLopQL = lblLopQL;
    }

    public JLabel getLblNgaySinh() {
        return lblNgaySinh;
    }

    public void setLblNgaySinh(JLabel lblNgaySinh) {
        this.lblNgaySinh = lblNgaySinh;
    }

    public JLabel getLblKhoa() {
        return lblKhoa;
    }

    public void setLblKhoa(JLabel lblKhoa) {
        this.lblKhoa = lblKhoa;
    }

    public JLabel getLblHeDaoTao() {
        return lblHeDaoTao;
    }

    public void setLblHeDaoTao(JLabel lblHeDaoTao) {
        this.lblHeDaoTao = lblHeDaoTao;
    }

    public JLabel getLblQueQuan() {
        return lblQueQuan;
    }

    public void setLblQueQuan(JLabel lblQueQuan) {
        this.lblQueQuan = lblQueQuan;
    }

    public JTextField getTxtTenSV() {
        return txtTenSV;
    }

    public void setTxtTenSV(JTextField txtTenSV) {
        this.txtTenSV = txtTenSV;
    }

    public JTextField getTxtLopQL() {
        return txtLopQL;
    }

    public void setTxtLopQL(JTextField txtLopQL) {
        this.txtLopQL = txtLopQL;
    }

    public JTextField getTxtNgaySinh() {
        return txtNgaySinh;
    }

    public void setTxtNgaySinh(JTextField txtNgaySinh) {
        this.txtNgaySinh = txtNgaySinh;
    }

    public JTextField getTxtKhoa() {
        return txtKhoa;
    }

    public void setTxtKhoa(JTextField txtKhoa) {
        this.txtKhoa = txtKhoa;
    }

    public JTextField getTxtHeDaoTao() {
        return txtHeDaoTao;
    }

    public void setTxtHeDaoTao(JTextField txtHeDaoTao) {
        this.txtHeDaoTao = txtHeDaoTao;
    }

    public JTextField getTxtQueQuan() {
        return txtQueQuan;
    }

    public void setTxtQueQuan(JTextField txtQueQuan) {
        this.txtQueQuan = txtQueQuan;
    }

    public TextField getTxtMaSV() {
        return txtMaSV;
    }

    public void setTxtMaSV(TextField txtMaSV) {
        this.txtMaSV = txtMaSV;
    }

    public buttonView getBtnFind() {
        return btnFind;
    }

    public void setBtnFind(buttonView btnFind) {
        this.btnFind = btnFind;
    }

    public JTable getTableBangDiem() {
        return tableBangDiem;
    }

    public void setTableBangDiem(JTable tableBangDiem) {
        this.tableBangDiem = tableBangDiem;
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

    public findStudentController getFindStudentController() {
        return FindStudentController;
    }

    public void setFindStudentController(findStudentController FindStudentController) {
        this.FindStudentController = FindStudentController;
    }
    
    
    
}
