package BLL;

import DAL.NhanVienDAL;
import Entities.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NhanVienBLL {
    public static List<NhanVien> getAll() {
        return NhanVienDAL.getAll();
    }
    
    public static List<String> getAllMaNV() {
        List<String> listMaNV = new ArrayList<>();
        List<NhanVien> listNV = NhanVienDAL.getAll();
        for (NhanVien nv : listNV) {
            listMaNV.add(nv.getMaNV());
        }
        return listMaNV;
    }
    
    public static String add(NhanVien nv) {
        // Validate dữ liệu
        String error = validateNhanVien(nv);
        if (error != null) {
            return error;
        }
        
        // Thêm nhân viên
        return NhanVienDAL.add(nv);
    }
    
    public static String update(NhanVien nv) {
        // Validate dữ liệu
        String error = validateNhanVien(nv);
        if (error != null) {
            return error;
        }
        
        // Cập nhật nhân viên
        return NhanVienDAL.update(nv);
    }
    
    public static String delete(String maNV) {
        return NhanVienDAL.delete(maNV);
    }
    
    public static List<NhanVien> search(String keyword) {
        return NhanVienDAL.search(keyword);
    }
    
    public static String generateNewMaNV() {
        return NhanVienDAL.generateNewMaNV();
    }
    
    private static String validateNhanVien(NhanVien nv) {
        // Kiểm tra mã nhân viên
        if (nv.getMaNV() == null || nv.getMaNV().trim().isEmpty()) {
            return "Mã nhân viên không được để trống";
        }
        
        // Kiểm tra họ tên
        if (nv.getHoTen() == null || nv.getHoTen().trim().isEmpty()) {
            return "Họ tên không được để trống";
        }
        
        // Kiểm tra số điện thoại
        if (nv.getSdt() == null || nv.getSdt().trim().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (!Pattern.matches("^0\\d{9}$", nv.getSdt().trim())) {
            return "Số điện thoại không hợp lệ (phải bắt đầu bằng số 0 và có 10 chữ số)";
        }
        
        // Kiểm tra email
        if (nv.getEmail() != null && !nv.getEmail().trim().isEmpty()) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!Pattern.matches(emailRegex, nv.getEmail().trim())) {
                return "Email không hợp lệ";
            }
        }
        
        // Kiểm tra giới tính
        if (nv.getGioiTinh() != null && !nv.getGioiTinh().trim().isEmpty()) {
            if (!nv.getGioiTinh().equals("Nam") && !nv.getGioiTinh().equals("Nữ")) {
                return "Giới tính phải là Nam hoặc Nữ";
            }
        }
        
        // Kiểm tra ngày sinh
        if (nv.getNgaySinh() == null) {
            return "Ngày sinh không được để trống";
        }
        
        return null; // Không có lỗi
    }
} 