
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import view.tableSinhVienView;

public class tableLopQLMouse extends MouseAdapter{
    private JTable table;

    public tableLopQLMouse(JTable table) {
        this.table = table;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            int row = table.getSelectedRow();
            if(row != -1){
                tableSinhVienView TableSinhVienView = new tableSinhVienView(table.getValueAt(row, 1).toString());
            }
        }
    }
}
