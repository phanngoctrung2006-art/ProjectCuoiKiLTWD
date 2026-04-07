package com.cafe.dao;

import com.cafe.model.entity.NhaCungCap;

public interface NhaCungCapDAO extends GenericDAO<NhaCungCap, String> {
    String getMaxId();
}
