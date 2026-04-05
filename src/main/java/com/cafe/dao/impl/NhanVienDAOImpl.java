package com.cafe.dao.impl;

import com.cafe.dao.NhanVienDAO;
import com.cafe.model.entity.NhanVien;
public class NhanVienDAOImpl extends GenericDAOImpl<NhanVien, String> implements NhanVienDAO {

    public NhanVienDAOImpl() {
        super(NhanVien.class);
    }

}
