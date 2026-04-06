package com.cafe.service.implement;

import com.cafe.dao.HoaDonDAO;
import com.cafe.model.entity.HoaDon;
import com.cafe.service.HoaDonService;
import java.math.BigDecimal;
import java.util.List;

public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonDAO hoaDonDAO;

    public HoaDonServiceImpl(HoaDonDAO hoaDonDAO) {
        this.hoaDonDAO = hoaDonDAO;
    }

    @Override
    public List<HoaDon> getAll() {
        return hoaDonDAO.findAll();
    }

    @Override
    public HoaDon getById(String id) {
        return hoaDonDAO.findById(id);
    }

    @Override
    public HoaDon create(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getMaHoaDon() == null || hoaDon.getMaHoaDon().isEmpty()) {
            throw new IllegalArgumentException("Mã hóa đơn không được trống");
        }
        return hoaDonDAO.save(hoaDon);
    }

    @Override
    public HoaDon update(HoaDon hoaDon) {
        if (hoaDon == null || hoaDon.getMaHoaDon() == null) {
            throw new IllegalArgumentException("Hóa đơn không hợp lệ");
        }
        return hoaDonDAO.update(hoaDon);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID không được trống");
        }
        hoaDonDAO.delete(id);
    }

    @Override
    public List<HoaDon> findByKhachHangId(String maKhachHang) {
        if (maKhachHang == null || maKhachHang.isEmpty()) {
            throw new IllegalArgumentException("Mã khách hàng không được trống");
        }
        return hoaDonDAO.findAll().stream()
                .filter(h -> h.getKhachHang() != null && h.getKhachHang().getMaKhachHang().equals(maKhachHang))
                .toList();
    }

    @Override
    public BigDecimal getTotalRevenue() {
        return hoaDonDAO.findAll().stream()
                .map(HoaDon::getTongTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void updateTongTienOnly(String maHoaDon, BigDecimal tongTien) {
        // Ép kiểu về impl cụ thể để dùng JPQL UPDATE, tránh cascade merge
        ((com.cafe.dao.impl.HoaDonDAOImpl) hoaDonDAO).updateTongTien(maHoaDon, tongTien);
    }
}
