package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity map với bảng CongThuc trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "CongThuc")
public class CongThuc {

    @Id
    @Column(name = "MaCongThuc", columnDefinition = "CHAR(5)")
    private String MaCongThuc;

    @Column(name = "TenCongThuc", length = 100)
    private String TenCongThuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaThucUong")
    private ThucUong ThucUong;

    @OneToMany(mappedBy = "CongThuc", cascade = CascadeType.ALL)
    private List<ChiTietCongThuc> DanhSachChiTietCongThuc;

    // Constructors
    public CongThuc() {}

    public CongThuc(String MaCongThuc, String TenCongThuc, ThucUong ThucUong) {
        this.MaCongThuc = MaCongThuc;
        this.TenCongThuc = TenCongThuc;
        this.ThucUong = ThucUong;
    }

    // Getters and Setters
    public String getMaCongThuc() {
        return MaCongThuc;
    }

    public void setMaCongThuc(String MaCongThuc) {
        this.MaCongThuc = MaCongThuc;
    }

    public String getTenCongThuc() {
        return TenCongThuc;
    }

    public void setTenCongThuc(String TenCongThuc) {
        this.TenCongThuc = TenCongThuc;
    }

    public ThucUong getThucUong() {
        return ThucUong;
    }

    public void setThucUong(ThucUong ThucUong) {
        this.ThucUong = ThucUong;
    }

    public List<ChiTietCongThuc> getDanhSachChiTietCongThuc() {
        return DanhSachChiTietCongThuc;
    }

    public void setDanhSachChiTietCongThuc(List<ChiTietCongThuc> DanhSachChiTietCongThuc) {
        this.DanhSachChiTietCongThuc = DanhSachChiTietCongThuc;
    }
}
