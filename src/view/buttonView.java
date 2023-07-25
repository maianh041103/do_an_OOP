
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class buttonView extends JButton{
    
    public buttonView(){
        init();
        this.setVisible(true);
    }
    
    public void init(){
        setBounds(500,20,120,30);
        setBackground(new Color(250,250,250));
        setFont(new Font("sansserif", 1, 14));
        setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(monView.class.getResource("/icon/insert.png"))));
        setBorder(new RoundedBorder(10,new Color(150, 150, 150)));
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                setBorder(new RoundedBorder(10,new Color(3, 155, 216)));
                setForeground(new Color(3, 155, 216));
            }
            
            @Override
            public void mouseExited(MouseEvent me){
                setBorder(new RoundedBorder(10,new Color(150, 150, 150)));
                setForeground(new Color(0, 0, 0));
            }
        });
    }
}
