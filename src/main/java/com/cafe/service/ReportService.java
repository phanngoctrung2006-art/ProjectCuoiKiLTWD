package com.cafe.service;

import com.cafe.model.entity.HoaDon;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportService {
    // Nhóm A  Tra cứu hóa đơn
    List<HoaDon> q01_getAllHoaDonWithCustomer();

    // Nhóm B  Thống kê sản phẩm
    List<Map<String, Object>> q05_revenueByCustomer();

    List<Map<String, Object>> q07_topSellingProducts();

    long q09_countTotalOrders();

    BigDecimal q10_averageOrderValue();

    BigDecimal q11_maxOrderValue();

    BigDecimal q12_minOrderValue();

    BigDecimal q13_totalRevenue();

}
