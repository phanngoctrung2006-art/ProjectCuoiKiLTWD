package com.cafe.dao;

import com.cafe.model.entity.LoaiThucUong;

public interface LoaiThucUongDAO extends GenericDAO<LoaiThucUong, String> {
    String getMaxId();
}
