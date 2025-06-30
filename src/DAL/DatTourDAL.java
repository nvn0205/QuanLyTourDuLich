package DAL;

import Entities.DatTour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatTourDAL {
    
    public static List<DatTour> getAll() {
        List<DatTour> list = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM dattour";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DatTour datTour = new DatTour();
                datTour.setMaDatTour(rs.getString("MaDatTour"));
                datTour.setMaTour(rs.getString("MaTour"));
                datTour.setMaKH(rs.getString("MaKH"));
                datTour.setNgayDat(rs.getDate("NgayDat"));
                datTour.setSoLuongNguoi(rs.getInt("SoLuongNguoi"));
                datTour.setTongTien(rs.getDouble("TongTien"));
                datTour.setSoTienDaThanhToan(rs.getDouble("SoTienDaThanhToan"));
                datTour.setMaDT(rs.getString("MaDT"));
                datTour.setMaNV(rs.getString("MaNV"));
                datTour.setTrangThai(rs.getString("TrangThai"));
                list.add(datTour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static boolean add(DatTour datTour) {
        try (Connection conn = Database.getConnection()) {
            // Lấy giá tour
            String sqlGiaTour = "SELECT Gia FROM tour WHERE MaTour = ?";
            PreparedStatement psGia = conn.prepareStatement(sqlGiaTour);
            psGia.setString(1, datTour.getMaTour());
            ResultSet rs = psGia.executeQuery();
            
            if (rs.next()) {
                double giaTour = rs.getDouble("Gia");
                datTour.setTongTien(giaTour * datTour.getSoLuongNguoi());
            }
            
            String sql = "INSERT INTO dattour (MaDatTour, MaTour, MaKH, NgayDat, SoLuongNguoi, TongTien, SoTienDaThanhToan, MaDT, MaNV, TrangThai) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, datTour.getMaDatTour());
            ps.setString(2, datTour.getMaTour());
            ps.setString(3, datTour.getMaKH());
            ps.setDate(4, new java.sql.Date(datTour.getNgayDat().getTime()));
            ps.setInt(5, datTour.getSoLuongNguoi());
            ps.setDouble(6, datTour.getTongTien());
            ps.setDouble(7, datTour.getSoTienDaThanhToan());
            ps.setString(8, datTour.getMaDT());
            ps.setString(9, datTour.getMaNV());
            ps.setString(10, datTour.getTrangThai());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean update(DatTour datTour) {
        try (Connection conn = Database.getConnection()) {
            // Lấy giá tour
            String sqlGiaTour = "SELECT Gia FROM tour WHERE MaTour = ?";
            PreparedStatement psGia = conn.prepareStatement(sqlGiaTour);
            psGia.setString(1, datTour.getMaTour());
            ResultSet rs = psGia.executeQuery();
            
            if (rs.next()) {
                double giaTour = rs.getDouble("Gia");
                datTour.setTongTien(giaTour * datTour.getSoLuongNguoi());
            }
            
            String sql = "UPDATE dattour SET MaTour=?, MaKH=?, NgayDat=?, SoLuongNguoi=?, "
                    + "TongTien=?, SoTienDaThanhToan=?, MaDT=?, MaNV=?, TrangThai=? WHERE MaDatTour=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, datTour.getMaTour());
            ps.setString(2, datTour.getMaKH());
            ps.setDate(3, new java.sql.Date(datTour.getNgayDat().getTime()));
            ps.setInt(4, datTour.getSoLuongNguoi());
            ps.setDouble(5, datTour.getTongTien());
            ps.setDouble(6, datTour.getSoTienDaThanhToan());
            ps.setString(7, datTour.getMaDT());
            ps.setString(8, datTour.getMaNV());
            ps.setString(9, datTour.getTrangThai());
            ps.setString(10, datTour.getMaDatTour());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean delete(String maDatTour) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM dattour WHERE MaDatTour=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maDatTour);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<DatTour> search(String keyword) {
        List<DatTour> list = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM dattour WHERE MaDatTour LIKE ? OR MaTour LIKE ? OR MaKH LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DatTour datTour = new DatTour();
                datTour.setMaDatTour(rs.getString("MaDatTour"));
                datTour.setMaTour(rs.getString("MaTour"));
                datTour.setMaKH(rs.getString("MaKH"));
                datTour.setNgayDat(rs.getDate("NgayDat"));
                datTour.setSoLuongNguoi(rs.getInt("SoLuongNguoi"));
                datTour.setTongTien(rs.getDouble("TongTien"));
                datTour.setSoTienDaThanhToan(rs.getDouble("SoTienDaThanhToan"));
                datTour.setMaDT(rs.getString("MaDT"));
                datTour.setMaNV(rs.getString("MaNV"));
                datTour.setTrangThai(rs.getString("TrangThai"));
                list.add(datTour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static String generateNewMaDatTour() {
        String prefix = "DT";
        String newMaDatTour = prefix + "001";
        
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT MaDatTour FROM dattour WHERE MaDatTour LIKE ? ORDER BY MaDatTour DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, prefix + "%");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String lastMaDatTour = rs.getString("MaDatTour");
                int number = Integer.parseInt(lastMaDatTour.substring(2)) + 1;
                newMaDatTour = String.format("%s%03d", prefix, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return newMaDatTour;
    }
} 