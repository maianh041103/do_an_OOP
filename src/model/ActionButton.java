package model;

import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton{

    private boolean mousePress;
    public ActionButton() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePress = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePress = false;
            }  
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int x = (width - size)/2;
        int y = (height - size)/2;
        if(mousePress){
            g2.setColor(new Color(225, 225, 225));
        }
        else{
            g2.setColor(new Color(245, 245, 245));
        }
        g2.fill(new Ellipse2D.Double(x, y, size, size));
        super.paintComponent(g);
        g2.dispose();
    }
    
    public static void main(String[] args) {
        
    }
}
