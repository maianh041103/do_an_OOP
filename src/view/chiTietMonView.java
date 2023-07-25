package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.TableActionCellEditer;
import model.TableActionCellRender;
import model.TableActionEvent;

public class chiTietMonView extends JFrame {

    private tableLopHPView TableLopHPView = new tableLopHPView();

    public chiTietMonView(String maMon) {
        TableLopHPView.getTableLopHPController().getLopHPView().getLopHPController().setCount(0);
        setUpViewPanel();
        getDataLopHP(maMon);
        setUpViewTitle(maMon);
        addAction(maMon);
        this.setVisible(true);
    }

    void setUpViewTitle(String maMon) {
        TableLopHPView.getLblTitle().setText("Lớp Học Phần Mã Môn " + maMon);
        buttonView btnBack = new buttonView();
        btnBack.setBounds(950, 0, 80, 30);
        btnBack.setText("Back");
        btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(chiTietMonView.class.getResource("/icon/back.png"))));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }

        });
        if (TableLopHPView.getTableLopHPController().getTrangThaiByMaMon(maMon) == 1) {
            TableLopHPView.remove(TableLopHPView.getBtnInsert());
        }
        TableLopHPView.getLblTitle().setLayout(null);
        TableLopHPView.getLblTitle().add(btnBack);
        TableLopHPView.getTableLopHPController().getLopHPView().getCbbMaMon().removeAllItems();
        TableLopHPView.getTableLopHPController().getLopHPView().getCbbMaMon().addItem(maMon);

    }

    public void setUpViewPanel() {
        this.setSize(1120, 700);
        this.setLocationRelativeTo(null);
        this.add(TableLopHPView);
    }

    public void getDataLopHP(String maMon) {
        TableLopHPView.getTableLopHPController().getDataLopHPByMaMon(maMon);
    }

    public void addAction(String maMon) {
        TableActionEvent event1 = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                if (TableLopHPView.getTableLopHP().isEditing()) {
                    TableLopHPView.getTableLopHP().getCellEditor().stopCellEditing();
                }
                int result = JOptionPane.showConfirmDialog(TableLopHPView.getScroll(), "Bạn có chắc chắn muốn sửa thông tin", "Sửa lớp học phần", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    TableLopHPView.getTableLopHPController().editDatabase(row);
                } else {
                    TableLopHPView.getModel().setRowCount(0);
                    TableLopHPView.getTableLopHPController().getDataLopHPByMaMon(maMon);

                }
            }

            @Override
            public void onDelete(int row) {
                int result = JOptionPane.showConfirmDialog(TableLopHPView.getScroll(), "Bạn có chắc chắn muốn xóa", "Xóa lớp học phần", JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (TableLopHPView.getTableLopHP().isEditing()) {
                        TableLopHPView.getTableLopHP().getCellEditor().stopCellEditing();
                    }
                    TableLopHPView.getTableLopHPController().deleteSVDatabaseByMaLopQL(row);
                    TableLopHPView.getTableLopHPController().deleteDatabase(row);
                    TableLopHPView.getModel().setRowCount(0);
                    TableLopHPView.getTableLopHPController().getDataLopHPByMaMon(maMon);
                }
            }

        };
        TableLopHPView.getTableLopHP().getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        TableLopHPView.getTableLopHP().getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditer(event1));
    }

}
