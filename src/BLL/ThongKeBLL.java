package BLL;

import DAL.ThongKeDAL;
import Entities.ThongKe;

public class ThongKeBLL {
    public static ThongKe getThongKe() {
        return ThongKeDAL.getThongKe();
    }
    
    public static String formatCurrency(double amount) {
        return String.format("%,.0f VNĐ", amount);
    }
    
    public static String getTyLeTourDaDat(ThongKe thongKe) {
        if (thongKe.getTongTour() == 0) return "0%";
        double tyLe = (double) thongKe.getTourDaDat() / thongKe.getTongTour() * 100;
        return String.format("%.1f%%", tyLe);
    }
} 