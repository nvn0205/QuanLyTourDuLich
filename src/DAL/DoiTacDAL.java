package DAL;

import Entities.DoiTac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoiTacDAL {
    public static List<DoiTac> getAll() {
        List<DoiTac> list = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM doitac";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DoiTac dt = new DoiTac();
                dt.setMaDT(rs.getString("MaDT"));
                dt.setTenDT(rs.getString("TenDT"));
                dt.setSdt(rs.getString("SDT"));
                dt.setEmail(rs.getString("Email"));
                dt.setDiaChi(rs.getString("DiaChi"));
                dt.setLoaiDoiTac(rs.getString("LoaiDoiTac"));
                dt.setGhiChu(rs.getString("GhiChu"));
                list.add(dt);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String add(DoiTac dt) {
        try {
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO doitac (MaDT, TenDT, SDT, Email, DiaChi, LoaiDoiTac, GhiChu) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dt.getMaDT());
            stmt.setString(2, dt.getTenDT());
            stmt.setString(3, dt.getSdt());
            stmt.setString(4, dt.getEmail());
            stmt.setString(5, dt.getDiaChi());
            stmt.setString(6, dt.getLoaiDoiTac());
            stmt.setString(7, dt.getGhiChu());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public static String update(DoiTac dt) {
        try {
            Connection conn = Database.getConnection();
            String sql = "UPDATE doitac SET TenDT=?, SDT=?, Email=?, DiaChi=?, LoaiDoiTac=?, GhiChu=? " +
                        "WHERE MaDT=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dt.getTenDT());
            stmt.setString(2, dt.getSdt());
            stmt.setString(3, dt.getEmail());
            stmt.setString(4, dt.getDiaChi());
            stmt.setString(5, dt.getLoaiDoiTac());
            stmt.setString(6, dt.getGhiChu());
            stmt.setString(7, dt.getMaDT());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public static String delete(String maDT) {
        try {
            Connection conn = Database.getConnection();
            String sql = "DELETE FROM doitac WHERE MaDT=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maDT);
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public static List<DoiTac> search(String keyword) {
        List<DoiTac> list = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM doitac WHERE MaDT LIKE ? OR TenDT LIKE ? OR SDT LIKE ? OR Email LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String searchKey = "%" + keyword + "%";
            stmt.setString(1, searchKey);
            stmt.setString(2, searchKey);
            stmt.setString(3, searchKey);
            stmt.setString(4, searchKey);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DoiTac dt = new DoiTac();
                dt.setMaDT(rs.getString("MaDT"));
                dt.setTenDT(rs.getString("TenDT"));
                dt.setSdt(rs.getString("SDT"));
                dt.setEmail(rs.getString("Email"));
                dt.setDiaChi(rs.getString("DiaChi"));
                dt.setLoaiDoiTac(rs.getString("LoaiDoiTac"));
                dt.setGhiChu(rs.getString("GhiChu"));
                list.add(dt);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String generateNewMaDT() {
        String newMaDT = "DT001";
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT MaDT FROM doitac ORDER BY MaDT DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String lastMaDT = rs.getString("MaDT");
                int number = Integer.parseInt(lastMaDT.substring(2)) + 1;
                newMaDT = String.format("DT%03d", number);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newMaDT;
    }
    
    public static List<String> getAllMaDT() {
        List<String> list = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT MaDT FROM doitac";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                list.add(rs.getString("MaDT"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
} 