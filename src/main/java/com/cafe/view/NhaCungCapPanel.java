package com.cafe.view;

import com.cafe.model.entity.NhaCungCap;
import com.cafe.service.NhaCungCapService;
import javax.swing.*;
import java.awt.*;

public class NhaCungCapPanel extends BaseManagementPanel {

    private final NhaCungCapService service;
    private JTextField txtMa, txtTen, txtDiaChi, txtSdt;

    private static final int[] FILTER_COLS = {0, 1, 2, 3};

    public NhaCungCapPanel(NhaCungCapService service) {
        this.service = service;
        build();
    }

    private void build() {
        add(makeHeader("QUẢN LÝ NHÀ CUNG CẤP"), BorderLayout.NORTH);

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

        txtMa     = makeField(10);
        txtTen    = makeField(20);
        txtDiaChi = makeField(20);
        txtSdt    = makeField(15);

        g.gridy = 0; g.gridwidth = 1;
        g.gridx = 0; form.add(makeLabel("Mã NCC:"), g);
        g.gridx = 1; form.add(txtMa, g);
        g.gridx = 2; form.add(makeLabel("Tên NCC:"), g);
        g.gridx = 3; form.add(txtTen, g);

        g.gridy = 1;
        g.gridx = 0; form.add(makeLabel("Địa Chỉ:"), g);
        g.gridx = 1; form.add(txtDiaChi, g);
        g.gridx = 2; form.add(makeLabel("Số ĐT:"), g);
        g.gridx = 3; form.add(txtSdt, g);

        g.gridy = 2; g.gridx = 0; g.gridwidth = 4;
        form.add(buildButtons(), g);

        JScrollPane sp = buildStyledTable(new String[]{"Mã NCC", "Tên Nhà Cung Cấp", "Địa Chỉ", "SĐT"});
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) fillForm();
        });

        content.add(form, BorderLayout.NORTH);
        content.add(sp, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        attachSearchListeners(txtMa, txtTen, txtDiaChi, txtSdt);
        loadData();
    }

    private JPanel buildButtons() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        p.setOpaque(false);
        JButton bSearch = makeButton("Tìm Kiếm", new Color(0, 150, 136));
        JButton bSave   = makeButton("Lưu",       new Color(33, 150, 243));
        JButton bUpdate = makeButton("Cập Nhật",  new Color(255, 152, 0));
        JButton bDelete = makeButton("Xóa",       new Color(244, 67, 54));
        JButton bRefresh= makeButton("Làm Mới",   new Color(156, 39, 176));
        bSearch.addActionListener(e -> activateSearch());
        bSave.addActionListener(e -> doSave());
        bUpdate.addActionListener(e -> doUpdate());
        bDelete.addActionListener(e -> doDelete());
        bRefresh.addActionListener(e -> { clearForm(); loadData(); });
        p.add(bSearch); p.add(bSave); p.add(bUpdate); p.add(bDelete); p.add(bRefresh);
        return p;
    }

    @Override
    public void loadData() {
        tableModel.setRowCount(0);
        for (NhaCungCap ncc : service.getAll())
            tableModel.addRow(new Object[]{ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getDiaChi(), ncc.getSoDienThoai()});
    }

    private void fillForm() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        isUpdatingForm = true;
        int m = table.convertRowIndexToModel(row);
        txtMa.setText(str(tableModel.getValueAt(m, 0)));
        txtTen.setText(str(tableModel.getValueAt(m, 1)));
        txtDiaChi.setText(str(tableModel.getValueAt(m, 2)));
        txtSdt.setText(str(tableModel.getValueAt(m, 3)));
        isUpdatingForm = false;
    }

    private void clearForm() {
        isUpdatingForm = true;
        txtMa.setText(""); txtTen.setText(""); txtDiaChi.setText(""); txtSdt.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        clearFilter();
    }

    private void activateSearch() {
        isUpdatingForm = true;
        txtMa.setText(""); txtTen.setText(""); txtDiaChi.setText(""); txtSdt.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        applyFilter();
    }

    @Override
    protected void applyFilter() {
        applyFilterOnColumns(new JTextField[]{txtMa, txtTen, txtDiaChi, txtSdt}, FILTER_COLS);
    }

    private void doSave() {
        try {
            String ma = txtMa.getText().trim(); String ten = txtTen.getText().trim();
            if (ma.isEmpty() || ten.isEmpty()) { showError(this, "Mã và Tên không được để trống!"); return; }
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNhaCungCap(ma); ncc.setTenNhaCungCap(ten);
            ncc.setDiaChi(txtDiaChi.getText().trim()); ncc.setSoDienThoai(txtSdt.getText().trim());
            service.create(ncc);
            showSuccess(this, "Lưu thành công!"); clearForm(); loadData();
        } catch (Exception ex) { showError(this, ex.getMessage()); }
    }

    private void doUpdate() {
        try {
            String ma = txtMa.getText().trim();
            if (ma.isEmpty()) { showError(this, "Chọn nhà cung cấp cần cập nhật!"); return; }
            NhaCungCap ncc = service.getById(ma);
            if (ncc == null) { showError(this, "Nhà cung cấp không tồn tại!"); return; }
            ncc.setTenNhaCungCap(txtTen.getText().trim());
            ncc.setDiaChi(txtDiaChi.getText().trim()); ncc.setSoDienThoai(txtSdt.getText().trim());
            service.update(ncc);
            showSuccess(this, "Cập nhật thành công!"); clearForm(); loadData();
        } catch (Exception ex) { showError(this, ex.getMessage()); }
    }

    private void doDelete() {
        String ma = txtMa.getText().trim();
        if (ma.isEmpty()) { showError(this, "Chọn NCC cần xóa!"); return; }
        int c = JOptionPane.showConfirmDialog(this, "Xác nhận xóa NCC " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            try { service.delete(ma); clearForm(); loadData(); } catch (Exception ex) { showError(this, ex.getMessage()); }
        }
    }

    private String str(Object o) { return o != null ? o.toString() : ""; }
}
