package com.cafe.view;

import javax.swing.*;
import java.awt.*;

/**
 * Giao diện màn hình chính của ứng dụng.
 * Là nơi chứa và quản lý các Tab chức năng (Hóa Đơn, Nhân Viên, Sản Phẩm...).
 * Được khởi tạo sau khi đăng nhập thành công.
 */
public class MainFrame extends JFrame {

    public MainFrame(HoaDonManagementPanel hoaDonPanel,
            NhanVienPanel nhanVienPanel,
            MenuPanel menuPanel,
            PhieuNhapPanel phieuNhapPanel,
            NguyenLieuPanel nguyenLieuPanel,
            NhaCungCapPanel nhaCungCapPanel,
            KhachHangPanel khachHangPanel) {
        setTitle("☕ Hệ Thống Quản Lý Bán Hàng Cafe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát hẳn chương trình khi bấm dấu X
        setSize(1400, 900); // Kích thước mặc định
        setLocationRelativeTo(null); // Hiển thị ở chính giữa màn hình
        setResizable(true); // Cho phép thay đổi kích thước
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Phóng to toàn màn hình ngay khi mở
        getContentPane().setBackground(new Color(20, 25, 35));

        // Menu bar tích hợp hiệu ứng chuyển màu Gradient
        JMenuBar menuBar = new JMenuBar() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Bật Antialiasing giúp hình vẽ mượt mà, không bị vỡ hạt (răng cưa)
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Đổ màu từ Xanh Đậm (trái) sang Xanh Nhạt (phải)
                g2d.setPaint(new GradientPaint(0, 0, new Color(30, 50, 100), getWidth(), 0, new Color(30, 144, 255)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        menuBar.setBackground(new Color(30, 50, 100));

        JMenu menuFile = new JMenu("File");
        menuFile.setForeground(Color.WHITE);
        menuFile.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JMenuItem exitItem = new JMenuItem("Thoát");
        exitItem.setBackground(new Color(220, 20, 60));
        exitItem.setForeground(Color.WHITE);
        exitItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        exitItem.addActionListener(e -> System.exit(0));
        menuFile.add(exitItem);
        menuBar.add(menuFile);

        JMenu menuHelp = new JMenu("Trợ giúp");
        menuHelp.setForeground(Color.WHITE);
        menuHelp.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JMenuItem aboutItem = new JMenuItem("Về ứng dụng");
        aboutItem.setBackground(new Color(255, 165, 0));
        aboutItem.setForeground(Color.WHITE);
        aboutItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "☕ Hệ Thống Quản Lý Bán Hàng Café\n\nVersion 2.0 - Modern Design\nPhát triển năm 2026\n\nCông nghệ: Java Swing\nDatabase: MySQL + Hibernate",
                "Về ứng dụng", JOptionPane.INFORMATION_MESSAGE));
        menuHelp.add(aboutItem);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);

        // Khởi tạo khung chứa các Tab (Tabbed Pane) chức năng
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.setBackground(new Color(30, 40, 55));
        tabs.setForeground(Color.WHITE);
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));

        tabs.addTab("Hóa Đơn", hoaDonPanel);
        tabs.addTab("Nhân Viên", nhanVienPanel);
        tabs.addTab("Sản Phẩm", menuPanel);
        tabs.addTab("Phiếu Nhập", phieuNhapPanel);
        tabs.addTab("Nguyên Liệu", nguyenLieuPanel);
        tabs.addTab("Nhà Cung Cấp", nhaCungCapPanel);
        tabs.addTab("Khách Hàng", khachHangPanel);

        add(tabs, BorderLayout.CENTER);
    }
}
