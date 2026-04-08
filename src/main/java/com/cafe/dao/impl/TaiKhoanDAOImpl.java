package com.cafe.dao.impl;

import com.cafe.dao.TaiKhoanDAO;
import com.cafe.model.entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.util.List;

public class TaiKhoanDAOImpl implements TaiKhoanDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafePU");

    @Override
    public List<TaiKhoan> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT t FROM TaiKhoan t", TaiKhoan.class).getResultList();
        }
    }

    @Override
    public TaiKhoan findById(String id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(TaiKhoan.class, id);
        }
    }

    @Override
    public TaiKhoan save(TaiKhoan taiKhoan) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TaiKhoan saved = em.find(TaiKhoan.class, taiKhoan.getTenDangNhap());
            if (saved == null) {
                em.persist(taiKhoan);
                saved = taiKhoan;
            } else {
                saved = em.merge(taiKhoan);
            }
            em.getTransaction().commit();
            return saved;
        }
    }

    @Override
    public TaiKhoan update(TaiKhoan taiKhoan) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TaiKhoan merged = em.merge(taiKhoan);
            em.getTransaction().commit();
            return merged;
        }
    }

    @Override
    public void delete(String id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TaiKhoan t = em.find(TaiKhoan.class, id);
            if (t != null) {
                em.remove(t);
            }
            em.getTransaction().commit();
        }
    }

    @Override
    public long count() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT COUNT(t) FROM TaiKhoan t", Long.class).getSingleResult();
        }
    }

    @Override
    public boolean existsById(String id) {
        return findById(id) != null;
    }

    @Override
    public TaiKhoan findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT t FROM TaiKhoan t WHERE t.tenDangNhap = :tenDangNhap AND t.matKhau = :matKhau", TaiKhoan.class)
                    .setParameter("tenDangNhap", tenDangNhap)
                    .setParameter("matKhau", matKhau)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Không tìm thấy
        }
    }

    @Override
    public void createDefaultAdminIfEmpty() {
        try (EntityManager em = emf.createEntityManager()) {
            Long count = em.createQuery("SELECT COUNT(t) FROM TaiKhoan t", Long.class).getSingleResult();
            if (count == 0) {
                em.getTransaction().begin();
                TaiKhoan admin = new TaiKhoan("admin", "123456", "ADMIN", null);
                em.persist(admin);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
