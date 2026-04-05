package com.cafe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietHoaDonId implements Serializable {

    @Column(name = "MaHoaDon", columnDefinition = "CHAR(5)")
    private String MaHoaDon;

    @Column(name = "MaThucUong", columnDefinition = "CHAR(5)")
    private String MaThucUong;

    public ChiTietHoaDonId() {}

    public ChiTietHoaDonId(String MaHoaDon, String MaThucUong) {
        this.MaHoaDon = MaHoaDon;
        this.MaThucUong = MaThucUong;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaThucUong() {
        return MaThucUong;
    }

    public void setMaThucUong(String MaThucUong) {
        this.MaThucUong = MaThucUong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDonId that = (ChiTietHoaDonId) o;
        return Objects.equals(MaHoaDon, that.MaHoaDon) &&
               Objects.equals(MaThucUong, that.MaThucUong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaHoaDon, MaThucUong);
    }
}
