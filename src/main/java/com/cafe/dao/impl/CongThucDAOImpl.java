package com.cafe.dao.impl;

import com.cafe.dao.CongThucDAO;
import com.cafe.model.entity.CongThuc;

/**
 * Class implementation của CongThucDAO.
 */
public class CongThucDAOImpl extends GenericDAOImpl<CongThuc, String> implements CongThucDAO {

    /**
     * Khởi tạo DAO cho Entity CongThuc.
     */
    public CongThucDAOImpl() {
        super(CongThuc.class);
    }
}
