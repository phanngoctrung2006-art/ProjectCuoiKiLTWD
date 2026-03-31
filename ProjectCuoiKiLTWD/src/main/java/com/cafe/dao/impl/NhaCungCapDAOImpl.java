package com.cafe.dao.impl;

import com.cafe.dao.NhaCungCapDAO;
import com.cafe.model.entity.NhaCungCap;

/**
 * Class implementation của NhaCungCapDAO.
 */
public class NhaCungCapDAOImpl extends GenericDAOImpl<NhaCungCap, String> implements NhaCungCapDAO {

    /**
     * Khởi tạo DAO cho Entity NhaCungCap.
     */
    public NhaCungCapDAOImpl() {
        super(NhaCungCap.class);
    }
}
