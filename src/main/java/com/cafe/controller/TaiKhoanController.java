package com.cafe.controller;

import com.cafe.model.entity.TaiKhoan;
import com.cafe.service.TaiKhoanService;

public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    public TaiKhoan authenticate(String username, String password) {
        return taiKhoanService.login(username, password);
    }
    
    public void initDefaultAccount() {
        taiKhoanService.createDefaultAdminIfEmpty();
    }
}
