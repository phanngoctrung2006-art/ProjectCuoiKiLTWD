package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Lớp Entity đại diện cho bảng "ThucUong" trong CSDL.
 * Quản lý thông tin các món nước (tên, giá cả, url hình ảnh...).
 */
@Entity
@Table(name = "ThucUong")
public class ThucUong {

    // Mã định danh món thức uống
    @Id
    @Column(name = "MaThucUong", columnDefinition = "CHAR(5)")
    private String MaThucUong;

    // Tên món thức uống
    @Column(name = "TenThucUong", length = 100)
    private String TenThucUong;

    // Giá bán của thức uống
    @Column(name = "Gia", precision = 10, scale = 2)
    private BigDecimal Gia;

    // Đường dẫn/Tên file hình ảnh hiển thị của món
    @Column(name = "Url", length = 50, nullable = false)
    private String url;

    // Phân loại nhóm thức uống (VD: Trà, Cà phê)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLoai")
    private LoaiThucUong LoaiThucUong;

    // Lịch sử các chi tiết hóa đơn chứa thức uống này
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
