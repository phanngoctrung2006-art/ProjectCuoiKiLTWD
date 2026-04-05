package com.cafe.model.dto;

import com.cafe.model.entity.Ban;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng Ban.
 * Chứa thông tin cơ bản của bàn, tránh vòng lặp vô hạn khi serialize.
 */
public class BanDTO implements Serializable {

    private String MaBan;

    // Constructors
    public BanDTO() {}

    public BanDTO(String MaBan) {
        this.MaBan = MaBan;
    }

    // Getters and Setters
    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static BanDTO fromEntity(Ban entity) {
        if (entity == null) return null;
        BanDTO dto = new BanDTO();
        dto.setMaBan(entity.getMaBan());
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity.
     */
    public Ban toEntity() {
        Ban entity = new Ban();
        entity.setMaBan(this.MaBan);
        return entity;
    }
}
