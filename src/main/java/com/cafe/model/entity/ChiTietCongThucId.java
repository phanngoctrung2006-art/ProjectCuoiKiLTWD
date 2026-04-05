package com.cafe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Khóa chính phức hợp cho bảng ChiTietCongThuc.
 */
@Embeddable
public class ChiTietCongThucId implements Serializable {

    @Column(name = "MaCongThuc", columnDefinition = "CHAR(5)")
    private String MaCongThuc;

    @Column(name = "MaNguyenLieu", columnDefinition = "CHAR(5)")
    private String MaNguyenLieu;

    public ChiTietCongThucId() {}

    public ChiTietCongThucId(String MaCongThuc, String MaNguyenLieu) {
        this.MaCongThuc = MaCongThuc;
        this.MaNguyenLieu = MaNguyenLieu;
    }

    // Getters and Setters
    public String getMaCongThuc() {
        return MaCongThuc;
    }

    public void setMaCongThuc(String MaCongThuc) {
        this.MaCongThuc = MaCongThuc;
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
        ChiTietCongThucId that = (ChiTietCongThucId) o;
        return Objects.equals(MaCongThuc, that.MaCongThuc) &&
               Objects.equals(MaNguyenLieu, that.MaNguyenLieu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaCongThuc, MaNguyenLieu);
    }
}
