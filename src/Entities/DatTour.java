package Entities;

import java.util.Date;

public class DatTour {
    private String maDatTour;
    private String maKH;
    private String maTour;
    private Date ngayDat;
    private int soLuongNguoi;
    private double tongTien;
    private double soTienDaThanhToan;
    private String maDT;
    private String maNV;
    private String trangThai;
    
    public DatTour() {
        this.ngayDat = new Date();
        this.trangThai = "Chờ xác nhận";
        this.soTienDaThanhToan = 0.0;
    }
    
    public String getMaDatTour() {
        return maDatTour;
    }
    
    public void setMaDatTour(String maDatTour) {
        this.maDatTour = maDatTour;
    }
    
    public String getMaKH() {
        return maKH;
    }
    
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
    
    public String getMaTour() {
        return maTour;
    }
    
    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }
    
    public Date getNgayDat() {
        return ngayDat;
    }
    
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }
    
    public int getSoLuongNguoi() {
        return soLuongNguoi;
    }
    
    public void setSoLuongNguoi(int soLuongNguoi) {
        this.soLuongNguoi = soLuongNguoi;
    }
    
    public double getTongTien() {
        return tongTien;
    }
    
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    public double getSoTienDaThanhToan() {
        return soTienDaThanhToan;
    }
    
    public void setSoTienDaThanhToan(double soTienDaThanhToan) {
        this.soTienDaThanhToan = soTienDaThanhToan;
    }
    
    public String getMaDT() {
        return maDT;
    }
    
    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }
    
    public String getMaNV() {
        return maNV;
    }
    
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    public String getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
} 