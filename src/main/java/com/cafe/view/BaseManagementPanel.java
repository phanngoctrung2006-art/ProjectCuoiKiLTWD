package com.cafe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp giao diện nền tảng (Base class) cho các màn hình quản lý danh sách (HoaDon, NhanVien, KhachHang...).
 * Chứa sẵn cấu trúc bảng (JTable), thanh tìm kiếm, và các quy chuẩn UI (Màu sắc, Font chữ).
 * Ứng dụng mẫu thiết kế Template Method cho việc tái sử dụng UI.
 */
public abstract class BaseManagementPanel extends JPanel {

    protected JTable table;
    protected DefaultTableModel tableModel;
    protected TableRowSorter<DefaultTableModel> sorter;
    protected boolean isUpdatingForm = false;

    // Các hằng số định nghĩa bảng mã màu (Color Palette) chuẩn của ứng dụng
    protected static final Color BG_DARK     = new Color(20, 25, 35);
    protected static final Color BG_PANEL    = new Color(35, 45, 60);
    protected static final Color ACCENT      = new Color(100, 200, 255);
    protected static final Color FIELD_BG    = new Color(50, 60, 80);
    protected static final Color BORDER_CLR  = new Color(80, 120, 180);

    public BaseManagementPanel() {
        setLayout(new BorderLayout(0, 0));
        setBackground(BG_DARK);
    }

    // Factory helpers

    protected JPanel makeHeader(String title) {
        JPanel panel = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new GradientPaint(0, 0, new Color(30, 144, 255), getWidth(), 0, new Color(0, 191, 255)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setPreferredSize(new Dimension(0, 60));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 14));
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setForeground(Color.WHITE);
        panel.add(lbl);
        return panel;
    }

    protected JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(ACCENT);
        return l;
    }

    protected JTextField makeField(int cols) {
        JTextField f = new JTextField(cols);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        f.setBackground(FIELD_BG);
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setBorder(BorderFactory.createLineBorder(BORDER_CLR, 1));
        return f;
    }

    protected JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bg.darker(), 1),
                BorderFactory.createEmptyBorder(7, 15, 7, 15)));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(bg.brighter()); }
            public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(bg); }
        });
        return btn;
    }

    /**
     * Khởi tạo và trang trí JTable dùng chung.
     * Cung cấp các tính năng hiển thị màu chẵn/lẻ, màu khi được chọn (SelectionColor),
     * và khóa không cho người dùng chỉnh sửa dữ liệu trực tiếp trên bảng.
     */
    protected JScrollPane buildStyledTable(String[] columns) {
        // Gắn model cho bảng, đè phương thức isCellEditable về false để cấm sửa trực tiếp ô (Cell)
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        
        // Hỗ trợ sắp xếp (Sorting) và lọc (Filtering) trực tiếp dựa trên Model của JTable
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        
        // --- Stylings (Chỉnh giao diện Bảng) ---
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(28); // Giãn dòng để dễ nhìn dữ liệu hơn
        table.setBackground(FIELD_BG);
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(80, 100, 130)); // Đổi màu lưới phân cách kẻ bảng
        
        // Màu sắc khi người dùng bôi đen / chọn dòng (Row Selection)
        table.setSelectionBackground(new Color(30, 144, 255)); 
        table.setSelectionForeground(Color.WHITE);
        
        // Chỉnh sửa Header của Table (Phần in đậm phía trên cùng chứa tên cột)
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(30, 100, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));

        // Nhét JTable vào JScrollPane để có thanh cuộn (Scrollbar) khi dữ liệu quá dài vượt màn hình
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(BORDER_CLR, 2));
        sp.getViewport().setBackground(FIELD_BG);
        return sp;
    }

    protected void attachSearchListeners(JTextField... fields) {
        DocumentListener dl = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e)  { if (!isUpdatingForm) applyFilter(); }
            @Override public void removeUpdate(DocumentEvent e)  { if (!isUpdatingForm) applyFilter(); }
            @Override public void changedUpdate(DocumentEvent e) { if (!isUpdatingForm) applyFilter(); }
        };
        for (JTextField f : fields) f.getDocument().addDocumentListener(dl);
    }

    /**
     * Thuật toán lọc dữ liệu dựa vào Biểu thức chính quy (Regex). 
     * Hỗ trợ tìm kiếm thời gian thực (Real-time Filtering) lúc người dùng gõ vào các ô TextBox tìm kiếm.
     */
    protected void applyFilterOnColumns(JTextField[] fields, int[] cols) {
        if (sorter == null) return;
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        // Duyệt qua từng Custom Field đã truyền vào bên trong hàm
        for (int i = 0; i < fields.length; i++) {
            String t = fields[i].getText().trim();
            if (!t.isEmpty()) {
                // Sử dụng (?i) trong regex để phớt lờ chữ hoa - chữ thường (Case Insensitive)
                // Dùng Pattern.quote(t) để tránh các kí tự đặc biệt làm sai cấu trúc Regex
                try { filters.add(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(t), cols[i])); }
                catch (Exception ignored) {}
            }
        }
        // Gộp tất cả các luật lọc (Filters) lại bằng hàm andFilter() => Nghĩa là lọc thoả mãn ĐỒNG THỜI nhiều cột
        sorter.setRowFilter(filters.isEmpty() ? null : RowFilter.andFilter(filters));
    }

    protected void clearFilter() {
        if (sorter != null) sorter.setRowFilter(null);
    }

    protected void showError(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    protected void showSuccess(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

    // Subclass phải implement:
    protected abstract void applyFilter();
    protected abstract void loadData();
}
