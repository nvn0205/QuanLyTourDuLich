package DAL;

import Entities.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAL {
    public static List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM khachhang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSdt(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static boolean add(KhachHang kh) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO khachhang (MaKH, HoTen, SDT, Email, NgaySinh, DiaChi) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getEmail());
            ps.setDate(5, kh.getNgaySinh() != null ? new java.sql.Date(kh.getNgaySinh().getTime()) : null);
            ps.setString(6, kh.getDiaChi());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean update(KhachHang kh) {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE khachhang SET HoTen=?, SDT=?, Email=?, NgaySinh=?, DiaChi=? "
                    + "WHERE MaKH=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getEmail());
            ps.setDate(4, kh.getNgaySinh() != null ? new java.sql.Date(kh.getNgaySinh().getTime()) : null);
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getMaKH());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean delete(String maKH) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM khachhang WHERE MaKH=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maKH);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<KhachHang> search(String keyword) {
        List<KhachHang> list = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM khachhang WHERE MaKH LIKE ? OR HoTen LIKE ? OR SDT LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSdt(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static String generateNewMaKH() {
        String prefix = "KH";
        String newMaKH = prefix + "001";
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT MaKH FROM khachhang WHERE MaKH LIKE ? ORDER BY MaKH DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, prefix + "%");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String lastMaKH = rs.getString("MaKH");
                int number = Integer.parseInt(lastMaKH.substring(2)) + 1;
                newMaKH = String.format("%s%03d", prefix, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return newMaKH;
    }
} 