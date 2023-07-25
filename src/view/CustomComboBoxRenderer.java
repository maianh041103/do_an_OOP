
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class CustomComboBoxRenderer extends BasicComboBoxRenderer {
    private Color borderColor;
    private int height;

    public CustomComboBoxRenderer(Color borderColor, int height) {
        this.borderColor = borderColor;
        this.height = height;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        list.setFixedCellHeight(height);
        renderer.setForeground(new Color(102, 102, 102));
        if (isSelected) {
            renderer.setBackground(new Color(239, 244, 255));
            
            
        } else {
           renderer.setBackground(Color.white);
           
        }

        if (cellHasFocus) {
            ((JComponent) renderer).setBorder(new LineBorder(borderColor));
        } else {
            ((JComponent) renderer).setBorder(null);
        }

        return renderer;
    }
    
    //Chieu cao cung khung keo
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.height = height;
        return size;
    }
}