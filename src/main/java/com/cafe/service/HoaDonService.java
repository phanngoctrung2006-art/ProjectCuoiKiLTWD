package com.cafe.service;

import com.cafe.model.entity.HoaDon;
import java.util.List;

/**
 * Interface định nghĩa các nghiệp vụ (Business Logic) liên quan đến quản lý Hóa Đơn.
 * Tầng Service đứng giữa Controller và DAO để xử lý dữ liệu trước khi lưu/hiển thị.
 */
public interface HoaDonService {
    List<HoaDon> getAll();

    HoaDon getById(String id);

    HoaDon create(HoaDon hoaDon);

    HoaDon update(HoaDon hoaDon);

    void delete(String id);

    List<HoaDon> findByKhachHangId(String maKhachHang);

    java.math.BigDecimal getTotalRevenue();

    /** Cập nhật chỉ TongTien bằng JPQL UPDATE, tránh cascade gây lỗi nhân bản. */
    void updateTongTienOnly(String maHoaDon, java.math.BigDecimal tongTien);
}
