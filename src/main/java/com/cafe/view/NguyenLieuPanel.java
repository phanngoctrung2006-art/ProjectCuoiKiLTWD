package com.cafe.view;

import com.cafe.controller.NguyenLieuController;
import com.cafe.model.entity.NguyenLieu;
import javax.swing.*;
import java.awt.*;

/**
 * Lớp cung cấp giao diện hiển thị danh sách Nguyên Liệu trong kho.
 * Các trường nhập tên, số lượng được kiểm tra hợp lệ trước khi lưu/cập nhật.
 */
public class NguyenLieuPanel extends BaseManagementPanel {

    private final NguyenLieuController controller;
    private JTextField txtMa, txtTen, txtSoLuong;

    private static final int[] FILTER_COLS = {0, 1, 2};

    /**
     * Khởi tạo panel quản lý nguyên liệu với controller tương ứng.
     * @param controller Biến điều khiển các thao tác xử lý nghiệp vụ cho Nguyên Liệu
     */
    public NguyenLieuPanel(NguyenLieuController controller) {
        this.controller = controller;
        build();
    }

    /**
     * Xây dựng và bố trí các thành phần giao diện người dùng trên panel.
     */
    private void build() {
        add(makeHeader("QUẢN LÝ NGUYÊN LIỆU"), BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG_PANEL);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT, 2),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 10, 6, 10);
        g.fill = GridBagConstraints.HORIZONTAL;

        txtMa      = makeField(10);
        txtMa.setEditable(false);
        txtTen     = makeField(20);
        txtSoLuong = makeField(12);

        g.gridy = 0; g.gridwidth = 1;
        g.gridx = 0; form.add(makeLabel("Mã NL:"), g);
        g.gridx = 1; form.add(txtMa, g);
        g.gridx = 2; form.add(makeLabel("Tên Nguyên Liệu:"), g);
        g.gridx = 3; form.add(txtTen, g);

        g.gridy = 1;
        g.gridx = 0; form.add(makeLabel("Số Lượng Tồn:"), g);
        g.gridx = 1; form.add(txtSoLuong, g);

        g.gridy = 2; g.gridx = 0; g.gridwidth = 4;
        form.add(buildButtons(), g);

        JScrollPane sp = buildStyledTable(new String[]{"Mã NL", "Tên Nguyên Liệu", "Số Lượng Tồn"});
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) fillForm();
        });

        content.add(form, BorderLayout.NORTH);
        content.add(sp, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        attachSearchListeners(txtMa, txtTen, txtSoLuong);
        loadData();
    }

    /**
     * Tạo và cấu hình panel chứa các nút chức năng.
     * @return Panel chứa các nút Tìm kiếm, Lưu, Cập nhật, Xóa, Làm mới
     */
    private JPanel buildButtons() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        p.setOpaque(false);
        JButton bSearch  = makeButton("Tìm Kiếm", new Color(0, 150, 136));
        JButton bSave    = makeButton("Lưu",       new Color(33, 150, 243));
        JButton bUpdate  = makeButton("Cập Nhật",  new Color(255, 152, 0));
        JButton bDelete  = makeButton("Xóa",       new Color(244, 67, 54));
        JButton bRefresh = makeButton("Làm Mới",   new Color(156, 39, 176));
        bSearch.addActionListener(e -> activateSearch());
        bSave.addActionListener(e -> doSave());
        bUpdate.addActionListener(e -> doUpdate());
        bDelete.addActionListener(e -> doDelete());
        bRefresh.addActionListener(e -> { clearForm(); loadData(); });
        p.add(bSearch); p.add(bSave); p.add(bUpdate); p.add(bDelete); p.add(bRefresh);
        return p;
    }

    /**
     * Tải dữ liệu danh sách nguyên liệu từ Controller và hiển thị lên bảng.
     */
    @Override
    public void loadData() {
        tableModel.setRowCount(0);
        for (NguyenLieu nl : controller.getAll())
            tableModel.addRow(new Object[]{nl.getMaNguyenLieu(), nl.getTenNguyenLieu(), nl.getSoLuong()});
    }

    /**
     * Điền thông tin nguyên liệu từ dòng được chọn trên bảng vào các trường nhập liệu.
     */
    private void fillForm() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        isUpdatingForm = true;
        int m = table.convertRowIndexToModel(row);
        txtMa.setText(str(tableModel.getValueAt(m, 0)));
        txtTen.setText(str(tableModel.getValueAt(m, 1)));
        txtSoLuong.setText(str(tableModel.getValueAt(m, 2)));
        isUpdatingForm = false;
    }

    /**
     * Làm sạch các trường nhập liệu trên form và bỏ chọn trên bảng.
     */
    private void clearForm() {
        isUpdatingForm = true;
        txtMa.setText(""); txtTen.setText(""); txtSoLuong.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        clearFilter();
    }

    /**
     * Làm sạch form form và khởi chạy quá trình tìm kiếm.
     */
    private void activateSearch() {
        isUpdatingForm = true;
        txtMa.setText(""); txtTen.setText(""); txtSoLuong.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        applyFilter();
    }

    /**
     * Áp dụng bộ lọc dữ liệu vào bảng dựa trên các giá trị nhập trên form.
     */
    @Override
    protected void applyFilter() {
        applyFilterOnColumns(new JTextField[]{txtMa, txtTen, txtSoLuong}, FILTER_COLS);
    }

    /**
     * Thực hiện việc lưu một nguyên liệu mới vào cơ sở dữ liệu.
     */
    // View chỉ thu thập dữ liệu từ form, gọi controller và hiển thị kết quả
    private void doSave() {
        try {
            NguyenLieu nl = new NguyenLieu();
            nl.setMaNguyenLieu(controller.getNextId());
            nl.setTenNguyenLieu(txtTen.getText().trim());
            String sl = txtSoLuong.getText().trim();
            if (!sl.isEmpty()) nl.setSoLuong(Integer.parseInt(sl));
            controller.save(nl);
            showSuccess(this, "Lưu thành công!"); clearForm(); loadData();
        } catch (Exception ex) { showError(this, ex.getMessage()); }
    }

    /**
     * Cập nhật thông tin của một nguyên liệu đã tồn tại trong cơ sở dữ liệu.
     */
    private void doUpdate() {
        try {
            String ma = txtMa.getText().trim();
            if (ma.isEmpty()) { showError(this, "Chọn nguyên liệu cần cập nhật!"); return; }
            NguyenLieu nl = controller.getById(ma);
            if (nl == null) { showError(this, "Nguyên liệu không tồn tại!"); return; }
            nl.setTenNguyenLieu(txtTen.getText().trim());
            String sl = txtSoLuong.getText().trim();
            if (!sl.isEmpty()) nl.setSoLuong(Integer.parseInt(sl));
            controller.update(nl);
            showSuccess(this, "Cập nhật thành công!"); clearForm(); loadData();
        } catch (Exception ex) { showError(this, ex.getMessage()); }
    }

    /**
     * Xóa nguyên liệu khỏi cơ sở dữ liệu sau khi xác nhận với người dùng.
     */
    private void doDelete() {
        String ma = txtMa.getText().trim();
        if (ma.isEmpty()) { showError(this, "Chọn nguyên liệu cần xóa!"); return; }
        int c = JOptionPane.showConfirmDialog(this, "Xác nhận xóa nguyên liệu " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            try { controller.delete(ma); clearForm(); loadData(); }
            catch (Exception ex) { showError(this, ex.getMessage()); }
        }
    }

    /**
     * Phương thức tiện ích chuyển đổi Đối tượng thành Chuỗi.
     * @param o Đối tượng cần chuyển đổi
     * @return Chuỗi String hoặc rỗng nếu nguyên thủy là null
     */
    private String str(Object o) { return o != null ? o.toString() : ""; }
}
