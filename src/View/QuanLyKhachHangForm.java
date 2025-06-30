package View;

import BLL.KhachHangBLL;
import Entities.KhachHang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class QuanLyKhachHangForm extends JPanel {
    private JTable tableKhachHang;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JTextField txtMaKH;
    private JTextField txtHoTen;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JSpinner dateNgaySinh;
    private JTextArea txtDiaChi;
    
    public QuanLyKhachHangForm() {
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(242, 242, 242));
        
        // Panel tiêu đề
        JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlTitle.setOpaque(false);
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG", JLabel.LEFT);
        lblTitle.setFont(new Font("sansserif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(7, 164, 121));
        pnlTitle.add(lblTitle);
        add(pnlTitle, BorderLayout.NORTH);
        
        // Panel chính
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2", "[grow 30][grow 70]", "[]"));
        mainPanel.setOpaque(false);
        
        // Form panel
        JPanel formPanel = new JPanel(new MigLayout("wrap 2", "[][grow]", "[]10[]10[]10[]10[]10[]10[]"));
        formPanel.setOpaque(false);
        
        // Các components của form
        txtMaKH = new JTextField();
        txtHoTen = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        
        // Spinner cho ngày sinh
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        SpinnerDateModel modelNgaySinh = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        
        dateNgaySinh = new JSpinner(modelNgaySinh);
        JSpinner.DateEditor editorNgaySinh = new JSpinner.DateEditor(dateNgaySinh, "dd/MM/yyyy");
        dateNgaySinh.setEditor(editorNgaySinh);
        
        txtDiaChi = new JTextArea(3, 20);
        txtDiaChi.setLineWrap(true);
        
        // Thêm các components vào form
        formPanel.add(new JLabel("Mã Khách Hàng:"));
        formPanel.add(txtMaKH, "growx");
        formPanel.add(new JLabel("Họ Tên:"));
        formPanel.add(txtHoTen, "growx");
        formPanel.add(new JLabel("Số Điện Thoại:"));
        formPanel.add(txtSDT, "growx");
        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail, "growx");
        formPanel.add(new JLabel("Ngày Sinh:"));
        formPanel.add(dateNgaySinh, "growx");
        formPanel.add(new JLabel("Địa Chỉ:"));
        formPanel.add(new JScrollPane(txtDiaChi), "growx");
        
        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnMoi = new JButton("Mới");
        
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnMoi);
        
        formPanel.add(buttonPanel, "span 2");
        
        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
        tablePanel.setOpaque(false);
        
        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(new MigLayout("", "[]5[grow]5[]", "[]"));
        searchPanel.setOpaque(false);
        
        searchPanel.add(new JLabel("Tìm Kiếm:"));
        txtSearch = new JTextField();
        searchPanel.add(txtSearch, "growx");
        JButton btnSearch = new JButton("Tìm");
        searchPanel.add(btnSearch);
        
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        
        // Bảng dữ liệu
        String[] columns = {"Mã KH", "Họ Tên", "SĐT", "Email", "Ngày Sinh", "Địa Chỉ"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableKhachHang = new JTable(tableModel);
        
        // Điều chỉnh chiều cao của hàng
        tableKhachHang.setRowHeight(25);
        
        // Điều chỉnh font chữ
        tableKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableKhachHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Căn giữa header
        ((DefaultTableCellRenderer) tableKhachHang.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        
        // Tạo renderer cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        // Áp dụng renderer và độ rộng cho từng cột
        tableKhachHang.getColumnModel().getColumn(0).setPreferredWidth(80);     // Mã KH
        tableKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        tableKhachHang.getColumnModel().getColumn(1).setPreferredWidth(150);    // Họ Tên
        tableKhachHang.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        
        tableKhachHang.getColumnModel().getColumn(2).setPreferredWidth(100);    // SĐT
        tableKhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tableKhachHang.getColumnModel().getColumn(3).setPreferredWidth(150);    // Email
        tableKhachHang.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        
        tableKhachHang.getColumnModel().getColumn(4).setPreferredWidth(100);    // Ngày Sinh
        tableKhachHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tableKhachHang.getColumnModel().getColumn(5).setPreferredWidth(200);    // Địa Chỉ
        tableKhachHang.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);
        
        // Cho phép sắp xếp
        tableKhachHang.setAutoCreateRowSorter(true);
        
        // Tạo scroll pane với kích thước lớn hơn
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm form panel và table panel vào main panel
        mainPanel.add(formPanel);
        mainPanel.add(tablePanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Xử lý sự kiện
        btnThem.addActionListener((ActionEvent e) -> {
            themKhachHang();
        });
        
        btnSua.addActionListener((ActionEvent e) -> {
            suaKhachHang();
        });
        
        btnXoa.addActionListener((ActionEvent e) -> {
            xoaKhachHang();
        });
        
        btnMoi.addActionListener((ActionEvent e) -> {
            lamMoi();
        });
        
        btnSearch.addActionListener((ActionEvent e) -> {
            timKiem();
        });
        
        tableKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableKhachHang.getSelectedRow();
                if (row >= 0) {
                    showDetail(row);
                }
            }
        });
    }
    
    private void loadData() {
        List<KhachHang> listKhachHang = KhachHangBLL.getAll();
        
        DefaultTableModel model = (DefaultTableModel) tableKhachHang.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (KhachHang kh : listKhachHang) {
            model.addRow(new Object[] {
                kh.getMaKH(),
                kh.getHoTen(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getNgaySinh() != null ? sdf.format(kh.getNgaySinh()) : "",
                kh.getDiaChi()
            });
        }
    }
    
    private void showDetail(int row) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            txtMaKH.setText(tableKhachHang.getValueAt(row, 0).toString());
            txtHoTen.setText(tableKhachHang.getValueAt(row, 1).toString());
            txtSDT.setText(tableKhachHang.getValueAt(row, 2).toString());
            txtEmail.setText(tableKhachHang.getValueAt(row, 3).toString());
            
            String ngaySinh = tableKhachHang.getValueAt(row, 4).toString();
            if (!ngaySinh.isEmpty()) {
                dateNgaySinh.setValue(sdf.parse(ngaySinh));
            }
            
            txtDiaChi.setText(tableKhachHang.getValueAt(row, 5).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void themKhachHang() {
        try {
            KhachHang kh = new KhachHang();
            kh.setMaKH(txtMaKH.getText().trim());
            kh.setHoTen(txtHoTen.getText().trim());
            kh.setSdt(txtSDT.getText().trim());
            kh.setEmail(txtEmail.getText().trim());
            kh.setNgaySinh((Date) dateNgaySinh.getValue());
            kh.setDiaChi(txtDiaChi.getText().trim());
            
            String error = KhachHangBLL.add(kh);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaKhachHang() {
        try {
            KhachHang kh = new KhachHang();
            kh.setMaKH(txtMaKH.getText().trim());
            kh.setHoTen(txtHoTen.getText().trim());
            kh.setSdt(txtSDT.getText().trim());
            kh.setEmail(txtEmail.getText().trim());
            kh.setNgaySinh((Date) dateNgaySinh.getValue());
            kh.setDiaChi(txtDiaChi.getText().trim());
            
            String error = KhachHangBLL.update(kh);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaKhachHang() {
        String maKH = txtMaKH.getText().trim();
        if (maKH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa khách hàng này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String error = KhachHangBLL.delete(maKH);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lamMoi() {
        txtMaKH.setText(KhachHangBLL.generateNewMaKH());
        txtHoTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        dateNgaySinh.setValue(new Date());
        txtDiaChi.setText("");
    }
    
    private void timKiem() {
        String keyword = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        List<KhachHang> list = KhachHangBLL.search(keyword);
        for (KhachHang kh : list) {
            Object[] row = {
                kh.getMaKH(),
                kh.getHoTen(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getNgaySinh() != null ? sdf.format(kh.getNgaySinh()) : "",
                kh.getDiaChi()
            };
            tableModel.addRow(row);
        }
    }
} 