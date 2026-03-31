package com.cafe.dao.impl;

import com.cafe.dao.KhachHangDAO;
import com.cafe.model.entity.KhachHang;

/**
 * Class implementation của KhachHangDAO.
 */
public class KhachHangDAOImpl extends GenericDAOImpl<KhachHang, String> implements KhachHangDAO {

    /**
     * Khởi tạo DAO cho Entity KhachHang.
     */
    public KhachHangDAOImpl() {
        super(KhachHang.class);
    }
}
