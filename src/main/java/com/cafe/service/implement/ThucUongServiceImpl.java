package com.cafe.service.implement;

import com.cafe.dao.ThucUongDAO;
import com.cafe.model.entity.ThucUong;
import com.cafe.service.ThucUongService;
import java.util.List;

public class ThucUongServiceImpl implements ThucUongService {

    private final ThucUongDAO thucUongDAO;

    public ThucUongServiceImpl(ThucUongDAO thucUongDAO) {
        this.thucUongDAO = thucUongDAO;
    }

    @Override public List<ThucUong> getAll() { return thucUongDAO.findAll(); }
    @Override public ThucUong getById(String id) { return thucUongDAO.findById(id); }
    @Override public ThucUong create(ThucUong tu) { return thucUongDAO.save(tu); }
    @Override public ThucUong update(ThucUong tu) { return thucUongDAO.update(tu); }
    @Override public void delete(String id) { thucUongDAO.delete(id); }
}
