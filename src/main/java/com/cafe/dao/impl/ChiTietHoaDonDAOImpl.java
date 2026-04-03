package com.cafe.dao.impl;

import com.cafe.dao.ChiTietHoaDonDAO;
import com.cafe.model.entity.ChiTietHoaDon;
import com.cafe.model.entity.ChiTietHoaDonId;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementation của ChiTietHoaDonDAO.
 */
public class ChiTietHoaDonDAOImpl extends GenericDAOImpl<ChiTietHoaDon, ChiTietHoaDonId> implements ChiTietHoaDonDAO {

    public ChiTietHoaDonDAOImpl() {
        super(ChiTietHoaDon.class);
    }

    @Override
    public List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT ct FROM ChiTietHoaDon ct " +
                    "LEFT JOIN FETCH ct.ThucUong " +
                    "WHERE ct.HoaDon.MaHoaDon = :maHD";
            return em.createQuery(jpql, ChiTietHoaDon.class)
                    .setParameter("maHD", maHoaDon)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteByMaHoaDon(String maHoaDon) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "DELETE FROM ChiTietHoaDon ct WHERE ct.HoaDon.MaHoaDon = :maHD";
            em.createQuery(jpql).setParameter("maHD", maHoaDon).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
