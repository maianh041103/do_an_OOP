
package model;

public class bangDiemModel {
    private String tenSV,maSV,ngaySinh,lopQL, khoa;
    private String maMon,tenMon,maLopHP;
    private int soTinChi;
    private double diemQT,diemThi;

    public bangDiemModel() {
    }

    public bangDiemModel(String tenSV, String maSV, String ngaySinh, String lopQL, String khoa, String maMon, String tenMon, String maLopHP, int soTinChi, double diemQT, double diemThi) {
        this.tenSV = tenSV;
        this.maSV = maSV;
        this.ngaySinh = ngaySinh;
        this.lopQL = lopQL;
        this.khoa = khoa;
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.maLopHP = maLopHP;
        this.soTinChi = soTinChi;
        this.diemQT = diemQT;
        this.diemThi = diemThi;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getLopQL() {
        return lopQL;
    }

    public void setLopQL(String lopQL) {
        this.lopQL = lopQL;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        this.maLopHP = maLopHP;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public double getDiemQT() {
        return diemQT;
    }

    public void setDiemQT(double diemQT) {
        this.diemQT = diemQT;
    }

    public double getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(double diemThi) {
        this.diemThi = diemThi;
    }

    
    
}
