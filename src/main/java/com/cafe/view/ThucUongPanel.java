package com.cafe.view;

import com.cafe.model.entity.ThucUong;
import com.cafe.service.ThucUongService;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ThucUongPanel extends BaseManagementPanel {

    private final ThucUongService service;
    private JTextField txtMa, txtTen, txtGia, txtMaLoai;

    private static final int[] FILTER_COLS = { 0, 1, 2, 3 };

    public ThucUongPanel(ThucUongService service) {
        this.service = service;
        build();
    }

    private void build() {
        add(makeHeader("QUẢN LÝ THỨC UỐNG"), BorderLayout.NORTH);

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

        txtMa = makeField(10);
        txtMa.setEditable(false);

        txtTen = makeField(20);
        txtGia = makeField(12);
        txtMaLoai = makeField(10);

        g.gridy = 0;
        g.gridwidth = 1;
        g.gridx = 0;
        form.add(makeLabel("Mã TU:"), g);
        g.gridx = 1;
        form.add(txtMa, g);
        g.gridx = 2;
        form.add(makeLabel("Tên Thức Uống:"), g);
        g.gridx = 3;
        form.add(txtTen, g);

        g.gridy = 1;
        g.gridx = 0;
        form.add(makeLabel("Giá (VNĐ):"), g);
        g.gridx = 1;
        form.add(txtGia, g);
        g.gridx = 2;
        form.add(makeLabel("Mã Loại:"), g);
        g.gridx = 3;
        form.add(txtMaLoai, g);

        g.gridy = 2;
        g.gridx = 0;
        g.gridwidth = 4;
        form.add(buildButtons(), g);

        JScrollPane sp = buildStyledTable(new String[] { "Mã TU", "Tên Thức Uống", "Giá", "Mã Loại", "Loại" });
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1)
                fillForm();
        });

        content.add(form, BorderLayout.NORTH);
        content.add(sp, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        attachSearchListeners(txtMa, txtTen, txtGia, txtMaLoai);
        loadData();
    }

    private JPanel buildButtons() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        p.setOpaque(false);
        JButton bSearch = makeButton("Tìm Kiếm", new Color(0, 150, 136));
        JButton bSave = makeButton("Lưu", new Color(33, 150, 243));
        JButton bUpdate = makeButton("Cập Nhật", new Color(255, 152, 0));
        JButton bDelete = makeButton("Xóa", new Color(244, 67, 54));
        JButton bRefresh = makeButton("Làm Mới", new Color(156, 39, 176));
        bSearch.addActionListener(e -> activateSearch());
        bSave.addActionListener(e -> doSave());
        bUpdate.addActionListener(e -> doUpdate());
        bDelete.addActionListener(e -> doDelete());
        bRefresh.addActionListener(e -> {
            clearForm();
            loadData();
        });
        p.add(bSearch);
        p.add(bSave);
        p.add(bUpdate);
        p.add(bDelete);
        p.add(bRefresh);
        return p;
    }

    @Override
    public void loadData() {
        tableModel.setRowCount(0);
        for (ThucUong tu : service.getAll()) {
            tableModel.addRow(new Object[] {
                    tu.getMaThucUong(), tu.getTenThucUong(), tu.getGia(),
                    tu.getLoaiThucUong() != null ? tu.getLoaiThucUong().getMaLoai() : "",
                    tu.getLoaiThucUong() != null ? tu.getLoaiThucUong().getTenLoaiThucUong() : ""
            });
        }
    }

    private void fillForm() {
        int row = table.getSelectedRow();
        if (row < 0)
            return;
        isUpdatingForm = true;
        int m = table.convertRowIndexToModel(row);
        txtMa.setText(str(tableModel.getValueAt(m, 0)));
        txtTen.setText(str(tableModel.getValueAt(m, 1)));
        txtGia.setText(str(tableModel.getValueAt(m, 2)));
        txtMaLoai.setText(str(tableModel.getValueAt(m, 3)));
        isUpdatingForm = false;
    }

    private void clearForm() {
        isUpdatingForm = true;
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtMaLoai.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        clearFilter();
    }

    private void activateSearch() {
        isUpdatingForm = true;
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtMaLoai.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        applyFilter();
    }

    @Override
    protected void applyFilter() {
        applyFilterOnColumns(new JTextField[] { txtMa, txtTen, txtGia, txtMaLoai }, FILTER_COLS);
    }

    private void doSave() {
        try {
            String ma = service.getNextId();

            String ten = txtTen.getText().trim();
            if (ma.isEmpty() || ten.isEmpty()) {
                showError(this, "Tên không được để trống!");
                return;
            }
            ThucUong tu = new ThucUong();
            tu.setMaThucUong(ma);
            tu.setTenThucUong(ten);

            String g = txtGia.getText().trim();
            if (!isValidPrice(g)) {
                showError(this, "Giá bán không hợp lệ");
                return;
            }
            tu.setGia(new BigDecimal(g));

            service.create(tu);
            showSuccess(this, "Lưu thành công!");
            clearForm();
            loadData();
        } catch (Exception ex) {
            showError(this, ex.getMessage());
        }
    }

    private boolean isValidPrice(String price) {
        if (price == null) {
            return false;
        }

        try {
            BigDecimal salary = new BigDecimal(price);

            if (salary.compareTo(BigDecimal.ZERO) < 0) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void doUpdate() {
        try {
            String ma = txtMa.getText().trim();
            if (ma.isEmpty()) {
                showError(this, "Chọn thức uống cần cập nhật!");
                return;
            }
            ThucUong tu = service.getById(ma);
            if (tu == null) {
                showError(this, "Thức uống không tồn tại!");
                return;
            }
            tu.setTenThucUong(txtTen.getText().trim());

            String g = txtGia.getText().trim();
            if (!isValidPrice(g)) {
                showError(this, "Giá bán không hợp lệ");
                return;
            }
            tu.setGia(new BigDecimal(g));

            service.update(tu);
            showSuccess(this, "Cập nhật thành công!");
            clearForm();
            loadData();
        } catch (Exception ex) {
            showError(this, ex.getMessage());
        }
    }

    private void doDelete() {
        String ma = txtMa.getText().trim();
        if (ma.isEmpty()) {
            showError(this, "Chọn thức uống cần xóa!");
            return;
        }
        int c = JOptionPane.showConfirmDialog(this, "Xác nhận xóa thức uống " + ma + "?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            try {
                service.delete(ma);
                clearForm();
                loadData();
            } catch (Exception ex) {
                showError(this, ex.getMessage());
            }
        }
    }

    private String str(Object o) {
        return o != null ? o.toString() : "";
    }
}
