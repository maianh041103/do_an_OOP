package model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditer1 extends DefaultCellEditor {

    private TableActionEvent1 event;

    public TableActionCellEditer1(TableActionEvent1 event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object o, boolean bln, int row, int col) {
        PanelAction1 action = new PanelAction1();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }   
}