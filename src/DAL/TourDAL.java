package DAL;

import Entities.Tour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDAL {
    
    public static List<Tour> getAll() {
        List<Tour> list = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM tour";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setMaTour(rs.getString("MaTour"));
                tour.setTenTour(rs.getString("TenTour"));
                tour.setDiaDiem(rs.getString("DiaDiem"));
                tour.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
                tour.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                tour.setSoNguoi(rs.getInt("SoNguoi"));
                tour.setGia(rs.getDouble("Gia"));
                tour.setTrangThai(rs.getString("TrangThai"));
                tour.setMoTa(rs.getString("MoTa"));
                list.add(tour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static boolean add(Tour tour) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO tour (MaTour, TenTour, DiaDiem, NgayKhoiHanh, NgayKetThuc, SoNguoi, Gia, TrangThai, MoTa) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tour.getMaTour());
            ps.setString(2, tour.getTenTour());
            ps.setString(3, tour.getDiaDiem());
            ps.setDate(4, new java.sql.Date(tour.getNgayKhoiHanh().getTime()));
            ps.setDate(5, tour.getNgayKetThuc() != null ? new java.sql.Date(tour.getNgayKetThuc().getTime()) : null);
            ps.setInt(6, tour.getSoNguoi());
            ps.setDouble(7, tour.getGia());
            ps.setString(8, tour.getTrangThai());
            ps.setString(9, tour.getMoTa());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean update(Tour tour) {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE tour SET TenTour=?, DiaDiem=?, NgayKhoiHanh=?, NgayKetThuc=?, "
                    + "SoNguoi=?, Gia=?, TrangThai=?, MoTa=? WHERE MaTour=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tour.getTenTour());
            ps.setString(2, tour.getDiaDiem());
            ps.setDate(3, new java.sql.Date(tour.getNgayKhoiHanh().getTime()));
            ps.setDate(4, tour.getNgayKetThuc() != null ? new java.sql.Date(tour.getNgayKetThuc().getTime()) : null);
            ps.setInt(5, tour.getSoNguoi());
            ps.setDouble(6, tour.getGia());
            ps.setString(7, tour.getTrangThai());
            ps.setString(8, tour.getMoTa());
            ps.setString(9, tour.getMaTour());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean delete(String maTour) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM tour WHERE MaTour=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maTour);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<Tour> search(String keyword) {
        List<Tour> list = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM tour WHERE MaTour LIKE ? OR TenTour LIKE ? OR DiaDiem LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setMaTour(rs.getString("MaTour"));
                tour.setTenTour(rs.getString("TenTour"));
                tour.setDiaDiem(rs.getString("DiaDiem"));
                tour.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
                tour.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                tour.setSoNguoi(rs.getInt("SoNguoi"));
                tour.setGia(rs.getDouble("Gia"));
                tour.setTrangThai(rs.getString("TrangThai"));
                tour.setMoTa(rs.getString("MoTa"));
                list.add(tour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
} 