package com.cafe.dao;

import com.cafe.model.entity.ChiTietHoaDon;
import com.cafe.model.entity.ChiTietHoaDonId;
import java.util.List;

public interface ChiTietHoaDonDAO extends GenericDAO<ChiTietHoaDon, ChiTietHoaDonId> {

    List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon);

    void deleteByMaHoaDon(String maHoaDon);
}
