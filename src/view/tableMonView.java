
package view;


import Table.StatusType;
import Table.TableHeader;
import controller.tableMonController;
import controller.tableMonMouse;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import scroll.ScrollPaneWin11;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.CustomLabel;
import model.TableActionCellEditer;
import model.TableActionCellRender;
import model.TableActionEvent;

public class tableMonView extends JPanel{
    
    private JTable tableMon ;
    private ScrollPaneWin11 scroll;
    private buttonView btnInsert;
    private CustomLabel lblTitle;
    private DefaultTableModel model; 
//    int slRow;
//    private int selectionRow = -1;
//    private int selectionCol = -1;
    
    private tableMonController TableMonController = new tableMonController(this);
    
    public tableMonView(){
        setUpViewPanel();
        setUpViewTitle();
        setUpViewButton();
        addAction();
        setTable();
        //showTable();
   
        this.setVisible(true);
    }

    
    void setUpViewPanel(){
        this.setLayout(null);
        this.setBackground(new Color(250,250,250));
    }
    
    void setUpViewTitle(){
        lblTitle = new CustomLabel();
        lblTitle.setText("Môn Học");
        lblTitle.setBackground(Color.white);
        lblTitle.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/book.png"))));
        lblTitle.setBounds(20, 20, 1070, 30);
        lblTitle.setFont(new Font("sansserif", 1, 15));
        
        this.add(lblTitle);
    }
    
    void setTable(){
        
        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        Vector<Object>col = new Vector<>();
        col.add("STT");
        col.add("Mã Môn");
        col.add("Tên Môn");
        col.add("Số Tín Chỉ");
        col.add("Số Tiết LT");
        col.add("Số Tiết TH");
        col.add("Trạng Thái");
        col.add("Hành Động");
        
        tableMon = new JTable(); 
        tableMon.setBackground( Color.WHITE);
        tableMon.setShowHorizontalLines(true);  // hien thi duong ke ngang
        tableMon.setGridColor(new Color(230, 230, 230));
        tableMon.setRowHeight(40); // chieu cao cua 1 hang
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableMon.setDefaultRenderer(Object.class, centerRenderer);

        tableMon.getTableHeader().setReorderingAllowed(false); // khong cho cac cot di chuyen
        tableMon.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  // xet dinh dang cho phan dau bang
            @Override // tham so truyen vao : JTable chua o can hien thi, gia tri o trong bang, isSelect? , isFocus, chi so hang, chi so cot
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + ""); // Khoi tao voi chi muc la chuoi
               
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        
        tableMon.setModel(new DefaultTableModel(row,col));
        model = (DefaultTableModel)tableMon.getModel();
        scroll = new ScrollPaneWin11();
        scroll.setViewportView(tableMon);
        scroll.setBounds(20, 70, 1070, 450);
        
        tableMon.setRowHeight(40);
        TableColumnModel tableColumnModel = tableMon.getColumnModel();
        
        TableColumn column0 = tableColumnModel.getColumn(0);
        column0.setPreferredWidth(scroll.getWidth()/8 - 90);
        
        TableColumn column1 = tableColumnModel.getColumn(1);
        column1.setPreferredWidth(scroll.getWidth()/8 + 30);
        
        TableColumn column2 = tableColumnModel.getColumn(2);
        column2.setPreferredWidth(scroll.getWidth()/8 + 150);
        
        TableColumn column3 = tableColumnModel.getColumn(3);
        column3.setPreferredWidth(scroll.getWidth()/8 - 10);
        
        TableColumn column4 = tableColumnModel.getColumn(4);
        column4.setPreferredWidth(scroll.getWidth()/8 - 10);
        
        TableColumn column5 = tableColumnModel.getColumn(5);
        column5.setPreferredWidth(scroll.getWidth()/8 - 10);
        
        TableColumn column6 = tableColumnModel.getColumn(6);
        column6.setPreferredWidth(scroll.getWidth()/8 - 10);
        
        TableColumn column7 = tableColumnModel.getColumn(7);
        column7.setPreferredWidth(scroll.getWidth()/8 - 50);
        

        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int result = JOptionPane.showConfirmDialog(scroll, "Bạn có chắc chắn muốn sửa thông tin", "Sửa môn học", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (tableMon.isEditing()) {
                        tableMon.getCellEditor().stopCellEditing();
                    }
                    TableMonController.editDatabase(row);
                    for(int i=0;i<tableMon.getModel().getRowCount();i++){
                        if(tableMon.getModel().getValueAt(i, 6).toString().equals("COMPLETE")){
                            TableMonController.editLopHPDatabase(tableMon.getModel().getValueAt(row, 1).toString());
                        }
                    }   
                }
                else{
                    model.setRowCount(0);
                    TableMonController.getDataMon();
                }    
            }

            @Override
            public void onDelete(int row) {
                int result = JOptionPane.showConfirmDialog(scroll, "Bạn có chắc chắn muốn xóa", "Xóa môn học", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (tableMon.isEditing()) {
                        tableMon.getCellEditor().stopCellEditing();
                    }
                    ArrayList<String> maLopHP = TableMonController.getMaLopHPByMaMon(row);
                    for(String tmp : maLopHP){
                        TableMonController.deleteSinhVienDatabaseByMaLopHP(tmp);
                    }
                    TableMonController.deleteCtKhungDatabase(row);
                    TableMonController.deleteLopHPDatabaseByMaMon(row);
                    TableMonController.deleteDatabase(row);
                    TableMonController.updateTable();
                }
            }

        };
        
        this.add(scroll); 
        tableMon.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        tableMon.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditer(event));
        
        DefaultCellEditor cellEditor = new DefaultCellEditor(new JComboBox<>());
        tableMon.getColumnModel().getColumn(6).setCellEditor(cellEditor);
        JComboBox cbbTrangThai = (JComboBox) cellEditor.getComponent();
        cbbTrangThai.setBounds(110,430,300,30);
        cbbTrangThai.addItem(StatusType.COMPLETE.toString());
        cbbTrangThai.addItem(StatusType.PROGRESSING.toString());
        cbbTrangThai.setBackground(Color.white);
        cbbTrangThai.setActionCommand("cbbTrangThai");
        cbbTrangThai.setRenderer(new CustomComboBoxRenderer(Color.yellow,30));
        
        tableMon.addMouseListener(new tableMonMouse(tableMon));
        
    }
    
    public void showTable(){
        TableMonController.getDataMon();
    }
    
    public void setUpViewButton(){
        btnInsert = new buttonView();
        btnInsert.setBounds(500,560,100,30);
        btnInsert.setText("Insert");
        this.add(btnInsert);
    }
    
    public void addAction(){
        btnInsert.addActionListener(TableMonController);      
    }

    public tableMonController getTableMonController() {
        return TableMonController;
    }

    public JTable getTableMon() {
        return tableMon;
    }

    public void setTableMon(JTable tableMon) {
        this.tableMon = tableMon;
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

    public buttonView getBtnInsert() {
        return btnInsert;
    }

    public void setBtnInsert(buttonView btnInsert) {
        this.btnInsert = btnInsert;
    }
  
}
