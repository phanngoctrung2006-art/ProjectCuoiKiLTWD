package com.cafe.service;

import com.cafe.model.entity.ThucUong;
import java.util.List;

public interface ThucUongService {
    List<ThucUong> getAll();
    ThucUong getById(String id);
    ThucUong create(ThucUong thucUong);
    ThucUong update(ThucUong thucUong);
    void delete(String id);
}
