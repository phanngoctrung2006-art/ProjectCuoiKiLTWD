package com.cafe.service;

import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Service interface cho báo cáo - 15 câu truy vấn JPQL.
 */
public interface ReportService {
    // Nhóm A - Tra cứu hóa đơn
    /**
     * Q1: Lấy tất cả hóa đơn kèm khách hàng, sắp xếp theo ngày.
     */
    List<HoaDon> q01_getAllHoaDonWithCustomer();

    /**
     * Q2: Tìm hóa đơn theo mã.
     */
    HoaDon q02_findHoaDonByMa(String ma);

    /**
     * Q3: Tìm hóa đơn theo tên khách hàng.
     */
    List<HoaDon> q03_findHoaDonByCustomerName(String name);

    /**
     * Q4: Tìm hóa đơn trong khoảng ngày.
     */
    List<HoaDon> q04_findHoaDonBetweenDates(java.sql.Date fromDate, java.sql.Date toDate);

    // Nhóm B - Thống kê sản phẩm
    /**
     * Q5: Tính doanh thu theo khách hàng.
     */
    List<Map<String, Object>> q05_revenueByCustomer();

    /**
     * Q6: Tính doanh thu theo tháng.
     */
    List<Map<String, Object>> q06_revenueByMonth();

    /**
     * Q7: Lấy sản phẩm bán chạy nhất.
     */
    List<Map<String, Object>> q07_topSellingProducts();

    /**
     * Q8: Lấy sản phẩm có doanh thu cao nhất.
     */
    List<Map<String, Object>> q08_topRevenueProducts();

    /**
     * Q9: Đếm số lượng hóa đơn.
     */
    long q09_countTotalOrders();

    /**
     * Q10: Tính trung bình doanh thu.
     */
    BigDecimal q10_averageOrderValue();

    /**
     * Q11: Tính doanh thu tối đa.
     */
    BigDecimal q11_maxOrderValue();

    /**
     * Q12: Tính doanh thu tối thiểu.
     */
    BigDecimal q12_minOrderValue();

    /**
     * Q13: Tính tổng doanh thu.
     */
    BigDecimal q13_totalRevenue();

    /**
     * Q14: Khách hàng chưa từng mua hàng.
     */
    List<KhachHang> q14_customersWithoutOrders();

    /**
     * Q15: Hóa đơn có chi tiết nhiều nhất.
     */
    Map<String, Object> q15_orderWithMostDetails();
}
