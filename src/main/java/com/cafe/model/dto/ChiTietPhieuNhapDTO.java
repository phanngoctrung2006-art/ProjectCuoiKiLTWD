package com.cafe.model.dto;

import com.cafe.model.entity.ChiTietPhieuNhap;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Data Transfer Object cho bảng ChiTietPhieuNhap.
 * Composite key (MaPhieuNhap, MaNguyenLieu) được flatten trực tiếp thành các trường.
 */
public class ChiTietPhieuNhapDTO implements Serializable {

    private String MaPhieuNhap;
    private String MaNguyenLieu;
    private String TenNguyenLieu;
    private Integer SoLuong;
    private BigDecimal Gia;

    public ChiTietPhieuNhapDTO() {}

    public ChiTietPhieuNhapDTO(String MaPhieuNhap, String MaNguyenLieu, String TenNguyenLieu, Integer SoLuong, BigDecimal Gia) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaNguyenLieu = MaNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
    }

    public String getMaPhieuNhap() { return MaPhieuNhap; }
    public void setMaPhieuNhap(String MaPhieuNhap) { this.MaPhieuNhap = MaPhieuNhap; }

    public String getMaNguyenLieu() { return MaNguyenLieu; }
    public void setMaNguyenLieu(String MaNguyenLieu) { this.MaNguyenLieu = MaNguyenLieu; }

    public String getTenNguyenLieu() { return TenNguyenLieu; }
    public void setTenNguyenLieu(String TenNguyenLieu) { this.TenNguyenLieu = TenNguyenLieu; }

    public Integer getSoLuong() { return SoLuong; }
    public void setSoLuong(Integer SoLuong) { this.SoLuong = SoLuong; }

    public BigDecimal getGia() { return Gia; }
    public void setGia(BigDecimal Gia) { this.Gia = Gia; }

    public static ChiTietPhieuNhapDTO fromEntity(ChiTietPhieuNhap entity) {
        if (entity == null) return null;
        ChiTietPhieuNhapDTO dto = new ChiTietPhieuNhapDTO();
        if (entity.getId() != null) {
            dto.setMaPhieuNhap(entity.getId().getMaPhieuNhap());
            dto.setMaNguyenLieu(entity.getId().getMaNguyenLieu());
        }
        dto.setSoLuong(entity.getSoLuong());
        dto.setGia(entity.getGia());
        if (entity.getNguyenLieu() != null) {
            dto.setTenNguyenLieu(entity.getNguyenLieu().getTenNguyenLieu());
        }
        return dto;
    }
}
