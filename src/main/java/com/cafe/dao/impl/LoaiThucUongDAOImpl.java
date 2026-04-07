package com.cafe.dao.impl;

import com.cafe.dao.LoaiThucUongDAO;
import com.cafe.model.entity.LoaiThucUong;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

public class LoaiThucUongDAOImpl extends GenericDAOImpl<LoaiThucUong, String> implements LoaiThucUongDAO {

    public LoaiThucUongDAOImpl() {
        super(LoaiThucUong.class);
    }

    @Override
    public String getMaxId() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT lt.MaLoai FROM LoaiThucUong lt ORDER BY lt.MaLoai DESC";
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
