package View;

import BLL.ThongKeBLL;
import Entities.ThongKe;
import View.component.Card;
import View.component.Model_Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class Dashboard extends JPanel {
    private ThongKe thongKe;
    private Timer refreshTimer;
    
    private Card cardTour;
    private Card cardDoanhThu;
    private Card cardKhachHang;
    private Card cardNhanVien;
    private Card cardDoiTac;
    
    public Dashboard() {
        initComponents();
        loadData();
        startRefreshTimer();
    }
    
    private void initComponents() {
        setLayout(new MigLayout("wrap 3, gap 20, insets 30", "[grow][grow][grow]", "[]30[]"));
        setBackground(new Color(242, 242, 242));
        
        // Tạo các card thống kê
        cardTour = new Card();
        cardDoanhThu = new Card();
        cardKhachHang = new Card();
        cardNhanVien = new Card();
        cardDoiTac = new Card();
        
        // Thêm các card vào panel
        add(cardTour, "grow");
        add(cardDoanhThu, "grow");
        add(cardKhachHang, "grow");
        add(cardNhanVien, "grow");
        add(cardDoiTac, "grow");
    }
    
    private void loadData() {
        thongKe = ThongKeBLL.getThongKe();
        refreshUI();
    }
    
    private void refreshUI() {
        // Cập nhật card Tour
        cardTour.setData(new Model_Card(
            "Tour Du Lịch",
            String.valueOf(thongKe.getTongTour()),
            ThongKeBLL.getTyLeTourDaDat(thongKe) + " đã đặt",
            new Color(41, 128, 185)
        ));
        
        // Cập nhật card Doanh Thu
        cardDoanhThu.setData(new Model_Card(
            "Doanh Thu",
            ThongKeBLL.formatCurrency(thongKe.getDoanhThu()),
            "Tổng doanh thu",
            new Color(39, 174, 96)
        ));
        
        // Cập nhật card Khách Hàng
        cardKhachHang.setData(new Model_Card(
            "Khách Hàng",
            String.valueOf(thongKe.getTongKhachHang()),
            "Tổng số khách hàng",
            new Color(142, 68, 173)
        ));
        
        // Cập nhật card Nhân Viên
        cardNhanVien.setData(new Model_Card(
            "Nhân Viên",
            String.valueOf(thongKe.getTongNhanVien()),
            "Tổng số nhân viên",
            new Color(230, 126, 34)
        ));
        
        // Cập nhật card Đối Tác
        cardDoiTac.setData(new Model_Card(
            "Đối Tác",
            String.valueOf(thongKe.getTongDoiTac()),
            "Tổng số đối tác",
            new Color(52, 152, 219)
        ));
    }
    
    private void startRefreshTimer() {
        // Tự động cập nhật dữ liệu mỗi 30 giây
        refreshTimer = new Timer(30000, e -> loadData());
        refreshTimer.start();
    }
    
    public void stopRefreshTimer() {
        if (refreshTimer != null && refreshTimer.isRunning()) {
            refreshTimer.stop();
        }
    }
}