package com.cafe.dao.impl;

import com.cafe.dao.ChiTietHoaDonDAO;
import com.cafe.model.entity.ChiTietHoaDon;
import com.cafe.model.entity.ChiTietHoaDonId;

/**
 * Class implementation của ChiTietHoaDonDAO.
 */
public class ChiTietHoaDonDAOImpl extends GenericDAOImpl<ChiTietHoaDon, ChiTietHoaDonId> implements ChiTietHoaDonDAO {

    /**
     * Khởi tạo DAO cho Entity ChiTietHoaDon.
     */
    public ChiTietHoaDonDAOImpl() {
        super(ChiTietHoaDon.class);
    }
}
