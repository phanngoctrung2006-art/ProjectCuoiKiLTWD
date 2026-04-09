package com.cafe.service.implement;

import com.cafe.dao.TaiKhoanDAO;
import com.cafe.model.entity.TaiKhoan;
import com.cafe.service.TaiKhoanService;

/**
 * Lớp nghiệp vụ chuyên xử lý đăng nhập hệ thống.
 * Chứa logic kết nối với TaiKhoanDAO để đối chiếu username/password.
 */
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
