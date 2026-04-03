package com.cafe.model.dto;

import com.cafe.model.entity.ChiTietHoaDon;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Data Transfer Object cho bảng ChiTietHoaDon.
 * Composite key (MaHoaDon, MaThucUong) được flatten trực tiếp thành các trường.
 * Bổ sung TenThucUong và Gia để hiển thị trên UI.
 */
public class ChiTietHoaDonDTO implements Serializable {

    private String MaHoaDon;
    private String MaThucUong;
    private String TenThucUong;
    private Integer SoLuong;
    private BigDecimal Gia;

    // Constructors
    public ChiTietHoaDonDTO() {}

    public ChiTietHoaDonDTO(String MaHoaDon, String MaThucUong, String TenThucUong, Integer SoLuong, BigDecimal Gia) {
        this.MaHoaDon = MaHoaDon;
        this.MaThucUong = MaThucUong;
        this.TenThucUong = TenThucUong;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
    }

    // Getters and Setters
    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
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

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static ChiTietHoaDonDTO fromEntity(ChiTietHoaDon entity) {
        if (entity == null) return null;
        ChiTietHoaDonDTO dto = new ChiTietHoaDonDTO();
        if (entity.getId() != null) {
            dto.setMaHoaDon(entity.getId().getMaHoaDon());
            dto.setMaThucUong(entity.getId().getMaThucUong());
        }
        dto.setSoLuong(entity.getSoLuong());
        if (entity.getThucUong() != null) {
            dto.setTenThucUong(entity.getThucUong().getTenThucUong());
            dto.setGia(entity.getThucUong().getGia());
        }
        return dto;
    }
}
