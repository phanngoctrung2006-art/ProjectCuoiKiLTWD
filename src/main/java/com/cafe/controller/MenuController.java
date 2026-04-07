package com.cafe.controller;

import java.util.List;

import com.cafe.model.entity.LoaiThucUong;
import com.cafe.model.entity.ThucUong;
import com.cafe.service.ThucUongService;
import com.cafe.view.MenuPanel;
import com.cafe.view.component.AddProductPanel;

public class MenuController {
    private MenuPanel menuPanel;
    private ThucUongService thucUongService;

    public MenuController(MenuPanel menuPanel, ThucUongService thucUongService) {
        this.menuPanel = menuPanel;
        this.thucUongService = thucUongService;

        initEvents();
        loadDataToUI();
    }

    public void loadDataToUI() {
        List<ThucUong> data = thucUongService.getAll();
        menuPanel.displayProducts(data);
    }

    private void initEvents() {
        AddProductPanel addPanel = menuPanel.getAddProductPanel();

        // Upload image
        addPanel.setOnUploadClick(() -> {
            String name = addPanel.getProductName().getText();
            String price = addPanel.getProductPrice().getText();
            LoaiThucUong category = addPanel.getCategoriesComboBox().getSelectedItem();
            ThucUong newProduct = new ThucUong(null, name, addPanel.ge)
            thucUongService.create();
        });

        // Add product
        addPanel.setOnAddClick(() -> {
            handleAddProduct();
        });
    }
}
