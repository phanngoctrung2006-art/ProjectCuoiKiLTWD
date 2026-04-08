package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ThucUong")
public class ThucUong {

    @Id
    @Column(name = "MaThucUong", columnDefinition = "CHAR(5)")
    private String MaThucUong;

    @Column(name = "TenThucUong", length = 100)
    private String TenThucUong;

    @Column(name = "Gia", precision = 10, scale = 2)
    private BigDecimal Gia;

    @Column(name = "Url", length = 50, nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLoai")
    private LoaiThucUong LoaiThucUong;

    @OneToMany(mappedBy = "ThucUong", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> DanhSachChiTietHoaDon;

    public ThucUong() {
    }

    public ThucUong(String MaThucUong, String TenThucUong, BigDecimal Gia, LoaiThucUong LoaiThucUong, String url) {
        this.MaThucUong = MaThucUong;
        this.TenThucUong = TenThucUong;
        this.Gia = Gia;
        this.LoaiThucUong = LoaiThucUong;
        this.url = url;
    }

    public String getMaThucUong() {
        return MaThucUong;
    }

    public void setMaThucUong(String MaThucUong) {
        this.MaThucUong = MaThucUong;
    }

    public String getTenThucUong() {
        return TenThucUong;
    }

    public void setTenThucUong(String TenThucUong) {
        this.TenThucUong = TenThucUong;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LoaiThucUong getLoaiThucUong() {
        return LoaiThucUong;
    }

    public void setLoaiThucUong(LoaiThucUong LoaiThucUong) {
        this.LoaiThucUong = LoaiThucUong;
    }

    public List<ChiTietHoaDon> getDanhSachChiTietHoaDon() {
        return DanhSachChiTietHoaDon;
    }

    public void setDanhSachChiTietHoaDon(List<ChiTietHoaDon> DanhSachChiTietHoaDon) {
        this.DanhSachChiTietHoaDon = DanhSachChiTietHoaDon;
    }
}
