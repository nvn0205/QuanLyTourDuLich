package View;

import BLL.DatTourBLL;
import BLL.TourBLL;
import BLL.DoiTacBLL;
import BLL.NhanVienBLL;
import Entities.DatTour;
import Entities.Tour;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class QuanLyDatTourForm extends JPanel {
    private JTable tableDatTour;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JTextField txtMaDatTour;
    private JComboBox<String> cboMaTour;
    private JTextField txtMaKH;
    private JSpinner dateNgayDat;
    private JTextField txtSoLuongKhach;
    private JTextField txtTongTien;
    private JTextField txtSoTienDaThanhToan;
    private JComboBox<String> cboMaDT;
    private JComboBox<String> cboMaNV;
    private JComboBox<String> cboTrangThai;
    
    public QuanLyDatTourForm() {
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(242, 242, 242));
        
        // Panel tiêu đề
        JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlTitle.setOpaque(false);
        JLabel lblTitle = new JLabel("QUẢN LÝ ĐẶT TOUR", JLabel.LEFT);
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
        txtMaDatTour = new JTextField();
        
        // ComboBox cho mã tour
        cboMaTour = new JComboBox<>();
        loadTourList();
        
        txtMaKH = new JTextField();
        
        // Spinner cho ngày đặt
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        SpinnerDateModel modelNgayDat = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        
        dateNgayDat = new JSpinner(modelNgayDat);
        JSpinner.DateEditor editorNgayDat = new JSpinner.DateEditor(dateNgayDat, "dd/MM/yyyy");
        dateNgayDat.setEditor(editorNgayDat);
        
        txtSoLuongKhach = new JTextField();
        txtTongTien = new JTextField();
        txtSoTienDaThanhToan = new JTextField("0");
        
        // ComboBox cho mã đối tác
        cboMaDT = new JComboBox<>();
        loadDoiTacList();
        
        // ComboBox cho mã nhân viên
        cboMaNV = new JComboBox<>();
        loadNhanVienList();
        
        cboTrangThai = new JComboBox<>(new String[]{"Chờ xác nhận", "Đã xác nhận", "Đã hủy"});
        
        // Thêm các components vào form
        formPanel.add(new JLabel("Mã Đặt Tour:"));
        formPanel.add(txtMaDatTour, "growx");
        formPanel.add(new JLabel("Mã Tour:"));
        formPanel.add(cboMaTour, "growx");
        formPanel.add(new JLabel("Mã Khách Hàng:"));
        formPanel.add(txtMaKH, "growx");
        formPanel.add(new JLabel("Ngày Đặt:"));
        formPanel.add(dateNgayDat, "growx");
        formPanel.add(new JLabel("Số Lượng Người:"));
        formPanel.add(txtSoLuongKhach, "growx");
        formPanel.add(new JLabel("Tổng Tiền:"));
        formPanel.add(txtTongTien, "growx");
        formPanel.add(new JLabel("Số Tiền Đã Thanh Toán:"));
        formPanel.add(txtSoTienDaThanhToan, "growx");
        formPanel.add(new JLabel("Mã Đối Tác:"));
        formPanel.add(cboMaDT, "growx");
        formPanel.add(new JLabel("Mã Nhân Viên:"));
        formPanel.add(cboMaNV, "growx");
        formPanel.add(new JLabel("Trạng Thái:"));
        formPanel.add(cboTrangThai, "growx");
        
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
        String[] columns = {"Mã ĐT", "Mã Tour", "Mã KH", "Ngày Đặt", 
            "SL Người", "Tổng Tiền", "Đã Thanh Toán", "Mã ĐT", "Mã NV", "Trạng Thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableDatTour = new JTable(tableModel);
        
        // Điều chỉnh chiều cao của hàng
        tableDatTour.setRowHeight(25);
        
        // Điều chỉnh font chữ
        tableDatTour.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableDatTour.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Căn giữa header
        ((DefaultTableCellRenderer) tableDatTour.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        
        // Tạo renderer cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        // Áp dụng renderer và độ rộng cho từng cột
        tableDatTour.getColumnModel().getColumn(0).setPreferredWidth(80);     // Mã ĐT
        tableDatTour.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        tableDatTour.getColumnModel().getColumn(1).setPreferredWidth(80);     // Mã Tour
        tableDatTour.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        tableDatTour.getColumnModel().getColumn(2).setPreferredWidth(80);     // Mã KH
        tableDatTour.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tableDatTour.getColumnModel().getColumn(3).setPreferredWidth(100);    // Ngày Đặt
        tableDatTour.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tableDatTour.getColumnModel().getColumn(4).setPreferredWidth(80);     // SL Người
        tableDatTour.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tableDatTour.getColumnModel().getColumn(5).setPreferredWidth(120);    // Tổng Tiền
        tableDatTour.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        
        tableDatTour.getColumnModel().getColumn(6).setPreferredWidth(120);    // Đã Thanh Toán
        tableDatTour.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        
        tableDatTour.getColumnModel().getColumn(7).setPreferredWidth(80);     // Mã ĐT
        tableDatTour.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        
        tableDatTour.getColumnModel().getColumn(8).setPreferredWidth(80);     // Mã NV
        tableDatTour.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        
        tableDatTour.getColumnModel().getColumn(9).setPreferredWidth(100);    // Trạng Thái
        tableDatTour.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        
        // Cho phép sắp xếp
        tableDatTour.setAutoCreateRowSorter(true);
        
        // Tạo scroll pane với kích thước lớn hơn
        JScrollPane scrollPane = new JScrollPane(tableDatTour);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm form panel và table panel vào main panel
        mainPanel.add(formPanel);
        mainPanel.add(tablePanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Xử lý sự kiện
        btnThem.addActionListener((ActionEvent e) -> {
            themDatTour();
        });
        
        btnSua.addActionListener((ActionEvent e) -> {
            suaDatTour();
        });
        
        btnXoa.addActionListener((ActionEvent e) -> {
            xoaDatTour();
        });
        
        btnMoi.addActionListener((ActionEvent e) -> {
            lamMoi();
        });
        
        btnSearch.addActionListener((ActionEvent e) -> {
            timKiem();
        });
        
        tableDatTour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableDatTour.getSelectedRow();
                if (row >= 0) {
                    showDetail(row);
                }
            }
        });
        
        // Xử lý sự kiện khi chọn tour
        cboMaTour.addActionListener((ActionEvent e) -> {
            tinhTongTien();
        });
        
        // Xử lý sự kiện khi thay đổi số lượng khách
        txtSoLuongKhach.addActionListener((ActionEvent e) -> {
            tinhTongTien();
        });
    }
    
    private void loadTourList() {
        cboMaTour.removeAllItems();
        List<Tour> tours = TourBLL.getAll();
        for (Tour tour : tours) {
            cboMaTour.addItem(tour.getMaTour());
        }
    }
    
    private void loadDoiTacList() {
        cboMaDT.removeAllItems();
        List<String> listMaDT = DoiTacBLL.getAllMaDT();
        for (String maDT : listMaDT) {
            cboMaDT.addItem(maDT);
        }
    }
    
    private void loadNhanVienList() {
        cboMaNV.removeAllItems();
        List<String> listMaNV = NhanVienBLL.getAllMaNV();
        for (String maNV : listMaNV) {
            cboMaNV.addItem(maNV);
        }
    }
    
    private void tinhTongTien() {
        try {
            String maTour = cboMaTour.getSelectedItem().toString();
            int soLuong = Integer.parseInt(txtSoLuongKhach.getText().trim());
            
            List<Tour> tours = TourBLL.getAll();
            for (Tour tour : tours) {
                if (tour.getMaTour().equals(maTour)) {
                    double tongTien = tour.getGia() * soLuong;
                    txtTongTien.setText(String.valueOf(tongTien));
                    break;
                }
            }
        } catch (NumberFormatException e) {
            // Không làm gì khi chưa nhập số lượng
        }
    }
    
    private void loadData() {
        List<DatTour> listDatTour = DatTourBLL.getAll();
        
        DefaultTableModel model = (DefaultTableModel) tableDatTour.getModel();
        model.setRowCount(0);
        
        for (DatTour datTour : listDatTour) {
            model.addRow(new Object[] {
                datTour.getMaDatTour(),
                datTour.getMaTour(),
                datTour.getMaKH(),
                datTour.getNgayDat(),
                datTour.getSoLuongNguoi(),
                DatTourBLL.formatCurrency(datTour.getTongTien()),
                DatTourBLL.formatCurrency(datTour.getSoTienDaThanhToan()),
                datTour.getMaDT(),
                datTour.getMaNV(),
                datTour.getTrangThai()
            });
        }
    }
    
    private void showDetail(int row) {
        try {
            txtMaDatTour.setText(tableDatTour.getValueAt(row, 0).toString());
            cboMaTour.setSelectedItem(tableDatTour.getValueAt(row, 1).toString());
            txtMaKH.setText(tableDatTour.getValueAt(row, 2).toString());
            dateNgayDat.setValue(tableDatTour.getValueAt(row, 3));
            txtSoLuongKhach.setText(tableDatTour.getValueAt(row, 4).toString());
            
            // Xử lý tổng tiền
            String tongTienStr = tableDatTour.getValueAt(row, 5).toString()
                    .replace(" VNĐ", "").replace(",", "").trim();
            txtTongTien.setText(tongTienStr);
            
            // Xử lý số tiền đã thanh toán
            String daThanhToanStr = tableDatTour.getValueAt(row, 6).toString()
                    .replace(" VNĐ", "").replace(",", "").trim();
            txtSoTienDaThanhToan.setText(daThanhToanStr);
            
            cboMaDT.setSelectedItem(tableDatTour.getValueAt(row, 7).toString());
            cboMaNV.setSelectedItem(tableDatTour.getValueAt(row, 8).toString());
            cboTrangThai.setSelectedItem(tableDatTour.getValueAt(row, 9).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void themDatTour() {
        try {
            // Lấy dữ liệu từ form
            DatTour datTour = new DatTour();
            datTour.setMaDatTour(txtMaDatTour.getText().trim());
            datTour.setMaTour(cboMaTour.getSelectedItem().toString());
            datTour.setMaKH(txtMaKH.getText().trim());
            datTour.setNgayDat((Date) dateNgayDat.getValue());
            datTour.setSoLuongNguoi(Integer.parseInt(txtSoLuongKhach.getText().trim()));
            datTour.setTongTien(Double.parseDouble(txtTongTien.getText().trim()));
            datTour.setSoTienDaThanhToan(0.0); // Mặc định là 0
            datTour.setMaDT(cboMaDT.getSelectedItem().toString());
            datTour.setMaNV(cboMaNV.getSelectedItem().toString());
            datTour.setTrangThai(cboTrangThai.getSelectedItem().toString());
            
            // Thêm đặt tour mới
            String error = DatTourBLL.add(datTour);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Thêm đặt tour thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng người và tổng tiền phải là số!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaDatTour() {
        try {
            // Lấy dữ liệu từ form
            DatTour datTour = new DatTour();
            datTour.setMaDatTour(txtMaDatTour.getText().trim());
            datTour.setMaTour(cboMaTour.getSelectedItem().toString());
            datTour.setMaKH(txtMaKH.getText().trim());
            datTour.setNgayDat((Date) dateNgayDat.getValue());
            datTour.setSoLuongNguoi(Integer.parseInt(txtSoLuongKhach.getText().trim()));
            datTour.setTongTien(Double.parseDouble(txtTongTien.getText().trim()));
            datTour.setSoTienDaThanhToan(Double.parseDouble(txtSoTienDaThanhToan.getText().trim()));
            datTour.setMaDT(cboMaDT.getSelectedItem().toString());
            datTour.setMaNV(cboMaNV.getSelectedItem().toString());
            datTour.setTrangThai(cboTrangThai.getSelectedItem().toString());
            
            // Cập nhật đặt tour
            String error = DatTourBLL.update(datTour);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Cập nhật đặt tour thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng người và tổng tiền phải là số!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaDatTour() {
        String maDatTour = txtMaDatTour.getText().trim();
        if (maDatTour.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đặt tour cần xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa đặt tour này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String error = DatTourBLL.delete(maDatTour);
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Xóa đặt tour thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + error, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lamMoi() {
        txtMaDatTour.setText(DatTourBLL.generateNewMaDatTour());
        cboMaTour.setSelectedIndex(0);
        txtMaKH.setText("");
        dateNgayDat.setValue(new Date());
        txtSoLuongKhach.setText("");
        txtTongTien.setText("");
        txtSoTienDaThanhToan.setText("0");
        cboMaDT.setSelectedIndex(0);
        cboMaNV.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);
    }
    
    private void timKiem() {
        String keyword = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        List<DatTour> list = DatTourBLL.search(keyword);
        for (DatTour datTour : list) {
            Object[] row = {
                datTour.getMaDatTour(),
                datTour.getMaTour(),
                datTour.getMaKH(),
                datTour.getNgayDat() != null ? sdf.format(datTour.getNgayDat()) : "",
                datTour.getSoLuongNguoi(),
                DatTourBLL.formatCurrency(datTour.getTongTien()),
                DatTourBLL.formatCurrency(datTour.getSoTienDaThanhToan()),
                datTour.getMaDT(),
                datTour.getMaNV(),
                datTour.getTrangThai()
            };
            tableModel.addRow(row);
        }
    }
} 