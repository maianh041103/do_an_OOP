
package model;

public class lopHPModel {
    private String maMon,maLopHP,tenLopHP,giangVien;
    private int hocKi, nam, trangThai;

    public lopHPModel() {
    }

    public lopHPModel(String maMon, String maLopHP, String tenLopHP, String giangVien, int hocKi, int nam, int trangThai) {
        this.maMon = maMon;
        this.maLopHP = maLopHP;
        this.tenLopHP = tenLopHP;
        this.giangVien = giangVien;
        this.hocKi = hocKi;
        this.nam = nam;
        this.trangThai = trangThai;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        this.maLopHP = maLopHP;
    }

    public String getTenLopHP() {
        return tenLopHP;
    }

    public void setTenLopHP(String tenLopHP) {
        this.tenLopHP = tenLopHP;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }

    public int getHocKi() {
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    
}
