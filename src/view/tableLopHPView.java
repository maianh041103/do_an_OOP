package view;

import Table.StatusType;
import Table.TableHeader;
import controller.tableLopHPController;
import controller.tableLopHPMouse;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomLabel;
import model.TableActionCellEditer;
import model.TableActionCellRender;
import model.TableActionEvent;
import scroll.ScrollPaneWin11;

public class tableLopHPView extends JPanel {

    private JTable tableLopHP;
    private ScrollPaneWin11 scroll;
    private buttonView btnInsert;
    private CustomLabel lblTitle;
    private DefaultTableModel model;
    private TableActionEvent event;
    private tableLopHPController TableLopHPController = new tableLopHPController(this);
//    int slRow;
//    private int selectionRow = -1;
//    private int selectionCol = -1;

    public tableLopHPView() {
        setUpViewPanel();
        setUpViewTitle();
        setUpViewButton();
        addAction();
        setTable();
        // showTable();

        this.setVisible(true);

    }

    void setUpViewPanel() {
        this.setLayout(null);
        this.setBackground(new Color(250, 250, 250));
    }

    void setUpViewTitle() {
        lblTitle = new CustomLabel();
        lblTitle.setText("Lớp Học Phần");
        lblTitle.setBackground(Color.white);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/course.png"))));
        lblTitle.setBounds(20, 20, 1070, 30);
        lblTitle.setFont(new Font("sansserif", 1, 15));

        this.add(lblTitle);
    }

    void setTable() {

        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object> col = new Vector<>();
        col.add("STT");
        col.add("Mã Môn");
        col.add("Mã Lớp HP");
        col.add("Tên Lớp HP");
        col.add("Giảng Viên");
        col.add("Học Kì");
        col.add("Trạng Thái");
        col.add("Hành Động");

        tableLopHP = new JTable();
        tableLopHP.setBackground(Color.WHITE);
        tableLopHP.setShowHorizontalLines(true);  // hien thi duong ke ngang
        tableLopHP.setGridColor(new Color(230, 230, 230));
        tableLopHP.setRowHeight(40); // chieu cao cua 1 hang

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableLopHP.setDefaultRenderer(Object.class, centerRenderer);

        tableLopHP.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        tableLopHP.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi

                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });

        tableLopHP.setModel(new DefaultTableModel(row, col));
        model = (DefaultTableModel) tableLopHP.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableLopHP);
        scroll.setBounds(20, 70, 1070, 450);

        tableLopHP.setRowHeight(40);
        TableColumnModel tableColumnModel = tableLopHP.getColumnModel();

        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth() / 8 - 90);

        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth() / 8 - 10);

        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth() / 8 - 10);

        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth() / 8 + 100);

        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth() / 8 + 80);

        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth() / 8 + 10 );

        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth() / 8 - 30);
        
        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth() / 8 - 50);

        event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                if (tableLopHP.isEditing()) {
                    tableLopHP.getCellEditor().stopCellEditing();
                }
                int result = JOptionPane.showConfirmDialog(scroll, "Bạn có chắc chắn muốn sửa thông tin", "Sửa lớp học phần", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    TableLopHPController.editDatabase(row);
                } else {
                    model.setRowCount(0);
                    TableLopHPController.getDataLopHP();
                    
                }
            }

            @Override
            public void onDelete(int row) {
                int result = JOptionPane.showConfirmDialog(scroll, "Bạn có chắc chắn muốn xóa", "Xóa lớp học phần", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (tableLopHP.isEditing()) {
                        tableLopHP.getCellEditor().stopCellEditing();
                    }
                    TableLopHPController.deleteSVDatabaseByMaLopQL(row);
                    TableLopHPController.deleteDatabase(row);
                    TableLopHPController.updateTable();
                }
            }

        };

        this.add(scroll);
        tableLopHP.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        tableLopHP.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditer(event));

        DefaultCellEditor cellEditor = new DefaultCellEditor(new JComboBox<>());
        tableLopHP.getColumnModel().getColumn(6).setCellEditor(cellEditor);
        JComboBox cbbTrangThai = (JComboBox) cellEditor.getComponent();
        cbbTrangThai.setBounds(110, 430, 300, 30);
        cbbTrangThai.addItem(StatusType.COMPLETE.toString());
        cbbTrangThai.addItem(StatusType.PROGRESSING.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow, 30));

        tableLopHP.addMouseListener(new tableLopHPMouse(tableLopHP,this));

    }

//    public void showTable(){
//        TableLopHPController.getDataLopHP();
//    }
    public void setUpViewButton() {
        btnInsert = new buttonView();
        btnInsert.setBounds(500, 560, 100, 30);
        btnInsert.setText("Insert");
        this.add(btnInsert);
    }

    public TableActionEvent getEvent() {
        return event;
    }

    public void setEvent(TableActionEvent event) {
        this.event = event;
    }

    public void addAction() {
        btnInsert.addActionListener(TableLopHPController);
    }

    public tableLopHPController getTableLopHPController() {
        return TableLopHPController;
    }

    public void setTableLopHPController(tableLopHPController TableLopHPController) {
        this.TableLopHPController = TableLopHPController;
    }

    public JTable getTableLopHP() {
        return tableLopHP;
    }

    public void setTableLopHP(JTable tableLopHP) {
        this.tableLopHP = tableLopHP;
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
