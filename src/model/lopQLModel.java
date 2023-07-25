
package model;


public class lopQLModel {
    private String maLopQL, tenKhoa,gvcn,hocKi;
    private int trangThai;

    public lopQLModel() {
    }

    public lopQLModel(String maLopQL, String tenKhoa, String gvcn, String hocKi, int trangThai) {
        this.maLopQL = maLopQL;
        this.tenKhoa = tenKhoa;
        this.gvcn = gvcn;
        this.hocKi = hocKi;
        this.trangThai = trangThai;
    }

    public String getMaLopQL() {
        return maLopQL;
    }

    public void setMaLopQL(String maLopQL) {
        this.maLopQL = maLopQL;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getGvcn() {
        return gvcn;
    }

    public void setGvcn(String gvcn) {
        this.gvcn = gvcn;
    }

    public String getHocKi() {
        return hocKi;
    }

    public void setHocKi(String hocKi) {
        this.hocKi = hocKi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
