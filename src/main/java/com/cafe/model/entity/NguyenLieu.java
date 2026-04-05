package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

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
    private List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap;

    public NguyenLieu() {}

    public NguyenLieu(String MaNguyenLieu, String TenNguyenLieu, Integer SoLuong) {
        this.MaNguyenLieu = MaNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.SoLuong = SoLuong;
    }

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

    public List<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap() {
        return DanhSachChiTietPhieuNhap;
    }

    public void setDanhSachChiTietPhieuNhap(List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap) {
        this.DanhSachChiTietPhieuNhap = DanhSachChiTietPhieuNhap;
    }
}
