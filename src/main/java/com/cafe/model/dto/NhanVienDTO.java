package com.cafe.model.dto;

import com.cafe.model.entity.NhanVien;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object cho bảng NhanVien.
 * Chứa thông tin cơ bản của nhân viên, không chứa danh sách hóa đơn/phiếu nhập.
 */
public class NhanVienDTO implements Serializable {

    private String MaNhanVien;
    private String TenNhanVien;
    private Date NgaySinh;
    private String DiaChi;
    private String SoDienThoai;
    private BigDecimal Luong;

    // Constructors
    public NhanVienDTO() {}

    public NhanVienDTO(String MaNhanVien, String TenNhanVien, Date NgaySinh, String DiaChi, String SoDienThoai, BigDecimal Luong) {
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.Luong = Luong;
    }

    // Getters and Setters
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

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
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

    public BigDecimal getLuong() {
        return Luong;
    }

    public void setLuong(BigDecimal Luong) {
        this.Luong = Luong;
    }

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static NhanVienDTO fromEntity(NhanVien entity) {
        if (entity == null) return null;
        NhanVienDTO dto = new NhanVienDTO();
        dto.setMaNhanVien(entity.getMaNhanVien());
        dto.setTenNhanVien(entity.getTenNhanVien());
        dto.setNgaySinh(entity.getNgaySinh());
        dto.setDiaChi(entity.getDiaChi());
        dto.setSoDienThoai(entity.getSoDienThoai());
        dto.setLuong(entity.getLuong());
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity.
     */
    public NhanVien toEntity() {
        NhanVien entity = new NhanVien();
        entity.setMaNhanVien(this.MaNhanVien);
        entity.setTenNhanVien(this.TenNhanVien);
        entity.setNgaySinh(this.NgaySinh);
        entity.setDiaChi(this.DiaChi);
        entity.setSoDienThoai(this.SoDienThoai);
        entity.setLuong(this.Luong);
        return entity;
    }
}
