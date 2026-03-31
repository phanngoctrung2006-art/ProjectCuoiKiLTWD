package com.cafe.view;

import javax.swing.*;
import java.awt.*;

/**
 * Main Frame cho ứng dụng quản lý bán hàng.
 */
public class MainFrame extends JFrame {
    public MainFrame(HoaDonManagementPanel panel) {
        setTitle("Hệ Thống Quản Lý Bán Hàng Café");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Thoát");
        exitItem.addActionListener(e -> System.exit(0));
        menuFile.add(exitItem);
        menuBar.add(menuFile);
        
        JMenu menuHelp = new JMenu("Trợ giúp");
        JMenuItem aboutItem = new JMenuItem("Về ứng dụng");
        aboutItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, 
                "Hệ Thống Quản Lý Bán Hàng Café\nVersion 1.0\nPhát triển năm 2026",
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
