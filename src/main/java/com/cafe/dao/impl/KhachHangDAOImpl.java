package com.cafe.dao.impl;

import com.cafe.dao.KhachHangDAO;
import com.cafe.model.entity.KhachHang;
public class KhachHangDAOImpl extends GenericDAOImpl<KhachHang, String> implements KhachHangDAO {

    public KhachHangDAOImpl() {
        super(KhachHang.class);
    }

}
