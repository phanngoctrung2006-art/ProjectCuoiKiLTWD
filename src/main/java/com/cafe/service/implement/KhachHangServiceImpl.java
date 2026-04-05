package com.cafe.service.implement;

import com.cafe.dao.KhachHangDAO;
import com.cafe.model.entity.KhachHang;
import com.cafe.service.KhachHangService;
import java.util.List;

public class KhachHangServiceImpl implements KhachHangService {
    private final KhachHangDAO khachHangDAO;

    public KhachHangServiceImpl(KhachHangDAO khachHangDAO) {
        this.khachHangDAO = khachHangDAO;
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangDAO.findAll();
    }

    @Override
    public KhachHang getById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID không được trống");
        }
        return khachHangDAO.findById(id);
    }

    @Override
    public KhachHang create(KhachHang khachHang) {
        if (khachHang == null) {
            throw new IllegalArgumentException("Khách hàng không được null");
        }
        if (khachHang.getTenKhachHang() == null || khachHang.getTenKhachHang().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được trống");
        }
        if (khachHang.getMaKhachHang() == null || khachHang.getMaKhachHang().isEmpty()) {
            khachHang.setMaKhachHang(generateNextMaKhachHang());
        }
        return khachHangDAO.save(khachHang);
    }

    @Override
    public KhachHang update(KhachHang khachHang) {
        if (khachHang == null || khachHang.getMaKhachHang() == null) {
            throw new IllegalArgumentException("Khách hàng không hợp lệ");
        }
        return khachHangDAO.update(khachHang);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID không được trống");
        }
        khachHangDAO.delete(id);
    }

    @Override
    public List<KhachHang> searchByName(String name) {
        if (name == null || name.isEmpty()) {
            return getAll();
        }
        String keyword = name.toLowerCase();
        return khachHangDAO.findAll().stream()
                .filter(k -> k.getTenKhachHang() != null && k.getTenKhachHang().toLowerCase().contains(keyword))
                .toList();
    }

    @Override
    public KhachHang findByPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return null;
        }
        return khachHangDAO.findAll().stream()
                .filter(k -> phone.equals(k.getSoDienThoai()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public KhachHang findByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return khachHangDAO.findAll().stream()
                .filter(k -> name.equalsIgnoreCase(k.getTenKhachHang()))
                .findFirst()
                .orElse(null);
    }

    private String generateNextMaKhachHang() {
        List<KhachHang> all = khachHangDAO.findAll();
        int maxNum = 0;
        for (KhachHang kh : all) {
            String ma = kh.getMaKhachHang();
            if (ma != null && ma.startsWith("KH")) {
                try {
                    int num = Integer.parseInt(ma.substring(2));
                    if (num > maxNum) {
                        maxNum = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid format
                }
            }
        }
        return String.format("KH%02d", maxNum + 1);
    }
}
