package com.cafe.dao.impl;

import com.cafe.dao.NguyenLieuDAO;
import com.cafe.model.entity.NguyenLieu;

/**
 * Class implementation của NguyenLieuDAO.
 */
public class NguyenLieuDAOImpl extends GenericDAOImpl<NguyenLieu, String> implements NguyenLieuDAO {

    /**
     * Khởi tạo DAO cho Entity NguyenLieu.
     */
    public NguyenLieuDAOImpl() {
        super(NguyenLieu.class);
    }
}
