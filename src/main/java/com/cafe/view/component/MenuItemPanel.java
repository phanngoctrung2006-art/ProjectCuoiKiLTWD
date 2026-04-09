package com.cafe.view.component;

import javax.swing.*;
import java.awt.*;

/**
 * Giao diện tùy chỉnh (Custom Component) hiển thị 1 món nước ở trong Menu.
 * Dùng layout kết hợp để hiển thị Ảnh, Tên món và Giá bán cách trực quan.
 */
public class MenuItemPanel extends JPanel {
    public MenuItemPanel(String name, String price, ImageIcon image) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        setBackground(Color.WHITE);

        // Set image for Product
        JLabel imageLabel = new JLabel(image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.NORTH);

        // Set information for Product
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(Color.BLUE);

        infoPanel.add(nameLabel, FlowLayout.LEFT);
        infoPanel.add(priceLabel);

        add(infoPanel, BorderLayout.CENTER);
    }

}
