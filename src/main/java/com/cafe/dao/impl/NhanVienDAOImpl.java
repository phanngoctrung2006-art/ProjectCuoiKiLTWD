package com.cafe.dao.impl;

import com.cafe.dao.NhanVienDAO;
import com.cafe.model.entity.NhanVien;
import com.cafe.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class NhanVienDAOImpl extends GenericDAOImpl<NhanVien, String> implements NhanVienDAO {

    public NhanVienDAOImpl() {
        super(NhanVien.class);
    }

    @Override
    public String getMaxId() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT nv.MaNhanVien FROM NhanVien nv ORDER BY nv.MaNhanVien DESC";
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
