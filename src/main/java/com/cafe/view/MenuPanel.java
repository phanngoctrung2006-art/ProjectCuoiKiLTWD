package com.cafe.view;

import com.cafe.model.entity.ThucUong;
import com.cafe.view.component.AddProductPanel;
import com.cafe.view.component.MenuItemPanel;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Màn hình giao diện bán hàng hiển thị danh sách Thức Uống (Menu).
 * Hỗ trợ hiển thị ảnh sản phẩm dưới dạng lưới (Grid/MigLayout) và tích hợp panel AddProductPanel.
 */
public class MenuPanel extends JPanel {
    private JPanel productContainer;
    private AddProductPanel addProductPanel;

    public MenuPanel() {
        setBackground(new Color(245, 245, 245));
        setOpaque(false);
        setLayout(new MigLayout("fill", "[80%][20%]"));

        initComponents();
    }

    private void initComponents() {
        productContainer = new JPanel();
        productContainer.setOpaque(false);

        productContainer.setLayout(new MigLayout(
                "wrap 4, fillx, insets 10",
                "[grow]5[grow]5[grow]5[grow]",
                "[]"));

        JScrollPane scrollPane = new JScrollPane(productContainer);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        addProductPanel = new AddProductPanel();

        add(scrollPane, "grow");
        add(addProductPanel, "grow");
    }

    /** Render lại giao diện hiển thị danh sách tất cả các Thức Uống đang có */
    public void displayProducts(List<ThucUong> products) {
        productContainer.removeAll();

        for (ThucUong p : products) {
            System.out.print(p.getTenThucUong());

            ImageIcon image = new ImageIcon(getClass().getResource(p.getUrl()));
            image = resizeImage(image, 200, 200);
            MenuItemPanel items = new MenuItemPanel(p.getTenThucUong(), p.getGia().toString(), image);

            productContainer.add(items);
        }

        productContainer.revalidate();
        productContainer.repaint();
    }

    public ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public AddProductPanel getAddProductPanel() {
        return addProductPanel;
    }
}
