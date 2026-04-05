package com.cafe.dao;

import com.cafe.model.entity.HoaDon;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportDAO {
    List<HoaDon> q01_getAllHoaDonWithCustomer();

    List<Map<String, Object>> q05_revenueByCustomer();

    List<Map<String, Object>> q07_topSellingProducts();

    long q09_countTotalOrders();
    BigDecimal q10_averageOrderValue();
    BigDecimal q11_maxOrderValue();
    BigDecimal q12_minOrderValue();
    BigDecimal q13_totalRevenue();

}

