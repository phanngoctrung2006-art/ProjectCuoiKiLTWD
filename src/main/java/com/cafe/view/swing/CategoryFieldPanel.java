package com.cafe.view.swing;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class CategoryFieldPanel<T> extends JPanel {

    private JLabel label;
    private JComboBox<T> comboBox;

    public CategoryFieldPanel(String title) {
        setLayout(new MigLayout(
                "wrap 1, insets 0",
                "[grow]",
                "[]5[]"));

        setOpaque(false); // trong suốt nếu đặt trên nền trắng

        // ===== Label =====
        label = new JLabel(title);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setAlignmentX(LEFT_ALIGNMENT);

        comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(0, 35));
        comboBox.setFont(new Font("Segoe UI", Font.BOLD, 13));
        comboBox.setAlignmentX(LEFT_ALIGNMENT);
        comboBox.setMaximumSize(new Dimension(200, 35));

        // ===== Add vào panel =====
        add(label);
        add(comboBox, "growx");
    }

    // ===== Getter =====
    public JComboBox<T> getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }
}