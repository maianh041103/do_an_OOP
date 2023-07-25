package view;

import Table.TableHeader;
import controller.tableSinhVienLopHPController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomLabel;
import model.MyDefaultTableModel;
import model.PanelDat;
import model.PanelNiem;
import model.PanelTrong;
import model.TableActionCellEditer1;
import model.TableActionCellRender1;
import model.TableActionEvent1;
import scroll.ScrollPaneWin11;

public class tableSinhVienLopHPView extends JFrame {

    private JTable tableSinhVienLopHP;
    private ScrollPaneWin11 scroll;
    private buttonView btnInsert, btnImportExcel, btnExportExcel, btnBack, btnLock;
    private CustomLabel lblTitle;
    private MyDefaultTableModel model;
    private JPanel pn;
    private String maLopHP;

    private tableSinhVienLopHPController TableSinhVienLopHPController = new tableSinhVienLopHPController(this);
    private tableLopHPView TableLopHPView;

    public tableSinhVienLopHPView(String maLopHP, tableLopHPView TableLopHPView) {
        this.TableLopHPView = TableLopHPView;
        this.maLopHP = maLopHP;
        setUpViewPanel();
        setUpViewTitle(maLopHP);
        if (TableSinhVienLopHPController.getTrangThaiByMaLopHP(maLopHP) == 0) {
            setUpViewButton();
            addAction();
        }
        addBtnBack();

        setTable();

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

    void setUpViewTitle(String maLopHP) {
        lblTitle = new CustomLabel();
        lblTitle.setText("Sinh Viên Lớp " + maLopHP);
        lblTitle.setBackground(Color.white);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/student.png"))));
        lblTitle.setBounds(20, 20, 1120, 30);
        lblTitle.setFont(new Font("sansserif", 1, 15));

        pn.add(lblTitle);
    }

    void setTable() {

        Vector<Vector<Object>> row = TableSinhVienLopHPController.getDataSinhVienByLopHP(maLopHP);
        Vector<Object> col = new Vector<>();
        col.add("STT");
        col.add("Mã SV");
        col.add("Tên SV");
        col.add("Lớp QL");
        col.add("ĐQT");
        col.add("ĐKT");
        col.add("Điểm Tổng Kết");
        col.add("Thang Điểm 4");
        col.add("Điểm Chữ");
        col.add("Đạt");
        col.add("Hành Động");

        tableSinhVienLopHP = new JTable();
        tableSinhVienLopHP.setBackground(Color.WHITE);
        tableSinhVienLopHP.setShowHorizontalLines(true);  // hien thi duong ke ngang
        tableSinhVienLopHP.setGridColor(new Color(230, 230, 230));
        tableSinhVienLopHP.setRowHeight(40); // chieu cao cua 1 hang

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSinhVienLopHP.setDefaultRenderer(Object.class, centerRenderer);

        tableSinhVienLopHP.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        tableSinhVienLopHP.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi

                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });

