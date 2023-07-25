
package model;

import java.text.DecimalFormat;
import java.util.Vector;

public class monModel{
    private String maMon,tenMon;
    private int soTinChi,soTietLiThuyet,soTietThucHanh,trangThai;

    public monModel() {
    }

    public monModel(String maMon, String tenMon, int soTinChi, int soTietLiThuyet, int soTietThucHanh, int trangThai) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
        this.soTietLiThuyet = soTietLiThuyet;
        this.soTietThucHanh = soTietThucHanh;
        this.trangThai = trangThai;
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

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public int getSoTietLiThuyet() {
        return soTietLiThuyet;
    }

    public void setSoTietLiThuyet(int soTietLiThuyet) {
        this.soTietLiThuyet = soTietLiThuyet;
    }

    public int getSoTietThucHanh() {
        return soTietThucHanh;
    }

    public void setSoTietThucHanh(int soTietThucHanh) {
        this.soTietThucHanh = soTietThucHanh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Vector<Object> toRowTable() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        Vector<Object> data = new Vector<>();
        data.add(maMon);
        data.add(tenMon);
        data.add(soTinChi);
        data.add(soTietLiThuyet);
        data.add(soTietThucHanh);
        data.add(trangThai);
        return data;
    }   
}
