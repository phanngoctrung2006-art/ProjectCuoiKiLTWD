package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Lớp Entity đại diện cho bảng "NguyenLieu".
 * Theo dõi số lượng kho hoặc các nguyên vật liệu cần thiết để pha chế.
 */
@Entity
@Table(name = "NguyenLieu")
public class NguyenLieu {

    // Mã định danh nguyên liệu
    @Id
    @Column(name = "MaNguyenLieu", columnDefinition = "CHAR(5)")
    private String MaNguyenLieu;

    // Tên của nguyên liệu (VD: Đường, Sữa, Cà phê...)
    @Column(name = "TenNguyenLieu", length = 100)
    private String TenNguyenLieu;

    // Số lượng tồn kho hiện tại
    @Column(name = "SoLuong")
    private Integer SoLuong;

    // Lịch sử nhập nguyên liệu này vào kho
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
