package com.cafe.service;

import com.cafe.model.entity.PhieuNhap;
import java.util.List;

public interface PhieuNhapService {
    List<PhieuNhap> getAll();
    PhieuNhap getById(String id);
    PhieuNhap create(PhieuNhap phieuNhap);
    PhieuNhap update(PhieuNhap phieuNhap);
    void delete(String id);
    String getNextId();
}
