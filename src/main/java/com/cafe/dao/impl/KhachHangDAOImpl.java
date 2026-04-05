package com.cafe.dao.impl;

import com.cafe.dao.KhachHangDAO;
import com.cafe.model.entity.KhachHang;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementation của KhachHangDAO.
 */
public class KhachHangDAOImpl extends GenericDAOImpl<KhachHang, String> implements KhachHangDAO {

    public KhachHangDAOImpl() {
        super(KhachHang.class);
    }

    @Override
    public KhachHang findBySoDienThoai(String soDienThoai) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT k FROM KhachHang k WHERE k.SoDienThoai = :sdt";
            List<KhachHang> results = em.createQuery(jpql, KhachHang.class)
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

    @Override
    public List<KhachHang> findByTenContaining(String ten) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT k FROM KhachHang k " +
                    "WHERE LOWER(k.TenKhachHang) LIKE LOWER(:ten) " +
                    "ORDER BY k.TenKhachHang";
            return em.createQuery(jpql, KhachHang.class)
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
