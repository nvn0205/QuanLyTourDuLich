package View;

import BLL.TourBLL;
import Entities.Tour;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import net.miginfocom.swing.MigLayout;

public class QuanLyTourForm extends JPanel {
    private JTable tableTour;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JTextField txtMaTour;
    private JTextField txtTenTour;
    private JTextField txtDiaDiem;
    private JSpinner dateKhoiHanh;
    private JSpinner dateKetThuc;
    private JTextField txtSoNguoi;
    private JTextField txtGia;
    private JComboBox<String> cboTrangThai;
    private JTextArea txtMoTa;
    private boolean isManager = false;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    
    public QuanLyTourForm() {
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
        JLabel lblTitle = new JLabel("QUẢN LÝ TOUR", JLabel.LEFT);
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
        txtMaTour = new JTextField();
        txtTenTour = new JTextField();
        txtDiaDiem = new JTextField();
        
        // Tạo spinner cho ngày tháng
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        SpinnerDateModel modelKhoiHanh = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        SpinnerDateModel modelKetThuc = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        
        dateKhoiHanh = new JSpinner(modelKhoiHanh);
        dateKetThuc = new JSpinner(modelKetThuc);
        
        JSpinner.DateEditor editorKhoiHanh = new JSpinner.DateEditor(dateKhoiHanh, "dd/MM/yyyy");
        JSpinner.DateEditor editorKetThuc = new JSpinner.DateEditor(dateKetThuc, "dd/MM/yyyy");
        dateKhoiHanh.setEditor(editorKhoiHanh);
        dateKetThuc.setEditor(editorKetThuc);
        
        txtSoNguoi = new JTextField();
        txtGia = new JTextField();
        cboTrangThai = new JComboBox<>(new String[]{"Mở", "Đóng", "Đã hủy"});
        txtMoTa = new JTextArea(3, 20);
        txtMoTa.setLineWrap(true);
        
        // Thêm các components vào form
        formPanel.add(new JLabel("Mã Tour:"));
        formPanel.add(txtMaTour, "growx");
        formPanel.add(new JLabel("Tên Tour:"));
        formPanel.add(txtTenTour, "growx");
        formPanel.add(new JLabel("Địa Điểm:"));
        formPanel.add(txtDiaDiem, "growx");
        formPanel.add(new JLabel("Ngày Khởi Hành:"));
        formPanel.add(dateKhoiHanh, "growx");
        formPanel.add(new JLabel("Ngày Kết Thúc:"));
        formPanel.add(dateKetThuc, "growx");
        formPanel.add(new JLabel("Số Người:"));
        formPanel.add(txtSoNguoi, "growx");
        formPanel.add(new JLabel("Giá:"));
        formPanel.add(txtGia, "growx");
        formPanel.add(new JLabel("Trạng Thái:"));
        formPanel.add(cboTrangThai, "growx");
        formPanel.add(new JLabel("Mô Tả:"));
        formPanel.add(new JScrollPane(txtMoTa), "growx");
        
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
        String[] columns = {"Mã Tour", "Tên Tour", "Địa Điểm", "Ngày KH", "Ngày KT", 
            "Số Người", "Giá", "Trạng Thái", "Mô Tả"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTour = new JTable(tableModel);
        
        // Điều chỉnh chiều cao của hàng
        tableTour.setRowHeight(25);
        
        // Điều chỉnh font chữ
        tableTour.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableTour.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Căn giữa header
        ((DefaultTableCellRenderer) tableTour.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        
        // Tạo renderer cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        // Áp dụng renderer và độ rộng cho từng cột
        tableTour.getColumnModel().getColumn(0).setPreferredWidth(80);     // Mã Tour
        tableTour.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        tableTour.getColumnModel().getColumn(1).setPreferredWidth(200);    // Tên Tour
        tableTour.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        
        tableTour.getColumnModel().getColumn(2).setPreferredWidth(150);    // Địa Điểm
        tableTour.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
        
        tableTour.getColumnModel().getColumn(3).setPreferredWidth(100);    // Ngày KH
        tableTour.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tableTour.getColumnModel().getColumn(4).setPreferredWidth(100);    // Ngày KT
        tableTour.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tableTour.getColumnModel().getColumn(5).setPreferredWidth(80);     // Số Người
        tableTour.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        tableTour.getColumnModel().getColumn(6).setPreferredWidth(120);    // Giá
        tableTour.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        
        tableTour.getColumnModel().getColumn(7).setPreferredWidth(100);    // Trạng Thái
        tableTour.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        
        tableTour.getColumnModel().getColumn(8).setPreferredWidth(200);    // Mô Tả
        tableTour.getColumnModel().getColumn(8).setCellRenderer(leftRenderer);
        
        // Cho phép sắp xếp
        tableTour.setAutoCreateRowSorter(true);
        
        // Tạo scroll pane với kích thước lớn hơn
        JScrollPane scrollPane = new JScrollPane(tableTour);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm form panel và table panel vào main panel
        mainPanel.add(formPanel);
        mainPanel.add(tablePanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Xử lý sự kiện
        btnThem.addActionListener((ActionEvent e) -> {
            themTour();
        });
        
        btnSua.addActionListener((ActionEvent e) -> {
            suaTour();
        });
        
        btnXoa.addActionListener((ActionEvent e) -> {
            xoaTour();
        });
        
        btnMoi.addActionListener((ActionEvent e) -> {
            lamMoi();
        });
        
        btnSearch.addActionListener((ActionEvent e) -> {
            timKiem();
        });
        
        tableTour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableTour.getSelectedRow();
                if (row >= 0) {
                    showDetail(row);
                }
            }
        });
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        List<Tour> list = TourBLL.getAll();
        for (Tour tour : list) {
            Object[] row = {
                tour.getMaTour(),
                tour.getTenTour(),
                tour.getDiaDiem(),
                tour.getNgayKhoiHanh() != null ? sdf.format(tour.getNgayKhoiHanh()) : "",
                tour.getNgayKetThuc() != null ? sdf.format(tour.getNgayKetThuc()) : "",
                tour.getSoNguoi(),
                TourBLL.formatCurrency(tour.getGia()),
                tour.getTrangThai(),
                tour.getMoTa()
            };
            tableModel.addRow(row);
        }
    }
    
