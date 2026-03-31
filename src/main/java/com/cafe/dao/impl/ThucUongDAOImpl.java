package com.cafe.dao.impl;

import com.cafe.dao.ThucUongDAO;
import com.cafe.model.entity.ThucUong;

/**
 * Class implementation của ThucUongDAO.
 */
public class ThucUongDAOImpl extends GenericDAOImpl<ThucUong, String> implements ThucUongDAO {

    /**
     * Khởi tạo DAO cho Entity ThucUong.
     */
    public ThucUongDAOImpl() {
        super(ThucUong.class);
    }
}
