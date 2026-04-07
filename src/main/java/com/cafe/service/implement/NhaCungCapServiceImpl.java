package com.cafe.service.implement;

import com.cafe.dao.NhaCungCapDAO;
import com.cafe.model.entity.NhaCungCap;
import com.cafe.service.NhaCungCapService;
import java.util.List;

public class NhaCungCapServiceImpl implements NhaCungCapService {
    private final NhaCungCapDAO dao;

    public NhaCungCapServiceImpl(NhaCungCapDAO dao) {
        this.dao = dao;
    }

    @Override public List<NhaCungCap> getAll() { return dao.findAll(); }
    @Override public NhaCungCap getById(String id) { return dao.findById(id); }

    @Override
    public NhaCungCap create(NhaCungCap ncc) {
        validate(ncc);
        return dao.save(ncc);
    }

    @Override
    public NhaCungCap update(NhaCungCap ncc) {
        validate(ncc);
        return dao.update(ncc);
    }

    @Override
    public void delete(String id) { dao.delete(id); }

    @Override
    public String getNextId() {
        String maxId = dao.getMaxId();
        if (maxId == null) return "NC01";
        int number = Integer.parseInt(maxId.trim().substring(2));
        return String.format("NC%02d", number + 1);
    }

    // ===== Validation tập trung tại Service =====
    private void validate(NhaCungCap ncc) {
        if (ncc.getTenNhaCungCap() == null || ncc.getTenNhaCungCap().trim().isEmpty()) {
            throw new RuntimeException("Tên nhà cung cấp không được để trống!");
        }
        if (!ncc.getTenNhaCungCap().trim().matches("[\\p{L}\\s]+")) {
            throw new RuntimeException("Tên nhà cung cấp không được chứa số hoặc ký tự đặc biệt!");
        }
        if (ncc.getSoDienThoai() != null && !ncc.getSoDienThoai().trim().isEmpty()) {
            if (!ncc.getSoDienThoai().trim().matches("\\d{10}")) {
                throw new RuntimeException("Số điện thoại phải gồm đúng 10 chữ số!");
            }
        }
    }
}
