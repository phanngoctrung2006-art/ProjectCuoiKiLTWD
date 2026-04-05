package com.cafe.dao.impl;

import com.cafe.dao.KhachHangDAO;
import com.cafe.model.entity.KhachHang;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAOImpl extends GenericDAOImpl<KhachHang, String> implements KhachHangDAO {

    public KhachHangDAOImpl() {
        super(KhachHang.class);
    }

}
