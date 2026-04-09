package com.cafe.dao;

import java.util.List;

/**
 * Interface GenericDAO định nghĩa các thao tác CRUD cơ bản dùng chung cho tất cả các Entity.
 * @param <T> Loại thực thể (Entity)
 * @param <ID> Kiểu dữ liệu của Khóa chính (Primary Key)
 */
public interface GenericDAO<T, ID> {
    // Lưu một thực thể mới vào CSDL
    T save(T entity);

    // Cập nhật thông tin một thực thể đã tồn tại
    T update(T entity);

    // Xóa thực thể dựa trên ID
    void delete(ID id);

    // Tìm kiếm thực thể theo ID
    T findById(ID id);

    // Lấy toàn bộ danh sách các thực thể
    List<T> findAll();

    long count();

    boolean existsById(ID id);
}
