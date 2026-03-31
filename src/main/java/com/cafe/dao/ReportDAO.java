package com.cafe.dao;

import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * DAO interface cho báo cáo - 15 câu truy vấn JPQL.
 */
public interface ReportDAO {
    List<HoaDon> q01_getAllHoaDonWithCustomer();
    HoaDon q02_findHoaDonByMa(String ma);
    List<HoaDon> q03_findHoaDonByCustomerName(String name);
    List<HoaDon> q04_findHoaDonBetweenDates(Date fromDate, Date toDate);
    List<Map<String, Object>> q05_revenueByCustomer();
    List<Map<String, Object>> q06_revenueByMonth();
    List<Map<String, Object>> q07_topSellingProducts();
    List<Map<String, Object>> q08_topRevenueProducts();
    long q09_countTotalOrders();
    BigDecimal q10_averageOrderValue();
    BigDecimal q11_maxOrderValue();
    BigDecimal q12_minOrderValue();
    BigDecimal q13_totalRevenue();
    List<KhachHang> q14_customersWithoutOrders();
    Map<String, Object> q15_orderWithMostDetails();
}

