package com.cafe.service;

import com.cafe.model.entity.TaiKhoan;

/**
 * Dịch vụ xử lý liên quan đến xác thực (Authentication) và tài khoản người dùng.
 */
public interface TaiKhoanService {
    TaiKhoan login(String username, String password);
    void createDefaultAdminIfEmpty();
}
