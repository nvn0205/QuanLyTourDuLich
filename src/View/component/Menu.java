package View.component;

import View.Welcome;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;
import net.miginfocom.swing.MigLayout;

public class Menu extends JPanel {
    private EventListenerList listenerList;
    private MenuItem selectedMenu;
    private int selectedIndex;
    private int selectedSubIndex;
    private JLabel usernameLabel;
    private JLabel roleLabel;
    private JPanel menuPanel;
    private String currentRole;
    
    public Menu() {
        listenerList = new EventListenerList();
        init();
    }
    
    private void init() {
        setBackground(new Color(20, 20, 20));
        
        // Logo hoặc tên ứng dụng
        JLabel logo = new JLabel("QUẢN LÝ TOUR", SwingConstants.CENTER);
        logo.setFont(new Font("sansserif", Font.BOLD, 20));
        logo.setForeground(new Color(7, 164, 121));
        logo.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Thông tin người dùng
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]"));
        userInfoPanel.setOpaque(false);
        
        usernameLabel = new JLabel("Chưa đăng nhập", SwingConstants.LEFT);
        roleLabel = new JLabel("Chức vụ: --", SwingConstants.LEFT);
        usernameLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("sansserif", Font.PLAIN, 12));
        roleLabel.setFont(new Font("sansserif", Font.PLAIN, 12));
        
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(roleLabel);
        
        // Panel chứa các menu items
        menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]"));
        
        // Tạo layout
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        
        // Thiết lập GroupLayout cho menu
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(logo, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(userInfoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(logo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
            .addComponent(userInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(20)
            .addComponent(menuPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
    }
    
    private void addMenu(JPanel menuPanel, String menuName, int index, int subIndex, boolean selected) {
        MenuItem menuItem = new MenuItem(menuName);
        menuItem.setSelected(selected);
        if (selected) {
            selectedMenu = menuItem;
            selectedIndex = index;
            selectedSubIndex = subIndex;
        }
        
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedMenu != null) {
                    selectedMenu.setSelected(false);
                }
                menuItem.setSelected(true);
                selectedMenu = menuItem;
                selectedIndex = index;
                selectedSubIndex = subIndex;
                
                // Xử lý đăng xuất
                if (index == 3 && subIndex == 0) {
                    // Lấy frame chính
                    Window window = SwingUtilities.getWindowAncestor(Menu.this);
                    
                    // Tạo JOptionPane với icon và style mới
                    JOptionPane pane = new JOptionPane(
                        "Bạn có chắc muốn đăng xuất?",
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.YES_NO_OPTION
                    );
                    
                    // Tạo dialog với vị trí center
                    JDialog dialog = pane.createDialog("Xác nhận đăng xuất");
                    dialog.setLocationRelativeTo(window); // Đặt vị trí giữa frame chính
                    dialog.setVisible(true);
                    
                    // Xử lý kết quả
                    Object result = pane.getValue();
                    if (result != null && (Integer) result == JOptionPane.YES_OPTION) {
                        // Hiển thị form đăng nhập
                        new Welcome().setVisible(true);
                        
                        // Đóng form hiện tại
                        if (window instanceof JFrame) {
                            ((JFrame) window).dispose();
                        }
                    } else {
                        // Nếu không đăng xuất, quay lại menu trước đó
                        menuItem.setSelected(false);
                        if (selectedMenu != null) {
                            selectedMenu.setSelected(true);
                        }
                    }
                    return;
                }
                
                fireMenuSelected(index, subIndex);
            }
        });
        
        menuPanel.add(menuItem);
    }
    
    private void addSubMenu(JPanel menuPanel, String menuName, int index, int subIndex) {
        MenuItem menuItem = new MenuItem("   " + menuName);  // Thêm khoảng trắng để tạo indent
        menuItem.setFont(new Font("sansserif", Font.PLAIN, 12));
        
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedMenu != null) {
                    selectedMenu.setSelected(false);
                }
                menuItem.setSelected(true);
                selectedMenu = menuItem;
                selectedIndex = index;
                selectedSubIndex = subIndex;
                fireMenuSelected(index, subIndex);
            }
        });
        
        menuPanel.add(menuItem);
    }
    
    public void addMenuListener(MenuListener listener) {
        listenerList.add(MenuListener.class, listener);
    }
    
    public void removeMenuListener(MenuListener listener) {
        listenerList.remove(MenuListener.class, listener);
    }
    
    protected void fireMenuSelected(int index, int subIndex) {
        MenuListener[] listeners = listenerList.getListeners(MenuListener.class);
        for (MenuListener listener : listeners) {
            listener.menuSelected(index, subIndex);
        }
    }
    
    // Phương thức cập nhật thông tin người dùng
    public void updateUserInfo(String username, String role) {
        if (usernameLabel != null && roleLabel != null) {
            usernameLabel.setText("Tài khoản: " + username);
            roleLabel.setText("Chức vụ: " + role);
            this.currentRole = role;
            updateMenuByRole();
        }
    }

    // Phương thức cập nhật menu theo chức vụ
    private void updateMenuByRole() {
        menuPanel.removeAll();
        
        // Menu chung cho mọi chức vụ
        addMenu(menuPanel, "Tổng Quan", 0, 0, true);
        addMenu(menuPanel, "Quản Lý Tour", 1, 1, false);
        addMenu(menuPanel, "Quản Lý Đặt Tour", 1, 2, false);
        addMenu(menuPanel, "Quản Lý Khách Hàng", 2, 1, false);
        addMenu(menuPanel, "Quản Lý Đối Tác", 2, 3, false);
        
        // Menu riêng cho Quản lý
        if ("Quản lý".equals(currentRole)) {
            addMenu(menuPanel, "Quản Lý Nhân Viên", 2, 2, false);
        }
        
        // Menu đăng xuất cho mọi chức vụ
        addMenu(menuPanel, "Đăng Xuất", 3, 0, false);
        
        // Cập nhật giao diện
        menuPanel.revalidate();
        menuPanel.repaint();
    }
}