package com.quanly.gui;

public class ProductGUI {
    void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        model.setRowCount(0); // Xóa bảng cũ
        List<ProductDTO> list = productBLL.getAll();
        for (ProductDTO p : list) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getCategoryName()});
        }
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        ProductDTO p = new ProductDTO();
        p.setName(txtName.getText());
        p.setPrice(Double.parseDouble(txtPrice.getText()));
        p.setQuantity(Integer.parseInt(txtQuantity.getText()));

        // Giả sử bạn đã lưu CategoryDTO vào JComboBox
        CategoryDTO selectedCat = (CategoryDTO) cbCategory.getSelectedItem();
        p.setCategoryId(selectedCat.getId());

        String result = productBLL.addProduct(p);
        JOptionPane.showMessageDialog(this, result);
        loadTable();
    }


}
