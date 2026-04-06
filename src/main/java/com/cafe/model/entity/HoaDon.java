package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @Column(name = "MaHoaDon", columnDefinition = "CHAR(5)")
    private String MaHoaDon;

    @Column(name = "NgayLap")
    private Date NgayLap;

    @Column(name = "TongTien", precision = 10, scale = 2)
    private BigDecimal TongTien;

    @Column(name = "GhiChu", length = 255)
    private String GhiChu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhachHang")
    private KhachHang KhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBan")
    private Ban Ban;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaThanhToan")
    private PhuongThucThanhToan PhuongThucThanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien")
    private NhanVien NhanVien;

    @OneToMany(mappedBy = "HoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> DanhSachChiTietHoaDon;

    public HoaDon() {}

    public HoaDon(String MaHoaDon, Date NgayLap, BigDecimal TongTien, KhachHang KhachHang, Ban Ban, PhuongThucThanhToan PhuongThucThanhToan, NhanVien NhanVien) {
        this.MaHoaDon = MaHoaDon;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
        this.KhachHang = KhachHang;
        this.Ban = Ban;
        this.PhuongThucThanhToan = PhuongThucThanhToan;
        this.NhanVien = NhanVien;
    }

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

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public KhachHang getKhachHang() {
        return KhachHang;
    }

    public void setKhachHang(KhachHang KhachHang) {
        this.KhachHang = KhachHang;
    }

    public Ban getBan() {
        return Ban;
    }

    public void setBan(Ban Ban) {
        this.Ban = Ban;
    }

    public PhuongThucThanhToan getPhuongThucThanhToan() {
        return PhuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(PhuongThucThanhToan PhuongThucThanhToan) {
        this.PhuongThucThanhToan = PhuongThucThanhToan;
    }

    public NhanVien getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(NhanVien NhanVien) {
        this.NhanVien = NhanVien;
    }

    public List<ChiTietHoaDon> getDanhSachChiTietHoaDon() {
        return DanhSachChiTietHoaDon;
    }

    public void setDanhSachChiTietHoaDon(List<ChiTietHoaDon> DanhSachChiTietHoaDon) {
        this.DanhSachChiTietHoaDon = DanhSachChiTietHoaDon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return java.util.Objects.equals(MaHoaDon != null ? MaHoaDon.trim() : null, 
                                        hoaDon.MaHoaDon != null ? hoaDon.MaHoaDon.trim() : null);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(MaHoaDon != null ? MaHoaDon.trim() : null);
    }
}
