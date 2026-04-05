package com.cafe.dao.impl;

import com.cafe.dao.ThucUongDAO;
import com.cafe.model.entity.ThucUong;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implementation của ThucUongDAO.
 */
public class ThucUongDAOImpl extends GenericDAOImpl<ThucUong, String> implements ThucUongDAO {

    public ThucUongDAOImpl() {
        super(ThucUong.class);
    }

    @Override
    public List<ThucUong> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT t FROM ThucUong t LEFT JOIN FETCH t.LoaiThucUong";
            return em.createQuery(jpql, ThucUong.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ThucUong findById(String id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT t FROM ThucUong t LEFT JOIN FETCH t.LoaiThucUong WHERE t.MaThucUong = :id";
            List<ThucUong> results = em.createQuery(jpql, ThucUong.class)
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
