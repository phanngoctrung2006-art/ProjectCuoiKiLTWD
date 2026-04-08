package com.cafe.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cafe.model.entity.LoaiThucUong;
import com.cafe.view.swing.MyTextField;

public class AddProductPanel extends JPanel {
    private JLabel titleLabel;
    private MyTextField productName;

    private JLabel categoryLabel;
    private JComboBox<LoaiThucUong> categoriesComboBox;

    private MyTextField productPrice;

    private JLabel imageLabel;
    private JButton uploadButton;

    private JButton addButton;

    public AddProductPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        titleLabel = new JLabel("Thêm sản phẩm mới");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        productName = new MyTextField("Tên sản phẩm");
        categoryLabel = new JLabel("Loại sản phẩm");

        categoriesComboBox = new JComboBox<>();
        categoriesComboBox.setAlignmentX(LEFT_ALIGNMENT);
        categoriesComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        productPrice = new MyTextField("Giá sản phẩm");
        imageLabel = new JLabel("Ảnh sản phẩm");

        Icon uploadButtons = new ImageIcon(getClass().getResource("/icon/upload.jpg"));
        uploadButton = new JButton(uploadButtons);
        addButton = new JButton("THÊM");

        // Gắn event vào Button
        uploadButton.addActionListener(e -> {
            if (onUploadClick != null)
                onUploadClick.run();
        });

        addButton.addActionListener(e -> {
            if (onAddClick != null)
                onAddClick.run();
        });

        add(titleLabel);
        add(productName);
        add(categoryLabel);
        add(Box.createVerticalStrut(5));
        add(categoriesComboBox);
        add(productPrice);
        add(imageLabel);
        add(uploadButton);
        add(addButton);
    }

    public void loadCatagories(List<LoaiThucUong> categories) {
        categoriesComboBox.removeAllItems();

        for (LoaiThucUong c : categories) {
            categoriesComboBox.addItem(c);
        }
    }

    private Runnable onUploadClick;
    private Runnable onAddClick;

    public void setOnUploadClick(Runnable onUploadClick) {
        this.onUploadClick = onUploadClick;
    }

    public void setOnAddClick(Runnable onAddClick) {
        this.onAddClick = onAddClick;
    }

    public MyTextField getProductName() {
        return productName;
    }

    public void setProductName(MyTextField productName) {
        this.productName = productName;
    }

    public MyTextField getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(MyTextField productPrice) {
        this.productPrice = productPrice;
    }

    public JComboBox<LoaiThucUong> getCategoriesComboBox() {
        return categoriesComboBox;
    }

    public void setCategoriesComboBox(JComboBox<LoaiThucUong> categoriesComboBox) {
        this.categoriesComboBox = categoriesComboBox;
    }

}
