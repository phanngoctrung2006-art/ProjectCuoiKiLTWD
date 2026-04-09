package com.cafe.dao;

import com.cafe.model.entity.HoaDon;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Interface cấu hình các phương thức lấy dữ liệu chuyên dành cho Thống kê báo cáo (Report).
 */
public interface ReportDAO {
    // Truy vấn lấy danh sách hóa đơn theo khách hàng
    List<HoaDon> q01_getAllHoaDonWithCustomer();

    // Thống kê doanh thu mang lại trên từng khách hàng
    List<Map<String, Object>> q05_revenueByCustomer();

    // Lấy top các sản phẩm bán chạy nhất
    List<Map<String, Object>> q07_topSellingProducts();

    // Các hàm tính tổng quan (Tổng số đơn, tổng doanh thu, doanh thu trung bình, cao nhất, thấp nhất)
    long q09_countTotalOrders();
    BigDecimal q10_averageOrderValue();
    BigDecimal q11_maxOrderValue();
    BigDecimal q12_minOrderValue();
    BigDecimal q13_totalRevenue();

}

