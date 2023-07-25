
package model;

import java.sql.Date;

public class sinhVienModel {
    private String maSv,tenSv;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String diaChi;
    private int trangThai;
    private String maLopQL;

    public sinhVienModel() {
    }

    public sinhVienModel(String maSv, String tenSv, Date ngaySinh, boolean gioiTinh, String diaChi, int trangThai, String maLopQL) {
        this.maSv = maSv;
        this.tenSv = tenSv;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.maLopQL = maLopQL;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaLopQL() {
        return maLopQL;
    }

    public void setMaLopQL(String maLopQL) {
        this.maLopQL = maLopQL;
    }
    
}
