package DAL;

import Entities.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAL {
    public static List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                list.add(nv);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String add(NhanVien nv) {
        try {
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO nhanvien (MaNV, HoTen, DiaChi, NgaySinh, GioiTinh, SDT, Email, ChucVu) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getHoTen());
            stmt.setString(3, nv.getDiaChi());
            stmt.setDate(4, new java.sql.Date(nv.getNgaySinh().getTime()));
            stmt.setString(5, nv.getGioiTinh());
            stmt.setString(6, nv.getSdt());
            stmt.setString(7, nv.getEmail());
            stmt.setString(8, nv.getChucVu());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public static String update(NhanVien nv) {
        try {
            Connection conn = Database.getConnection();
            String sql = "UPDATE nhanvien SET HoTen=?, DiaChi=?, NgaySinh=?, GioiTinh=?, SDT=?, Email=?, ChucVu=? " +
                        "WHERE MaNV=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nv.getHoTen());
            stmt.setString(2, nv.getDiaChi());
            stmt.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            stmt.setString(4, nv.getGioiTinh());
            stmt.setString(5, nv.getSdt());
            stmt.setString(6, nv.getEmail());
            stmt.setString(7, nv.getChucVu());
            stmt.setString(8, nv.getMaNV());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public static String delete(String maNV) {
        try {
            Connection conn = Database.getConnection();
            String sql = "DELETE FROM nhanvien WHERE MaNV=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNV);
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public static List<NhanVien> search(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE MaNV LIKE ? OR HoTen LIKE ? OR SDT LIKE ? OR Email LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String searchKey = "%" + keyword + "%";
            stmt.setString(1, searchKey);
            stmt.setString(2, searchKey);
            stmt.setString(3, searchKey);
            stmt.setString(4, searchKey);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                list.add(nv);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String generateNewMaNV() {
        String newMaNV = "NV001";
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT MaNV FROM nhanvien ORDER BY MaNV DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String lastMaNV = rs.getString("MaNV");
                int number = Integer.parseInt(lastMaNV.substring(2)) + 1;
                newMaNV = String.format("NV%03d", number);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newMaNV;
    }
} 