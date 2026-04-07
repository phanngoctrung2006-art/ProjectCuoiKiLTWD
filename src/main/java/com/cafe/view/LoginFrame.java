package com.cafe.view;

import com.cafe.controller.TaiKhoanController;
import com.cafe.model.entity.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private final TaiKhoanController taiKhoanController;
    private final Runnable onSuccessCallback;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnExit;

    public LoginFrame(TaiKhoanController taiKhoanController, Runnable onSuccessCallback) {
        this.taiKhoanController = taiKhoanController;
        this.onSuccessCallback = onSuccessCallback;

        // Initialize default admin if empty
        this.taiKhoanController.initDefaultAccount();

        initComponents();
        setupListeners();
    }

    private void initComponents() {
        setTitle("Đăng Nhập - Hệ Thống Quản Lý Quán Cafe");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center screen
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(60, 100, 180));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Tên đăng nhập:"), gbc);
        
        txtUsername = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(txtUsername, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Mật khẩu:"), gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(txtPassword, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnLogin = new JButton("Đăng nhập");
        btnExit = new JButton("Thoát");
        
        btnLogin.setBackground(new Color(40, 167, 69));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        
        btnExit.setBackground(new Color(220, 53, 69));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnExit);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Add padding
        add(mainPanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Allow Enter key to submit
        txtPassword.addActionListener(e -> handleLogin());
        txtUsername.addActionListener(e -> txtPassword.requestFocus());

        btnExit.addActionListener(e -> System.exit(0));
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TaiKhoan tk = taiKhoanController.authenticate(username, password);
        if (tk != null) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Xin chào, " + tk.getTenDangNhap() + ".", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Hide login window
            if (onSuccessCallback != null) {
                onSuccessCallback.run(); // Launch MainFrame
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
