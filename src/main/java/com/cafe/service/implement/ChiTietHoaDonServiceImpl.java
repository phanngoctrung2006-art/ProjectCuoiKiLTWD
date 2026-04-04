package com.cafe.service.implement;

import com.cafe.dao.ChiTietHoaDonDAO;
import com.cafe.model.entity.ChiTietHoaDon;
import com.cafe.service.ChiTietHoaDonService;
import java.util.List;

public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
    private final ChiTietHoaDonDAO chiTietHoaDonDAO;

    public ChiTietHoaDonServiceImpl(ChiTietHoaDonDAO chiTietHoaDonDAO) {
        this.chiTietHoaDonDAO = chiTietHoaDonDAO;
    }

    @Override
    public List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon) {
        return chiTietHoaDonDAO.findByMaHoaDon(maHoaDon);
    }

    @Override
    public void deleteByMaHoaDon(String maHoaDon) {
        chiTietHoaDonDAO.deleteByMaHoaDon(maHoaDon);
    }

    @Override
    public ChiTietHoaDon create(ChiTietHoaDon chiTietHoaDon) {
        return chiTietHoaDonDAO.save(chiTietHoaDon);
    }

    @Override
    public ChiTietHoaDon update(ChiTietHoaDon chiTietHoaDon) {
        return chiTietHoaDonDAO.update(chiTietHoaDon);
    }

    @Override
    public void delete(com.cafe.model.entity.ChiTietHoaDonId id) {
        chiTietHoaDonDAO.delete(id);
    }
}
