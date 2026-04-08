package com.cafe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cafe.dao.LoaiThucUongDAO;
import com.cafe.model.entity.LoaiThucUong;
import com.cafe.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class LoaiThucUongDAOImpl extends GenericDAOImpl<LoaiThucUong, String> implements LoaiThucUongDAO {

    public LoaiThucUongDAOImpl() {
        super(LoaiThucUong.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<LoaiThucUong> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT t FROM LoaiThucUong t";
            return em.createQuery(jpql, LoaiThucUong.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

}
