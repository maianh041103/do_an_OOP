
package view;

import controller.forgotController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class forgotView extends JFrame{
    private TextFieldLogIn txtUser,txtPhone;
    private JPanel pn;
    private JLabel lblLogIn;
    private Font font;
    private JButton btnLogIn,btnForgot;
    
    private forgotController ForgotController = new forgotController(this);

    public forgotView() {
        setUpViewFrame();
        setUpView();
        setUpViewButton();
        this.setVisible(true);
    }
    
    public void setUpViewFrame(){
        this.setTitle("Forgot");
        this.setSize(500,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pn = new JPanel();
        pn.setBackground(Color.white);
        pn.setLayout(null);
        this.add(pn);
        
        font = new Font("Arial", Font.BOLD, 24);
    }
    
    public void setUpView(){
        lblLogIn = new JLabel("Forgot",JLabel.HORIZONTAL);
        lblLogIn.setBounds(200, 30, 80, 40);
        lblLogIn.setFont(font);
        lblLogIn.setForeground(new Color(51,153,255));
        
        txtUser = new TextFieldLogIn("UserName");
        txtUser.setBounds(60,100,350,50);
        
        txtPhone = new TextFieldLogIn("Phone");
        txtPhone.setBounds(60,180,350,50);
        
        pn.add(lblLogIn);
        pn.add(txtUser);
        pn.add(txtPhone);
    }
    
    public void setUpViewButton(){
        btnLogIn = new JButton("Next");
        btnLogIn.setBackground(new Color(51, 154, 240));
        btnLogIn.setBounds(60, 300, 350,40);
        btnLogIn.setForeground(Color.white);
        btnLogIn.setFont(new Font("Arial", Font.BOLD, 20));
        btnLogIn.addActionListener(ForgotController);
        
        pn.add(btnLogIn);
    }

    public TextFieldLogIn getTxtUser() {
        return txtUser;
    }

    public void setTxtUser(TextFieldLogIn txtUser) {
        this.txtUser = txtUser;
    }

    public TextFieldLogIn getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(TextFieldLogIn txtPhone) {
        this.txtPhone = txtPhone;
    }

    public JLabel getLblLogIn() {
        return lblLogIn;
    }

    public void setLblLogIn(JLabel lblLogIn) {
        this.lblLogIn = lblLogIn;
    }

    public JButton getBtnLogIn() {
        return btnLogIn;
    }

    public void setBtnLogIn(JButton btnLogIn) {
        this.btnLogIn = btnLogIn;
    }

    public JButton getBtnForgot() {
        return btnForgot;
    }

    public void setBtnForgot(JButton btnForgot) {
        this.btnForgot = btnForgot;
    }
    
    
}
