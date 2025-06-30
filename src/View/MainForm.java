package View;

import View.component.Menu;
import View.component.MenuListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainForm extends JFrame {
    private Menu menu;
    private JPanel mainPanel;
    private Dashboard dashboard;
    private QuanLyTourForm tourForm;
    private QuanLyDatTourForm datTourForm;
    private QuanLyKhachHangForm khachHangForm;
    private QuanLyNhanVienForm nhanVienForm;
    private QuanLyDoiTacForm doiTacForm;
    
    public MainForm() {
        init();
    }
    
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Tạo menu
        menu = new Menu();
        add(menu, BorderLayout.WEST);
        
        // Panel chính để chứa nội dung
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(242, 242, 242));
        add(mainPanel, BorderLayout.CENTER);
        
        // Khởi tạo các form
        dashboard = new Dashboard();
        tourForm = new QuanLyTourForm();
        datTourForm = new QuanLyDatTourForm();
        khachHangForm = new QuanLyKhachHangForm();
        nhanVienForm = new QuanLyNhanVienForm();
        doiTacForm = new QuanLyDoiTacForm();
        
        // Hiển thị dashboard mặc định
        showForm(dashboard);
        
        // Xử lý sự kiện khi chọn menu
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(int index, int subIndex) {
                if (index == 0) {
                    showForm(dashboard);
                } else if (index == 1) {
                    if (subIndex == 1) {
                        showForm(tourForm);
                    } else if (subIndex == 2) {
                        showForm(datTourForm);
                    }
                } else if (index == 2) {
                    if (subIndex == 1) {
                        showForm(khachHangForm);
                    } else if (subIndex == 2) {
                        showForm(nhanVienForm);
                    } else if (subIndex == 3) {
                        showForm(doiTacForm);
                    }
                }
            }
        });
    }
    
    private void showForm(JPanel form) {
        mainPanel.removeAll();
        mainPanel.add(form);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    private void logout() {
        // Dừng timer của dashboard
        if (dashboard != null) {
            dashboard.stopRefreshTimer();
        }
        
        // Hiển thị form đăng nhập
        new Welcome().setVisible(true);
        
        // Đóng form hiện tại
        dispose();
    }
    
    public void updateUserInfo(String username, String chucVu) {
        // Cập nhật thông tin người dùng trên menu
        menu.updateUserInfo(username, chucVu);
        
        // Cập nhật quyền cho các form
        tourForm.setPermission(chucVu);
        doiTacForm.setPermission(chucVu);
    }
} 