package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Entity map với bảng PhieuNhap trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "PhieuNhap")
public class PhieuNhap {

    @Id
    @Column(name = "MaPhieuNhap", columnDefinition = "CHAR(5)")
    private String MaPhieuNhap;

    @Column(name = "NgayNhap")
    private Date NgayNhap;

    @Column(name = "TongTien", precision = 10, scale = 2)
    private BigDecimal TongTien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien")
    private NhanVien NhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhaCungCap")
    private NhaCungCap NhaCungCap;

    @OneToMany(mappedBy = "PhieuNhap", cascade = CascadeType.ALL)
    private List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap;

    // Constructors
    public PhieuNhap() {}

    public PhieuNhap(String MaPhieuNhap, Date NgayNhap, BigDecimal TongTien, NhanVien NhanVien, NhaCungCap NhaCungCap) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
        this.NhanVien = NhanVien;
        this.NhaCungCap = NhaCungCap;
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

    public NhanVien getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(NhanVien NhanVien) {
        this.NhanVien = NhanVien;
    }

    public NhaCungCap getNhaCungCap() {
        return NhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap NhaCungCap) {
        this.NhaCungCap = NhaCungCap;
    }

    public List<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap() {
        return DanhSachChiTietPhieuNhap;
    }

    public void setDanhSachChiTietPhieuNhap(List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap) {
        this.DanhSachChiTietPhieuNhap = DanhSachChiTietPhieuNhap;
    }
}
