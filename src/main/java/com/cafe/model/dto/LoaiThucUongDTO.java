package com.cafe.model.dto;

import com.cafe.model.entity.LoaiThucUong;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng LoaiThucUong.
 * Chứa thông tin cơ bản của loại thức uống, không chứa danh sách thức uống để tránh vòng lặp.
 */
public class LoaiThucUongDTO implements Serializable {

    private String MaLoai;
    private String TenLoaiThucUong;

    // Constructors
    public LoaiThucUongDTO() {}

    public LoaiThucUongDTO(String MaLoai, String TenLoaiThucUong) {
        this.MaLoai = MaLoai;
        this.TenLoaiThucUong = TenLoaiThucUong;
    }

    // Getters and Setters
    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoaiThucUong() {
        return TenLoaiThucUong;
    }

    public void setTenLoaiThucUong(String TenLoaiThucUong) {
        this.TenLoaiThucUong = TenLoaiThucUong;
    }

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static LoaiThucUongDTO fromEntity(LoaiThucUong entity) {
        if (entity == null) return null;
        LoaiThucUongDTO dto = new LoaiThucUongDTO();
        dto.setMaLoai(entity.getMaLoai());
        dto.setTenLoaiThucUong(entity.getTenLoaiThucUong());
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity.
     */
    public LoaiThucUong toEntity() {
        LoaiThucUong entity = new LoaiThucUong();
        entity.setMaLoai(this.MaLoai);
        entity.setTenLoaiThucUong(this.TenLoaiThucUong);
        return entity;
    }
}
