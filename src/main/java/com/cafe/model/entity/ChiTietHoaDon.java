package com.cafe.model.entity;

import jakarta.persistence.*;

/**
 * Entity map với bảng ChiTietHoaDon trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {

    @EmbeddedId
    private ChiTietHoaDonId Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaHoaDon")
    @JoinColumn(name = "MaHoaDon", columnDefinition = "CHAR(5)")
    private HoaDon HoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaThucUong")
    @JoinColumn(name = "MaThucUong", columnDefinition = "CHAR(5)")
    private ThucUong ThucUong;

    @Column(name = "SoLuong")
    private Integer SoLuong;

    // Constructors
    public ChiTietHoaDon() {}

    public ChiTietHoaDon(HoaDon HoaDon, ThucUong ThucUong, Integer SoLuong) {
        this.HoaDon = HoaDon;
        this.ThucUong = ThucUong;
        this.SoLuong = SoLuong;
        this.Id = new ChiTietHoaDonId(HoaDon.getMaHoaDon(), ThucUong.getMaThucUong());
    }

    // Getters and Setters
    public ChiTietHoaDonId getId() {
        return Id;
    }

    public void setId(ChiTietHoaDonId Id) {
        this.Id = Id;
    }

    public HoaDon getHoaDon() {
        return HoaDon;
    }

    public void setHoaDon(HoaDon HoaDon) {
        this.HoaDon = HoaDon;
    }

    public ThucUong getThucUong() {
        return ThucUong;
    }

    public void setThucUong(ThucUong ThucUong) {
        this.ThucUong = ThucUong;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }
}
