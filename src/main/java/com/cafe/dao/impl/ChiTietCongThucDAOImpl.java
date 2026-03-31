package com.cafe.dao.impl;

import com.cafe.dao.ChiTietCongThucDAO;
import com.cafe.model.entity.ChiTietCongThuc;
import com.cafe.model.entity.ChiTietCongThucId;

/**
 * Class implementation của ChiTietCongThucDAO.
 */
public class ChiTietCongThucDAOImpl extends GenericDAOImpl<ChiTietCongThuc, ChiTietCongThucId> implements ChiTietCongThucDAO {

    /**
     * Khởi tạo DAO cho Entity ChiTietCongThuc.
     */
    public ChiTietCongThucDAOImpl() {
        super(ChiTietCongThuc.class);
    }
}