        tableSinhVienLopHP.setModel(new MyDefaultTableModel(row, col));
        model = (MyDefaultTableModel) tableSinhVienLopHP.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableSinhVienLopHP);

        TableSinhVienLopHPController.setTable(maLopHP);
        scroll.setBounds(20, 70, 1070, 450);

        tableSinhVienLopHP.setRowHeight(40);
        TableColumnModel tableColumnModel = tableSinhVienLopHP.getColumnModel();

        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth() / 11 - 50);

        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth() / 11 + 85);

        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth() / 11);

        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth() / 11 - 10);

        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth() / 11 + 20);

        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth() / 11);

        TableColumn column8 = tableColumnModel.getColumn(8);
        column8.setPreferredWidth(scroll.getWidth() / 11);

        TableColumn column9 = tableColumnModel.getColumn(9);
        column9.setPreferredWidth(scroll.getWidth() / 11 - 45);

        TableColumn column10 = tableColumnModel.getColumn(10);
        column10.setPreferredWidth(scroll.getWidth() / 11 + 20);

        TableActionEvent1 event = new TableActionEvent1() {
            @Override
            public void onEdit(int row) {
                if (tableSinhVienLopHP.isEditing()) {
                    tableSinhVienLopHP.getCellEditor().stopCellEditing();
                }
                int result = JOptionPane.showConfirmDialog(scroll, "Bạn có chắc chắn muốn sửa thông tin", "Sửa thông tin ", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (!TableSinhVienLopHPController.checkEditDiem(row)) {
                        JOptionPane.showMessageDialog(scroll, "Nhập điểm không hợp lệ", "Nhập điểm", JOptionPane.CANCEL_OPTION);
                        TableSinhVienLopHPController.updateTable();
                    } else {
                        TableSinhVienLopHPController.editDatabase(row);
                        TableSinhVienLopHPController.updateTable();
                    }
                } else {
                    TableSinhVienLopHPController.updateTable();
                }
            }

            @Override
            public void onDelete(int row) {
                int result = JOptionPane.showConfirmDialog(scroll, "Bạn có chắc chắn muốn xóa", "Xóa thông tin ", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (tableSinhVienLopHP.isEditing()) {
                        tableSinhVienLopHP.getCellEditor().stopCellEditing();
                    }
                    TableSinhVienLopHPController.deleteDatabase(row);
                    for (int i = 0; i < model.getRowCount(); i++) {
                        model.setCellEditable(i, 4, true);
                        model.setCellEditable(i, 5, true);
                    }
                    TableSinhVienLopHPController.updateTable();
                    TableSinhVienLopHPController.setTable(maLopHP);
                }
            }

            @Override
            public void onLock(int row) {
                int result = JOptionPane.showConfirmDialog(scroll, "Bạn có chắc chắn muốn lưu", "Lưu thông tin ", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (tableSinhVienLopHP.isEditing()) {
                        tableSinhVienLopHP.getCellEditor().stopCellEditing();
                    }
                    TableSinhVienLopHPController.setTrangThai(row);
                    TableSinhVienLopHPController.setTable(maLopHP);
                }
            }
        };

        pn.add(scroll);
        tableSinhVienLopHP.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender1());
        tableSinhVienLopHP.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditer1(event));
        tableSinhVienLopHP.getColumnModel().getColumn(9).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Object valueAtColumn9 = table.getModel().getValueAt(row, 8);
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
    }

    public void setUpViewButton() {
        btnInsert = new buttonView();
        btnInsert.setBounds(144, 560, 100, 30);
        btnInsert.setText("Insert");
        pn.add(btnInsert);

        btnImportExcel = new buttonView();
        btnImportExcel.setBounds(388, 560, 100, 30);
        btnImportExcel.setText("Import");
        btnImportExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/excel.png"))));
        pn.add(btnImportExcel);

        btnExportExcel = new buttonView();
        btnExportExcel.setBounds(632, 560, 100, 30);
        btnExportExcel.setText("Export");
        btnExportExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/excel.png"))));
        pn.add(btnExportExcel);

        btnLock = new buttonView();
        btnLock.setBounds(876, 560, 100, 30);
        btnLock.setText("Lock");
        btnLock.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/lock-20.png"))));
        pn.add(btnLock);

    }

    public void addBtnBack() {
        btnBack = new buttonView();
        btnBack.setBounds(950, 20, 80, 30);
        btnBack.setText("Back");
        btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/back.png"))));
        pn.add(btnBack);

        btnBack.addActionListener(TableSinhVienLopHPController);
    }

    public void addAction() {
        btnInsert.addActionListener(TableSinhVienLopHPController);
        btnImportExcel.addActionListener(TableSinhVienLopHPController);
        btnExportExcel.addActionListener(TableSinhVienLopHPController);
        btnLock.addActionListener(TableSinhVienLopHPController);
    }

    public tableSinhVienLopHPController getTableSinhVienLopHPController() {
        return TableSinhVienLopHPController;
    }

    public void setTableSinhVienLopHPController(tableSinhVienLopHPController TableSinhVienLopHPController) {
        this.TableSinhVienLopHPController = TableSinhVienLopHPController;
    }

    public tableLopHPView getTableLopHPView() {
        return TableLopHPView;
    }

    public void setTableLopHPView(tableLopHPView TableLopHPView) {
        this.TableLopHPView = TableLopHPView;
    }

    public JTable getTableSinhVienLopHP() {
        return tableSinhVienLopHP;
    }

    public void setTableSinhVienLopHP(JTable tableSinhVienLopHP) {
        this.tableSinhVienLopHP = tableSinhVienLopHP;
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

    public buttonView getBtnImportExcel() {
        return btnImportExcel;
    }

    public void setBtnImportExcel(buttonView btnImportExcel) {
        this.btnImportExcel = btnImportExcel;
    }

    public buttonView getBtnExportExcel() {
        return btnExportExcel;
    }

    public void setBtnExportExcel(buttonView btnExportExcel) {
        this.btnExportExcel = btnExportExcel;
    }

    public buttonView getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(buttonView btnBack) {
        this.btnBack = btnBack;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public buttonView getBtnLock() {
        return btnLock;
    }

    public void setBtnLock(buttonView btnLock) {
        this.btnLock = btnLock;
    }

    public MyDefaultTableModel getModel() {
        return model;
    }

    public void setModel(MyDefaultTableModel model) {
        this.model = model;
    }

    public JPanel getPn() {
        return pn;
    }

    public void setPn(JPanel pn) {
        this.pn = pn;
    }

    public String getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        this.maLopHP = maLopHP;
    }

}
