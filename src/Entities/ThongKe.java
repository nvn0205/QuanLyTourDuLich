package Entities;

public class ThongKe {
    private int tongTour;
    private int tourDaDat;
    private double doanhThu;
    private int tongKhachHang;
    private int tongNhanVien;
    private int tongDoiTac;
    
    public ThongKe() {
    }
    
    public ThongKe(int tongTour, int tourDaDat, double doanhThu, int tongKhachHang, int tongNhanVien, int tongDoiTac) {
        this.tongTour = tongTour;
        this.tourDaDat = tourDaDat;
        this.doanhThu = doanhThu;
        this.tongKhachHang = tongKhachHang;
        this.tongNhanVien = tongNhanVien;
        this.tongDoiTac = tongDoiTac;
    }

    // Getters and Setters
    public int getTongTour() {
        return tongTour;
    }

    public void setTongTour(int tongTour) {
        this.tongTour = tongTour;
    }

    public int getTourDaDat() {
        return tourDaDat;
    }

    public void setTourDaDat(int tourDaDat) {
        this.tourDaDat = tourDaDat;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public int getTongKhachHang() {
        return tongKhachHang;
    }

    public void setTongKhachHang(int tongKhachHang) {
        this.tongKhachHang = tongKhachHang;
    }

    public int getTongNhanVien() {
        return tongNhanVien;
    }

    public void setTongNhanVien(int tongNhanVien) {
        this.tongNhanVien = tongNhanVien;
    }

    public int getTongDoiTac() {
        return tongDoiTac;
    }

    public void setTongDoiTac(int tongDoiTac) {
        this.tongDoiTac = tongDoiTac;
    }
} 