package BLL;

import DAL.KhachHangDAL;
import Entities.KhachHang;
import java.util.List;
import java.util.regex.Pattern;

public class KhachHangBLL {
    public static List<KhachHang> getAll() {
        return KhachHangDAL.getAll();
    }
    
    public static String add(KhachHang kh) {
        String error = validateKhachHang(kh);
        if (error != null) {
            return error;
        }
        
        if (KhachHangDAL.add(kh)) {
            return null;
        }
        return "Thêm khách hàng thất bại!";
    }
    
    public static String update(KhachHang kh) {
        String error = validateKhachHang(kh);
        if (error != null) {
            return error;
        }
        
        if (KhachHangDAL.update(kh)) {
            return null;
        }
        return "Cập nhật khách hàng thất bại!";
    }
    
    public static String delete(String maKH) {
        if (KhachHangDAL.delete(maKH)) {
            return null;
        }
        return "Xóa khách hàng thất bại!";
    }
    
    public static List<KhachHang> search(String keyword) {
        return KhachHangDAL.search(keyword);
    }
    
    public static String generateNewMaKH() {
        return KhachHangDAL.generateNewMaKH();
    }
    
    private static String validateKhachHang(KhachHang kh) {
        if (kh.getMaKH() == null || kh.getMaKH().trim().isEmpty()) {
            return "Mã khách hàng không được để trống!";
        }
        
        if (kh.getHoTen() == null || kh.getHoTen().trim().isEmpty()) {
            return "Họ tên không được để trống!";
        }
        
        if (kh.getSdt() == null || kh.getSdt().trim().isEmpty()) {
            return "Số điện thoại không được để trống!";
        }
        
        // Kiểm tra định dạng số điện thoại
        if (!Pattern.matches("^0\\d{9}$", kh.getSdt())) {
            return "Số điện thoại không hợp lệ! (Phải bắt đầu bằng số 0 và có 10 chữ số)";
        }
        
        // Kiểm tra định dạng email nếu có
        if (kh.getEmail() != null && !kh.getEmail().trim().isEmpty()) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!Pattern.matches(emailRegex, kh.getEmail())) {
                return "Email không hợp lệ!";
            }
        }
        
        return null;
    }
} 