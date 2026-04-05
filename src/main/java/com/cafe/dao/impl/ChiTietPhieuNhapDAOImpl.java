package com.cafe.dao.impl;

import com.cafe.dao.ChiTietPhieuNhapDAO;
import com.cafe.model.entity.ChiTietPhieuNhap;
import com.cafe.model.entity.ChiTietPhieuNhapId;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapDAOImpl extends GenericDAOImpl<ChiTietPhieuNhap, ChiTietPhieuNhapId> implements ChiTietPhieuNhapDAO {

    public ChiTietPhieuNhapDAOImpl() {
        super(ChiTietPhieuNhap.class);
    }

    @Override
    public List<ChiTietPhieuNhap> findByMaPhieuNhap(String maPhieuNhap) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT ct FROM ChiTietPhieuNhap ct " +
                    "LEFT JOIN FETCH ct.NguyenLieu " +
                    "WHERE ct.PhieuNhap.MaPhieuNhap = :maPN";
            return em.createQuery(jpql, ChiTietPhieuNhap.class)
                    .setParameter("maPN", maPhieuNhap)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteByMaPhieuNhap(String maPhieuNhap) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "DELETE FROM ChiTietPhieuNhap ct WHERE ct.PhieuNhap.MaPhieuNhap = :maPN";
            em.createQuery(jpql).setParameter("maPN", maPhieuNhap).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
