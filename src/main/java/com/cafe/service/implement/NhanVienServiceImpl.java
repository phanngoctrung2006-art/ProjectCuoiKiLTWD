package com.cafe.service.implement;

import com.cafe.dao.NhanVienDAO;
import com.cafe.model.entity.NhanVien;
import com.cafe.service.NhanVienService;
import java.math.BigDecimal;
import java.util.List;

public class NhanVienServiceImpl implements NhanVienService {
    private final NhanVienDAO dao;

    public NhanVienServiceImpl(NhanVienDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<NhanVien> getAll() {
        return dao.findAll();
    }

    @Override
    public NhanVien getById(String id) {
        return dao.findById(id);
    }

    @Override
    public NhanVien create(NhanVien nv) {
        validate(nv);
        return dao.save(nv);
    }

    @Override
    public NhanVien update(NhanVien nv) {
        validate(nv);
        return dao.update(nv);
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public String getNextId() {
        String maxId = dao.getMaxId();
        if (maxId == null) return "NV01";
        int number = Integer.parseInt(maxId.trim().substring(2));
        return String.format("NV%02d", number + 1);
    }

    // ===== Validation tập trung tại Service =====
    private void validate(NhanVien nv) {
        if (nv.getTenNhanVien() == null || nv.getTenNhanVien().trim().isEmpty()) {
            throw new RuntimeException("Tên nhân viên không được để trống!");
        }
        if (!nv.getTenNhanVien().trim().matches("[\\p{L}\\s]+")) {
            throw new RuntimeException("Tên nhân viên không được chứa số hoặc ký tự đặc biệt!");
        }
        if (nv.getSoDienThoai() != null && !nv.getSoDienThoai().trim().isEmpty()) {
            if (!nv.getSoDienThoai().trim().matches("\\d{10}")) {
                throw new RuntimeException("Số điện thoại phải gồm đúng 10 chữ số!");
            }
        }
        if (nv.getNgaySinh() == null) {
            throw new RuntimeException("Ngày sinh không được để trống!");
        }
        if (nv.getLuong() != null && nv.getLuong().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Lương không được âm!");
        }
    }
}
