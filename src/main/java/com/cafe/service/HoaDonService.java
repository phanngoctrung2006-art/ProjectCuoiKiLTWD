package com.cafe.service;

import com.cafe.model.entity.HoaDon;
import java.util.List;

/**
 * Service interface cho HoaDon.
 */
public interface HoaDonService {
    /**
     * Lấy tất cả hóa đơn.
     */
    List<HoaDon> getAll();

    /**
     * Tìm hóa đơn theo ID.
     */
    HoaDon getById(String id);

    /**
     * Tạo mới hóa đơn.
     */
    HoaDon create(HoaDon hoaDon);

    /**
     * Cập nhật hóa đơn.
     */
    HoaDon update(HoaDon hoaDon);

    /**
     * Xóa hóa đơn.
     */
    void delete(String id);

    /**
     * Tìm hóa đơn theo khách hàng.
     */
    List<HoaDon> findByKhachHangId(String maKhachHang);

    /**
     * Tính tổng doanh thu.
     */
    java.math.BigDecimal getTotalRevenue();
}
