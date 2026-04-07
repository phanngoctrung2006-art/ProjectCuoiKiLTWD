package com.cafe.service;

import com.cafe.model.entity.NhaCungCap;
import java.util.List;

public interface NhaCungCapService {
    List<NhaCungCap> getAll();
    NhaCungCap getById(String id);
    NhaCungCap create(NhaCungCap nhaCungCap);
    NhaCungCap update(NhaCungCap nhaCungCap);
    void delete(String id);
    String getNextId();
}
