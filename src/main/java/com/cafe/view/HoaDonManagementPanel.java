package com.cafe.view;

import com.cafe.controller.HoaDonController;
import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private JTextField txtTenKhachHang;
    private JTextField txtSoDienThoai;
    private JTable tableHoaDon;
    private DefaultTableModel modelHoaDon;
    private DefaultTableModel modelReport;
    private BufferedImage backgroundImage;

    public HoaDonManagementPanel(HoaDonController controller) {
        this.controller = controller;
        loadBackgroundImage();
        initComponents();
        loadData();
    }

    private void loadBackgroundImage() {
        try {
            String imagePath = "src/main/resources/anh-mo-ta.png";
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                backgroundImage = javax.imageio.ImageIO.read(imageFile);
            }
        } catch (Exception e) {
            System.out.println("Không thể tải hình ảnh background: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        setOpaque(true);
        setBackground(new Color(20, 25, 35)); // Màu đen đậm - modern style

        // Header Panel với gradient
        JPanel headerPanel = createHeaderPanel();

        // Content Panel chính
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Form panel
        JPanel formPanel = buildFormPanel();

        // Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        Font tabFont = new Font("Segoe UI", Font.BOLD, 13);
        tabbedPane.setFont(tabFont);
        tabbedPane.setBackground(new Color(35, 45, 60));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.addTab("Quản Lý Hóa Đơn", buildOrderTablePanel());
        tabbedPane.addTab("Báo Cáo", buildReportPanel());

        // Set tab header component font
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JLabel tabLabel = new JLabel(tabbedPane.getTitleAt(i));
            tabLabel.setFont(tabFont);
            tabLabel.setForeground(Color.WHITE);
            tabbedPane.setTabComponentAt(i, tabLabel);
        }

        contentPanel.add(formPanel, BorderLayout.NORTH);
        contentPanel.add(tabbedPane, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Tạo gradient từ xanh dương sang xanh nhạt
                Paint gp = new java.awt.GradientPaint(0, 0, new Color(30, 144, 255), getWidth(), 0,
                        new Color(0, 191, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setPreferredSize(new Dimension(0, 70));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titleLabel = new JLabel("HỆ THỐNG QUẢN LÝ BÁN HÀNG CAFE");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        panel.add(titleLabel);
        return panel;
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(35, 45, 60)); // Màu xám đen hiện đại
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 200, 255), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblMaHoaDon = createStyledLabel("Mã Hóa Đơn:");
        panel.add(lblMaHoaDon, gbc);

        gbc.gridx = 1;
        txtMaHoaDon = createStyledTextField(12);
        panel.add(txtMaHoaDon, gbc);

        gbc.gridx = 2;
        JLabel lblNgayLap = createStyledLabel(" Ngày Lập:");
        panel.add(lblNgayLap, gbc);

        gbc.gridx = 3;
        txtNgayLap = createStyledTextField(12);
        txtNgayLap.setText(LocalDate.now().toString());
        panel.add(txtNgayLap, gbc);

        // Dòng 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblKhachHang = createStyledLabel("Khách Hàng:");
        panel.add(lblKhachHang, gbc);

        gbc.gridx = 1;
        txtTenKhachHang = createStyledTextField(12);
        panel.add(txtTenKhachHang, gbc);

        gbc.gridx = 2;
        JLabel lblSoDienThoai = createStyledLabel("Số Điện Thoại:");
        panel.add(lblSoDienThoai, gbc);

        gbc.gridx = 3;
        txtSoDienThoai = createStyledTextField(12);
        panel.add(txtSoDienThoai, gbc);

        // Dòng 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblTongTien = createStyledLabel("Tổng Tiền:");
        panel.add(lblTongTien, gbc);

        gbc.gridx = 1;
        txtTongTien = createStyledTextField(12);
        txtTongTien.setText("0");
        txtTongTien.setEditable(false);
        txtTongTien.setToolTipText("Tổng tiền được tính tự động từ Quản Lý Món");
        txtTongTien.setEditable(false);
        panel.add(txtTongTien, gbc);

        gbc.gridx = 2;
        JLabel lblGhiChu = createStyledLabel("Ghi Chú:");
        panel.add(lblGhiChu, gbc);

        gbc.gridx = 3;
        txtGhiChu = createStyledTextField(12);
        panel.add(txtGhiChu, gbc);

        // Nút bấm
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        JPanel panelButtons = buildButtonPanel();
        panel.add(panelButtons, gbc);

        return panel;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(100, 200, 255));
        return label;
    }

    private JTextField createStyledTextField(int columns) {
        JTextField txt = new JTextField(columns);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txt.setBackground(new Color(50, 60, 80));
        txt.setForeground(Color.WHITE);
        txt.setBorder(BorderFactory.createLineBorder(new Color(80, 120, 180), 1));
        txt.setCaretColor(Color.WHITE);
        return txt;
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panel.setBackground(new Color(35, 45, 60));

        JButton btnNew = createModernButton("Mới", new Color(76, 175, 80));
        JButton btnSave = createModernButton("Lưu", new Color(33, 150, 243));
        JButton btnUpdate = createModernButton("Cập Nhật", new Color(255, 152, 0));
        JButton btnDelete = createModernButton("Xóa", new Color(244, 67, 54));
        JButton btnRefresh = createModernButton("Làm Mới", new Color(156, 39, 176));
        JButton btnManageDetails = createModernButton("Quản Lý Món", new Color(30, 200, 180));

        btnNew.addActionListener(e -> clearForm());
        btnSave.addActionListener(e -> saveHoaDon());
        btnUpdate.addActionListener(e -> updateHoaDon());
        btnDelete.addActionListener(e -> deleteHoaDon());
        btnRefresh.addActionListener(e -> loadData());
        btnManageDetails.addActionListener(e -> openManageDetails());

        panel.add(btnNew);
        panel.add(btnSave);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnRefresh);
        panel.add(btnManageDetails);

        return panel;
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor);
            }
        });
        return btn;
    }

    private JComponent buildOrderTablePanel() {
        modelHoaDon = new DefaultTableModel(
                new String[] { "Mã", "Ngày Lập", "Khách Hàng", "SĐT", "Tổng Tiền", "Ghi Chú" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableHoaDon = new JTable(modelHoaDon);
        tableHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableHoaDon.setRowHeight(28);
        tableHoaDon.setBackground(new Color(50, 60, 80));
        tableHoaDon.setForeground(Color.WHITE);
        tableHoaDon.setGridColor(new Color(80, 100, 130));
        tableHoaDon.setSelectionBackground(new Color(30, 144, 255));
        tableHoaDon.setSelectionForeground(Color.WHITE);

        tableHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableHoaDon.getTableHeader().setBackground(new Color(30, 100, 180));
        tableHoaDon.getTableHeader().setForeground(Color.WHITE);
        tableHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 30));

        tableHoaDon.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableHoaDon.getSelectedRow() != -1) {
                loadOrderToForm();
            }
        });

        JScrollPane scrollPaneHoaDon = new JScrollPane(tableHoaDon);
        scrollPaneHoaDon.setBackground(new Color(50, 60, 80));
        scrollPaneHoaDon.setBorder(BorderFactory.createLineBorder(new Color(80, 120, 180), 2));

        return scrollPaneHoaDon;
    }

    private JPanel buildReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelButtons.setBackground(new Color(35, 45, 60));

        JButton btnReportAll = createModernButton("Tất Cả", new Color(0, 200, 200));
        JButton btnReportRevenue = createModernButton("Doanh Thu", new Color(255, 100, 150));
        JButton btnReportProducts = createModernButton("Bán Chạy", new Color(100, 200, 100));
        JButton btnReportStats = createModernButton("Thống Kê", new Color(255, 170, 50));

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
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tableReport = new JTable(modelReport);
        tableReport.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableReport.setRowHeight(28);
        tableReport.setBackground(new Color(50, 60, 80));
        tableReport.setForeground(Color.WHITE);
        tableReport.setGridColor(new Color(80, 100, 130));
        tableReport.setSelectionBackground(new Color(30, 144, 255));
        tableReport.setSelectionForeground(Color.WHITE);
        tableReport.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableReport.getTableHeader().setBackground(new Color(30, 100, 180));
        tableReport.getTableHeader().setForeground(Color.WHITE);
        tableReport.getTableHeader().setPreferredSize(new Dimension(0, 30));

        JScrollPane scrollPane = new JScrollPane(tableReport);
        scrollPane.setBackground(new Color(50, 60, 80));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(80, 120, 180), 2));

        panel.add(panelButtons, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void loadData() {
        loadKhachHang();
        refreshHoaDonTable();
    }

    private void loadKhachHang() {
        txtTenKhachHang.setText("");
        // Không còn sử dụng JComboBox cho Khách Hàng
    }

    private void refreshHoaDonTable() {
        modelHoaDon.setRowCount(0);
        List<HoaDon> list = controller.getAllHoaDon();
        for (HoaDon hd : list) {
            modelHoaDon.addRow(new Object[] {
                    hd.getMaHoaDon(),
                    hd.getNgayLap(),
                    hd.getKhachHang() != null ? hd.getKhachHang().getTenKhachHang() : "",
                    hd.getKhachHang() != null ? hd.getKhachHang().getSoDienThoai() : "",
                    hd.getTongTien(),
                    hd.getGhiChu() != null ? hd.getGhiChu() : ""
            });
        }
    }

    private void loadOrderToForm() {
        int row = tableHoaDon.getSelectedRow();
        if (row == -1)
            return;

        String maHoaDon = tableHoaDon.getValueAt(row, 0).toString();
        HoaDon hd = controller.getHoaDonById(maHoaDon);

        if (hd != null) {
            txtMaHoaDon.setText(hd.getMaHoaDon());
            txtNgayLap.setText(hd.getNgayLap().toString());
            txtTongTien.setText(hd.getTongTien().toString());
            txtTenKhachHang.setText(hd.getKhachHang() != null ? hd.getKhachHang().getTenKhachHang() : "");
            txtSoDienThoai.setText(hd.getKhachHang() != null ? hd.getKhachHang().getSoDienThoai() : "");
            txtGhiChu.setText(hd.getGhiChu() != null ? hd.getGhiChu() : "");
        }
    }

    private void saveHoaDon() {
        try {
            String ma = txtMaHoaDon.getText().trim();
            String ngay = txtNgayLap.getText().trim();
            String tien = txtTongTien.getText().trim();
            String tenKhachHang = txtTenKhachHang.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();

            if (ma.isEmpty() || ngay.isEmpty() || tenKhachHang.isEmpty() || soDienThoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            KhachHang kh = controller.getKhachHangByPhone(soDienThoai);
            if (kh == null) {
                // Nếu không tìm thấy khách hàng, có thể tạo mới hoặc thông báo lỗi
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Khách hàng không tồn tại. Bạn có muốn tạo mới không?",
                        "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    kh = new KhachHang();
                    kh.setMaKhachHang("K" + java.util.UUID.randomUUID().toString().substring(0, 4).toUpperCase());
                    kh.setTenKhachHang(tenKhachHang);
                    kh.setSoDienThoai(soDienThoai);
                    controller.createKhachHang(kh);
                } else {
                    return;
                }
            } else {
                if (!kh.getTenKhachHang().equals(tenKhachHang)) {
                    kh.setTenKhachHang(tenKhachHang);
                    controller.updateKhachHang(kh);
                }
            }

            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(ma);
            hd.setNgayLap(Date.valueOf(ngay));
            hd.setTongTien(new java.math.BigDecimal(tien));
            hd.setKhachHang(kh);
            
            String ghiChu = txtGhiChu.getText().trim();
            hd.setGhiChu(ghiChu);

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
            String tenKhachHang = txtTenKhachHang.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            KhachHang kh = controller.getKhachHangByPhone(soDienThoai);
            if (kh == null) {
                kh = new KhachHang();
                kh.setMaKhachHang("K" + java.util.UUID.randomUUID().toString().substring(0, 4).toUpperCase());
                kh.setTenKhachHang(tenKhachHang);
                kh.setSoDienThoai(soDienThoai);
                controller.createKhachHang(kh);
            } else {
                if (!kh.getTenKhachHang().equals(tenKhachHang)) {
                    kh.setTenKhachHang(tenKhachHang);
                    controller.updateKhachHang(kh);
                }
            }
            hd.setKhachHang(kh);
            
            String ghiChu = txtGhiChu.getText().trim();
            hd.setGhiChu(ghiChu);

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
        txtTenKhachHang.setText("");
        txtSoDienThoai.setText("");
        tableHoaDon.clearSelection();
    }

    private void openManageDetails() {
        String ma = txtMaHoaDon.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chọn hoặc nhập chung Hóa Đơn và ấn Cập Nhật/Lưu trước khi Quản Lý Món", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        HoaDon hd = controller.getHoaDonById(ma);
        if (hd == null) {
            JOptionPane.showMessageDialog(this, "Hóa Đơn không tồn tại trên hệ thống. Hãy Lưu trước!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Window win = SwingUtilities.getWindowAncestor(this);
        Frame owner = null;
        if (win instanceof Frame) {
            owner = (Frame) win; }
        
        HoaDonChiTietDialog dialog = new HoaDonChiTietDialog(owner, ma, controller, () -> {
            refreshHoaDonTable();
            loadOrderToForm(); // reload to get new TongTien
        });
        dialog.setVisible(true);
    }

    private void showAllOrders() {
        updateReportTable(
                new String[] { "Mã", "Ngày", "Khách Hàng", "Tổng Tiền" },
                controller.reportAllHoaDonWithCustomer().stream()
                        .map(h -> new Object[] {
                                h.getMaHoaDon(),
                                h.getNgayLap(),
                                h.getKhachHang() != null ? h.getKhachHang().getTenKhachHang() : "",
                                h.getTongTien()
                        })
                        .toArray(size -> new Object[size][]));
    }

    private void showRevenueByCustomer() {
        List<Map<String, Object>> data = controller.reportRevenueByCustomer();
        String[] columns = { "Mã KH", "Tên Khách Hàng", "Tổng Doanh Thu" };
        Object[][] rows = data.stream()
                .map(m -> new Object[] {
                        m.get("maKhachHang"),
                        m.get("tenKhachHang"),
                        m.get("tongDoanhThu")
                })
                .toArray(size -> new Object[size][]);
        updateReportTable(columns, rows);
    }

    private void showTopProducts() {
        List<Map<String, Object>> data = controller.reportTopSellingProducts();
        String[] columns = { "Mã", "Tên Sản Phẩm", "Số Lượng Bán" };
        Object[][] rows = data.stream()
                .limit(10)
                .map(m -> new Object[] {
                        m.get("ma"),
                        m.get("ten"),
                        m.get("tongSoLuong")
                })
                .toArray(size -> new Object[size][]);
        updateReportTable(columns, rows);
    }

    private void showStatistics() {
        modelReport.setRowCount(0);
        modelReport.setColumnIdentifiers(new String[] { "Thống Kê", "Giá Trị" });

        long totalOrders = controller.reportCountTotalOrders();
        java.math.BigDecimal avgValue = controller.reportAverageOrderValue();
        java.math.BigDecimal maxValue = controller.reportMaxOrderValue();
        java.math.BigDecimal minValue = controller.reportMinOrderValue();
        java.math.BigDecimal totalRevenue = controller.reportTotalRevenue();

        modelReport.addRow(new Object[] { "Tổng số hóa đơn", totalOrders });
        modelReport.addRow(new Object[] { "Giá trị TB hóa đơn", avgValue });
        modelReport.addRow(new Object[] { "Giá trị max", maxValue });
        modelReport.addRow(new Object[] { "Giá trị min", minValue });
        modelReport.addRow(new Object[] { "Tổng doanh thu", totalRevenue });
    }

    private void updateReportTable(String[] columns, Object[][] data) {
        modelReport.setColumnIdentifiers(columns);
        modelReport.setRowCount(0);
        for (Object[] row : data) {
            modelReport.addRow(row);
        }
    }
}