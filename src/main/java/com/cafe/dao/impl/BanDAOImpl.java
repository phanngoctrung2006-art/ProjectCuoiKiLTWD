package com.cafe.dao.impl;

import com.cafe.dao.BanDAO;
import com.cafe.model.entity.Ban;

/**
 * Class implementation của BanDAO.
 */
public class BanDAOImpl extends GenericDAOImpl<Ban, String> implements BanDAO {

    /**
     * Khởi tạo DAO cho Entity Ban.
     */
    public BanDAOImpl() {
        super(Ban.class);
    }
}
