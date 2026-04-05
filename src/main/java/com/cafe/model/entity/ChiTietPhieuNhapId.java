package com.cafe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietPhieuNhapId implements Serializable {

    @Column(name = "MaPhieuNhap", columnDefinition = "CHAR(5)")
    private String MaPhieuNhap;

    @Column(name = "MaNguyenLieu", columnDefinition = "CHAR(5)")
    private String MaNguyenLieu;

    public ChiTietPhieuNhapId() {}

    public ChiTietPhieuNhapId(String MaPhieuNhap, String MaNguyenLieu) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaNguyenLieu = MaNguyenLieu;
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public String getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public void setMaNguyenLieu(String MaNguyenLieu) {
        this.MaNguyenLieu = MaNguyenLieu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietPhieuNhapId that = (ChiTietPhieuNhapId) o;
        return Objects.equals(MaPhieuNhap, that.MaPhieuNhap) &&
               Objects.equals(MaNguyenLieu, that.MaNguyenLieu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaPhieuNhap, MaNguyenLieu);
    }
}
