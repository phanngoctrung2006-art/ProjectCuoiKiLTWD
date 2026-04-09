package com.cafe.controller;

import com.cafe.dao.impl.ChiTietHoaDonDAOImpl;
import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import com.cafe.service.HoaDonService;
import com.cafe.service.KhachHangService;
import com.cafe.service.ReportService;
import com.cafe.service.ThucUongService;
import com.cafe.service.ChiTietHoaDonService;
import java.util.List;
import java.util.Map;

/**
 * Lớp điều khiển chính cho chức năng Hóa Đơn.
 * Đóng vai trò cầu nối (Controller) điều phối tương tác giữa Giao diện (View) và Nghiệp vụ (Service).
 */
public class HoaDonController {
    private final HoaDonService hoaDonService;
    private final KhachHangService khachHangService;
    private final ReportService reportService;
    private final ChiTietHoaDonService chiTietHoaDonService;
    private final ThucUongService thucUongService;
    private final ChiTietHoaDonDAOImpl chiTietHoaDonDAO;

    public HoaDonController(HoaDonService hoaDonService,
            KhachHangService khachHangService,
            ReportService reportService,
            ChiTietHoaDonService chiTietHoaDonService,
            ThucUongService thucUongService,
            ChiTietHoaDonDAOImpl chiTietHoaDonDAO) {
        this.hoaDonService = hoaDonService;
        this.khachHangService = khachHangService;
        this.reportService = reportService;
        this.chiTietHoaDonService = chiTietHoaDonService;
        this.thucUongService = thucUongService;
        this.chiTietHoaDonDAO = chiTietHoaDonDAO;
    }

    public List<com.cafe.model.entity.ThucUong> getAllThucUong() {
        return thucUongService.getAll();
    }

    /**
     * Them hoac cap nhat so luong thuc uong trong hoa don.
     * Toan bo logic nam trong 1 transaction tai DAO — tranh detached entity & cascade bug.
     */
    public void addThucUongToHoaDon(String maHoaDon, String maThucUong, int soLuong) {
        chiTietHoaDonDAO.addOrUpdateChiTiet(maHoaDon, maThucUong, soLuong);
    }

    /**
     * Xoa thuc uong khoi hoa don va cap nhat tong tien.
     * Toan bo logic nam trong 1 transaction tai DAO.
     */
    public void deleteThucUongFromHoaDon(String maHoaDon, String maThucUong) {
        chiTietHoaDonDAO.deleteChiTietAndRecalculate(maHoaDon, maThucUong);
    }

    /**
     * Giam so luong thuc uong di 1.
     * Neu so luong ve 0 thi xoa han khoi hoa don.
     */
    public void decreaseThucUongFromHoaDon(String maHoaDon, String maThucUong) {
        chiTietHoaDonDAO.decreaseOrRemoveChiTiet(maHoaDon, maThucUong);
    }

    // CRUD Hoa Don
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

    // Quan ly Khach Hang
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

    // Bao Cao
    public List<HoaDon> reportAllHoaDonWithCustomer() {
        return reportService.q01_getAllHoaDonWithCustomer();
    }

    public List<Map<String, Object>> reportRevenueByCustomer() {
        return reportService.q05_revenueByCustomer();
    }

    public List<Map<String, Object>> reportTopSellingProducts() {
        return reportService.q07_topSellingProducts();
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
}
