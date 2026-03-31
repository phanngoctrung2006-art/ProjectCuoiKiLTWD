package com.cafe.dao.impl;

import com.cafe.dao.ChiTietPhieuNhapDAO;
import com.cafe.model.entity.ChiTietPhieuNhap;
import com.cafe.model.entity.ChiTietPhieuNhapId;

/**
 * Class implementation của ChiTietPhieuNhapDAO.
 */
public class ChiTietPhieuNhapDAOImpl extends GenericDAOImpl<ChiTietPhieuNhap, ChiTietPhieuNhapId> implements ChiTietPhieuNhapDAO {

    /**
     * Khởi tạo DAO cho Entity ChiTietPhieuNhap.
     */
    public ChiTietPhieuNhapDAOImpl() {
        super(ChiTietPhieuNhap.class);
    }
}
