package com.cafe.dao;

import com.cafe.model.entity.TaiKhoan;

public interface TaiKhoanDAO extends GenericDAO<TaiKhoan, String> {
    TaiKhoan findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    void createDefaultAdminIfEmpty();
}
