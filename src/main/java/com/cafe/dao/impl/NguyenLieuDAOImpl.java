package com.cafe.dao.impl;

import com.cafe.dao.NguyenLieuDAO;
import com.cafe.model.entity.NguyenLieu;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class NguyenLieuDAOImpl extends GenericDAOImpl<NguyenLieu, String> implements NguyenLieuDAO {

    public NguyenLieuDAOImpl() {
        super(NguyenLieu.class);
    }

}
