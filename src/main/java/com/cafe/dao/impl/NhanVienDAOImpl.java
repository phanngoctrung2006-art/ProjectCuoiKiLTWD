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
            String jpql = "SELECT nv.maNhanVien FROM NhanVien nv ORDER BY nv.maNhanVien DESC";
            return em.createQuery(jpql, String.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // nếu chưa có dữ liệu
        } finally {
            em.close();
        }
    }
}
