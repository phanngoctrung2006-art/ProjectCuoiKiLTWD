package com.cafe.dao.impl;

import com.cafe.dao.CongThucDAO;
import com.cafe.model.entity.CongThuc;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementation của CongThucDAO.
 */
public class CongThucDAOImpl extends GenericDAOImpl<CongThuc, String> implements CongThucDAO {

    public CongThucDAOImpl() {
        super(CongThuc.class);
    }

    @Override
    public List<CongThuc> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT ct FROM CongThuc ct LEFT JOIN FETCH ct.ThucUong";
            return em.createQuery(jpql, CongThuc.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public CongThuc findById(String id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT ct FROM CongThuc ct LEFT JOIN FETCH ct.ThucUong WHERE ct.MaCongThuc = :id";
            List<CongThuc> results = em.createQuery(jpql, CongThuc.class)
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
}
