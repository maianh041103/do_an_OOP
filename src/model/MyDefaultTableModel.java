package model;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel  extends DefaultTableModel  {
    
    private boolean[][] editableCells;

    public MyDefaultTableModel(Vector<Vector<Object>> data, Vector<Object> columnNames) {
        super(data, columnNames);
        int rowCount = data.size();
        int columnCount = columnNames.size();
        editableCells = new boolean[rowCount + 1000][columnCount + 1000];
        // Gán giá trị mặc định cho editableCells
        for (int i = 0; i < rowCount + 1000; i++) {
            for (int j = 0; j < columnCount + 1000; j++) {
                editableCells[i][j] = true; // Mặc định tất cả các ô có thể chỉnh sửa
            }
        }
    }
    
    @Override
    public int getRowCount() {
        return super.getRowCount(); // hoặc trả về một biến số đếm dòng của dữ liệu
    }

    public void setCellEditable(int row, int column, boolean editable) {
        editableCells[row][column] = editable;
        this.fireTableCellUpdated(row, column);
    }
   
    @Override
    public boolean isCellEditable(int row, int column) {
        return editableCells[row][column];
    }
}
