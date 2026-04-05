package com.cafe.dao;

import com.cafe.model.entity.ChiTietPhieuNhap;
import com.cafe.model.entity.ChiTietPhieuNhapId;
import java.util.List;

/**
 * Interface DAO cho bảng ChiTietPhieuNhap.
 */
public interface ChiTietPhieuNhapDAO extends GenericDAO<ChiTietPhieuNhap, ChiTietPhieuNhapId> {

    /**
     * Lấy danh sách chi tiết của một phiếu nhập.
     * @param maPhieuNhap Mã phiếu nhập
     * @return Danh sách chi tiết phiếu nhập
     */
    List<ChiTietPhieuNhap> findByMaPhieuNhap(String maPhieuNhap);

    /**
     * Xóa tất cả chi tiết của một phiếu nhập.
     * @param maPhieuNhap Mã phiếu nhập
     */
    void deleteByMaPhieuNhap(String maPhieuNhap);
}
