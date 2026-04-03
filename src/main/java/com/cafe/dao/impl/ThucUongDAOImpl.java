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

    @Override
    public List<ThucUong> findByMaLoai(String maLoai) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT t FROM ThucUong t LEFT JOIN FETCH t.LoaiThucUong " +
                    "WHERE t.LoaiThucUong.MaLoai = :maLoai";
            return em.createQuery(jpql, ThucUong.class)
                    .setParameter("maLoai", maLoai)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ThucUong> findByTenContaining(String ten) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT t FROM ThucUong t LEFT JOIN FETCH t.LoaiThucUong " +
                    "WHERE LOWER(t.TenThucUong) LIKE LOWER(:ten)";
            return em.createQuery(jpql, ThucUong.class)
                    .setParameter("ten", "%" + ten + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ThucUong> findByGiaBetween(BigDecimal giaMin, BigDecimal giaMax) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT t FROM ThucUong t LEFT JOIN FETCH t.LoaiThucUong " +
                    "WHERE t.Gia BETWEEN :giaMin AND :giaMax ORDER BY t.Gia ASC";
            return em.createQuery(jpql, ThucUong.class)
                    .setParameter("giaMin", giaMin)
                    .setParameter("giaMax", giaMax)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
