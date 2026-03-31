package com.cafe.dao.impl;

import com.cafe.dao.NhanVienDAO;
import com.cafe.model.entity.NhanVien;

/**
 * Class implementation của NhanVienDAO.
 */
public class NhanVienDAOImpl extends GenericDAOImpl<NhanVien, String> implements NhanVienDAO {

    /**
     * Khởi tạo DAO cho Entity NhanVien.
     */
    public NhanVienDAOImpl() {
        super(NhanVien.class);
    }
}
