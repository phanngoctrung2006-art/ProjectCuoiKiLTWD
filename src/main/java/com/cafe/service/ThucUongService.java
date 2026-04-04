package com.cafe.service;

import com.cafe.model.entity.ThucUong;
import java.util.List;

public interface ThucUongService {
    List<ThucUong> getAll();
    ThucUong getById(String id);
}
