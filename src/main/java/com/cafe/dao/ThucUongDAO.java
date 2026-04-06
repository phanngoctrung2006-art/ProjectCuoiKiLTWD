package com.cafe.dao;

import com.cafe.model.entity.ThucUong;

public interface ThucUongDAO extends GenericDAO<ThucUong, String> {
    String getMaxId();
}
