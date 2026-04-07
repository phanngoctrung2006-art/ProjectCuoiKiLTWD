package com.cafe.dao;

import com.cafe.model.entity.PhieuNhap;
public interface PhieuNhapDAO extends GenericDAO<PhieuNhap, String> {
    String getMaxId();
}
