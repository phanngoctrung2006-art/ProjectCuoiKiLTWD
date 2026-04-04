package com.cafe.service;

import com.cafe.model.entity.ChiTietHoaDon;
import java.util.List;

public interface ChiTietHoaDonService {
    List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon);
    void deleteByMaHoaDon(String maHoaDon);
    ChiTietHoaDon create(ChiTietHoaDon chiTietHoaDon);
    ChiTietHoaDon update(ChiTietHoaDon chiTietHoaDon);
    void delete(com.cafe.model.entity.ChiTietHoaDonId id);
}
