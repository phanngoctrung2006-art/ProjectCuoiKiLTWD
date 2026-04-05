package com.cafe.dao;

import com.cafe.model.entity.ThucUong;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interface DAO cho bảng ThucUong.
 */
public interface ThucUongDAO extends GenericDAO<ThucUong, String> {

    /**
     * Tìm danh sách thức uống theo mã loại.
     * @param maLoai Mã loại thức uống
     * @return Danh sách thức uống thuộc loại
     */
    List<ThucUong> findByMaLoai(String maLoai);

    /**
     * Tìm thức uống theo tên (chứa chuỗi, không phân biệt hoa thường).
     * @param ten Chuỗi tên cần tìm
     * @return Danh sách thức uống khớp
     */
    List<ThucUong> findByTenContaining(String ten);

    /**
     * Tìm thức uống theo khoảng giá.
     * @param giaMin Giá tối thiểu
     * @param giaMax Giá tối đa
     * @return Danh sách thức uống trong khoảng giá
     */
    List<ThucUong> findByGiaBetween(BigDecimal giaMin, BigDecimal giaMax);
}
