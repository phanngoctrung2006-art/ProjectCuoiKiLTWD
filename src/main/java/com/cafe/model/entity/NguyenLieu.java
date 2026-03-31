package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity map với bảng NguyenLieu trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "NguyenLieu")
public class NguyenLieu {

    @Id
    @Column(name = "MaNguyenLieu", columnDefinition = "CHAR(5)")
    private String MaNguyenLieu;

    @Column(name = "TenNguyenLieu", length = 100)
    private String TenNguyenLieu;

    @Column(name = "SoLuong")
    private Integer SoLuong;

    @OneToMany(mappedBy = "NguyenLieu", cascade = CascadeType.ALL)
    private List<ChiTietCongThuc> DanhSachChiTietCongThuc;

    @OneToMany(mappedBy = "NguyenLieu", cascade = CascadeType.ALL)
    private List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap;

    // Constructors
    public NguyenLieu() {}

    public NguyenLieu(String MaNguyenLieu, String TenNguyenLieu, Integer SoLuong) {
        this.MaNguyenLieu = MaNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.SoLuong = SoLuong;
    }

    // Getters and Setters
    public String getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public void setMaNguyenLieu(String MaNguyenLieu) {
        this.MaNguyenLieu = MaNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public void setTenNguyenLieu(String TenNguyenLieu) {
        this.TenNguyenLieu = TenNguyenLieu;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public List<ChiTietCongThuc> getDanhSachChiTietCongThuc() {
        return DanhSachChiTietCongThuc;
    }

    public void setDanhSachChiTietCongThuc(List<ChiTietCongThuc> DanhSachChiTietCongThuc) {
        this.DanhSachChiTietCongThuc = DanhSachChiTietCongThuc;
    }

    public List<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap() {
        return DanhSachChiTietPhieuNhap;
    }

    public void setDanhSachChiTietPhieuNhap(List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap) {
        this.DanhSachChiTietPhieuNhap = DanhSachChiTietPhieuNhap;
    }
}
