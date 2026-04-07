package com.cafe.controller;

import com.cafe.model.entity.NhanVien;
import com.cafe.service.NhanVienService;
import java.util.List;

/**
 * Controller: cầu nối giữa View và Service.
 * Không chứa logic nghiệp vụ hay validation - mọi thứ đều ở Service.
 */
public class NhanVienController {
    private final NhanVienService service;

    public NhanVienController(NhanVienService service) {
        this.service = service;
    }

    public List<NhanVien> getAll() { return service.getAll(); }
    public NhanVien getById(String id) { return service.getById(id); }
    public NhanVien save(NhanVien nv) { return service.create(nv); }
    public NhanVien update(NhanVien nv) { return service.update(nv); }
    public void delete(String id) { service.delete(id); }
    public String getNextId() { return service.getNextId(); }
}
