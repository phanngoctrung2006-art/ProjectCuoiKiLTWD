package com.cafe.dao.impl;

import com.cafe.dao.NguyenLieuDAO;
import com.cafe.model.entity.NguyenLieu;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

public class NguyenLieuDAOImpl extends GenericDAOImpl<NguyenLieu, String> implements NguyenLieuDAO {

    public NguyenLieuDAOImpl() {
        super(NguyenLieu.class);
    }

    @Override
    public String getMaxId() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT nl.MaNguyenLieu FROM NguyenLieu nl ORDER BY nl.MaNguyenLieu DESC";
            return em.createQuery(jpql, String.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
