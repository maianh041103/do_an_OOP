package model;

public interface TableActionEvent1 {
    
    public void onEdit( int row);
    public void onDelete( int row);
    public void onLock(int row);
}
