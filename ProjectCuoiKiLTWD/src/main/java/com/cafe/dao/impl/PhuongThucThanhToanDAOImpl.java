package com.cafe.dao.impl;

import com.cafe.dao.PhuongThucThanhToanDAO;
import com.cafe.model.entity.PhuongThucThanhToan;

/**
 * Class implementation của PhuongThucThanhToanDAO.
 */
public class PhuongThucThanhToanDAOImpl extends GenericDAOImpl<PhuongThucThanhToan, String> implements PhuongThucThanhToanDAO {

    /**
     * Khởi tạo DAO cho Entity PhuongThucThanhToan.
     */
    public PhuongThucThanhToanDAOImpl() {
        super(PhuongThucThanhToan.class);
    }
}
