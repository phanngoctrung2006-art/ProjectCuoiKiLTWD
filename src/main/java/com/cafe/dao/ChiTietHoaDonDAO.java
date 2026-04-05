package com.cafe.dao;

import com.cafe.model.entity.ChiTietHoaDon;
import com.cafe.model.entity.ChiTietHoaDonId;
import java.util.List;

/**
 * Interface DAO cho bảng ChiTietHoaDon.
 */
public interface ChiTietHoaDonDAO extends GenericDAO<ChiTietHoaDon, ChiTietHoaDonId> {

    /**
     * Lấy danh sách chi tiết của một hóa đơn.
     * @param maHoaDon Mã hóa đơn
     * @return Danh sách chi tiết hóa đơn
     */
    List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon);

    /**
     * Xóa tất cả chi tiết của một hóa đơn.
     * @param maHoaDon Mã hóa đơn
     */
    void deleteByMaHoaDon(String maHoaDon);
}
