package com.cafe.view;

import com.cafe.controller.NhanVienController;
import com.cafe.model.entity.NhanVien;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Màn hình cho chức năng Quản Lý Nhân Sự (Nhân Viên).
 * Cho phép xem lương, ngày sinh, địa chỉ, bổ sung nút Cập nhật và Xóa.
 */
public class NhanVienPanel extends BaseManagementPanel {

    private final NhanVienController controller;
    private JTextField txtMa, txtTen, txtNgaySinh, txtDiaChi, txtSdt, txtLuong;

    private static final int[] FILTER_COLS = { 0, 1, 2, 3, 4, 5 };

    public NhanVienPanel(NhanVienController controller) {
        this.controller = controller;
        build();
    }

    private void build() {
        add(makeHeader("QUẢN LÝ NHÂN VIÊN"), BorderLayout.NORTH);

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
        txtTen = makeField(18);
        txtNgaySinh = makeField(12);
        txtDiaChi = makeField(18);
        txtSdt = makeField(15);
        txtLuong = makeField(12);

        row(form, g, 0, "Mã NV:", txtMa, "Họ Tên:", txtTen);
        row(form, g, 1, "Ngày Sinh:", txtNgaySinh, "Địa Chỉ:", txtDiaChi);
        row(form, g, 2, "Số ĐT:", txtSdt, "Lương:", txtLuong);

        g.gridy = 3; g.gridx = 0; g.gridwidth = 4;
        form.add(buildButtons(), g);

        JScrollPane sp = buildStyledTable(new String[] { "Mã NV", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "SĐT", "Lương" });
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) fillForm();
        });

        content.add(form, BorderLayout.NORTH);
        content.add(sp, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        attachSearchListeners(txtMa, txtTen, txtNgaySinh, txtDiaChi, txtSdt, txtLuong);
        loadData();
    }

    private void row(JPanel p, GridBagConstraints g, int y,
                     String l1, JTextField f1, String l2, JTextField f2) {
        g.gridy = y; g.gridwidth = 1;
        g.gridx = 0; p.add(makeLabel(l1), g);
        g.gridx = 1; p.add(f1, g);
        g.gridx = 2; p.add(makeLabel(l2), g);
        g.gridx = 3; p.add(f2, g);
    }

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

    @Override
    public void loadData() {
        tableModel.setRowCount(0);
        for (NhanVien nv : controller.getAll()) {
            tableModel.addRow(new Object[] {
                nv.getMaNhanVien(), nv.getTenNhanVien(),
                nv.getNgaySinh(), nv.getDiaChi(),
                nv.getSoDienThoai(), nv.getLuong()
            });
        }
    }

    /**
     * Logic đẩy (Fill) dữ liệu khi click vô danh sách:
     * Sử dụng sự kiện ListSelectionListener từ JTable. RowIndexToModel được dùng thay 
     * vì RowIndex thường để lấy dữ liệu đúng vị trí ngay cả khi bảng JTable đang bị Sort (Sắp xếp) hay Filter.
     */
    private void fillForm() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        
        // Khóa Event không mong muốn phát súng (trigger) liên tục khi Fill form
        isUpdatingForm = true;
        
        // Convert dữ liệu để tương thích với chức năng Lọc (Filter) khi view bị biến dạng
        int m = table.convertRowIndexToModel(row);
        
        txtMa.setText(str(tableModel.getValueAt(m, 0)));
        txtTen.setText(str(tableModel.getValueAt(m, 1)));
        txtNgaySinh.setText(str(tableModel.getValueAt(m, 2)));
        txtDiaChi.setText(str(tableModel.getValueAt(m, 3)));
        txtSdt.setText(str(tableModel.getValueAt(m, 4)));
        txtLuong.setText(str(tableModel.getValueAt(m, 5)));
        
        isUpdatingForm = false;
    }

    private void clearForm() {
        isUpdatingForm = true;
        txtMa.setText(""); txtTen.setText(""); txtNgaySinh.setText("");
        txtDiaChi.setText(""); txtSdt.setText(""); txtLuong.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        clearFilter();
    }

    private void activateSearch() {
        isUpdatingForm = true;
        txtMa.setText(""); txtTen.setText(""); txtNgaySinh.setText("");
        txtDiaChi.setText(""); txtSdt.setText(""); txtLuong.setText("");
        table.clearSelection();
        isUpdatingForm = false;
        applyFilter();
    }

    @Override
    protected void applyFilter() {
        applyFilterOnColumns(new JTextField[]{ txtMa, txtTen, txtNgaySinh, txtDiaChi, txtSdt, txtLuong }, FILTER_COLS);
    }

    /**
     * Logic hàm Thêm Mới: 
     * 1. Thu thập dữ liệu từ giao diện Form (Họ Tên, Ngày sinh, Loại...)
     * 2. Gọi API getNextId để tự động sinh mã mới cho nhân viên.
     * 3. Ép kiểu String từ GUI về BigDecimal / Date.
     * 4. Gửi entity xuống Tầng Service để validation và Lưu SQL.
     */
    private void doSave() {
        try {
            NhanVien nv = new NhanVien();
            nv.setMaNhanVien(controller.getNextId());
            nv.setTenNhanVien(txtTen.getText().trim());
            // Sử dụng chuẩn java.sql.Date cho Data Type Ngày sinh trên DB
            nv.setNgaySinh(Date.valueOf(txtNgaySinh.getText().trim()));
            nv.setDiaChi(txtDiaChi.getText().trim());
            nv.setSoDienThoai(txtSdt.getText().trim());
            
            String l = txtLuong.getText().trim();
            if (!l.isEmpty()) nv.setLuong(new BigDecimal(l));
            
            // Invoke Service logic
            controller.save(nv);
            showSuccess(this, "Lưu thành công!"); clearForm(); loadData();
        } catch (Exception ex) {
            // Ném lỗi lên GUI qua Component JOptionPane nếu validation (ex) cấm thêm
            showError(this, ex.getMessage());
        }
    }

    private void doUpdate() {
        try {
            String ma = txtMa.getText().trim();
            if (ma.isEmpty()) { showError(this, "Chọn nhân viên cần cập nhật!"); return; }
            NhanVien nv = controller.getById(ma);
            if (nv == null) { showError(this, "Nhân viên không tồn tại!"); return; }
            nv.setTenNhanVien(txtTen.getText().trim());
            String ns = txtNgaySinh.getText().trim();
            if (!ns.isEmpty()) nv.setNgaySinh(Date.valueOf(ns));
            nv.setDiaChi(txtDiaChi.getText().trim());
            nv.setSoDienThoai(txtSdt.getText().trim());
            String l = txtLuong.getText().trim();
            if (!l.isEmpty()) nv.setLuong(new BigDecimal(l));
            controller.update(nv);
            showSuccess(this, "Cập nhật thành công!"); clearForm(); loadData();
        } catch (Exception ex) {
            showError(this, ex.getMessage());
        }
    }

    private void doDelete() {
        String ma = txtMa.getText().trim();
        if (ma.isEmpty()) { showError(this, "Chọn nhân viên cần xóa!"); return; }
        int c = JOptionPane.showConfirmDialog(this, "Xác nhận xóa nhân viên " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            try { controller.delete(ma); clearForm(); loadData(); }
            catch (Exception ex) { showError(this, ex.getMessage()); }
        }
    }

    private String str(Object o) { return o != null ? o.toString() : ""; }
}
