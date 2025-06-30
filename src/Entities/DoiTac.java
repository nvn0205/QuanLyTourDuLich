package Entities;

public class DoiTac {
    private String maDT;
    private String tenDT;
    private String sdt;
    private String email;
    private String diaChi;
    private String loaiDoiTac;
    private String ghiChu;
    
    public DoiTac() {
    }
    
    public DoiTac(String maDT, String tenDT, String sdt, String email, 
            String diaChi, String loaiDoiTac, String ghiChu) {
        this.maDT = maDT;
        this.tenDT = tenDT;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.loaiDoiTac = loaiDoiTac;
        this.ghiChu = ghiChu;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getLoaiDoiTac() {
        return loaiDoiTac;
    }

    public void setLoaiDoiTac(String loaiDoiTac) {
        this.loaiDoiTac = loaiDoiTac;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
} 