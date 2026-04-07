package com.cafe.dao.impl;

import com.cafe.dao.PhieuNhapDAO;
import com.cafe.model.entity.PhieuNhap;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAOImpl extends GenericDAOImpl<PhieuNhap, String> implements PhieuNhapDAO {

    public PhieuNhapDAOImpl() {
        super(PhieuNhap.class);
    }

    @Override
    public List<PhieuNhap> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT pn FROM PhieuNhap pn " +
                    "LEFT JOIN FETCH pn.NhanVien " +
                    "LEFT JOIN FETCH pn.NhaCungCap " +
                    "ORDER BY pn.NgayNhap DESC";
            return em.createQuery(jpql, PhieuNhap.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public PhieuNhap findById(String id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT pn FROM PhieuNhap pn " +
                    "LEFT JOIN FETCH pn.NhanVien " +
                    "LEFT JOIN FETCH pn.NhaCungCap " +
                    "WHERE pn.MaPhieuNhap = :id";
            List<PhieuNhap> results = em.createQuery(jpql, PhieuNhap.class)
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
    public String getMaxId() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT pn.MaPhieuNhap FROM PhieuNhap pn ORDER BY pn.MaPhieuNhap DESC";
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
