package com.cafe.view;

import com.cafe.controller.HoaDonController;
import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Panel quản lý hóa đơn - CRUD và Báo cáo.
 */
public class HoaDonManagementPanel extends JPanel {
    private final HoaDonController controller;
    private JTextField txtMaHoaDon;
    private JTextField txtNgayLap;
    private JTextField txtTongTien;
    private JTextField txtGhiChu;
    private JComboBox<KhachHang> cboKhachHang;
    private JTable tableHoaDon;
    private DefaultTableModel modelHoaDon;
    private DefaultTableModel modelReport;

    public HoaDonManagementPanel(HoaDonController controller) {
        this.controller = controller;
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Panel trên - Form nhập liệu
        JPanel panelForm = buildFormPanel();
        
        // Panel giữa - Table hóa đơn
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Quản Lý Hóa Đơn", buildOrderTablePanel());
        tabbedPane.addTab("Báo Cáo", buildReportPanel());
        
        add(panelForm, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thông Tin Hóa Đơn"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Mã Hóa Đơn:"), gbc);
        
        gbc.gridx = 1;
        txtMaHoaDon = new JTextField(10);
        panel.add(txtMaHoaDon, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Ngày Lập (yyyy-MM-dd):"), gbc);
        
        gbc.gridx = 3;
        txtNgayLap = new JTextField(10);
        txtNgayLap.setText(LocalDate.now().toString());
        panel.add(txtNgayLap, gbc);

        // Dòng 2
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Khách Hàng:"), gbc);
        
        gbc.gridx = 1;
        cboKhachHang = new JComboBox<>();
        panel.add(cboKhachHang, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Tổng Tiền:"), gbc);
        
        gbc.gridx = 3;
        txtTongTien = new JTextField(10);
        txtTongTien.setText("0");
        panel.add(txtTongTien, gbc);

        // Dòng 3
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Ghi Chú:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtGhiChu = new JTextField();
        panel.add(txtGhiChu, gbc);
        gbc.gridwidth = 1;

        // Nút bấm
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;
        JPanel panelButtons = buildButtonPanel();
        panel.add(panelButtons, gbc);

        return panel;
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnNew = new JButton("Mới");
        JButton btnSave = new JButton("Lưu");
        JButton btnUpdate = new JButton("Cập Nhật");
        JButton btnDelete = new JButton("Xóa");
        JButton btnRefresh = new JButton("Làm Mới");

        btnNew.addActionListener(e -> clearForm());
        btnSave.addActionListener(e -> saveHoaDon());
        btnUpdate.addActionListener(e -> updateHoaDon());
        btnDelete.addActionListener(e -> deleteHoaDon());
        btnRefresh.addActionListener(e -> loadData());

        panel.add(btnNew);
        panel.add(btnSave);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnRefresh);

        return panel;
    }

    private JScrollPane buildOrderTablePanel() {
        modelHoaDon = new DefaultTableModel(
            new String[]{"Mã", "Ngày Lập", "Khách Hàng", "Tổng Tiền", "Ghi Chú"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tableHoaDon = new JTable(modelHoaDon);
        tableHoaDon.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableHoaDon.getSelectedRow() != -1) {
                loadOrderToForm();
            }
        });

