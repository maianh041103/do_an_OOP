
package model;

public class sinhVienLopHPModel {
    private String maLopHP,maSV,tenSV,maLopQL;
    private double dqt,diemThi;
    private int trangThai;
    
    public sinhVienLopHPModel() {
    }
    
    public sinhVienLopHPModel(String maLopHP, String maSV, String tenSV, String maLopQL, double dqt, double diemThi) {
        this.maLopHP = maLopHP;
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.maLopQL = maLopQL;
        this.dqt = dqt;
        this.diemThi = diemThi;
        this.trangThai = 0;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        this.maLopHP = maLopHP;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getMaLopQL() {
        return maLopQL;
    }

    public void setMaLopQL(String maLopQL) {
        this.maLopQL = maLopQL;
    }

    public double getDqt() {
        return dqt;
    }

    public void setDqt(double dqt) {
        this.dqt = dqt;
    }

    public double getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(double diemThi) {
        this.diemThi = diemThi;
    }

}
