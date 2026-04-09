package com.cafe.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.cafe.dao.LoaiThucUongDAO;
import com.cafe.model.entity.LoaiThucUong;
import com.cafe.model.entity.ThucUong;
import com.cafe.service.ThucUongService;
import com.cafe.view.MenuPanel;
import com.cafe.view.component.AddProductPanel;

/**
 * Controller quản lý hiển thị và tương tác của giao diện Thực Đơn (Menu).
 * Đảm nhiệm các chức năng thêm món, tải danh sách đồ uống, upload ảnh sản phẩm.
 */
public class MenuController {
    private MenuPanel menuPanel;
    private ThucUongService thucUongService;
    private LoaiThucUongDAO loaiThucUongDAO;

    public MenuController(MenuPanel menuPanel, ThucUongService thucUongService, LoaiThucUongDAO loaiThucUongDAO) {
        this.menuPanel = menuPanel;
        this.thucUongService = thucUongService;
        this.loaiThucUongDAO = loaiThucUongDAO;

        initEvents();
        loadProducts();
        loadCategories();
    }

    private void loadProducts() {
        List<ThucUong> data = thucUongService.getAll();
        menuPanel.displayProducts(data);
    }

    private void loadCategories() {
        List<LoaiThucUong> categories = loaiThucUongDAO.findAll();
        AddProductPanel addPanel = menuPanel.getAddProductPanel();
        addPanel.loadCatagories(categories);
    }

    private String selectedImageUrl;

    private void initEvents() {
        AddProductPanel addPanel = menuPanel.getAddProductPanel();

        // Xử lý sự kiện Upload hình ảnh sản phẩm
        addPanel.setOnUploadClick(() -> {
            JFileChooser fileChooser = new JFileChooser();

            // chỉ cho chọn ảnh
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Image files", "jpg", "png", "jpeg"));

            int result = fileChooser.showOpenDialog(menuPanel);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                try {
                    // ===== THƯ MỤC LƯU TRONG PROJECT =====
                    String projectPath = System.getProperty("user.dir");
                    File destDir = new File(projectPath + "/src/main/resources/images");

                    if (!destDir.exists()) {
                        destDir.mkdirs();
                    }

                    // ===== TẠO TÊN FILE KHÔNG TRÙNG =====
                    String fileName = System.currentTimeMillis() + "_" + selectedFile.getName();

                    File destFile = new File(destDir, fileName);

                    // ===== COPY FILE =====
                    java.nio.file.Files.copy(
                            selectedFile.toPath(),
                            destFile.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                    // ===== URL LƯU DB =====
                    selectedImageUrl = "/images/" + fileName;

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(menuPanel, "Upload ảnh thất bại!");
                }
            }
        });

        // Xử lý sự kiện Thêm sản phẩm mới vào Menu
        addPanel.setOnAddClick(() -> {
            String name = addPanel.getProductName().getText();
            if (!validateName(name)) {
                return;
            }
            String priceStr = addPanel.getProductPrice().getText();
            if (!validatePrice(priceStr))
                return;
            BigDecimal price = new BigDecimal(priceStr);

            if (this.selectedImageUrl == null) {
                JOptionPane.showMessageDialog(menuPanel, "Bạn vẫn chưa tải ảnh sản phẩm");
                return;
            }

            LoaiThucUong category = (LoaiThucUong) addPanel.getCategoriesComboBox().getSelectedItem();
            ThucUong newProduct = new ThucUong(thucUongService.getNextId(), name,
                    price, category,
                    selectedImageUrl);

            thucUongService.create(newProduct);

            loadProducts();
        });
    }

    private boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(menuPanel, "Tên không được để trống!");
            return false;
        }
        return true;
    }

    private boolean validatePrice(String priceStr) {
        if (priceStr == null || priceStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(menuPanel, "Giá không được để trống!");
            return false;
        }

        try {
            double price = Double.parseDouble(priceStr);

            if (price <= 0) {
                JOptionPane.showMessageDialog(menuPanel, "Giá phải lớn hơn 0!");
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(menuPanel, "Giá phải là số hợp lệ!");
            return false;
        }

        return true;
    }

}
