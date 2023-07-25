
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import view.chiTietMonView;

public class tableMonMouse extends MouseAdapter{
    private JTable table;

    public tableMonMouse(JTable table) {
        this.table = table;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            int row = table.getSelectedRow();
            if(row != -1){
                chiTietMonView ChiTietMonView = new chiTietMonView(table.getValueAt(row, 1).toString());
            }
        }
    }
}
