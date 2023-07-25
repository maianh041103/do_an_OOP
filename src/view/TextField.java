
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class TextField extends JTextField{
    
    private boolean mouseOver = false;
   

    public TextField() {
        setBorder(new EmptyBorder(5, 3, 5, 3));
        setSelectionColor(new Color(76,204,255));
        addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            mouseOver = true;
            repaint();
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            mouseOver = false;
            repaint();
        }
        });
    }

    
     @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();
        if (mouseOver) {
            g2.setColor(new Color(3, 155, 216));
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        g2.fillRect(2, height - 1, width - 4, 1);
        g2.dispose();
    }

    
}



