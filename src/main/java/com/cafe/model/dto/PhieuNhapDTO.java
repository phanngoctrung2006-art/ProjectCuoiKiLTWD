package com.cafe.model.dto;

import com.cafe.model.entity.PhieuNhap;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object cho bảng PhieuNhap.
 * Các FK (NhanVien, NhaCungCap) được flatten thành mã + tên để tránh vòng lặp.
 */
public class PhieuNhapDTO implements Serializable {

    private String MaPhieuNhap;
    private Date NgayNhap;
    private BigDecimal TongTien;
    private String MaNhanVien;
    private String TenNhanVien;
    private String MaNhaCungCap;
    private String TenNhaCungCap;

    // Constructors
    public PhieuNhapDTO() {}

    public PhieuNhapDTO(String MaPhieuNhap, Date NgayNhap, BigDecimal TongTien,
                        String MaNhanVien, String TenNhanVien,
                        String MaNhaCungCap, String TenNhaCungCap) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
    }

    // Getters and Setters
    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

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

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static PhieuNhapDTO fromEntity(PhieuNhap entity) {
        if (entity == null) return null;
        PhieuNhapDTO dto = new PhieuNhapDTO();
        dto.setMaPhieuNhap(entity.getMaPhieuNhap());
        dto.setNgayNhap(entity.getNgayNhap());
        dto.setTongTien(entity.getTongTien());
        if (entity.getNhanVien() != null) {
            dto.setMaNhanVien(entity.getNhanVien().getMaNhanVien());
            dto.setTenNhanVien(entity.getNhanVien().getTenNhanVien());
        }
        if (entity.getNhaCungCap() != null) {
            dto.setMaNhaCungCap(entity.getNhaCungCap().getMaNhaCungCap());
            dto.setTenNhaCungCap(entity.getNhaCungCap().getTenNhaCungCap());
        }
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity (không set quan hệ, cần xử lý riêng).
     */
    public PhieuNhap toEntity() {
        PhieuNhap entity = new PhieuNhap();
        entity.setMaPhieuNhap(this.MaPhieuNhap);
        entity.setNgayNhap(this.NgayNhap);
        entity.setTongTien(this.TongTien);
        return entity;
    }
}
