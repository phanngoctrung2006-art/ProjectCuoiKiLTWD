package com.cafe.controller;

import com.cafe.model.entity.NhaCungCap;
import com.cafe.service.NhaCungCapService;
import java.util.List;

/**
 * Controller: cầu nối giữa View và Service.
 * Không chứa logic nghiệp vụ hay validation - mọi thứ đều ở Service.
 */
public class NhaCungCapController {
    private final NhaCungCapService service;

    public NhaCungCapController(NhaCungCapService service) {
        this.service = service;
    }

    public List<NhaCungCap> getAll() { return service.getAll(); }
    public NhaCungCap getById(String id) { return service.getById(id); }
    public NhaCungCap save(NhaCungCap ncc) { return service.create(ncc); }
    public NhaCungCap update(NhaCungCap ncc) { return service.update(ncc); }
    public void delete(String id) { service.delete(id); }
    public String getNextId() { return service.getNextId(); }
}
