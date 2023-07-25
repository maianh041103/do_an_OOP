
package menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;


public class Header extends javax.swing.JPanel {
    
    private JLabel lblHeader;

    public Header() {
        setUpViewHeader();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setPaint(new GradientPaint(0,0,Color.decode("#1CB5E0"),0,getWidth(),Color.decode("#000046")));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    public void setUpViewHeader(){
        lblHeader = new JLabel();
        lblHeader.setText("Quản Lý Sinh Viên");
        lblHeader.setBounds(30, 0, 300, 75);
        lblHeader.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255,255,255));
        lblHeader.setIconTextGap(20);
        lblHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png")));
        add(lblHeader);
    }
    

   
}
