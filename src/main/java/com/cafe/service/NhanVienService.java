package com.cafe.service;

import com.cafe.model.entity.NhanVien;
import java.util.List;

public interface NhanVienService {
    List<NhanVien> getAll();
    NhanVien getById(String id);
    NhanVien create(NhanVien nhanVien);
    NhanVien update(NhanVien nhanVien);
    void delete(String id);
}
