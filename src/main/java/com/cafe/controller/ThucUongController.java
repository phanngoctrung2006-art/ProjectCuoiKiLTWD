package com.cafe.controller;

import com.cafe.model.entity.ThucUong;
import com.cafe.service.ThucUongService;
import java.util.List;

/**
 * Controller: cầu nối giữa View và Service.
 * Không chứa logic nghiệp vụ hay validation - mọi thứ đều ở Service.
 */
public class ThucUongController {
    private final ThucUongService service;

    public ThucUongController(ThucUongService service) {
        this.service = service;
    }

    public List<ThucUong> getAll() { return service.getAll(); }
    public ThucUong getById(String id) { return service.getById(id); }
    public ThucUong save(ThucUong tu) { return service.create(tu); }
    public ThucUong update(ThucUong tu) { return service.update(tu); }
    public void delete(String id) { service.delete(id); }
    public String getNextId() { return service.getNextId(); }
}
