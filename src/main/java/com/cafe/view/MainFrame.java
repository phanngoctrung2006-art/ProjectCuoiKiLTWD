package com.cafe.view;

import javax.swing.*;
import java.awt.*;

/**
 * Main Frame cho ứng dụng quản lý bán hàng.
 */
public class MainFrame extends JFrame {
    public MainFrame(HoaDonManagementPanel panel) {
        setTitle("☕ Hệ Thống Quản Lý Bán Hàng Café ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Thêm màu nền cho frame
        getContentPane().setBackground(new Color(20, 25, 35));

        // Tạo menu bar với gradient
        JMenuBar menuBar = new JMenuBar() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Paint gp = new java.awt.GradientPaint(0, 0, new Color(30, 50, 100), getWidth(), 0, new Color(30, 144, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        menuBar.setBackground(new Color(30, 50, 100));
        menuBar.setForeground(Color.WHITE);
        
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
        aboutItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, 
                "☕ Hệ Thống Quản Lý Bán Hàng Café\n\n" +
                "Version 2.0 - Modern Design\n" +
                "Phát triển năm 2026\n\n" +
                "Công nghệ: Java Swing\n" +
                "Database: MySQL + Hibernate",
                "Về ứng dụng", JOptionPane.INFORMATION_MESSAGE)
        );
        menuHelp.add(aboutItem);
        menuBar.add(menuHelp);
        
        setJMenuBar(menuBar);
        
        // Thêm panel chính
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}
