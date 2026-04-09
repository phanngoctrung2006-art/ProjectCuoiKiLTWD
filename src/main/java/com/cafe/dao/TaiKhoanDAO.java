package com.cafe.dao;

import com.cafe.model.entity.TaiKhoan;

/**
 * Giao diện DAO chuyên biệt cho bảng TaiKhoan.
 * Cung cấp thêm cấu trúc truy vấn đặc thù (VD: Tìm theo User/Pass) ngoài CRUD cơ bản.
 */
public interface TaiKhoanDAO extends GenericDAO<TaiKhoan, String> {
    // Kiểm tra đăng nhập (Tìm tài khoản dựa trên username và password)
    TaiKhoan findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    // Tạo tài khoản Admin mặc định nếu CSDL rỗng
    void createDefaultAdminIfEmpty();
}
