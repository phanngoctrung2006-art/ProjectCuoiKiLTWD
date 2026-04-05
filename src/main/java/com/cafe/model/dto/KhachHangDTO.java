package com.cafe.model.dto;

import com.cafe.model.entity.KhachHang;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng KhachHang.
 * Chứa thông tin cơ bản của khách hàng, không chứa danh sách hóa đơn.
 */
public class KhachHangDTO implements Serializable {

    private String MaKhachHang;
    private String TenKhachHang;
    private String SoDienThoai;

    // Constructors
    public KhachHangDTO() {}

    public KhachHangDTO(String MaKhachHang, String TenKhachHang, String SoDienThoai) {
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.SoDienThoai = SoDienThoai;
    }

    // Getters and Setters
    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static KhachHangDTO fromEntity(KhachHang entity) {
        if (entity == null) return null;
        KhachHangDTO dto = new KhachHangDTO();
        dto.setMaKhachHang(entity.getMaKhachHang());
        dto.setTenKhachHang(entity.getTenKhachHang());
        dto.setSoDienThoai(entity.getSoDienThoai());
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity.
     */
    public KhachHang toEntity() {
        KhachHang entity = new KhachHang();
        entity.setMaKhachHang(this.MaKhachHang);
        entity.setTenKhachHang(this.TenKhachHang);
        entity.setSoDienThoai(this.SoDienThoai);
        return entity;
    }
}
