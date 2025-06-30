package View;

import BLL.DoiTacBLL;
import Entities.DoiTac;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class QuanLyDoiTacForm extends JPanel {
    private JTable tableDoiTac;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JTextField txtMaDT;
    private JTextField txtTenDT;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextArea txtDiaChi;
    private JComboBox<String> cboLoaiDoiTac;
    private JTextArea txtGhiChu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private boolean isManager = false;
    
    public QuanLyDoiTacForm() {
        initComponents();
        loadData();
    }
    
    public void setPermission(String role) {
        isManager = "Quản lý".equals(role);
        // Cập nhật trạng thái các nút
        btnThem.setEnabled(isManager);
        btnSua.setEnabled(isManager);
        btnXoa.setEnabled(isManager);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(242, 242, 242));
        
        // Panel tiêu đề
        JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlTitle.setOpaque(false);
        JLabel lblTitle = new JLabel("QUẢN LÝ ĐỐI TÁC", JLabel.LEFT);
        lblTitle.setFont(new Font("sansserif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(7, 164, 121));
        pnlTitle.add(lblTitle);
        add(pnlTitle, BorderLayout.NORTH);
        
        // Panel chính
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2", "[grow 30][grow 70]", "[]"));
        mainPanel.setOpaque(false);
        
        // Form panel
        JPanel formPanel = new JPanel(new MigLayout("wrap 2", "[][grow]", "[]10[]10[]10[]10[]10[]10[]10[]"));
        formPanel.setOpaque(false);
        
        // Các components của form
        txtMaDT = new JTextField();
        txtTenDT = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        
        txtDiaChi = new JTextArea(3, 20);
        txtDiaChi.setLineWrap(true);
        
        cboLoaiDoiTac = new JComboBox<>(new String[]{
            "Đại lý tour", "Khách sạn", "Nhà hàng"
        });
        
        txtGhiChu = new JTextArea(3, 20);
        txtGhiChu.setLineWrap(true);
        
        // Thêm các components vào form
        formPanel.add(new JLabel("Mã Đối Tác:"));
        formPanel.add(txtMaDT, "growx");
        formPanel.add(new JLabel("Tên Đối Tác:"));
        formPanel.add(txtTenDT, "growx");
        formPanel.add(new JLabel("Số Điện Thoại:"));
        formPanel.add(txtSDT, "growx");
        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail, "growx");
        formPanel.add(new JLabel("Địa Chỉ:"));
        formPanel.add(new JScrollPane(txtDiaChi), "growx");
        formPanel.add(new JLabel("Loại Đối Tác:"));
        formPanel.add(cboLoaiDoiTac, "growx");
        formPanel.add(new JLabel("Ghi Chú:"));
        formPanel.add(new JScrollPane(txtGhiChu), "growx");
        
        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        JButton btnMoi = new JButton("Mới");
        
        // Mặc định disable các nút cho đến khi biết role
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        
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
        String[] columns = {"Mã ĐT", "Tên Đối Tác", "SĐT", "Email", "Địa Chỉ", "Loại Đối Tác", "Ghi Chú"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableDoiTac = new JTable(tableModel);
        
        // Điều chỉnh chiều cao của hàng
        tableDoiTac.setRowHeight(25);
        
        // Điều chỉnh font chữ
        tableDoiTac.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableDoiTac.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Căn giữa header
        ((DefaultTableCellRenderer) tableDoiTac.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        
        // Tạo renderer cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        // Áp dụng renderer và độ rộng cho từng cột
        tableDoiTac.getColumnModel().getColumn(0).setPreferredWidth(80);     // Mã ĐT
        tableDoiTac.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        tableDoiTac.getColumnModel().getColumn(1).setPreferredWidth(150);    // Tên Đối Tác
        tableDoiTac.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        
        tableDoiTac.getColumnModel().getColumn(2).setPreferredWidth(100);    // SĐT
        tableDoiTac.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tableDoiTac.getColumnModel().getColumn(3).setPreferredWidth(150);    // Email
        tableDoiTac.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        
        tableDoiTac.getColumnModel().getColumn(4).setPreferredWidth(200);    // Địa Chỉ
        tableDoiTac.getColumnModel().getColumn(4).setCellRenderer(leftRenderer);
        
        tableDoiTac.getColumnModel().getColumn(5).setPreferredWidth(100);    // Loại Đối Tác
        tableDoiTac.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        tableDoiTac.getColumnModel().getColumn(6).setPreferredWidth(200);    // Ghi Chú
        tableDoiTac.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);
        
        // Cho phép sắp xếp
        tableDoiTac.setAutoCreateRowSorter(true);
        
        // Tạo scroll pane với kích thước lớn hơn
        JScrollPane scrollPane = new JScrollPane(tableDoiTac);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm form panel và table panel vào main panel
        mainPanel.add(formPanel);
        mainPanel.add(tablePanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Xử lý sự kiện
        btnThem.addActionListener((ActionEvent e) -> {
            themDoiTac();
        });
        
        btnSua.addActionListener((ActionEvent e) -> {
            suaDoiTac();
        });
        
        btnXoa.addActionListener((ActionEvent e) -> {
            xoaDoiTac();
        });
        
        btnMoi.addActionListener((ActionEvent e) -> {
            lamMoi();
        });
        
        btnSearch.addActionListener((ActionEvent e) -> {
            timKiem();
        });
        
        tableDoiTac.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableDoiTac.getSelectedRow();
                if (row >= 0) {
                    showDetail(row);
                }
            }
        });
    }
    
    private void loadData() {
        List<DoiTac> listDoiTac = DoiTacBLL.getAll();
        
        DefaultTableModel model = (DefaultTableModel) tableDoiTac.getModel();
        model.setRowCount(0);
        
        for (DoiTac dt : listDoiTac) {
            model.addRow(new Object[] {
                dt.getMaDT(),
                dt.getTenDT(),
                dt.getSdt(),
                dt.getEmail(),
                dt.getDiaChi(),
                dt.getLoaiDoiTac(),
                dt.getGhiChu()
            });
        }
    }
    
    private void showDetail(int row) {
        txtMaDT.setText(tableDoiTac.getValueAt(row, 0).toString());
        txtTenDT.setText(tableDoiTac.getValueAt(row, 1).toString());
        txtSDT.setText(tableDoiTac.getValueAt(row, 2).toString());
        txtEmail.setText(tableDoiTac.getValueAt(row, 3).toString());
        txtDiaChi.setText(tableDoiTac.getValueAt(row, 4).toString());
        cboLoaiDoiTac.setSelectedItem(tableDoiTac.getValueAt(row, 5).toString());
        txtGhiChu.setText(tableDoiTac.getValueAt(row, 6).toString());
    }
    
    private void themDoiTac() {
        try {
            DoiTac dt = new DoiTac();
            dt.setMaDT(txtMaDT.getText().trim());
            dt.setTenDT(txtTenDT.getText().trim());
            dt.setSdt(txtSDT.getText().trim());
            dt.setEmail(txtEmail.getText().trim());
            dt.setDiaChi(txtDiaChi.getText().trim());
            dt.setLoaiDoiTac(cboLoaiDoiTac.getSelectedItem().toString());
            dt.setGhiChu(txtGhiChu.getText().trim());
            
            String error = DoiTacBLL.add(dt);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Thêm đối tác thành công!");
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
    
    private void suaDoiTac() {
        try {
            DoiTac dt = new DoiTac();
            dt.setMaDT(txtMaDT.getText().trim());
            dt.setTenDT(txtTenDT.getText().trim());
            dt.setSdt(txtSDT.getText().trim());
            dt.setEmail(txtEmail.getText().trim());
            dt.setDiaChi(txtDiaChi.getText().trim());
            dt.setLoaiDoiTac(cboLoaiDoiTac.getSelectedItem().toString());
            dt.setGhiChu(txtGhiChu.getText().trim());
            
            String error = DoiTacBLL.update(dt);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Cập nhật đối tác thành công!");
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
    
    private void xoaDoiTac() {
        String maDT = txtMaDT.getText().trim();
        if (maDT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đối tác cần xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa đối tác này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String error = DoiTacBLL.delete(maDT);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Xóa đối tác thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lamMoi() {
        txtMaDT.setText(DoiTacBLL.generateNewMaDT());
        txtTenDT.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        cboLoaiDoiTac.setSelectedIndex(0);
        txtGhiChu.setText("");
    }
    
    private void timKiem() {
        String keyword = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        
        List<DoiTac> list = DoiTacBLL.search(keyword);
        for (DoiTac dt : list) {
            Object[] row = {
                dt.getMaDT(),
                dt.getTenDT(),
                dt.getSdt(),
                dt.getEmail(),
                dt.getDiaChi(),
                dt.getLoaiDoiTac(),
                dt.getGhiChu()
            };
            tableModel.addRow(row);
        }
    }
} 