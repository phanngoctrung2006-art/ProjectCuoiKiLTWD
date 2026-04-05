package com.cafe.dao;

import com.cafe.model.entity.KhachHang;
import java.util.List;

/**
 * Interface DAO cho bảng KhachHang.
 */
public interface KhachHangDAO extends GenericDAO<KhachHang, String> {

    /**
     * Tìm khách hàng theo số điện thoại.
     * @param soDienThoai Số điện thoại cần tìm
     * @return Khách hàng tìm được hoặc null
     */
    KhachHang findBySoDienThoai(String soDienThoai);

    /**
     * Tìm khách hàng theo tên (chứa chuỗi, không phân biệt hoa thường).
     * @param ten Chuỗi tên cần tìm
     * @return Danh sách khách hàng khớp
     */
    List<KhachHang> findByTenContaining(String ten);
}
