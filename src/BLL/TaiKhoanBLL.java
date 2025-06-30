package BLL;

import DAL.TaiKhoanDAL;

public class TaiKhoanBLL {
    public static boolean checkLogin(String username, String password) {
        // Kiểm tra dữ liệu đầu vào
        if (username == null || password == null || 
            username.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }
        
        // Chuẩn hóa dữ liệu
        username = username.trim();
        password = password.trim();
        
        // Gọi xuống DAL để kiểm tra đăng nhập
        return TaiKhoanDAL.checkLogin(username, password);
    }
    
    public static String getChucVu(String username) {
        return TaiKhoanDAL.getChucVu(username);
    }
    
    public static String getMaNV(String username) {
        return TaiKhoanDAL.getMaNV(username);
    }
} 