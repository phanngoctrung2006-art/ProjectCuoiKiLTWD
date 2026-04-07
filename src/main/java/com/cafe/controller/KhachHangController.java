package com.cafe.controller;

import com.cafe.model.entity.KhachHang;
import com.cafe.service.KhachHangService;
import java.util.List;

/**
 * Controller: cầu nối giữa View và Service.
 * Không chứa logic nghiệp vụ hay validation - mọi thứ đều ở Service.
 */
public class KhachHangController {
    private final KhachHangService service;

    public KhachHangController(KhachHangService service) {
        this.service = service;
    }

    public List<KhachHang> getAll() { return service.getAll(); }
    public KhachHang getById(String id) { return service.getById(id); }
    public KhachHang save(KhachHang kh) { return service.create(kh); }
    public KhachHang update(KhachHang kh) { return service.update(kh); }
    public void delete(String id) { service.delete(id); }
    public KhachHang findByPhone(String phone) { return service.findByPhone(phone); }
}
