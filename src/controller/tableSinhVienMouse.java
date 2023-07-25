
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import view.bangDiemView;

public class tableSinhVienMouse extends MouseAdapter{
    private JTable table;

    public tableSinhVienMouse(JTable table) {
        this.table = table;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            int row = table.getSelectedRow();
            if(row != -1){
                bangDiemView BangDiemView = new bangDiemView(table.getValueAt(row, 2).toString());
            }
        }
    }
}
