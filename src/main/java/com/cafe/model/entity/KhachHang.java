package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Lớp Entity (Thực thể) đại diện cho bảng "KhachHang" trong CSDL.
 * Dùng để quản lý thông tin khách hàng mua hàng (tên, sdt...).
 */
@Entity
@Table(name = "KhachHang")
public class KhachHang {

    // Mã định danh khách hàng
    @Id
    @Column(name = "MaKhachHang", columnDefinition = "CHAR(5)")
    private String MaKhachHang;

    // Tên đầy đủ của khách hàng
    @Column(name = "TenKhachHang", length = 100)
    private String TenKhachHang;

    // Số điện thoại liên hệ
    @Column(name = "SoDienThoai", length = 15)
    private String SoDienThoai;

    // Lịch sử các hóa đơn mà khách hàng này đã mua
    @OneToMany(mappedBy = "KhachHang", cascade = CascadeType.ALL)
    private List<HoaDon> DanhSachHoaDon;

    public KhachHang() {}

    public KhachHang(String MaKhachHang, String TenKhachHang, String SoDienThoai) {
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.SoDienThoai = SoDienThoai;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public List<HoaDon> getDanhSachHoaDon() {
        return DanhSachHoaDon;
    }

    public void setDanhSachHoaDon(List<HoaDon> DanhSachHoaDon) {
        this.DanhSachHoaDon = DanhSachHoaDon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhachHang khachHang = (KhachHang) o;
        return java.util.Objects.equals(MaKhachHang != null ? MaKhachHang.trim() : null, 
                                        khachHang.MaKhachHang != null ? khachHang.MaKhachHang.trim() : null);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(MaKhachHang != null ? MaKhachHang.trim() : null);
    }
}
