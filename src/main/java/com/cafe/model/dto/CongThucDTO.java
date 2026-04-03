package com.cafe.model.dto;

import com.cafe.model.entity.CongThuc;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng CongThuc.
 * FK ThucUong được flatten thành MaThucUong và TenThucUong để tránh vòng lặp.
 */
public class CongThucDTO implements Serializable {

    private String MaCongThuc;
    private String TenCongThuc;
    private String MaThucUong;
    private String TenThucUong;

    // Constructors
    public CongThucDTO() {}

    public CongThucDTO(String MaCongThuc, String TenCongThuc, String MaThucUong, String TenThucUong) {
        this.MaCongThuc = MaCongThuc;
        this.TenCongThuc = TenCongThuc;
        this.MaThucUong = MaThucUong;
        this.TenThucUong = TenThucUong;
    }

    // Getters and Setters
    public String getMaCongThuc() {
        return MaCongThuc;
    }

    public void setMaCongThuc(String MaCongThuc) {
        this.MaCongThuc = MaCongThuc;
    }

    public String getTenCongThuc() {
        return TenCongThuc;
    }

    public void setTenCongThuc(String TenCongThuc) {
        this.TenCongThuc = TenCongThuc;
    }

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

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static CongThucDTO fromEntity(CongThuc entity) {
        if (entity == null) return null;
        CongThucDTO dto = new CongThucDTO();
        dto.setMaCongThuc(entity.getMaCongThuc());
        dto.setTenCongThuc(entity.getTenCongThuc());
        if (entity.getThucUong() != null) {
            dto.setMaThucUong(entity.getThucUong().getMaThucUong());
            dto.setTenThucUong(entity.getThucUong().getTenThucUong());
        }
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity (không set quan hệ, cần xử lý riêng).
     */
    public CongThuc toEntity() {
        CongThuc entity = new CongThuc();
        entity.setMaCongThuc(this.MaCongThuc);
        entity.setTenCongThuc(this.TenCongThuc);
        return entity;
    }
}
