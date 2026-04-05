package com.cafe.service.implement;

import com.cafe.dao.NguyenLieuDAO;
import com.cafe.model.entity.NguyenLieu;
import com.cafe.service.NguyenLieuService;
import java.util.List;

public class NguyenLieuServiceImpl implements NguyenLieuService {
    private final NguyenLieuDAO dao;
    public NguyenLieuServiceImpl(NguyenLieuDAO dao) { this.dao = dao; }

    @Override public List<NguyenLieu> getAll() { return dao.findAll(); }
    @Override public NguyenLieu getById(String id) { return dao.findById(id); }
    @Override public NguyenLieu create(NguyenLieu nl) { return dao.save(nl); }
    @Override public NguyenLieu update(NguyenLieu nl) { return dao.update(nl); }
    @Override public void delete(String id) { dao.delete(id); }
}
