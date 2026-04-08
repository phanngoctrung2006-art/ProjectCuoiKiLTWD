package com.cafe.dao.impl;

import com.cafe.dao.HoaDonDAO;
import com.cafe.model.entity.HoaDon;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAOImpl extends GenericDAOImpl<HoaDon, String> implements HoaDonDAO {

    public HoaDonDAOImpl() {
        super(HoaDon.class);
    }

    @Override
    public List<HoaDon> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT h FROM HoaDon h " +
                    "LEFT JOIN FETCH h.KhachHang " +
                    "LEFT JOIN FETCH h.Ban " +
                    "LEFT JOIN FETCH h.PhuongThucThanhToan " +
                    "LEFT JOIN FETCH h.NhanVien " +
                    "ORDER BY h.NgayLap DESC";
            List<HoaDon> results = em.createQuery(jpql, HoaDon.class).getResultList();
            return new java.util.ArrayList<>(new java.util.LinkedHashSet<>(results));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public HoaDon findById(String id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT h FROM HoaDon h " +
                    "LEFT JOIN FETCH h.KhachHang " +
                    "LEFT JOIN FETCH h.Ban " +
                    "LEFT JOIN FETCH h.PhuongThucThanhToan " +
                    "LEFT JOIN FETCH h.NhanVien " +
                    "WHERE h.MaHoaDon = :id";
            List<HoaDon> results = em.createQuery(jpql, HoaDon.class)
                    .setParameter("id", id)
                    .getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Cập nhật chỉ riêng TongTien bằng JPQL UPDATE — tránh cascade merge gây trùng
     * dữ liệu.
     */
    public void updateTongTien(String maHoaDon, java.math.BigDecimal tongTien) {
        EntityManager em = HibernateUtil.getEntityManager();
        jakarta.persistence.EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createQuery(
                    "UPDATE HoaDon h SET h.TongTien = :tien WHERE h.MaHoaDon = :ma")
                    .setParameter("tien", tongTien)
                    .setParameter("ma", maHoaDon)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
