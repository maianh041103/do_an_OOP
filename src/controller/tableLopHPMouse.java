
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import view.tableLopHPView;
import view.tableSinhVienLopHPView;


public class tableLopHPMouse extends MouseAdapter{
    private JTable table;
    private tableLopHPView TableLopHPView;

    public tableLopHPMouse(JTable table,tableLopHPView TableLopHPView) {
        this.table = table;
        this.TableLopHPView = TableLopHPView;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            int row = table.getSelectedRow();
            if(row != -1){
                tableSinhVienLopHPView TableSinhVienLopHPView = new tableSinhVienLopHPView(table.getValueAt(row, 2).toString(),TableLopHPView);
            }
        }
    }
}
