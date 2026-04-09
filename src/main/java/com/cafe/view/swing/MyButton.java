package com.cafe.view.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton {
    public MyButton() {
        super();
        initUI();
    }

    // Constructor với text
    public MyButton(String text) {
        super(text);
        initUI();
    }

    public MyButton(Icon icon) {
        setIcon(resizeImage(icon));
        initUI();
    }

    public MyButton(String text, Icon icon) {
        super(text);
        setIcon(resizeImage(icon));

        initUI();
    }

    private Icon resizeImage(Icon icon) {
        Image img = ((ImageIcon) icon).getImage();
        Image resizedImg = img.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    private void initUI() {
        // Font và màu mặc định
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setBackground(new Color(70, 130, 180));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Padding cho nút
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Hiệu ứng hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(100, 149, 237));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(70, 130, 180));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(new Color(30, 90, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(new Color(100, 149, 237));
            }
        });
    }
}
