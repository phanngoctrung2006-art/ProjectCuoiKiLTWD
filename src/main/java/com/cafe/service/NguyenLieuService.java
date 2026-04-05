package com.cafe.service;

import com.cafe.model.entity.NguyenLieu;
import java.util.List;

public interface NguyenLieuService {
    List<NguyenLieu> getAll();
    NguyenLieu getById(String id);
    NguyenLieu create(NguyenLieu nguyenLieu);
    NguyenLieu update(NguyenLieu nguyenLieu);
    void delete(String id);
}
