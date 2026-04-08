package com.cafe.service;

import com.cafe.model.entity.TaiKhoan;

public interface TaiKhoanService {
    TaiKhoan login(String username, String password);
    void createDefaultAdminIfEmpty();
}
