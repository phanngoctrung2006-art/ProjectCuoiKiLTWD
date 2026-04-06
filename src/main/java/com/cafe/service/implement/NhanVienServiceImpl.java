package com.cafe.service.implement;

import com.cafe.dao.NhanVienDAO;
import com.cafe.model.entity.NhanVien;
import com.cafe.service.NhanVienService;
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
        return dao.save(nv);
    }

    @Override
    public NhanVien update(NhanVien nv) {
        return dao.update(nv);
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public String getNextId() {
        String maxId = dao.getMaxId();
        if (maxId == null) {
            return "NV01";
        }

        String numberPart = maxId.substring(2);

        int number = Integer.parseInt(numberPart);

        number++;

        return String.format("NV%02d", number);
    }
}
