package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @Column(name = "MaKhachHang", columnDefinition = "CHAR(5)")
    private String MaKhachHang;

    @Column(name = "TenKhachHang", length = 100)
    private String TenKhachHang;

    @Column(name = "SoDienThoai", length = 15)
    private String SoDienThoai;

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
}
