
package lib;

import java.sql.*;

public class ConnectDatabase {
    public static Connection getConnection(){
        Connection connect = null;
        String url = "jdbc:mysql://localhost:3306/do_an";
        String username = "root";
        String password = "maianhhocdot23";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }
    
    public static void closeConnection(Connection connect){
        try {
            if(connect != null){
                connect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

