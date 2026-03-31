package com.cafe;

import com.cafe.dao.HoaDonDAO;
import com.cafe.dao.KhachHangDAO;
import com.cafe.dao.ReportDAO;
import com.cafe.dao.impl.HoaDonDAOImpl;
import com.cafe.dao.impl.KhachHangDAOImpl;
import com.cafe.dao.impl.ReportDAOImpl;
import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Test class để kiểm tra database connection và các DAO.
 * Chạy class này trước để đảm bảo mọi thứ hoạt động.
 */
public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("=== TEST DATABASE CONNECTION ===");

        try {
            // Test DAO
            HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();
            KhachHangDAO khachHangDAO = new KhachHangDAOImpl();
            ReportDAO reportDAO = new ReportDAOImpl();

            // Test 1: Lấy tất cả khách hàng
            System.out.println("\n1. DANH SÁCH KHÁCH HÀNG:");
            List<KhachHang> khachHangList = khachHangDAO.findAll();
            for (KhachHang kh : khachHangList) {
                System.out.println("  - " + kh.getMaKhachHang() + ": " + kh.getTenKhachHang());
            }

            // Test 2: Lấy tất cả hóa đơn
            System.out.println("\n2. DANH SÁCH HÓA ĐƠN:");
            List<HoaDon> hoaDonList = hoaDonDAO.findAll();
            for (HoaDon hd : hoaDonList) {
                String khachHang = hd.getKhachHang() != null ? hd.getKhachHang().getTenKhachHang() : "N/A";
                System.out.println("  - " + hd.getMaHoaDon() + ": " + khachHang + " - " + hd.getTongTien() + " VND");
            }

            // Test 3: Báo cáo doanh thu theo khách hàng
            System.out.println("\n3. DOANH THU THEO KHÁCH HÀNG:");
            List<Map<String, Object>> revenueByCustomer = reportDAO.q05_revenueByCustomer();
            for (Map<String, Object> row : revenueByCustomer) {
                System.out.println("  - " + row.get("tenKhachHang") + ": " + row.get("tongDoanhThu") + " VND");
            }

            // Test 4: Thống kê
            System.out.println("\n4. THỐNG KÊ:");
            long totalOrders = reportDAO.q09_countTotalOrders();
            BigDecimal avgValue = reportDAO.q10_averageOrderValue();
            BigDecimal totalRevenue = reportDAO.q13_totalRevenue();

            System.out.println("  - Tổng số hóa đơn: " + totalOrders);
            System.out.println("  - Giá trị TB: " + avgValue + " VND");
            System.out.println("  - Tổng doanh thu: " + totalRevenue + " VND");

            System.out.println("\n✅ DATABASE CONNECTION SUCCESSFUL!");
            System.out.println("✅ TẤT CẢ DAO HOẠT ĐỘNG TỐT!");
            System.out.println("\n🎉 Bây giờ bạn có thể chạy AppLauncher để mở giao diện!");

        } catch (Exception e) {
            System.err.println("\n❌ LỖI DATABASE CONNECTION:");
            System.err.println("Chi tiết lỗi: " + e.getMessage());
            e.printStackTrace();

            System.out.println("\n🔧 HƯỚNG DẪN KHẮC PHỤC:");
            System.out.println("1. Kiểm tra MySQL đang chạy");
            System.out.println("2. Chạy các file SQL:");
            System.out.println("   - database/create_database.sql");
            System.out.println("   - database/create_table.sql");
            System.out.println("   - database/insert_data.sql");
            System.out.println("3. Kiểm tra persistence.xml:");
            System.out.println("   - jdbc.url: jdbc:mysql://localhost:3306/QuanLyCafe");
            System.out.println("   - jdbc.user: root");
            System.out.println("   - jdbc.password: [password của bạn]");
        }
    }
}

