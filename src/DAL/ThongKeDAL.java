package DAL;

import Entities.ThongKe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeDAL {
    public static ThongKe getThongKe() {
        ThongKe thongKe = new ThongKe();
        
        try (Connection conn = Database.getConnection()) {
            // Đếm tổng số tour
            String queryTour = "SELECT COUNT(*) FROM tour";
            PreparedStatement psTour = conn.prepareStatement(queryTour);
            ResultSet rsTour = psTour.executeQuery();
            if (rsTour.next()) {
                thongKe.setTongTour(rsTour.getInt(1));
            }
            
            // Đếm số tour đã đặt
            String queryTourDat = "SELECT COUNT(DISTINCT MaTour) FROM dattour";
            PreparedStatement psTourDat = conn.prepareStatement(queryTourDat);
            ResultSet rsTourDat = psTourDat.executeQuery();
            if (rsTourDat.next()) {
                thongKe.setTourDaDat(rsTourDat.getInt(1));
            }
            
            // Tính tổng doanh thu
            String queryDoanhThu = "SELECT SUM(SoTienDaThanhToan) FROM dattour";
            PreparedStatement psDoanhThu = conn.prepareStatement(queryDoanhThu);
            ResultSet rsDoanhThu = psDoanhThu.executeQuery();
            if (rsDoanhThu.next()) {
                thongKe.setDoanhThu(rsDoanhThu.getDouble(1));
            }
            
            // Đếm tổng số khách hàng
            String queryKH = "SELECT COUNT(*) FROM khachhang";
            PreparedStatement psKH = conn.prepareStatement(queryKH);
            ResultSet rsKH = psKH.executeQuery();
            if (rsKH.next()) {
                thongKe.setTongKhachHang(rsKH.getInt(1));
            }
            
            // Đếm tổng số nhân viên
            String queryNV = "SELECT COUNT(*) FROM nhanvien";
            PreparedStatement psNV = conn.prepareStatement(queryNV);
            ResultSet rsNV = psNV.executeQuery();
            if (rsNV.next()) {
                thongKe.setTongNhanVien(rsNV.getInt(1));
            }
            
            // Đếm tổng số đối tác
            String queryDT = "SELECT COUNT(*) FROM doitac";
            PreparedStatement psDT = conn.prepareStatement(queryDT);
            ResultSet rsDT = psDT.executeQuery();
            if (rsDT.next()) {
                thongKe.setTongDoiTac(rsDT.getInt(1));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return thongKe;
    }
} 