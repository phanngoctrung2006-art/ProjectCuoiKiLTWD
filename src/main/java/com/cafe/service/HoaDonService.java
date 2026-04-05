package com.cafe.service;

import com.cafe.model.entity.HoaDon;
import java.util.List;

public interface HoaDonService {
    List<HoaDon> getAll();

    HoaDon getById(String id);

    HoaDon create(HoaDon hoaDon);

    HoaDon update(HoaDon hoaDon);

    void delete(String id);

    List<HoaDon> findByKhachHangId(String maKhachHang);

    java.math.BigDecimal getTotalRevenue();
}
