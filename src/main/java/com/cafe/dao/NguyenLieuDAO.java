package com.cafe.dao;

import com.cafe.model.entity.NguyenLieu;
import java.util.List;

/**
 * Interface DAO cho bảng NguyenLieu.
 */
public interface NguyenLieuDAO extends GenericDAO<NguyenLieu, String> {

    /**
     * Tìm nguyên liệu có số lượng tồn kho nhỏ hơn ngưỡng (cảnh báo hết hàng).
     * @param soLuong Ngưỡng số lượng
     * @return Danh sách nguyên liệu sắp hết
     */
    List<NguyenLieu> findBySoLuongLessThan(int soLuong);

    /**
     * Tìm nguyên liệu theo tên (chứa chuỗi, không phân biệt hoa thường).
     * @param ten Chuỗi tên cần tìm
     * @return Danh sách nguyên liệu khớp
     */
    List<NguyenLieu> findByTenContaining(String ten);
}
