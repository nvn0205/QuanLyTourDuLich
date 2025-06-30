package BLL;

import DAL.TourDAL;
import Entities.Tour;
import java.util.List;
import java.util.regex.Pattern;

public class TourBLL {
    
    public static List<Tour> getAll() {
        return TourDAL.getAll();
    }
    
    public static boolean add(Tour tour) {
        // Kiểm tra dữ liệu
        if (!validateTour(tour)) {
            return false;
        }
        
        return TourDAL.add(tour);
    }
    
    public static boolean update(Tour tour) {
        // Kiểm tra dữ liệu
        if (!validateTour(tour)) {
            return false;
        }
        
        return TourDAL.update(tour);
    }
    
    public static boolean delete(String maTour) {
        if (maTour == null || maTour.trim().isEmpty()) {
            return false;
        }
        
        return TourDAL.delete(maTour);
    }
    
    public static List<Tour> search(String keyword) {
        if (keyword == null) {
            return getAll();
        }
        return TourDAL.search(keyword.trim());
    }
    
    private static boolean validateTour(Tour tour) {
        if (tour == null) {
            return false;
        }
        
        // Kiểm tra mã tour
        if (tour.getMaTour() == null || !Pattern.matches("T\\d{3}", tour.getMaTour())) {
            return false;
        }
        
        // Kiểm tra tên tour
        if (tour.getTenTour() == null || tour.getTenTour().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra địa điểm
        if (tour.getDiaDiem() == null || tour.getDiaDiem().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra ngày khởi hành
        if (tour.getNgayKhoiHanh() == null) {
            return false;
        }
        
        // Kiểm tra số người
        if (tour.getSoNguoi() <= 0) {
            return false;
        }
        
        // Kiểm tra giá
        if (tour.getGia() <= 0) {
            return false;
        }
        
        return true;
    }
    
    public static String formatCurrency(double amount) {
        return String.format("%,.0f VNĐ", amount);
    }
    
    public static String generateNewMaTour() {
        List<Tour> tours = getAll();
        int maxNumber = 0;
        
        for (Tour tour : tours) {
            String maTour = tour.getMaTour();
            if (maTour != null && maTour.matches("T\\d{3}")) {
                int number = Integer.parseInt(maTour.substring(1));
                if (number > maxNumber) {
                    maxNumber = number;
                }
            }
        }
        
        return String.format("T%03d", maxNumber + 1);
    }
} 