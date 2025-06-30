package BLL;

import DAL.DatTourDAL;
import Entities.DatTour;
import java.text.DecimalFormat;
import java.util.List;

public class DatTourBLL {
    
    public static List<DatTour> getAll() {
        return DatTourDAL.getAll();
    }
    
    public static String add(DatTour datTour) {
        String error = validateDatTour(datTour);
        if (error != null) {
            return error;
        }
        
        if (DatTourDAL.add(datTour)) {
            return null;
        }
        return "Thêm đặt tour thất bại!";
    }
    
    public static String update(DatTour datTour) {
        String error = validateDatTour(datTour);
        if (error != null) {
            return error;
        }
        
        if (DatTourDAL.update(datTour)) {
            return null;
        }
        return "Cập nhật đặt tour thất bại!";
    }
    
    public static String delete(String maDatTour) {
        if (DatTourDAL.delete(maDatTour)) {
            return null;
        }
        return "Xóa đặt tour thất bại!";
    }
    
    public static List<DatTour> search(String keyword) {
        return DatTourDAL.search(keyword);
    }
    
    public static String generateNewMaDatTour() {
        return DatTourDAL.generateNewMaDatTour();
    }
    
    private static String validateDatTour(DatTour datTour) {
        if (datTour.getMaDatTour() == null || datTour.getMaDatTour().trim().isEmpty()) {
            return "Mã đặt tour không được để trống!";
        }
        
        if (datTour.getMaTour() == null || datTour.getMaTour().trim().isEmpty()) {
            return "Mã tour không được để trống!";
        }
        
        if (datTour.getMaKH() == null || datTour.getMaKH().trim().isEmpty()) {
            return "Mã khách hàng không được để trống!";
        }
        
        if (datTour.getMaDT() == null || datTour.getMaDT().trim().isEmpty()) {
            return "Mã đối tác không được để trống!";
        }
        
        if (datTour.getNgayDat() == null) {
            return "Ngày đặt không được để trống!";
        }
        
        if (datTour.getSoLuongNguoi() <= 0) {
            return "Số lượng người phải lớn hơn 0!";
        }
        
        if (datTour.getTongTien() < 0) {
            return "Tổng tiền không được âm!";
        }
        
        if (datTour.getSoTienDaThanhToan() < 0) {
            return "Số tiền đã thanh toán không được âm!";
        }
        
        if (datTour.getSoTienDaThanhToan() > datTour.getTongTien()) {
            return "Số tiền đã thanh toán không được lớn hơn tổng tiền!";
        }
        
        return null;
    }
    
    public static String formatCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,### VNĐ");
        return formatter.format(amount);
    }
} 