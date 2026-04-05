package com.cafe.dao;

import com.cafe.model.entity.NhanVien;
import java.util.List;

/**
 * Interface DAO cho bảng NhanVien.
 */
public interface NhanVienDAO extends GenericDAO<NhanVien, String> {

    /**
     * Tìm nhân viên theo tên (chứa chuỗi, không phân biệt hoa thường).
     * @param ten Chuỗi tên cần tìm
     * @return Danh sách nhân viên khớp
     */
    List<NhanVien> findByTenContaining(String ten);

    /**
     * Tìm nhân viên theo số điện thoại.
     * @param soDienThoai Số điện thoại cần tìm
     * @return Nhân viên tìm được hoặc null
     */
    NhanVien findBySoDienThoai(String soDienThoai);
}
