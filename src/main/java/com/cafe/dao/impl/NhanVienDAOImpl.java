package com.cafe.dao.impl;

import com.cafe.dao.NhanVienDAO;
import com.cafe.model.entity.NhanVien;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementation của NhanVienDAO.
 */
public class NhanVienDAOImpl extends GenericDAOImpl<NhanVien, String> implements NhanVienDAO {

    public NhanVienDAOImpl() {
        super(NhanVien.class);
    }

    @Override
    public List<NhanVien> findByTenContaining(String ten) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT nv FROM NhanVien nv " +
                    "WHERE LOWER(nv.TenNhanVien) LIKE LOWER(:ten) " +
                    "ORDER BY nv.TenNhanVien";
            return em.createQuery(jpql, NhanVien.class)
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
    public NhanVien findBySoDienThoai(String soDienThoai) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT nv FROM NhanVien nv WHERE nv.SoDienThoai = :sdt";
            List<NhanVien> results = em.createQuery(jpql, NhanVien.class)
                    .setParameter("sdt", soDienThoai)
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
