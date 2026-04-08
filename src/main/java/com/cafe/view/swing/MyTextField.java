package com.cafe.view.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyTextField extends JPanel {
    private JLabel label;
    private JTextField textField;

    public MyTextField(String labelText) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false); // optional: trong suốt nếu dùng UI đẹp hơn

        // Label
        label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setAlignmentX(LEFT_ALIGNMENT);

        // TextField
        textField = new JTextField();
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setAlignmentX(LEFT_ALIGNMENT);

        // Border cho TextField (giống UI nhẹ nhàng)
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        // Khoảng cách
        add(label);
        add(Box.createVerticalStrut(5));
        add(textField);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public JTextField getTextField() {
        return textField;
    }
}
