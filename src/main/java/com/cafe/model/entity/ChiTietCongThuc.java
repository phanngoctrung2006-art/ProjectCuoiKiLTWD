package com.cafe.model.entity;

import jakarta.persistence.*;

/**
 * Entity map với bảng ChiTietCongThuc trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "ChiTietCongThuc")
public class ChiTietCongThuc {

    @EmbeddedId
    private ChiTietCongThucId Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaCongThuc")
    @JoinColumn(name = "MaCongThuc")
    private CongThuc CongThuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaNguyenLieu")
    @JoinColumn(name = "MaNguyenLieu")
    private NguyenLieu NguyenLieu;

    @Column(name = "SoLuong")
    private Integer SoLuong;

    // Constructors
    public ChiTietCongThuc() {}

    public ChiTietCongThuc(CongThuc CongThuc, NguyenLieu NguyenLieu, Integer SoLuong) {
        this.CongThuc = CongThuc;
        this.NguyenLieu = NguyenLieu;
        this.SoLuong = SoLuong;
        this.Id = new ChiTietCongThucId(CongThuc.getMaCongThuc(), NguyenLieu.getMaNguyenLieu());
    }

    // Getters and Setters
    public ChiTietCongThucId getId() {
        return Id;
    }

    public void setId(ChiTietCongThucId Id) {
        this.Id = Id;
    }

    public CongThuc getCongThuc() {
        return CongThuc;
    }

    public void setCongThuc(CongThuc CongThuc) {
        this.CongThuc = CongThuc;
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
}
