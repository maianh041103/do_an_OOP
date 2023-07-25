
package controller;

import Table.StatusType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.monModel;
import view.monView;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import lib.ConnectDatabase;

public class monController implements ActionListener{
    
    private monView MonView;
    private monModel MonModel;
    int i = 1;
   
    public monController(monView MonView) {
        this.MonView = MonView;
    }
    
    //Get Data Mon
    public void getDataMon(){
        try {
            try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "SELECT * FROM mon_hoc ";
            PreparedStatement stt = connect.prepareStatement(sql);
            ResultSet rs = stt.executeQuery();
           
            
            while(rs.next()){
                Vector<Object>data = new Vector<>();
                data.add(i);
                data.add(rs.getString("ma_mon"));
                data.add(rs.getString("ten_mon"));
                data.add(Integer.parseInt(rs.getString("so_tin_chi")));
                data.add(Integer.parseInt(rs.getString("so_tiet_li_thuyet")));
                data.add(Integer.parseInt(rs.getString("so_tiet_thuc_hanh")));
                int trangThai = Integer.parseInt(rs.getString("trang_thai"));
                if(trangThai == 0){
                    data.add("PROCESSING");
                }
                else
                    data.add("COMPLETE");
               
                MonView.getTableMonView().getModel().addRow(data);
                i++;
            }
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertMonTable(){
        MonView.getTableMonView().getModel().setRowCount(0);
        getDataMon();
    }
    
    //Them du lieu vao database
    public int insertMonDatabase(monModel t){
        int ketQua = 0;
        try {
            Connection connect = ConnectDatabase.getConnection();
            String sql = "INSERT INTO mon_hoc(ma_mon,ten_mon,so_tin_chi,so_tiet_li_thuyet,so_tiet_thuc_hanh,trang_thai) "
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement stt = connect.prepareStatement(sql);
            stt.setString(1, t.getMaMon());
            stt.setString(2,t.getTenMon());
            stt.setInt(3, t.getSoTinChi());
            stt.setInt(4, t.getSoTietLiThuyet());
            stt.setInt(5, t.getSoTietThucHanh());
            stt.setInt(6, t.getTrangThai());
            ketQua = stt.executeUpdate();
            ConnectDatabase.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public boolean isDigit(String s){
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
    
    public void reset(){
        MonView.getTxtMaMon().setText("");
        MonView.getTxtSoTC().setText("");
        MonView.getTxtSoTietLT().setText("");
        MonView.getTxtSoTietTH().setText("");
        MonView.getTxtTenMon().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if(src.equals("Insert")){
            String maMon = MonView.getTxtMaMon().getText();
            String tenMon = MonView.getTxtTenMon().getText();
            if(isDigit(MonView.getTxtSoTC().getText())&&isDigit(MonView.getTxtSoTietLT().getText())&&isDigit(MonView.getTxtSoTietTH().getText())
                    && !maMon.equals("")&& !tenMon.equals("")&& !MonView.getTxtSoTC().getText().equals("")
                    && !MonView.getTxtSoTietLT().getText().equals("")&& !MonView.getTxtSoTietTH().getText().equals("")){
                int soTC = Integer.parseInt(MonView.getTxtSoTC().getText());
                int soTietLT = Integer.parseInt(MonView.getTxtSoTietLT().getText());
                int soTietTH = Integer.parseInt(MonView.getTxtSoTietTH().getText());
                String tmp = MonView.getCbbTrangThai().getSelectedItem().toString();
                int trangThai;
                // 1 la hoan thanh 0 la chua hoan thanh
                if(tmp.equals(StatusType.COMPLETE.toString())){
                    trangThai = 1;
                }
                else{
                    trangThai = 0;
                }
                MonModel = new monModel(maMon,tenMon,soTC,soTietLT,soTietTH,trangThai);
                insertMonDatabase(MonModel);
                reset();
                MonView.setVisible(false);
                
                insertMonTable();

            }
            else{
                JOptionPane.showMessageDialog(MonView.getTableMonView(), "Nhập Lỗi!", tenMon, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
