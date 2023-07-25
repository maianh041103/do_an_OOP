
package model;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.*;

public class Icon extends JPanel{
    
    public Icon() {
        this.setSize(100,40);
        this.setBackground(Color.white);
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Icon.class.getResource("/icon/tick.png"))));
        this.add(lblIcon);
    }
    
}
