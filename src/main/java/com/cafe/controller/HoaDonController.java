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
    private final com.cafe.service.ChiTietHoaDonService chiTietHoaDonService;
    private final com.cafe.service.ThucUongService thucUongService;

    public HoaDonController(HoaDonService hoaDonService,
            KhachHangService khachHangService,
            ReportService reportService,
            com.cafe.service.ChiTietHoaDonService chiTietHoaDonService,
            com.cafe.service.ThucUongService thucUongService) {
        this.hoaDonService = hoaDonService;
        this.khachHangService = khachHangService;
        this.reportService = reportService;
        this.chiTietHoaDonService = chiTietHoaDonService;
        this.thucUongService = thucUongService;
    }

    // ===== Tính tổng tiền hóa đơn =====
    public void calculateAndUpdateTongTien(String maHoaDon) {
        List<com.cafe.model.entity.ChiTietHoaDon> details = chiTietHoaDonService.findByMaHoaDon(maHoaDon);
        java.math.BigDecimal tongTien = java.math.BigDecimal.ZERO;
        for (com.cafe.model.entity.ChiTietHoaDon c : details) {
            if (c.getThucUong() != null && c.getThucUong().getGia() != null) {
                java.math.BigDecimal thanhTien = c.getThucUong().getGia()
                        .multiply(new java.math.BigDecimal(c.getSoLuong()));
                tongTien = tongTien.add(thanhTien);
            }
        }
        HoaDon hd = hoaDonService.getById(maHoaDon);
        if (hd != null) {
            hd.setTongTien(tongTien);
            hoaDonService.update(hd);
        }
    }

    public List<com.cafe.model.entity.ThucUong> getAllThucUong() {
        return thucUongService.getAll();
    }

    public void addThucUongToHoaDon(String maHoaDon, String maThucUong, int soLuong) {
        // Prepare Entities
        HoaDon hd = hoaDonService.getById(maHoaDon);
        com.cafe.model.entity.ThucUong tu = thucUongService.getById(maThucUong);

        // Prevent duplication
        List<com.cafe.model.entity.ChiTietHoaDon> current = chiTietHoaDonService.findByMaHoaDon(maHoaDon);
        com.cafe.model.entity.ChiTietHoaDon existing = null;
        for (com.cafe.model.entity.ChiTietHoaDon c : current) {
            if (c.getId().getMaThucUong().equals(maThucUong)) {
                existing = c;
                break;
            }
        }

        if (existing != null) {
            existing.setSoLuong(existing.getSoLuong() + soLuong);
            chiTietHoaDonService.update(existing); // Update via merge
        } else {
            com.cafe.model.entity.ChiTietHoaDon chiTiet = new com.cafe.model.entity.ChiTietHoaDon(hd, tu, soLuong);
            chiTietHoaDonService.create(chiTiet);
        }

        calculateAndUpdateTongTien(maHoaDon);
    }

    public void deleteThucUongFromHoaDon(String maHoaDon, String maThucUong) {
        chiTietHoaDonService.delete(new com.cafe.model.entity.ChiTietHoaDonId(maHoaDon, maThucUong));
        calculateAndUpdateTongTien(maHoaDon);
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

    public List<com.cafe.model.entity.ChiTietHoaDon> getChiTietHoaDonByMa(String maHoaDon) {
        return chiTietHoaDonService.findByMaHoaDon(maHoaDon);
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
