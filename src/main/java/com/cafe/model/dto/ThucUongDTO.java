package com.cafe.model.dto;

import com.cafe.model.entity.ThucUong;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Data Transfer Object cho bảng ThucUong.
 * FK LoaiThucUong được flatten thành MaLoai và TenLoaiThucUong để tránh vòng lặp.
 */
public class ThucUongDTO implements Serializable {

    private String MaThucUong;
    private String TenThucUong;
    private BigDecimal Gia;
    private String MaLoai;
    private String TenLoaiThucUong;

    // Constructors
    public ThucUongDTO() {}

    public ThucUongDTO(String MaThucUong, String TenThucUong, BigDecimal Gia, String MaLoai, String TenLoaiThucUong) {
        this.MaThucUong = MaThucUong;
        this.TenThucUong = TenThucUong;
        this.Gia = Gia;
        this.MaLoai = MaLoai;
        this.TenLoaiThucUong = TenLoaiThucUong;
    }

    // Getters and Setters
    public String getMaThucUong() {
        return MaThucUong;
    }

    public void setMaThucUong(String MaThucUong) {
        this.MaThucUong = MaThucUong;
    }

    public String getTenThucUong() {
        return TenThucUong;
    }

    public void setTenThucUong(String TenThucUong) {
        this.TenThucUong = TenThucUong;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }

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
    public static ThucUongDTO fromEntity(ThucUong entity) {
        if (entity == null) return null;
        ThucUongDTO dto = new ThucUongDTO();
        dto.setMaThucUong(entity.getMaThucUong());
        dto.setTenThucUong(entity.getTenThucUong());
        dto.setGia(entity.getGia());
        if (entity.getLoaiThucUong() != null) {
            dto.setMaLoai(entity.getLoaiThucUong().getMaLoai());
            dto.setTenLoaiThucUong(entity.getLoaiThucUong().getTenLoaiThucUong());
        }
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity (không set quan hệ, cần xử lý riêng).
     */
    public ThucUong toEntity() {
        ThucUong entity = new ThucUong();
        entity.setMaThucUong(this.MaThucUong);
        entity.setTenThucUong(this.TenThucUong);
        entity.setGia(this.Gia);
        return entity;
    }
}
