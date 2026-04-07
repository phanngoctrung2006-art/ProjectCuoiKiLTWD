package com.cafe.service.implement;

import com.cafe.dao.ThucUongDAO;
import com.cafe.model.entity.ThucUong;
import com.cafe.service.ThucUongService;
import java.math.BigDecimal;
import java.util.List;

public class ThucUongServiceImpl implements ThucUongService {
    private final ThucUongDAO thucUongDAO;

    public ThucUongServiceImpl(ThucUongDAO thucUongDAO) {
        this.thucUongDAO = thucUongDAO;
    }

    @Override public List<ThucUong> getAll() { return thucUongDAO.findAll(); }
    @Override public ThucUong getById(String id) { return thucUongDAO.findById(id); }

    @Override
    public ThucUong create(ThucUong tu) {
        validate(tu);
        return thucUongDAO.save(tu);
    }

    @Override
    public ThucUong update(ThucUong tu) {
        validate(tu);
        return thucUongDAO.update(tu);
    }

    @Override
    public void delete(String id) { thucUongDAO.delete(id); }

    @Override
    public String getNextId() {
        String maxId = thucUongDAO.getMaxId();
        if (maxId == null) return "TU01";
        int number = Integer.parseInt(maxId.trim().substring(2));
        return String.format("TU%02d", number + 1);
    }

    // ===== Validation tập trung tại Service =====
    private void validate(ThucUong tu) {
        if (tu.getTenThucUong() == null || tu.getTenThucUong().trim().isEmpty()) {
            throw new RuntimeException("Tên thức uống không được để trống!");
        }
        if (!tu.getTenThucUong().trim().matches("[\\p{L}\\s\\d]+")) {
            throw new RuntimeException("Tên thức uống không được chứa ký tự đặc biệt!");
        }
        if (tu.getGia() == null) {
            throw new RuntimeException("Giá không được để trống!");
        }
        if (tu.getGia().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Giá không được âm!");
        }
    }
}
