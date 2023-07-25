package view;

import Table.StatusTypeStudent;
import Table.TableHeader;
import controller.tableSinhVienController;
import controller.tableSinhVienMouse;
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
import model.PanelAction;
import model.TableActionCellEditer;
import model.TableActionCellRender;
import model.TableActionEvent;
import scroll.ScrollPaneWin11;

public class tableSinhVienView extends JFrame {

    private JTable tableSinhVien;
    private ScrollPaneWin11 scroll;
    private buttonView btnInsert, btnImportExcel, btnExportExcel, btnBack;
    private CustomLabel lblTitle;
    private DefaultTableModel model;
    private JPanel pn;
    private String maLopQL;

    private tableSinhVienController TableSinhVienController = new tableSinhVienController(this);
//    int slRow;
//    private int selectionRow = -1;
//    private int selectionCol = -1;

    public tableSinhVienView(String maLopQL) {
        this.maLopQL = maLopQL;
        setUpViewPanel();
        setUpViewTitle(maLopQL);
        if (TableSinhVienController.getTrangThaiLopQL(maLopQL) == 0) {
            setUpViewButton();
            addAction();
        }
        addBtnBack();
        setTable();
        showTable();

        this.setVisible(true);
    }

    void setUpViewPanel() {
        this.setSize(1120, 700);
        this.setLocationRelativeTo(null);
        pn = new JPanel();
        pn.setBackground(new Color(250, 250, 250));
        this.add(pn);
        pn.setLayout(null);
    }

    void setUpViewTitle(String maLopQL) {
        lblTitle = new CustomLabel();
        lblTitle.setText("Sinh Viên Lớp " + maLopQL);
        lblTitle.setBackground(Color.white);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/student.png"))));
        lblTitle.setBounds(20, 20, 1120, 30);
        lblTitle.setFont(new Font("sansserif", 1, 15));

        pn.add(lblTitle);
    }

    void setTable() {

        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object> col = new Vector<>();
        col.add("STT");
        col.add("Lớp QL");
        col.add("Mã SV");
        col.add("Tên SV");
        col.add("Ngày Sinh");
        col.add("Giới Tính");
        col.add("Địa Chỉ");
        col.add("Trạng Thái");
        col.add("Hành Động");

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
        scroll.setBounds(20, 70, 1070, 450);

        tableSinhVien.setRowHeight(40);
        TableColumnModel tableColumnModel = tableSinhVien.getColumnModel();

        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth() / 9 - 70);

        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth() / 9 - 20);

        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth() / 9 - 20);

        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth() / 9 + 80);

        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth() / 9 + 30);

        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth() / 9 - 20);

        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth() / 9 + 60);

        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth() / 9 );

        TableColumn column8 = tableColumnModel.getColumn(8);
        column8.setPreferredWidth(scroll.getWidth() / 9 - 30);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int result = JOptionPane.showConfirmDialog(tableSinhVien, "Bạn có chắc chắn muốn sửa thông tin", "Sửa thông tin sinh viên", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (tableSinhVien.isEditing()) {
                        tableSinhVien.getCellEditor().stopCellEditing();
                    }
                    TableSinhVienController.editDatabase(row);
                } else {
                    model.setRowCount(0);
                    TableSinhVienController.getDataSinhVienByLopQL(maLopQL);
                }
            }

            @Override
            public void onDelete(int row) {
                int result = JOptionPane.showConfirmDialog(tableSinhVien, "Bạn có chắc chắn muốn xóa", "Xóa thông tin sinh viên", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (tableSinhVien.isEditing()) {
                        tableSinhVien.getCellEditor().stopCellEditing();
                    }
                    TableSinhVienController.deleteDatabase(row);
                    TableSinhVienController.updateTable();
                }
            }

        };

        pn.add(scroll);
        tableSinhVien.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        tableSinhVien.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditer(event));

        DefaultCellEditor cellEditor = new DefaultCellEditor(new JComboBox<>());
        tableSinhVien.getColumnModel().getColumn(7).setCellEditor(cellEditor);
        JComboBox cbbTrangThai = (JComboBox) cellEditor.getComponent();
        cbbTrangThai.setBounds(110, 430, 300, 30);
        cbbTrangThai.addItem(StatusTypeStudent.COMPLETE.toString());
        cbbTrangThai.addItem(StatusTypeStudent.PROGRESSING.toString());
        cbbTrangThai.addItem(StatusTypeStudent.RESERVE.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));

        tableSinhVien.addMouseListener(new tableSinhVienMouse(tableSinhVien));

    }

    public void showTable() {
        
        TableSinhVienController.updateTrangThaiSV();  // Xoas o day de bo phan xet trang thai cho sinh vien
        TableSinhVienController.getDataSinhVienByLopQL(maLopQL);
    }

    public void setUpViewButton() {
        btnInsert = new buttonView();
        btnInsert.setBounds(200, 560, 100, 30);
        btnInsert.setText("Insert");
        pn.add(btnInsert);

        btnImportExcel = new buttonView();
        btnImportExcel.setBounds(485, 560, 100, 30);
        btnImportExcel.setText("Import");
        btnImportExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/excel.png"))));
        pn.add(btnImportExcel);

        btnExportExcel = new buttonView();
        btnExportExcel.setBounds(770, 560, 100, 30);
        btnExportExcel.setText("Export");
        btnExportExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/excel.png"))));
        pn.add(btnExportExcel);

    }

    public void addBtnBack() {
        btnBack = new buttonView();
        btnBack.setBounds(950, 20, 80, 30);
        btnBack.setText("Back");
        btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/back.png"))));
        pn.add(btnBack);

        btnBack.addActionListener(TableSinhVienController);
    }

    public void addAction() {
        btnInsert.addActionListener(TableSinhVienController);
        btnImportExcel.addActionListener(TableSinhVienController);
        btnExportExcel.addActionListener(TableSinhVienController);
    }

    public String getMaLopQL() {
        return maLopQL;
    }

    public void setMaLopQL(String maLopQL) {
        this.maLopQL = maLopQL;
    }

    public JTable getTableSinhVien() {
        return tableSinhVien;
    }

    public void setTableSinhVien(JTable tableSinhVien) {
        this.tableSinhVien = tableSinhVien;
    }

    public ScrollPaneWin11 getScroll() {
        return scroll;
    }

    public void setScroll(ScrollPaneWin11 scroll) {
        this.scroll = scroll;
    }

    public buttonView getBtnInsert() {
        return btnInsert;
    }

    public void setBtnInsert(buttonView btnInsert) {
        this.btnInsert = btnInsert;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JPanel getPn() {
        return pn;
    }

    public void setPn(JPanel pn) {
        this.pn = pn;
    }

}
