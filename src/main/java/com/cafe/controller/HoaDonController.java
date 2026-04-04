package com.cafe.controller;

import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import com.cafe.service.HoaDonService;
import com.cafe.service.KhachHangService;
import com.cafe.service.ReportService;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller cho quản lý hóa đơn.
 */
public class HoaDonController {
    private final HoaDonService hoaDonService;
    private final KhachHangService khachHangService;
    private final ReportService reportService;

    public HoaDonController(HoaDonService hoaDonService, 
                           KhachHangService khachHangService,
                           ReportService reportService) {
        this.hoaDonService = hoaDonService;
        this.khachHangService = khachHangService;
        this.reportService = reportService;
    }

    // ===== CRUD Hóa Đơn =====
    public List<HoaDon> getAllHoaDon() {
        return hoaDonService.getAll();
    }

    public HoaDon getHoaDonById(String id) {
        return hoaDonService.getById(id);
    }

    public HoaDon createHoaDon(HoaDon hoaDon) {
        return hoaDonService.create(hoaDon);
    }

    public HoaDon updateHoaDon(HoaDon hoaDon) {
        return hoaDonService.update(hoaDon);
    }

    public void deleteHoaDon(String id) {
        hoaDonService.delete(id);
    }

    public List<HoaDon> findHoaDonByKhachHang(String maKhachHang) {
        return hoaDonService.findByKhachHangId(maKhachHang);
    }

    // ===== Quản lý Khách Hàng =====
    public List<KhachHang> getAllKhachHang() {
        return khachHangService.getAll();
    }

    public KhachHang getKhachHangById(String id) {
        return khachHangService.getById(id);
    }

    public KhachHang createKhachHang(KhachHang khachHang) {
        return khachHangService.create(khachHang);
    }

    public KhachHang updateKhachHang(KhachHang khachHang) {
        return khachHangService.update(khachHang);
    }

    public void deleteKhachHang(String id) {
        khachHangService.delete(id);
    }

    public List<KhachHang> searchKhachHang(String name) {
        return khachHangService.searchByName(name);
    }

    public KhachHang getKhachHangByName(String name) {
        return khachHangService.findByName(name);
    }

    public KhachHang getKhachHangByPhone(String phone) {
        return khachHangService.findByPhone(phone);
    }

    // ===== Báo Cáo - 15 Queries =====
    public List<HoaDon> reportAllHoaDonWithCustomer() {
        return reportService.q01_getAllHoaDonWithCustomer();
    }

    public HoaDon reportFindHoaDonByMa(String ma) {
        return reportService.q02_findHoaDonByMa(ma);
    }

    public List<HoaDon> reportFindHoaDonByCustomerName(String name) {
        return reportService.q03_findHoaDonByCustomerName(name);
    }

    public List<HoaDon> reportFindHoaDonBetweenDates(Date fromDate, Date toDate) {
        return reportService.q04_findHoaDonBetweenDates(fromDate, toDate);
    }

    public List<Map<String, Object>> reportRevenueByCustomer() {
        return reportService.q05_revenueByCustomer();
    }

    public List<Map<String, Object>> reportRevenueByMonth() {
        return reportService.q06_revenueByMonth();
    }

    public List<Map<String, Object>> reportTopSellingProducts() {
        return reportService.q07_topSellingProducts();
    }

    public List<Map<String, Object>> reportTopRevenueProducts() {
        return reportService.q08_topRevenueProducts();
    }

    public long reportCountTotalOrders() {
        return reportService.q09_countTotalOrders();
    }

    public java.math.BigDecimal reportAverageOrderValue() {
        return reportService.q10_averageOrderValue();
    }

    public java.math.BigDecimal reportMaxOrderValue() {
        return reportService.q11_maxOrderValue();
    }

    public java.math.BigDecimal reportMinOrderValue() {
        return reportService.q12_minOrderValue();
    }

    public java.math.BigDecimal reportTotalRevenue() {
        return reportService.q13_totalRevenue();
    }

    public List<KhachHang> reportCustomersWithoutOrders() {
        return reportService.q14_customersWithoutOrders();
    }

    public Map<String, Object> reportOrderWithMostDetails() {
        return reportService.q15_orderWithMostDetails();
    }
}

