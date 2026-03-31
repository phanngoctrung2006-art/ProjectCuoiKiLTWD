package com.cafe.dao.impl;

import com.cafe.dao.HoaDonDAO;
import com.cafe.model.entity.HoaDon;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * Class implementation của HoaDonDAO.
 */
public class HoaDonDAOImpl extends GenericDAOImpl<HoaDon, String> implements HoaDonDAO {

    /**
     * Khởi tạo DAO cho Entity HoaDon.
     */
    public HoaDonDAOImpl() {
        super(HoaDon.class);
    }

    @Override
    public List<HoaDon> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT h FROM HoaDon h LEFT JOIN FETCH h.KhachHang";
            return em.createQuery(jpql, HoaDon.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public HoaDon findById(String id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT h FROM HoaDon h LEFT JOIN FETCH h.KhachHang WHERE h.MaHoaDon = :id";
            return em.createQuery(jpql, HoaDon.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
