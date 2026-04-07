package com.cafe.service.implement;

import com.cafe.dao.PhieuNhapDAO;
import com.cafe.model.entity.PhieuNhap;
import com.cafe.service.PhieuNhapService;
import java.util.List;

public class PhieuNhapServiceImpl implements PhieuNhapService {
    private final PhieuNhapDAO dao;

    public PhieuNhapServiceImpl(PhieuNhapDAO dao) {
        this.dao = dao;
    }

    @Override public List<PhieuNhap> getAll() { return dao.findAll(); }
    @Override public PhieuNhap getById(String id) { return dao.findById(id); }

    @Override
    public PhieuNhap create(PhieuNhap pn) {
        validate(pn);
        return dao.save(pn);
    }

    @Override
    public PhieuNhap update(PhieuNhap pn) {
        validate(pn);
        return dao.update(pn);
    }

    @Override
    public void delete(String id) { dao.delete(id); }

    @Override
    public String getNextId() {
        String maxId = dao.getMaxId();
        if (maxId == null) return "PN01";
        int number = Integer.parseInt(maxId.trim().substring(2));
        return String.format("PN%02d", number + 1);
    }

    // ===== Validation tập trung tại Service =====
    private void validate(PhieuNhap pn) {
        if (pn.getNgayNhap() == null) {
            throw new RuntimeException("Ngày nhập không được để trống!");
        }
    }
}
