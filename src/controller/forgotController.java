
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import lib.ConnectDatabase;
import view.LogInView;
import view.forgotView;

public class forgotController implements ActionListener{
    private forgotView ForgotView;

    public forgotController(forgotView ForgotView) {
        this.ForgotView = ForgotView;
    }
    
    public void setDataFromForgot(){
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM dang_nhap";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                String username = rs.getString("tai_khoan");
                String password = rs.getString("mat_khau");
                String phone = rs.getString("sdt");
                if(ForgotView.getTxtUser().getText().equals(username)&&ForgotView.getTxtPhone().getText().equals(phone)){
                    JOptionPane.showMessageDialog(ForgotView, "Password : "+password, "Successful", JOptionPane.INFORMATION_MESSAGE);
                    ForgotView.setVisible(false);
                    LogInView logInView = new LogInView();
                    logInView.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(ForgotView, "Account is not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setDataFromForgot();
    }
    
}
