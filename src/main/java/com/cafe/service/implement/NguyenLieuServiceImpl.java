package com.cafe.service.implement;

import com.cafe.dao.NguyenLieuDAO;
import com.cafe.model.entity.NguyenLieu;
import com.cafe.service.NguyenLieuService;
import java.util.List;

public class NguyenLieuServiceImpl implements NguyenLieuService {
    private final NguyenLieuDAO dao;

    public NguyenLieuServiceImpl(NguyenLieuDAO dao) {
        this.dao = dao;
    }

    @Override public List<NguyenLieu> getAll() { return dao.findAll(); }
    @Override public NguyenLieu getById(String id) { return dao.findById(id); }

    @Override
    public NguyenLieu create(NguyenLieu nl) {
        validate(nl);
        return dao.save(nl);
    }

    @Override
    public NguyenLieu update(NguyenLieu nl) {
        validate(nl);
        return dao.update(nl);
    }

    @Override
    public void delete(String id) { dao.delete(id); }

    @Override
    public String getNextId() {
        String maxId = dao.getMaxId();
        if (maxId == null) return "NL01";
        int number = Integer.parseInt(maxId.trim().substring(2));
        return String.format("NL%02d", number + 1);
    }

    // ===== Validation tập trung tại Service =====
    private void validate(NguyenLieu nl) {
        if (nl.getTenNguyenLieu() == null || nl.getTenNguyenLieu().trim().isEmpty()) {
            throw new RuntimeException("Tên nguyên liệu không được để trống!");
        }
        if (!nl.getTenNguyenLieu().trim().matches("[\\p{L}\\s]+")) {
            throw new RuntimeException("Tên nguyên liệu không được chứa số hoặc ký tự đặc biệt!");
        }
        if (nl.getSoLuong() != null && nl.getSoLuong() < 0) {
            throw new RuntimeException("Số lượng không được âm!");
        }
    }
}
