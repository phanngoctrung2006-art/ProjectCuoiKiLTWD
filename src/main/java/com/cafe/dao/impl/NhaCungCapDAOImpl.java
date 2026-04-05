package com.cafe.dao.impl;

import com.cafe.dao.NhaCungCapDAO;
import com.cafe.model.entity.NhaCungCap;

public class NhaCungCapDAOImpl extends GenericDAOImpl<NhaCungCap, String> implements NhaCungCapDAO {

    public NhaCungCapDAOImpl() {
        super(NhaCungCap.class);
    }
}
