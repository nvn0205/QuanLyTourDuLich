package View;

import BLL.NhanVienBLL;
import Entities.NhanVien;
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

public class QuanLyNhanVienForm extends JPanel {
    private JTable tableNhanVien;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JTextField txtMaNV;
    private JTextField txtHoTen;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JSpinner dateNgaySinh;
    private JTextArea txtDiaChi;
    private JComboBox<String> cboGioiTinh;
    private JComboBox<String> cboChucVu;
    
    public QuanLyNhanVienForm() {
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(242, 242, 242));
        
        // Panel tiêu đề
        JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlTitle.setOpaque(false);
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", JLabel.LEFT);
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
        txtMaNV = new JTextField();
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
        
        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cboChucVu = new JComboBox<>(new String[]{
            "Hướng dẫn viên", "Nhân viên bán hàng", "Quản lý", "Nhân viên kế toán"
        });
        
        // Thêm các components vào form
        formPanel.add(new JLabel("Mã Nhân Viên:"));
        formPanel.add(txtMaNV, "growx");
        formPanel.add(new JLabel("Họ Tên:"));
        formPanel.add(txtHoTen, "growx");
        formPanel.add(new JLabel("Số Điện Thoại:"));
        formPanel.add(txtSDT, "growx");
        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail, "growx");
        formPanel.add(new JLabel("Ngày Sinh:"));
        formPanel.add(dateNgaySinh, "growx");
        formPanel.add(new JLabel("Giới Tính:"));
        formPanel.add(cboGioiTinh, "growx");
        formPanel.add(new JLabel("Chức Vụ:"));
        formPanel.add(cboChucVu, "growx");
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
        String[] columns = {"Mã NV", "Họ Tên", "SĐT", "Email", "Ngày Sinh", "Giới Tính", "Chức Vụ", "Địa Chỉ"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableNhanVien = new JTable(tableModel);
        
        // Điều chỉnh chiều cao của hàng
        tableNhanVien.setRowHeight(25);
        
        // Điều chỉnh font chữ
        tableNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Căn giữa header
        ((DefaultTableCellRenderer) tableNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        
        // Tạo renderer cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        // Áp dụng renderer và độ rộng cho từng cột
        tableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(80);     // Mã NV
        tableNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        tableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);    // Họ Tên
        tableNhanVien.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        
        tableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);    // SĐT
        tableNhanVien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(150);    // Email
        tableNhanVien.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
        
        tableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);    // Ngày Sinh
        tableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(80);     // Giới Tính
        tableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        tableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(120);    // Chức Vụ
        tableNhanVien.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);
        
        tableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(200);    // Địa Chỉ
        tableNhanVien.getColumnModel().getColumn(7).setCellRenderer(leftRenderer);
        
        // Cho phép sắp xếp
        tableNhanVien.setAutoCreateRowSorter(true);
        
        // Tạo scroll pane với kích thước lớn hơn
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm form panel và table panel vào main panel
        mainPanel.add(formPanel);
        mainPanel.add(tablePanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Xử lý sự kiện
        btnThem.addActionListener((ActionEvent e) -> {
            themNhanVien();
        });
        
        btnSua.addActionListener((ActionEvent e) -> {
            suaNhanVien();
        });
        
        btnXoa.addActionListener((ActionEvent e) -> {
            xoaNhanVien();
        });
        
        btnMoi.addActionListener((ActionEvent e) -> {
            lamMoi();
        });
        
        btnSearch.addActionListener((ActionEvent e) -> {
            timKiem();
        });
        
        tableNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableNhanVien.getSelectedRow();
                if (row >= 0) {
                    showDetail(row);
                }
            }
        });
    }
    
    private void loadData() {
        List<NhanVien> listNhanVien = NhanVienBLL.getAll();
        
        DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (NhanVien nv : listNhanVien) {
            model.addRow(new Object[] {
                nv.getMaNV(),
                nv.getHoTen(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "",
                nv.getGioiTinh(),
                nv.getChucVu(),
                nv.getDiaChi()
            });
        }
    }
    
    private void showDetail(int row) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            txtMaNV.setText(tableNhanVien.getValueAt(row, 0).toString());
            txtHoTen.setText(tableNhanVien.getValueAt(row, 1).toString());
            txtSDT.setText(tableNhanVien.getValueAt(row, 2).toString());
            txtEmail.setText(tableNhanVien.getValueAt(row, 3).toString());
            
            String ngaySinh = tableNhanVien.getValueAt(row, 4).toString();
            if (!ngaySinh.isEmpty()) {
                dateNgaySinh.setValue(sdf.parse(ngaySinh));
            }
            
            cboGioiTinh.setSelectedItem(tableNhanVien.getValueAt(row, 5).toString());
            cboChucVu.setSelectedItem(tableNhanVien.getValueAt(row, 6).toString());
            txtDiaChi.setText(tableNhanVien.getValueAt(row, 7).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void themNhanVien() {
        try {
            NhanVien nv = new NhanVien();
            nv.setMaNV(txtMaNV.getText().trim());
            nv.setHoTen(txtHoTen.getText().trim());
            nv.setSdt(txtSDT.getText().trim());
            nv.setEmail(txtEmail.getText().trim());
            nv.setNgaySinh((Date) dateNgaySinh.getValue());
            nv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
            nv.setChucVu(cboChucVu.getSelectedItem().toString());
            nv.setDiaChi(txtDiaChi.getText().trim());
            
            String error = NhanVienBLL.add(nv);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
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
    
    private void suaNhanVien() {
        try {
            NhanVien nv = new NhanVien();
            nv.setMaNV(txtMaNV.getText().trim());
            nv.setHoTen(txtHoTen.getText().trim());
            nv.setSdt(txtSDT.getText().trim());
            nv.setEmail(txtEmail.getText().trim());
            nv.setNgaySinh((Date) dateNgaySinh.getValue());
            nv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
            nv.setChucVu(cboChucVu.getSelectedItem().toString());
            nv.setDiaChi(txtDiaChi.getText().trim());
            
            String error = NhanVienBLL.update(nv);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
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
    
    private void xoaNhanVien() {
        String maNV = txtMaNV.getText().trim();
        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa nhân viên này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String error = NhanVienBLL.delete(maNV);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lamMoi() {
        txtMaNV.setText(NhanVienBLL.generateNewMaNV());
        txtHoTen.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        dateNgaySinh.setValue(new Date());
        cboGioiTinh.setSelectedIndex(0);
        cboChucVu.setSelectedIndex(0);
        txtDiaChi.setText("");
    }
    
    private void timKiem() {
        String keyword = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        List<NhanVien> list = NhanVienBLL.search(keyword);
        for (NhanVien nv : list) {
            Object[] row = {
                nv.getMaNV(),
                nv.getHoTen(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "",
                nv.getGioiTinh(),
                nv.getChucVu(),
                nv.getDiaChi()
            };
            tableModel.addRow(row);
        }
    }
} 