package View;

import BLL.TaiKhoanBLL;
import View.Login.swing.Button;
import View.Login.swing.MyPasswordField;
import View.Login.swing.MyTextField;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class Welcome extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel loginPanel;
    private JLabel lblTitle;
    private JLabel lblSubTitle;
    private JLabel lblLogin;
    private MyTextField txtEmail;
    private MyPasswordField txtPass;
    private Button cmdLogin;

    public Welcome() {
        initComponents();
        init();
    }

    private void init() {
        // Thiết lập cửa sổ
        setUndecorated(true);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Tạo panel chính
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Tạo gradient background
                GradientPaint gp = new GradientPaint(0, 0, new Color(7, 164, 121), 
                                                   getWidth(), getHeight(), 
                                                   new Color(0, 102, 77));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        
        // Tiêu đề chính
        lblTitle = new JLabel("QUẢN LÝ TOUR DU LỊCH");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 50));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(0, 100, 1200, 60);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblTitle);
        
        // Tiêu đề phụ
        lblSubTitle = new JLabel("Chào mừng đến với hệ thống quản lý tour du lịch");
        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        lblSubTitle.setForeground(new Color(230, 230, 230));
        lblSubTitle.setBounds(0, 180, 1200, 30);
        lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblSubTitle);
        
        // Panel đăng nhập
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBounds(400, 250, 400, 350);
        loginPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        
        // Tiêu đề đăng nhập
        lblLogin = new JLabel("Đăng Nhập");
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblLogin.setForeground(new Color(7, 164, 121));
        loginPanel.add(lblLogin);
        
        // Trường email
        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/View/Login/icon/mail.png")));
        txtEmail.setHint("Tên đăng nhập");
        loginPanel.add(txtEmail, "w 60%");
        
        // Trường mật khẩu
        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/View/Login/icon/pass.png")));
        txtPass.setHint("Mật khẩu");
        loginPanel.add(txtPass, "w 60%");
        
        // Nút đăng nhập
        cmdLogin = new Button();
        cmdLogin.setBackground(new Color(7, 164, 121));
        cmdLogin.setForeground(new Color(250, 250, 250));
        cmdLogin.setText("ĐĂNG NHẬP");
        cmdLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtEmail.getText().trim();
                String password = new String(txtPass.getPassword()).trim();
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Welcome.this,
                        "Vui lòng nhập đầy đủ thông tin đăng nhập",
                        "Lỗi",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (TaiKhoanBLL.checkLogin(username, password)) {
                    String chucVu = TaiKhoanBLL.getChucVu(username);
                    
                    JOptionPane.showMessageDialog(Welcome.this, 
                        "Đăng nhập thành công!", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // Mở MainForm và truyền thông tin người dùng
                    MainForm mainForm = new MainForm();
                    mainForm.updateUserInfo(username, chucVu);
                    mainForm.setVisible(true);
                    Welcome.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(Welcome.this,
                        "Sai tên đăng nhập hoặc mật khẩu!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(cmdLogin, "w 40%, h 40");
        
        mainPanel.add(loginPanel);
        
        // Thêm panel vào frame
        add(mainPanel);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }
} 