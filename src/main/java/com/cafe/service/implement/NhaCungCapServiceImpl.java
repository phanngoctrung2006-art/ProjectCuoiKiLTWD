package com.cafe.service.implement;

import com.cafe.dao.NhaCungCapDAO;
import com.cafe.model.entity.NhaCungCap;
import com.cafe.service.NhaCungCapService;
import java.util.List;

public class NhaCungCapServiceImpl implements NhaCungCapService {
    private final NhaCungCapDAO dao;
    public NhaCungCapServiceImpl(NhaCungCapDAO dao) { this.dao = dao; }

    @Override public List<NhaCungCap> getAll() { return dao.findAll(); }
    @Override public NhaCungCap getById(String id) { return dao.findById(id); }
    @Override public NhaCungCap create(NhaCungCap ncc) { return dao.save(ncc); }
    @Override public NhaCungCap update(NhaCungCap ncc) { return dao.update(ncc); }
    @Override public void delete(String id) { dao.delete(id); }
}
