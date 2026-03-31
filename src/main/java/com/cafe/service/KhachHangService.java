package com.cafe.service;

import com.cafe.model.entity.KhachHang;
import java.util.List;

/**
 * Service interface cho KhachHang.
 */
public interface KhachHangService {
    /**
     * Lấy tất cả khách hàng.
     */
    List<KhachHang> getAll();

    /**
     * Tìm khách hàng theo ID.
     */
    KhachHang getById(String id);

    /**
     * Tạo mới khách hàng.
     */
    KhachHang create(KhachHang khachHang);

    /**
     * Cập nhật khách hàng.
     */
    KhachHang update(KhachHang khachHang);

    /**
     * Xóa khách hàng.
     */
    void delete(String id);

    /**
     * Tìm kiếm khách hàng theo tên.
     */
    List<KhachHang> searchByName(String name);

    /**
     * Tìm kiếm khách hàng theo số điện thoại.
     */
    KhachHang findByPhone(String phone);
}
