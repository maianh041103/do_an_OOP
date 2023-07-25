package view;

import Table.StatusType;
import Table.TableHeader;
import controller.tableLopQLController;
import controller.tableLopQLMouse;
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
import model.TableActionCellEditer;
import model.TableActionCellRender;
import model.TableActionEvent;
import scroll.ScrollPaneWin11;

public class tableLopQLView extends JPanel {

    private JTable tableLopQL;
    private ScrollPaneWin11 scroll;
    private buttonView btnInsert;
    private CustomLabel lblTitle;
    private DefaultTableModel model;
    private tableLopQLController TableLopQLController = new tableLopQLController(this);
//    int slRow;
//    private int selectionRow = -1;
//    private int selectionCol = -1;

    public tableLopQLView() {
        setUpViewPanel();
        setUpViewTitle();
        setUpViewButton();
        addAction();
        setTable();
        showTable();

        this.setVisible(true);

    }

    void setUpViewPanel() {
        this.setLayout(null);
        this.setBackground(new Color(250, 250, 250));
    }

    void setUpViewTitle() {
        lblTitle = new CustomLabel();
        lblTitle.setText("Lớp Quản Lý");
        lblTitle.setBackground(Color.white);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/class.png"))));
        lblTitle.setBounds(20, 20, 1070, 30);
        lblTitle.setFont(new Font("sansserif", 1, 15));

        this.add(lblTitle);
    }

    void setTable() {

        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object> col = new Vector<>();
        col.add("STT");
        col.add("Mã Lớp QL");
        col.add("Tên Khoa");
        col.add("GVCN");
        col.add("Năm Bắt Đầu");
        col.add("Trạng Thái");
        col.add("Hành Động");

        tableLopQL = new JTable();
        tableLopQL.setBackground(Color.WHITE);
        tableLopQL.setShowHorizontalLines(true);  // hien thi duong ke ngang
        tableLopQL.setGridColor(new Color(230, 230, 230));
        tableLopQL.setRowHeight(40); // chieu cao cua 1 hang

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableLopQL.setDefaultRenderer(Object.class, centerRenderer);

        tableLopQL.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        tableLopQL.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi

                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });

        tableLopQL.setModel(new DefaultTableModel(row, col));
        model = (DefaultTableModel) tableLopQL.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableLopQL);
        scroll.setBounds(20, 70, 1070, 450);

        tableLopQL.setRowHeight(40);
        TableColumnModel tableColumnModel = tableLopQL.getColumnModel();

        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth() / 7 - 108);

        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth() / 7 );

        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth() / 7 + 72);

        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth() / 7 + 120);

        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth() / 7 - 10);

        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth() / 7 );

        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth() / 7 - 70);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                if (tableLopQL.isEditing()) {
                    tableLopQL.getCellEditor().stopCellEditing();
                }
                int result = JOptionPane.showConfirmDialog(tableLopQL, "Bạn có chắc chắn muốn sửa thông tin", "Sửa lớp quản lý", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    TableLopQLController.editDatabase(row);
                } else {
                    model.setRowCount(0);
                    TableLopQLController.getDataLopQL();
                }
            }

            @Override
            public void onDelete(int row) {
                if (tableLopQL.isEditing()) {
                    tableLopQL.getCellEditor().stopCellEditing();
                }
                int result = JOptionPane.showConfirmDialog(tableLopQL, "Bạn có chắc chắn muốn xóa", "Xóa lớp quản lý", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    TableLopQLController.deleteSinhVienDatabaseByMaLopQL(row);
                    TableLopQLController.deleteDatabase(row);
                    TableLopQLController.updateTable();
                }
            }

        };

        this.add(scroll);
        tableLopQL.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tableLopQL.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditer(event));

        DefaultCellEditor cellEditor = new DefaultCellEditor(new JComboBox<>());
        tableLopQL.getColumnModel().getColumn(5).setCellEditor(cellEditor);
        JComboBox cbbTrangThai = (JComboBox) cellEditor.getComponent();
        cbbTrangThai.setBounds(110, 430, 300, 30);
        cbbTrangThai.addItem(StatusType.COMPLETE.toString());
        cbbTrangThai.addItem(StatusType.PROGRESSING.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));

        tableLopQL.addMouseListener(new tableLopQLMouse(tableLopQL));

    }

    public void showTable() {
        ArrayList<String> arr = TableLopQLController.getMaLopHPComplete();
        for(String tmp : arr){
            TableLopQLController.updateTrangThaiLopQL(tmp);
        }
        TableLopQLController.getDataLopQL();
    }

    public void setUpViewButton() {
        btnInsert = new buttonView();
        btnInsert.setBounds(500, 560, 100, 30);
        btnInsert.setText("Insert");
        this.add(btnInsert);
    }

    public void addAction() {
        btnInsert.addActionListener(TableLopQLController);
    }

    public JTable getTableLopQL() {
        return tableLopQL;
    }

    public void setTableLopQL(JTable tableLopQL) {
        this.tableLopQL = tableLopQL;
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

}
