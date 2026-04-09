package com.cafe.service;

import com.cafe.model.entity.KhachHang;
import java.util.List;

/**
 * Interface cấu hình các nghiệp vụ độc quyền liên quan đến Khách Hàng (Tích điểm, tìm kiếm SDT, ...).
 */
public interface KhachHangService {
    List<KhachHang> getAll();

    KhachHang getById(String id);

    KhachHang create(KhachHang khachHang);

    KhachHang update(KhachHang khachHang);

    void delete(String id);

    List<KhachHang> searchByName(String name);

    KhachHang findByPhone(String phone);

    KhachHang findByName(String name);
}
