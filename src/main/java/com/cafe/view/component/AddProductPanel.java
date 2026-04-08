package com.cafe.view.component;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cafe.model.entity.LoaiThucUong;
import com.cafe.view.swing.CategoryFieldPanel;
import com.cafe.view.swing.MyButton;
import com.cafe.view.swing.MyTextField;

import net.miginfocom.swing.MigLayout;

public class AddProductPanel extends JPanel {
    private JLabel titleLabel;
    private MyTextField productName;

    private CategoryFieldPanel<LoaiThucUong> productCategories;

    private MyTextField productPrice;

    private JLabel imageLabel;
    private MyButton uploadButton;

    private MyButton addButton;

    public AddProductPanel() {
        setLayout(new MigLayout(
                "fill, wrap 1, insets 0, gapy 5",
                "[grow]",
                ""));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        titleLabel = new JLabel("THÊM SẢN PHẨM MỚI");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        productName = new MyTextField("Tên sản phẩm");

        productCategories = new CategoryFieldPanel<LoaiThucUong>("Loại sản phẩm");

        productPrice = new MyTextField("Giá sản phẩm");
        imageLabel = new JLabel("Tải lên ảnh sản phẩm");
        imageLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        ImageIcon uploadIcon = new ImageIcon(getClass().getResource("/icon/upload.jpg"));
        uploadButton = new MyButton(uploadIcon);

        addButton = new MyButton("THÊM SẢN PHẨM");
        uploadButton.addActionListener(e -> {
            if (onUploadClick != null)
                onUploadClick.run();
        });
        addButton.addActionListener(e -> {
            if (onAddClick != null)
                onAddClick.run();
        });

        add(titleLabel, "growx");
        add(productName, "growx");
        add(productCategories, "growx");
        add(productPrice, "growx");
        add(imageLabel, "growx");
        add(uploadButton, "growx");
        add(addButton, "center, w 200!");
    }

    public void loadCatagories(List<LoaiThucUong> categories) {
        productCategories.getComboBox().removeAllItems();

        for (LoaiThucUong c : categories) {
            productCategories.getComboBox().addItem(c);
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
        return productCategories.getComboBox();
    }

    public void setCategoriesComboBox(JComboBox<LoaiThucUong> categoriesComboBox) {
        this.productCategories.setComboBox(categoriesComboBox);
    }

}
