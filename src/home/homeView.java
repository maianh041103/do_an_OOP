
package home;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.CustomLabel;

public class homeView extends JPanel{
    
    private JPanel pn,pnLeft,pnRight;
    private JLabel lblTitle,lblLeft1,lblLeft2,lblRight1,lblRight2,lblRight3,lblRight4,lblRight5;
    private CustomLabel lblImageLeft,lblImageRight;
    private Font font;
    private Font font1;
    
    
    public homeView(){
        initComponents();
        setBackground(new Color(255, 255, 255));
        slideshow1.initSlideshow(new Slide1(), new Slide2(), new Slide3());
    }
    
    private void initComponents() {
        
        this.setLayout(null);
        
        slideshow1 = new Slideshow();
        slideshow1.setBounds(0, 0, 1100, 300);

        this.add(slideshow1);
       
        pn = new JPanel();
        pn.setLayout(null);
        pn.setBounds(0, 300, 1100, 375);
        pn.setBackground(Color.white);
        
        lblTitle = new JLabel("TẠI SAO CHỌN TRƯỜNG ĐẠI HỌC XÂY DỰNG HÀ NỘI",JLabel.HORIZONTAL);
        lblTitle.setBounds(0, 20, 1100, 50);
        font = new Font("Aria",Font.BOLD,20);
        lblTitle.setFont(font);
        pn.add(lblTitle);   
        
        font1 = new Font("Aria",Font.BOLD,15);
        pnLeft = new JPanel();
        pnLeft.setBounds(0, 70, 550, 325);
        pnLeft.setBackground(Color.white);
        pnLeft.setLayout(null);
        
        lblImageLeft = new CustomLabel();
        lblImageLeft.setBounds(175, 0, 150, 130);
        lblImageLeft.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(homeView.class.getResource("/icon/RVB - Logo Hce_res UK.png"))));
        lblLeft1 = new JLabel("CƠ SỞ GIÁO DỤC ĐẠI HỌC ĐẠT CHUẨN",JLabel.HORIZONTAL);        
        lblLeft1.setBounds(100,135 , 300, 40);
        lblLeft1.setFont(font1);
        lblLeft2 = new JLabel("KIỂM ĐỊNH QUỐC TẾ",JLabel.HORIZONTAL);        
        lblLeft2.setBounds(100,175 , 300, 50);
        lblLeft2.setFont(font1);
        pnLeft.add(lblImageLeft);
        pnLeft.add(lblLeft1);
        pnLeft.add(lblLeft2);
        
        
        pnRight = new JPanel();
        pnRight.setBounds(550, 70, 550, 325);
        pnRight.setBackground(Color.white);
        pnRight.setLayout(null);
        
        lblImageRight = new CustomLabel();
        lblImageRight.setBounds(175, 20, 150, 100);
        lblImageRight.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(homeView.class.getResource("/icon/logo-UPM.png"))));
        lblRight1 = new JLabel("CƠ SỞ GIÁO DỤC ĐẠI HỌC ĐẠT CHUẨN",JLabel.HORIZONTAL);        
        lblRight1.setBounds(100,135 , 300, 40);
        lblRight1.setFont(font1);
        lblRight2 = new JLabel(); 
        lblRight2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(homeView.class.getResource("/icon/star-30.png"))));
        lblRight2.setBounds(175,175 , 50, 50);
        lblRight3 = new JLabel(); 
        lblRight3.setBounds(225,175 , 50, 50);
        lblRight3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(homeView.class.getResource("/icon/star-30.png"))));
        lblRight4 = new JLabel(); 
        lblRight4.setBounds(275,175 , 50, 50);
        lblRight4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(homeView.class.getResource("/icon/star-30.png"))));
        lblRight5 = new JLabel(); 
        lblRight5.setBounds(325,175 , 50, 50);
        lblRight5.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(homeView.class.getResource("/icon/star-30.png"))));
        
        
        pnRight.add(lblImageRight);
        pnRight.add(lblRight1);
        pnRight.add(lblRight2);
        pnRight.add(lblRight3);
        pnRight.add(lblRight4);
        pnRight.add(lblRight5);
        
        pn.add(pnLeft);
        pn.add(pnRight);
        this.add(pn);
        
    }
    
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        slideshow1.next();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        slideshow1.back();
    }
    
    private Slideshow slideshow1;
}
