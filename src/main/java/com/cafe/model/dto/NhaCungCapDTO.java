package com.cafe.model.dto;

import com.cafe.model.entity.NhaCungCap;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng NhaCungCap.
 * Chứa thông tin cơ bản của nhà cung cấp, không chứa danh sách phiếu nhập.
 */
public class NhaCungCapDTO implements Serializable {

    private String MaNhaCungCap;
    private String TenNhaCungCap;
    private String DiaChi;
    private String SoDienThoai;

    // Constructors
    public NhaCungCapDTO() {}

    public NhaCungCapDTO(String MaNhaCungCap, String TenNhaCungCap, String DiaChi, String SoDienThoai) {
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
    }

    // Getters and Setters
    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public void setMaNhaCungCap(String MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
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
    public static NhaCungCapDTO fromEntity(NhaCungCap entity) {
        if (entity == null) return null;
        NhaCungCapDTO dto = new NhaCungCapDTO();
        dto.setMaNhaCungCap(entity.getMaNhaCungCap());
        dto.setTenNhaCungCap(entity.getTenNhaCungCap());
        dto.setDiaChi(entity.getDiaChi());
        dto.setSoDienThoai(entity.getSoDienThoai());
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity.
     */
    public NhaCungCap toEntity() {
        NhaCungCap entity = new NhaCungCap();
        entity.setMaNhaCungCap(this.MaNhaCungCap);
        entity.setTenNhaCungCap(this.TenNhaCungCap);
        entity.setDiaChi(this.DiaChi);
        entity.setSoDienThoai(this.SoDienThoai);
        return entity;
    }
}
