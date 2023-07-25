
package view;

import controller.logInController;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class LogInView extends JFrame{
    private TextFieldLogIn txtUser;
    private PasswordField txtPassWord;
    private JPanel pn;
    private JLabel lblLogIn;
    private Font font;
    private JButton btnLogIn,btnForgot;
    
    private logInController LogInController = new logInController(this);

    public LogInView() {
        setUpViewFrame();
        setUpView();
        setUpViewButton();
        
        this.setVisible(true);
    }
    
    public void setUpViewFrame(){
        this.setTitle("LogIn");
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
        lblLogIn = new JLabel("LogIn",JLabel.HORIZONTAL);
        lblLogIn.setBounds(200, 30, 80, 40);
        lblLogIn.setFont(font);
        lblLogIn.setForeground(new Color(51,153,255));
        
        txtUser = new TextFieldLogIn("UserName");
        txtUser.setBounds(60,100,350,50);
        
        txtPassWord = new PasswordField("Password");
        txtPassWord.setBounds(60,180,350,50);
        
        pn.add(lblLogIn);
        pn.add(txtUser);
        pn.add(txtPassWord);
    }
    
    public void setUpViewButton(){
        btnLogIn = new JButton("LogIn");
        btnLogIn.setBackground(new Color(51, 154, 240));
        btnLogIn.setBounds(60, 300, 350,40);
        btnLogIn.setForeground(Color.white);
        btnLogIn.setFont(new Font("Arial", Font.BOLD, 20));
        btnLogIn.addActionListener(LogInController);
        
        btnForgot = new JButton("Forgot Password?");
        Border borderForgot = BorderFactory.createLineBorder(new Color(51, 154, 240), 0);
        btnForgot.setBounds(280, 380, 150, 32);
        btnForgot.setBorder(borderForgot);
        btnForgot.setForeground(new Color(51, 154, 240));
        btnForgot.setBackground(Color.WHITE);
        btnForgot.addActionListener(LogInController);
        
        pn.add(btnForgot);
        pn.add(btnLogIn);
    }
    
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private boolean isLogin;
    private javax.swing.JLayeredPane bg;
    
    public void init() {
        layout = new MigLayout("fill, insets 0");
        bg.setLayout(layout);
        
        showMessage(Message.MessageType.SUCCESS, "TestMessage");

    }

    public void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }
    
    
    @SuppressWarnings("unchecked")
    
    public void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public JLayeredPane getBg() {
        return bg;
    }

    public void setBg(JLayeredPane bg) {
        this.bg = bg;
    }

    public TextFieldLogIn getTxtUser() {
        return txtUser;
    }

    public void setTxtUser(TextFieldLogIn txtUser) {
        this.txtUser = txtUser;
    }

    public PasswordField getTxtPassWord() {
        return txtPassWord;
    }

    public void setTxtPassWord(PasswordField txtPassWord) {
        this.txtPassWord = txtPassWord;
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
