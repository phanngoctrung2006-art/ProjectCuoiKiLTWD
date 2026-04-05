package com.cafe.model.dto;

import com.cafe.model.entity.NguyenLieu;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng NguyenLieu.
 * Chứa thông tin cơ bản của nguyên liệu, không chứa danh sách chi tiết.
 */
public class NguyenLieuDTO implements Serializable {

    private String MaNguyenLieu;
    private String TenNguyenLieu;
    private Integer SoLuong;

    // Constructors
    public NguyenLieuDTO() {}

    public NguyenLieuDTO(String MaNguyenLieu, String TenNguyenLieu, Integer SoLuong) {
        this.MaNguyenLieu = MaNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.SoLuong = SoLuong;
    }

    // Getters and Setters
    public String getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public void setMaNguyenLieu(String MaNguyenLieu) {
        this.MaNguyenLieu = MaNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public void setTenNguyenLieu(String TenNguyenLieu) {
        this.TenNguyenLieu = TenNguyenLieu;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static NguyenLieuDTO fromEntity(NguyenLieu entity) {
        if (entity == null) return null;
        NguyenLieuDTO dto = new NguyenLieuDTO();
        dto.setMaNguyenLieu(entity.getMaNguyenLieu());
        dto.setTenNguyenLieu(entity.getTenNguyenLieu());
        dto.setSoLuong(entity.getSoLuong());
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity.
     */
    public NguyenLieu toEntity() {
        NguyenLieu entity = new NguyenLieu();
        entity.setMaNguyenLieu(this.MaNguyenLieu);
        entity.setTenNguyenLieu(this.TenNguyenLieu);
        entity.setSoLuong(this.SoLuong);
        return entity;
    }
}
