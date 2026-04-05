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

}
