package com.cafe.dao;

import java.util.List;

/**
 * Interface Generic cho DAO cung cấp các hàm CRUD cơ bản.
 * 
 * @param <T>  Kiểu Entity
 * @param <ID> Kiểu khóa chính
 */
public interface GenericDAO<T, ID> {
    /**
     * Lưu mới một Entity vào cơ sở dữ liệu.
     * @param entity Đối tượng entity cần lưu
     * @return Entity đã được lưu
     */
    T save(T entity);

    /**
     * Cập nhật thông tin một Entity trong cơ sở dữ liệu.
     * @param entity Đối tượng entity cần cập nhật
     * @return Entity sau khi cập nhật
     */
    T update(T entity);

    /**
     * Xóa một Entity dựa vào khóa chính.
     * @param id Khóa chính của entity cần xóa
     */
    void delete(ID id);

    /**
     * Tìm kiếm một Entity dựa vào khóa chính.
     * @param id Khóa chính của entity
     * @return Đối tượng được tìm thấy, nếu không có trả về null
     */
    T findById(ID id);

    /**
     * Lấy danh sách tất cả các Entity.
     * @return Danh sách Entity
     */
    List<T> findAll();
}
