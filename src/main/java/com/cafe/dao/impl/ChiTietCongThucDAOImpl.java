package com.cafe.dao.impl;

import com.cafe.dao.ChiTietCongThucDAO;
import com.cafe.model.entity.ChiTietCongThuc;
import com.cafe.model.entity.ChiTietCongThucId;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementation của ChiTietCongThucDAO.
 */
public class ChiTietCongThucDAOImpl extends GenericDAOImpl<ChiTietCongThuc, ChiTietCongThucId> implements ChiTietCongThucDAO {

    public ChiTietCongThucDAOImpl() {
        super(ChiTietCongThuc.class);
    }

    @Override
    public List<ChiTietCongThuc> findByMaCongThuc(String maCongThuc) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT ct FROM ChiTietCongThuc ct " +
                    "LEFT JOIN FETCH ct.NguyenLieu " +
                    "WHERE ct.CongThuc.MaCongThuc = :maCT";
            return em.createQuery(jpql, ChiTietCongThuc.class)
                    .setParameter("maCT", maCongThuc)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteByMaCongThuc(String maCongThuc) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "DELETE FROM ChiTietCongThuc ct WHERE ct.CongThuc.MaCongThuc = :maCT";
            em.createQuery(jpql).setParameter("maCT", maCongThuc).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
