package com.cafe.dao.impl;

import com.cafe.dao.LoaiThucUongDAO;
import com.cafe.model.entity.LoaiThucUong;

/**
 * Class implementation của LoaiThucUongDAO.
 */
public class LoaiThucUongDAOImpl extends GenericDAOImpl<LoaiThucUong, String> implements LoaiThucUongDAO {

    /**
     * Khởi tạo DAO cho Entity LoaiThucUong.
     */
    public LoaiThucUongDAOImpl() {
        super(LoaiThucUong.class);
    }
}
