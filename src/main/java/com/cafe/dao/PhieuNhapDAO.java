package com.cafe.dao;

import com.cafe.model.entity.PhieuNhap;
/**
 * Xử lý truy vấn dữ liệu từ bảng PhieuNhap.
 * Cung cấp thêm phương thức lấy mã lớn nhất (getMaxId) để tự động sinh mã mới.
 */
public interface PhieuNhapDAO extends GenericDAO<PhieuNhap, String> {
    String getMaxId();
}
