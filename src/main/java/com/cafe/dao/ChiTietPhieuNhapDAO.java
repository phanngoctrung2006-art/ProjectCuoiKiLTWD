package com.cafe.dao;

import com.cafe.model.entity.ChiTietPhieuNhap;
import com.cafe.model.entity.ChiTietPhieuNhapId;
import java.util.List;

public interface ChiTietPhieuNhapDAO extends GenericDAO<ChiTietPhieuNhap, ChiTietPhieuNhapId> {

    List<ChiTietPhieuNhap> findByMaPhieuNhap(String maPhieuNhap);

    void deleteByMaPhieuNhap(String maPhieuNhap);
}
