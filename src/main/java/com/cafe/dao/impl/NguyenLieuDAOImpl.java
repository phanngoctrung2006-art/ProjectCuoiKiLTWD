package com.cafe.dao.impl;

import com.cafe.dao.NguyenLieuDAO;
import com.cafe.model.entity.NguyenLieu;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementation của NguyenLieuDAO.
 */
public class NguyenLieuDAOImpl extends GenericDAOImpl<NguyenLieu, String> implements NguyenLieuDAO {

    public NguyenLieuDAOImpl() {
        super(NguyenLieu.class);
    }

    @Override
    public List<NguyenLieu> findBySoLuongLessThan(int soLuong) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT nl FROM NguyenLieu nl " +
                    "WHERE nl.SoLuong < :soLuong " +
                    "ORDER BY nl.SoLuong ASC";
            return em.createQuery(jpql, NguyenLieu.class)
                    .setParameter("soLuong", soLuong)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<NguyenLieu> findByTenContaining(String ten) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT nl FROM NguyenLieu nl " +
                    "WHERE LOWER(nl.TenNguyenLieu) LIKE LOWER(:ten) " +
                    "ORDER BY nl.TenNguyenLieu";
            return em.createQuery(jpql, NguyenLieu.class)
                    .setParameter("ten", "%" + ten + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
