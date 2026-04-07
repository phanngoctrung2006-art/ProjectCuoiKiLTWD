package com.cafe.view;

import com.cafe.model.entity.NhaCungCap;
import com.cafe.model.entity.NhanVien;
import com.cafe.model.entity.PhieuNhap;
import com.cafe.service.NhaCungCapService;
import com.cafe.service.NhanVienService;
import com.cafe.service.PhieuNhapService;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class PhieuNhapPanel extends BaseManagementPanel {
    private final PhieuNhapService service;
    private final NhanVienService nhanVienService;
    private final NhaCungCapService nhaCungCapService;

    private JTextField txtMa, txtNgay, txtTongTien, txtMaNV, txtMaNCC;

    private static final int[] FILTER_COLS = { 0, 1, 2, 3 };

    public PhieuNhapPanel(PhieuNhapService service,
            NhanVienService nhanVienService,
            NhaCungCapService nhaCungCapService) {
        this.service = service;
        this.nhanVienService = nhanVienService;
        this.nhaCungCapService = nhaCungCapService;
        build();
    }

    private void build() {
        add(makeHeader("QUẢN LÝ PHIẾU NHẬP"), BorderLayout.NORTH);

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
        txtNgay = makeField(12);
        txtTongTien = makeField(12);
        txtMaNV = makeField(10);
        txtMaNCC = makeField(10);
        txtTongTien.setEditable(false);
        txtTongTien.setToolTipText("Tổng tiền tự động tính từ chi tiết phiếu nhập");

        g.gridy = 0;
        g.gridwidth = 1;
        g.gridx = 0;
        form.add(makeLabel("Mã Phiếu:"), g);
        g.gridx = 1;
        form.add(txtMa, g);
        g.gridx = 2;
        form.add(makeLabel("Ngày Nhập:"), g);
        g.gridx = 3;
        form.add(txtNgay, g);

        g.gridy = 1;
        g.gridx = 0;
        form.add(makeLabel("Mã NV:"), g);
        g.gridx = 1;
        form.add(txtMaNV, g);
        g.gridx = 2;
        form.add(makeLabel("Mã NCC:"), g);
        g.gridx = 3;
        form.add(txtMaNCC, g);

        g.gridy = 2;
        g.gridx = 0;
        form.add(makeLabel("Tổng Tiền:"), g);
        g.gridx = 1;
        form.add(txtTongTien, g);

        g.gridy = 3;
        g.gridx = 0;
        g.gridwidth = 4;
        form.add(buildButtons(), g);

        JScrollPane sp = buildStyledTable(
                new String[] { "Mã Phiếu", "Ngày Nhập", "Nhân Viên", "Nhà Cung Cấp", "Tổng Tiền" });
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1)
                fillForm();
        });

        content.add(form, BorderLayout.NORTH);
        content.add(sp, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        attachSearchListeners(txtMa, txtNgay, txtMaNV, txtMaNCC);
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
        for (PhieuNhap pn : service.getAll()) {
            tableModel.addRow(new Object[] {
                    pn.getMaPhieuNhap(), pn.getNgayNhap(),
                    pn.getNhanVien() != null ? pn.getNhanVien().getTenNhanVien() : "",
                    pn.getNhaCungCap() != null ? pn.getNhaCungCap().getTenNhaCungCap() : "",
                    pn.getTongTien()
            });
        }
    }

    private void fillForm() {
        int row = table.getSelectedRow();
        if (row < 0)
            return;
        isUpdatingForm = true;
        int m = table.convertRowIndexToModel(row);
        String ma = str(tableModel.getValueAt(m, 0));
        txtMa.setText(ma);
        txtNgay.setText(str(tableModel.getValueAt(m, 1)));
        txtTongTien.setText(str(tableModel.getValueAt(m, 4)));
        // Load FK codes from DB
        PhieuNhap pn = service.getById(ma);
        if (pn != null) {
            txtMaNV.setText(pn.getNhanVien() != null ? pn.getNhanVien().getMaNhanVien() : "");
            txtMaNCC.setText(pn.getNhaCungCap() != null ? pn.getNhaCungCap().getMaNhaCungCap() : "");
        }
        isUpdatingForm = false;
    }

    private void clearForm() {
        isUpdatingForm = true;
        txtMa.setText("");
        txtNgay.setText(LocalDate.now().toString());
        txtTongTien.setText("0");
        txtMaNV.setText("");
        txtMaNCC.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        clearFilter();
    }

    private void activateSearch() {
        isUpdatingForm = true;
        txtMa.setText("");
        txtNgay.setText("");
        txtTongTien.setText("");
        txtMaNV.setText("");
        txtMaNCC.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        applyFilter();
    }

    @Override
    protected void applyFilter() {
        applyFilterOnColumns(new JTextField[] { txtMa, txtNgay, txtMaNV, txtMaNCC }, FILTER_COLS);
    }

    private void doSave() {
        try {
            String ma = txtMa.getText().trim();
            String ngay = txtNgay.getText().trim();
            if (ma.isEmpty() || ngay.isEmpty()) {
                showError(this, "Mã và Ngày không được để trống!");
                return;
            }
            NhanVien nv = txtMaNV.getText().trim().isEmpty() ? null : nhanVienService.getById(txtMaNV.getText().trim());
            NhaCungCap ncc = txtMaNCC.getText().trim().isEmpty() ? null
                    : nhaCungCapService.getById(txtMaNCC.getText().trim());
            PhieuNhap pn = new PhieuNhap();
            pn.setMaPhieuNhap(ma);
            pn.setNgayNhap(Date.valueOf(ngay));
            pn.setTongTien(BigDecimal.ZERO);
            pn.setNhanVien(nv);
            pn.setNhaCungCap(ncc);
            service.create(pn);
            showSuccess(this, "Lưu thành công!");
            clearForm();
            loadData();
        } catch (Exception ex) {
            showError(this, ex.getMessage());
        }
    }

    private void doUpdate() {
        try {
            String ma = txtMa.getText().trim();
            if (ma.isEmpty()) {
                showError(this, "Chọn phiếu nhập cần cập nhật!");
                return;
            }
            PhieuNhap pn = service.getById(ma);
            if (pn == null) {
                showError(this, "Phiếu nhập không tồn tại!");
                return;
            }
            String ngay = txtNgay.getText().trim();
            if (!ngay.isEmpty())
                pn.setNgayNhap(Date.valueOf(ngay));
            String maNV = txtMaNV.getText().trim();
            if (!maNV.isEmpty())
                pn.setNhanVien(nhanVienService.getById(maNV));
            String maNCC = txtMaNCC.getText().trim();
            if (!maNCC.isEmpty())
                pn.setNhaCungCap(nhaCungCapService.getById(maNCC));
            service.update(pn);
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
            showError(this, "Chọn phiếu nhập cần xóa!");
            return;
        }
        int c = JOptionPane.showConfirmDialog(this, "Xác nhận xóa phiếu nhập " + ma + "?", "Xác nhận",
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
