package com.cafe.dao.impl;

import com.cafe.dao.HoaDonDAO;
import com.cafe.model.entity.HoaDon;

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
}
