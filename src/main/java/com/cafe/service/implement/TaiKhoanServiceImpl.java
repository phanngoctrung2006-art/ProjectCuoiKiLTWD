package com.cafe.service.implement;

import com.cafe.dao.TaiKhoanDAO;
import com.cafe.model.entity.TaiKhoan;
import com.cafe.service.TaiKhoanService;

public class TaiKhoanServiceImpl implements TaiKhoanService {

    private final TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanServiceImpl(TaiKhoanDAO taiKhoanDAO) {
        this.taiKhoanDAO = taiKhoanDAO;
    }

    @Override
    public TaiKhoan login(String username, String password) {
        return taiKhoanDAO.findByTenDangNhapAndMatKhau(username, password);
    }

    @Override
    public void createDefaultAdminIfEmpty() {
        taiKhoanDAO.createDefaultAdminIfEmpty();
    }
}
