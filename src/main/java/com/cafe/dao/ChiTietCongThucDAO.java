package com.cafe.dao;

import com.cafe.model.entity.ChiTietCongThuc;
import com.cafe.model.entity.ChiTietCongThucId;
import java.util.List;

/**
 * Interface DAO cho bảng ChiTietCongThuc.
 */
public interface ChiTietCongThucDAO extends GenericDAO<ChiTietCongThuc, ChiTietCongThucId> {

    /**
     * Lấy danh sách nguyên liệu của một công thức.
     * @param maCongThuc Mã công thức
     * @return Danh sách chi tiết công thức
     */
    List<ChiTietCongThuc> findByMaCongThuc(String maCongThuc);

    /**
     * Xóa tất cả chi tiết của một công thức.
     * @param maCongThuc Mã công thức
     */
    void deleteByMaCongThuc(String maCongThuc);
}
