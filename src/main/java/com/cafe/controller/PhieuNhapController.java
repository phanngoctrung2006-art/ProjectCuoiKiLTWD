package com.cafe.controller;

import com.cafe.model.entity.NhaCungCap;
import com.cafe.model.entity.NhanVien;
import com.cafe.model.entity.PhieuNhap;
import com.cafe.service.NhaCungCapService;
import com.cafe.service.NhanVienService;
import com.cafe.service.PhieuNhapService;
import java.util.List;

/**
 * Controller: cầu nối giữa View và Service.
 * Không chứa logic nghiệp vụ hay validation - mọi thứ đều ở Service.
 */
public class PhieuNhapController {
    private final PhieuNhapService service;
    private final NhanVienService nhanVienService;
    private final NhaCungCapService nhaCungCapService;

    public PhieuNhapController(PhieuNhapService service,
                               NhanVienService nhanVienService,
                               NhaCungCapService nhaCungCapService) {
        this.service = service;
        this.nhanVienService = nhanVienService;
        this.nhaCungCapService = nhaCungCapService;
    }

    public List<PhieuNhap> getAll() { return service.getAll(); }
    public PhieuNhap getById(String id) { return service.getById(id); }
    public PhieuNhap save(PhieuNhap pn) { return service.create(pn); }
    public PhieuNhap update(PhieuNhap pn) { return service.update(pn); }
    public void delete(String id) { service.delete(id); }
    public String getNextId() { return service.getNextId(); }

    public NhanVien getNhanVienById(String id) { return nhanVienService.getById(id); }
    public NhaCungCap getNhaCungCapById(String id) { return nhaCungCapService.getById(id); }
}
