package com.cafe.service.implement;

import com.cafe.dao.ReportDAO;
import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import com.cafe.service.ReportService;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Implementation của ReportService - 15 câu truy vấn JPQL.
 */
public class ReportServiceImpl implements ReportService {
    private final ReportDAO reportDAO;

    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public List<HoaDon> q01_getAllHoaDonWithCustomer() {
        return reportDAO.q01_getAllHoaDonWithCustomer();
    }



    @Override
    public List<Map<String, Object>> q05_revenueByCustomer() {
        return reportDAO.q05_revenueByCustomer();
    }



    @Override
    public List<Map<String, Object>> q07_topSellingProducts() {
        return reportDAO.q07_topSellingProducts();
    }



    @Override
    public long q09_countTotalOrders() {
        return reportDAO.q09_countTotalOrders();
    }

    @Override
    public BigDecimal q10_averageOrderValue() {
        return reportDAO.q10_averageOrderValue();
    }

    @Override
    public BigDecimal q11_maxOrderValue() {
        return reportDAO.q11_maxOrderValue();
    }

    @Override
    public BigDecimal q12_minOrderValue() {
        return reportDAO.q12_minOrderValue();
    }

    @Override
    public BigDecimal q13_totalRevenue() {
        return reportDAO.q13_totalRevenue();
    }


}
