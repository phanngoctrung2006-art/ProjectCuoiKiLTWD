package com.cafe.model.entity;

import jakarta.persistence.*;

/**
 * Lớp Entity đại diện cho bảng "ChiTietHoaDon".
 * Lưu trữ thông tin chi tiết các thức uống được gọi trong một hóa đơn cụ thể.
 */
@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {

    // Khóa chính phức hợp (Bao gồm MaHoaDon và MaThucUong)
    @EmbeddedId
    private ChiTietHoaDonId Id;

    // Liên kết với hóa đơn hiện tại
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaHoaDon")
    @JoinColumn(name = "MaHoaDon", columnDefinition = "CHAR(5)")
    private HoaDon HoaDon;

    // Liên kết với thực thể thức uống
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaThucUong")
    @JoinColumn(name = "MaThucUong", columnDefinition = "CHAR(5)")
    private ThucUong ThucUong;

    // Số lượng thức uống được đặt
    @Column(name = "SoLuong")
    private Integer SoLuong;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(HoaDon HoaDon, ThucUong ThucUong, Integer SoLuong) {
        this.HoaDon = HoaDon;
        this.ThucUong = ThucUong;
        this.SoLuong = SoLuong;
        this.Id = new ChiTietHoaDonId(HoaDon.getMaHoaDon(), ThucUong.getMaThucUong());
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return java.util.Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Id);
    }
}
