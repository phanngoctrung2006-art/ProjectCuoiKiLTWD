package com.cafe.service.implement;

import com.cafe.dao.PhieuNhapDAO;
import com.cafe.model.entity.PhieuNhap;
import com.cafe.service.PhieuNhapService;
import java.util.List;

public class PhieuNhapServiceImpl implements PhieuNhapService {
    private final PhieuNhapDAO dao;
    public PhieuNhapServiceImpl(PhieuNhapDAO dao) { this.dao = dao; }

    @Override public List<PhieuNhap> getAll() { return dao.findAll(); }
    @Override public PhieuNhap getById(String id) { return dao.findById(id); }
    @Override public PhieuNhap create(PhieuNhap pn) { return dao.save(pn); }
    @Override public PhieuNhap update(PhieuNhap pn) { return dao.update(pn); }
    @Override public void delete(String id) { dao.delete(id); }
}