        return new JScrollPane(tableHoaDon);
    }

    private JPanel buildReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnReportAll = new JButton("Tất Cả Hóa Đơn");
        JButton btnReportRevenue = new JButton("Doanh Thu Khách");
        JButton btnReportProducts = new JButton("Sản Phẩm Bán Chạy");
        JButton btnReportStats = new JButton("Thống Kê");

        btnReportAll.addActionListener(e -> showAllOrders());
        btnReportRevenue.addActionListener(e -> showRevenueByCustomer());
        btnReportProducts.addActionListener(e -> showTopProducts());
        btnReportStats.addActionListener(e -> showStatistics());

        panelButtons.add(btnReportAll);
        panelButtons.add(btnReportRevenue);
        panelButtons.add(btnReportProducts);
        panelButtons.add(btnReportStats);

        modelReport = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable tableReport = new JTable(modelReport);
        JScrollPane scrollPane = new JScrollPane(tableReport);

        panel.add(panelButtons, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void loadData() {
        loadKhachHang();
        refreshHoaDonTable();
    }

    private void loadKhachHang() {
        cboKhachHang.removeAllItems();
        List<KhachHang> list = controller.getAllKhachHang();
        for (KhachHang kh : list) {
            cboKhachHang.addItem(kh);
        }
    }

    private void refreshHoaDonTable() {
        modelHoaDon.setRowCount(0);
        List<HoaDon> list = controller.getAllHoaDon();
        for (HoaDon hd : list) {
            modelHoaDon.addRow(new Object[]{
                hd.getMaHoaDon(),
                hd.getNgayLap(),
                hd.getKhachHang() != null ? hd.getKhachHang().getTenKhachHang() : "",
                hd.getTongTien(),
                hd.getMaHoaDon() // Ghi chú (chưa có field riêng)
            });
        }
    }

    private void loadOrderToForm() {
        int row = tableHoaDon.getSelectedRow();
        if (row == -1) return;

        String maHoaDon = tableHoaDon.getValueAt(row, 0).toString();
        HoaDon hd = controller.getHoaDonById(maHoaDon);

        if (hd != null) {
            txtMaHoaDon.setText(hd.getMaHoaDon());
            txtNgayLap.setText(hd.getNgayLap().toString());
            txtTongTien.setText(hd.getTongTien().toString());
            
            if (hd.getKhachHang() != null) {
                for (int i = 0; i < cboKhachHang.getItemCount(); i++) {
                    KhachHang kh = cboKhachHang.getItemAt(i);
                    if (kh.getMaKhachHang().equals(hd.getKhachHang().getMaKhachHang())) {
                        cboKhachHang.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }

    private void saveHoaDon() {
        try {
            String ma = txtMaHoaDon.getText().trim();
            String ngay = txtNgayLap.getText().trim();
            String tien = txtTongTien.getText().trim();
            KhachHang kh = (KhachHang) cboKhachHang.getSelectedItem();

            if (ma.isEmpty() || ngay.isEmpty() || kh == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(ma);
            hd.setNgayLap(Date.valueOf(ngay));
            hd.setTongTien(new java.math.BigDecimal(tien));
            hd.setKhachHang(kh);

            controller.createHoaDon(hd);
            JOptionPane.showMessageDialog(this, "Lưu thành công", 
                "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            refreshHoaDonTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateHoaDon() {
        try {
            String ma = txtMaHoaDon.getText().trim();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chọn hóa đơn cần cập nhật", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HoaDon hd = controller.getHoaDonById(ma);
            if (hd == null) {
                JOptionPane.showMessageDialog(this, "Hóa đơn không tồn tại", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            hd.setTongTien(new java.math.BigDecimal(txtTongTien.getText()));
            hd.setKhachHang((KhachHang) cboKhachHang.getSelectedItem());

            controller.updateHoaDon(hd);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công", 
                "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            refreshHoaDonTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteHoaDon() {
        try {
            String ma = txtMaHoaDon.getText().trim();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chọn hóa đơn cần xóa", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, 
                "Xác nhận xóa hóa đơn " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteHoaDon(ma);
                JOptionPane.showMessageDialog(this, "Xóa thành công", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshHoaDonTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtMaHoaDon.setText("");
        txtNgayLap.setText(LocalDate.now().toString());
        txtTongTien.setText("0");
        txtGhiChu.setText("");
        if (cboKhachHang.getItemCount() > 0) {
            cboKhachHang.setSelectedIndex(0);
        }
        tableHoaDon.clearSelection();
    }

    private void showAllOrders() {
        updateReportTable(
            new String[]{"Mã", "Ngày", "Khách Hàng", "Tổng Tiền"},
            controller.reportAllHoaDonWithCustomer().stream()
                .map(h -> new Object[]{
                    h.getMaHoaDon(),
                    h.getNgayLap(),
                    h.getKhachHang() != null ? h.getKhachHang().getTenKhachHang() : "",
                    h.getTongTien()
                })
                .toArray(Object[][]::new)
        );
    }

    private void showRevenueByCustomer() {
        List<Map<String, Object>> data = controller.reportRevenueByCustomer();
        String[] columns = {"Mã KH", "Tên Khách Hàng", "Tổng Doanh Thu"};
        Object[][] rows = data.stream()
            .map(m -> new Object[]{
                m.get("maKhachHang"),
                m.get("tenKhachHang"),
                m.get("tongDoanhThu")
            })
            .toArray(Object[][]::new);
        updateReportTable(columns, rows);
    }

    private void showTopProducts() {
        List<Map<String, Object>> data = controller.reportTopSellingProducts();
        String[] columns = {"Mã", "Tên Sản Phẩm", "Số Lượng Bán"};
        Object[][] rows = data.stream()
            .limit(10)
            .map(m -> new Object[]{
                m.get("ma"),
                m.get("ten"),
                m.get("tongSoLuong")
            })
            .toArray(Object[][]::new);
        updateReportTable(columns, rows);
    }

    private void showStatistics() {
        modelReport.setRowCount(0);
        modelReport.setColumnIdentifiers(new String[]{"Thống Kê", "Giá Trị"});

        long totalOrders = controller.reportCountTotalOrders();
        java.math.BigDecimal avgValue = controller.reportAverageOrderValue();
        java.math.BigDecimal maxValue = controller.reportMaxOrderValue();
        java.math.BigDecimal minValue = controller.reportMinOrderValue();
        java.math.BigDecimal totalRevenue = controller.reportTotalRevenue();

        modelReport.addRow(new Object[]{"Tổng số hóa đơn", totalOrders});
        modelReport.addRow(new Object[]{"Giá trị TB hóa đơn", avgValue});
        modelReport.addRow(new Object[]{"Giá trị max", maxValue});
        modelReport.addRow(new Object[]{"Giá trị min", minValue});
        modelReport.addRow(new Object[]{"Tổng doanh thu", totalRevenue});
    }

    private void updateReportTable(String[] columns, Object[][] data) {
        modelReport.setColumnIdentifiers(columns);
        modelReport.setRowCount(0);
        for (Object[] row : data) {
            modelReport.addRow(row);
        }
    }
}