    private void showDetail(int row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            txtMaTour.setText(tableTour.getValueAt(row, 0).toString());
            txtTenTour.setText(tableTour.getValueAt(row, 1).toString());
            txtDiaDiem.setText(tableTour.getValueAt(row, 2).toString());
            
            String ngayKH = tableTour.getValueAt(row, 3).toString();
            String ngayKT = tableTour.getValueAt(row, 4).toString();
            if (!ngayKH.isEmpty()) {
                dateKhoiHanh.setValue(sdf.parse(ngayKH));
            }
            if (!ngayKT.isEmpty()) {
                dateKetThuc.setValue(sdf.parse(ngayKT));
            }
            
            txtSoNguoi.setText(tableTour.getValueAt(row, 5).toString());
            String giaStr = tableTour.getValueAt(row, 6).toString().replace("VNĐ", "").replace(",", "").trim();
            txtGia.setText(giaStr);
            cboTrangThai.setSelectedItem(tableTour.getValueAt(row, 7));
            txtMoTa.setText(tableTour.getValueAt(row, 8).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void themTour() {
        try {
            // Lấy dữ liệu từ form
            Tour tour = new Tour();
            tour.setMaTour(txtMaTour.getText().trim());
            tour.setTenTour(txtTenTour.getText().trim());
            tour.setDiaDiem(txtDiaDiem.getText().trim());
            tour.setNgayKhoiHanh((Date) dateKhoiHanh.getValue());
            tour.setNgayKetThuc((Date) dateKetThuc.getValue());
            tour.setSoNguoi(Integer.parseInt(txtSoNguoi.getText().trim()));
            tour.setGia(Double.parseDouble(txtGia.getText().trim()));
            tour.setTrangThai(cboTrangThai.getSelectedItem().toString());
            tour.setMoTa(txtMoTa.getText().trim());
            
            // Thêm tour mới
            if (TourBLL.add(tour)) {
                JOptionPane.showMessageDialog(this, "Thêm tour thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm tour thất bại!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số người và giá phải là số!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaTour() {
        try {
            // Lấy dữ liệu từ form
            Tour tour = new Tour();
            tour.setMaTour(txtMaTour.getText().trim());
            tour.setTenTour(txtTenTour.getText().trim());
            tour.setDiaDiem(txtDiaDiem.getText().trim());
            tour.setNgayKhoiHanh((Date) dateKhoiHanh.getValue());
            tour.setNgayKetThuc((Date) dateKetThuc.getValue());
            tour.setSoNguoi(Integer.parseInt(txtSoNguoi.getText().trim()));
            tour.setGia(Double.parseDouble(txtGia.getText().trim()));
            tour.setTrangThai(cboTrangThai.getSelectedItem().toString());
            tour.setMoTa(txtMoTa.getText().trim());
            
            // Cập nhật tour
            if (TourBLL.update(tour)) {
                JOptionPane.showMessageDialog(this, "Cập nhật tour thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật tour thất bại!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số người và giá phải là số!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaTour() {
        String maTour = txtMaTour.getText().trim();
        if (maTour.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tour cần xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa tour này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (TourBLL.delete(maTour)) {
                JOptionPane.showMessageDialog(this, "Xóa tour thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa tour thất bại!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lamMoi() {
        txtMaTour.setText(TourBLL.generateNewMaTour());
        txtTenTour.setText("");
        txtDiaDiem.setText("");
        dateKhoiHanh.setValue(new Date());
        dateKetThuc.setValue(new Date());
        txtSoNguoi.setText("");
        txtGia.setText("");
        cboTrangThai.setSelectedIndex(0);
        txtMoTa.setText("");
    }
    
    private void timKiem() {
        String keyword = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        List<Tour> list = TourBLL.search(keyword);
        for (Tour tour : list) {
            Object[] row = {
                tour.getMaTour(),
                tour.getTenTour(),
                tour.getDiaDiem(),
                tour.getNgayKhoiHanh() != null ? sdf.format(tour.getNgayKhoiHanh()) : "",
                tour.getNgayKetThuc() != null ? sdf.format(tour.getNgayKetThuc()) : "",
                tour.getSoNguoi(),
                TourBLL.formatCurrency(tour.getGia()),
                tour.getTrangThai(),
                tour.getMoTa()
            };
            tableModel.addRow(row);
        }
    }
} 