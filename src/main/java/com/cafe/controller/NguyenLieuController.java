package com.cafe.controller;

import com.cafe.model.entity.NguyenLieu;
import com.cafe.service.NguyenLieuService;
import java.util.List;

/**
 * Controller: cầu nối giữa View và Service.
 * Không chứa logic nghiệp vụ hay validation - mọi thứ đều ở Service.
 */
public class NguyenLieuController {
    private final NguyenLieuService service;

    public NguyenLieuController(NguyenLieuService service) {
        this.service = service;
    }

    public List<NguyenLieu> getAll() { return service.getAll(); }
    public NguyenLieu getById(String id) { return service.getById(id); }
    public NguyenLieu save(NguyenLieu nl) { return service.create(nl); }
    public NguyenLieu update(NguyenLieu nl) { return service.update(nl); }
    public void delete(String id) { service.delete(id); }
    public String getNextId() { return service.getNextId(); }
}
