package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAL {
    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM taikhoan WHERE TenDangNhap = ? AND MatKhau = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next(); // Trả về true nếu tìm thấy tài khoản
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String getChucVu(String username) {
        String sql = "SELECT nv.ChucVu FROM taikhoan tk " +
                    "JOIN nhanvien nv ON tk.MaNV = nv.MaNV " +
                    "WHERE tk.TenDangNhap = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ChucVu");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getMaNV(String username) {
        String sql = "SELECT MaNV FROM taikhoan WHERE TenDangNhap = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("MaNV");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 