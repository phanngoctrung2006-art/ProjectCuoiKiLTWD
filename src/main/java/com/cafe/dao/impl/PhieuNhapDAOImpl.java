package com.cafe.dao.impl;

import com.cafe.dao.PhieuNhapDAO;
import com.cafe.model.entity.PhieuNhap;

/**
 * Class implementation của PhieuNhapDAO.
 */
public class PhieuNhapDAOImpl extends GenericDAOImpl<PhieuNhap, String> implements PhieuNhapDAO {

    /**
     * Khởi tạo DAO cho Entity PhieuNhap.
     */
    public PhieuNhapDAOImpl() {
        super(PhieuNhap.class);
    }
}
