package com.cafe.model.dto;

import com.cafe.model.entity.HoaDon;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object cho bảng HoaDon.
 * Các FK (KhachHang, Ban, PhuongThucThanhToan, NhanVien) được flatten thành mã + tên
 * để tránh vòng lặp và dễ hiển thị trên UI.
 */
public class HoaDonDTO implements Serializable {

    private String MaHoaDon;
    private Date NgayLap;
    private BigDecimal TongTien;
    private String MaKhachHang;
    private String TenKhachHang;
    private String MaBan;
    private String MaThanhToan;
    private String TenThanhToan;
    private String MaNhanVien;
    private String TenNhanVien;

    // Constructors
    public HoaDonDTO() {}

    public HoaDonDTO(String MaHoaDon, Date NgayLap, BigDecimal TongTien,
                     String MaKhachHang, String TenKhachHang,
                     String MaBan,
                     String MaThanhToan, String TenThanhToan,
                     String MaNhanVien, String TenNhanVien) {
        this.MaHoaDon = MaHoaDon;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.MaBan = MaBan;
        this.MaThanhToan = MaThanhToan;
        this.TenThanhToan = TenThanhToan;
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
    }

    // Getters and Setters
    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

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

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public String getMaThanhToan() {
        return MaThanhToan;
    }

    public void setMaThanhToan(String MaThanhToan) {
        this.MaThanhToan = MaThanhToan;
    }

    public String getTenThanhToan() {
        return TenThanhToan;
    }

    public void setTenThanhToan(String TenThanhToan) {
        this.TenThanhToan = TenThanhToan;
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

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static HoaDonDTO fromEntity(HoaDon entity) {
        if (entity == null) return null;
        HoaDonDTO dto = new HoaDonDTO();
        dto.setMaHoaDon(entity.getMaHoaDon());
        dto.setNgayLap(entity.getNgayLap());
        dto.setTongTien(entity.getTongTien());
        if (entity.getKhachHang() != null) {
            dto.setMaKhachHang(entity.getKhachHang().getMaKhachHang());
            dto.setTenKhachHang(entity.getKhachHang().getTenKhachHang());
        }
        if (entity.getBan() != null) {
            dto.setMaBan(entity.getBan().getMaBan());
        }
        if (entity.getPhuongThucThanhToan() != null) {
            dto.setMaThanhToan(entity.getPhuongThucThanhToan().getMaThanhToan());
            dto.setTenThanhToan(entity.getPhuongThucThanhToan().getTenThanhToan());
        }
        if (entity.getNhanVien() != null) {
            dto.setMaNhanVien(entity.getNhanVien().getMaNhanVien());
            dto.setTenNhanVien(entity.getNhanVien().getTenNhanVien());
        }
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity (không set quan hệ, cần xử lý riêng).
     */
    public HoaDon toEntity() {
        HoaDon entity = new HoaDon();
        entity.setMaHoaDon(this.MaHoaDon);
        entity.setNgayLap(this.NgayLap);
        entity.setTongTien(this.TongTien);
        return entity;
    }
}
