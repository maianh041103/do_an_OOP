
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lib.ConnectDatabase;
import java.sql.*;
import javax.swing.JOptionPane;
import view.LogInView;
import view.Message;
import view.forgotView;
import view.parentView;

public class logInController implements ActionListener{
    private LogInView logInView;

    public logInController(LogInView logInView) {
        this.logInView = logInView;
    }
    
    public void getDataFromLogIn(){
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM dang_nhap";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
            while(rs.next()){
                String username = rs.getString("tai_khoan");
                String password = rs.getString("mat_khau");
                String phone = rs.getString("sdt");
                if(username.equals(logInView.getTxtUser().getText())&&password.equals(String.valueOf(logInView.getTxtPassWord().getPassword()))){
                    parentView ParentView = new parentView();
                    logInView.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(logInView, "Login Fail!", "Error", JOptionPane.ERROR_MESSAGE);
                
                }
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("LogIn")){
            getDataFromLogIn();
            
        }
        else{
            logInView.setVisible(false);
            forgotView ForgotView = new forgotView();
        }
    }
}

