package com.cafe.view;

import com.cafe.controller.HoaDonController;
import com.cafe.model.entity.ChiTietHoaDon;
import com.cafe.model.entity.ThucUong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class HoaDonChiTietDialog extends JDialog {
    private final HoaDonController controller;
    private final String maHoaDon;

    private JComboBox<String> comboThucUong;
    private JTextField txtSoLuong;
    private JTable tableChiTiet;
    private DefaultTableModel modelChiTiet;
    private JLabel lblTongTien;

    private List<ThucUong> listThucUong;

    public HoaDonChiTietDialog(Frame owner, String maHoaDon, HoaDonController controller) {
        super(owner, "Quản Lý Chi Tiết - Hóa Đơn " + maHoaDon, true);
        this.controller = controller;
        this.maHoaDon = maHoaDon;

        initComponents();
        loadThucUong();
        loadData();

        setSize(800, 600);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(35, 45, 60));

        // Nửa trên: Form chọn món
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelTop.setOpaque(false);
        panelTop.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 200, 255), 1),
                "Thêm Món", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12), Color.WHITE));

        JLabel lblThucUong = new JLabel("Thức Uống:");
        lblThucUong.setForeground(Color.WHITE);
        comboThucUong = new JComboBox<>();
        comboThucUong.setPreferredSize(new Dimension(200, 30));

        JLabel lblSoLuong = new JLabel("Số Lượng:");
        lblSoLuong.setForeground(Color.WHITE);
        txtSoLuong = new JTextField(5);
        txtSoLuong.setText("1");
        txtSoLuong.setPreferredSize(new Dimension(50, 30));

        JButton btnAdd = new JButton("Thêm / Cập Nhật");
        btnAdd.setBackground(new Color(76, 175, 80));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.addActionListener(e -> addThucUong());

        JButton btnDecrease = new JButton("Giảm 1");
        btnDecrease.setBackground(new Color(255, 152, 0));
        btnDecrease.setForeground(Color.WHITE);
        btnDecrease.addActionListener(e -> decreaseThucUong());

        JButton btnDelete = new JButton("Xóa Hết");
        btnDelete.setBackground(new Color(244, 67, 54));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(e -> deleteThucUong());

        panelTop.add(lblThucUong);
        panelTop.add(comboThucUong);
        panelTop.add(lblSoLuong);
        panelTop.add(txtSoLuong);
        panelTop.add(btnAdd);
        panelTop.add(btnDecrease);
        panelTop.add(btnDelete);

        // Nửa giữa: Table
        modelChiTiet = new DefaultTableModel(
                new String[]{"ID", "Tên Thức Uống", "Đơn Giá", "Số Lượng", "Thành Tiền"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableChiTiet = new JTable(modelChiTiet);
        tableChiTiet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableChiTiet.setRowHeight(28);
        tableChiTiet.getTableHeader().setPreferredSize(new Dimension(0, 30));

        JScrollPane scrollPane = new JScrollPane(tableChiTiet);

        // Nửa dưới: Tổng tiền và Đóng
        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.setOpaque(false);
        panelBottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblTongTien = new JLabel("Tổng Tiền 0 VNĐ");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTien.setForeground(new Color(255, 200, 50));

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> {
            dispose();
        });

        panelBottom.add(lblTongTien, BorderLayout.WEST);
        panelBottom.add(btnClose, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
    }

    private void loadThucUong() {
        listThucUong = controller.getAllThucUong();
        comboThucUong.removeAllItems();
        for (ThucUong tu : listThucUong) {
            comboThucUong.addItem(tu.getMaThucUong() + " - " + tu.getTenThucUong() + " (" + tu.getGia() + ")");
        }
    }

    private void loadData() {
        modelChiTiet.setRowCount(0);
        List<ChiTietHoaDon> details = controller.getChiTietHoaDonByMa(maHoaDon);
        BigDecimal sum = BigDecimal.ZERO;

        for (ChiTietHoaDon c : details) {
            String ma = c.getThucUong() != null ? c.getThucUong().getMaThucUong() : "";
            String tenSp = c.getThucUong() != null ? c.getThucUong().getTenThucUong() : "Không Rõ";
            BigDecimal gia = c.getThucUong() != null ? c.getThucUong().getGia() : BigDecimal.ZERO;
            int soLuong = c.getSoLuong();
            BigDecimal thanhTien = gia.multiply(new BigDecimal(soLuong));
            
            sum = sum.add(thanhTien);
            modelChiTiet.addRow(new Object[]{ma, tenSp, gia, soLuong, thanhTien});
        }
        lblTongTien.setText("Tổng Tiền: " + sum.toString() + " VNĐ");
    }

    private void addThucUong() {
        int idx = comboThucUong.getSelectedIndex();
        if (idx < 0) return;
        String maThucUong = listThucUong.get(idx).getMaThucUong();
        int soLuong;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuong <= 0) throw new NumberFormatException();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            controller.addThucUongToHoaDon(maHoaDon, maThucUong, soLuong);
            loadData();
            JOptionPane.showMessageDialog(this, "Thêm món thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteThucUong() {
        int row = tableChiTiet.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String maThucUong = modelChiTiet.getValueAt(row, 0).toString();
        try {
            controller.deleteThucUongFromHoaDon(maHoaDon, maThucUong);
            loadData();
            JOptionPane.showMessageDialog(this, "Xóa món thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void decreaseThucUong() {
        int row = tableChiTiet.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để giảm món!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String maThucUong = modelChiTiet.getValueAt(row, 0).toString();
        try {
            controller.decreaseThucUongFromHoaDon(maHoaDon, maThucUong);
            loadData();
            JOptionPane.showMessageDialog(this, "Giảm món thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Giảm thất bại: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
