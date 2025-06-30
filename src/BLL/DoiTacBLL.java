package BLL;

import DAL.DoiTacDAL;
import Entities.DoiTac;
import java.util.List;
import java.util.regex.Pattern;

public class DoiTacBLL {
    public static List<DoiTac> getAll() {
        return DoiTacDAL.getAll();
    }
    
    public static List<String> getAllMaDT() {
        return DoiTacDAL.getAllMaDT();
    }
    
    public static String add(DoiTac dt) {
        // Validate dữ liệu
        String error = validateDoiTac(dt);
        if (error != null) {
            return error;
        }
        
        // Thêm đối tác
        return DoiTacDAL.add(dt);
    }
    
    public static String update(DoiTac dt) {
        // Validate dữ liệu
        String error = validateDoiTac(dt);
        if (error != null) {
            return error;
        }
        
        // Cập nhật đối tác
        return DoiTacDAL.update(dt);
    }
    
    public static String delete(String maDT) {
        return DoiTacDAL.delete(maDT);
    }
    
    public static List<DoiTac> search(String keyword) {
        return DoiTacDAL.search(keyword);
    }
    
    public static String generateNewMaDT() {
        return DoiTacDAL.generateNewMaDT();
    }
    
    private static String validateDoiTac(DoiTac dt) {
        // Kiểm tra mã đối tác
        if (dt.getMaDT() == null || dt.getMaDT().trim().isEmpty()) {
            return "Mã đối tác không được để trống";
        }
        
        // Kiểm tra tên đối tác
        if (dt.getTenDT() == null || dt.getTenDT().trim().isEmpty()) {
            return "Tên đối tác không được để trống";
        }
        
        // Kiểm tra số điện thoại
        if (dt.getSdt() == null || dt.getSdt().trim().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (!Pattern.matches("^0\\d{9}$", dt.getSdt().trim())) {
            return "Số điện thoại không hợp lệ (phải bắt đầu bằng số 0 và có 10 chữ số)";
        }
        
        // Kiểm tra email
        if (dt.getEmail() != null && !dt.getEmail().trim().isEmpty()) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!Pattern.matches(emailRegex, dt.getEmail().trim())) {
                return "Email không hợp lệ";
            }
        }
        
        // Kiểm tra loại đối tác
        if (dt.getLoaiDoiTac() == null || dt.getLoaiDoiTac().trim().isEmpty()) {
            return "Loại đối tác không được để trống";
        }
        
        return null; // Không có lỗi
    }
} 