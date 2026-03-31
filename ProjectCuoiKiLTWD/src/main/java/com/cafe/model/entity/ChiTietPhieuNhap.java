package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entity map với bảng ChiTietPhieuNhap trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "ChiTietPhieuNhap")
public class ChiTietPhieuNhap {

    @EmbeddedId
    private ChiTietPhieuNhapId Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaPhieuNhap")
    @JoinColumn(name = "MaPhieuNhap")
    private PhieuNhap PhieuNhap;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaNguyenLieu")
    @JoinColumn(name = "MaNguyenLieu")
    private NguyenLieu NguyenLieu;

    @Column(name = "SoLuong")
    private Integer SoLuong;

    @Column(name = "Gia", precision = 10, scale = 2)
    private BigDecimal Gia;

    // Constructors
    public ChiTietPhieuNhap() {}

    public ChiTietPhieuNhap(PhieuNhap PhieuNhap, NguyenLieu NguyenLieu, Integer SoLuong, BigDecimal Gia) {
        this.PhieuNhap = PhieuNhap;
        this.NguyenLieu = NguyenLieu;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.Id = new ChiTietPhieuNhapId(PhieuNhap.getMaPhieuNhap(), NguyenLieu.getMaNguyenLieu());
    }

    // Getters and Setters
    public ChiTietPhieuNhapId getId() {
        return Id;
    }

    public void setId(ChiTietPhieuNhapId Id) {
        this.Id = Id;
    }

    public PhieuNhap getPhieuNhap() {
        return PhieuNhap;
    }

    public void setPhieuNhap(PhieuNhap PhieuNhap) {
        this.PhieuNhap = PhieuNhap;
    }

    public NguyenLieu getNguyenLieu() {
        return NguyenLieu;
    }

    public void setNguyenLieu(NguyenLieu NguyenLieu) {
        this.NguyenLieu = NguyenLieu;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }
}
