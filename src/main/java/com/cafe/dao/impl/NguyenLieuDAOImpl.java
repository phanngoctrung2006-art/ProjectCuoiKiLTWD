package com.cafe.dao.impl;

import com.cafe.dao.NguyenLieuDAO;
import com.cafe.model.entity.NguyenLieu;
public class NguyenLieuDAOImpl extends GenericDAOImpl<NguyenLieu, String> implements NguyenLieuDAO {

    public NguyenLieuDAOImpl() {
        super(NguyenLieu.class);
    }

}
