package Entities;

import java.util.Date;

public class Tour {
    private String maTour;
    private String tenTour;
    private String diaDiem;
    private Date ngayKhoiHanh;
    private Date ngayKetThuc;
    private int soNguoi;
    private double gia;
    private String trangThai;
    private String moTa;
    
    public Tour() {
    }
    
    public Tour(String maTour, String tenTour, String diaDiem, Date ngayKhoiHanh, 
            Date ngayKetThuc, int soNguoi, double gia, String trangThai, String moTa) {
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.diaDiem = diaDiem;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.ngayKetThuc = ngayKetThuc;
        this.soNguoi = soNguoi;
        this.gia = gia;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
} 