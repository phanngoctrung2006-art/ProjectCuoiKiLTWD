package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @Column(name = "MaNhanVien", columnDefinition = "CHAR(5)")
    private String MaNhanVien;

    @Column(name = "TenNhanVien", length = 100)
    private String TenNhanVien;

    @Column(name = "NgaySinh")
    private Date NgaySinh;

    @Column(name = "DiaChi", length = 255)
    private String DiaChi;

    @Column(name = "SoDienThoai", length = 15)
    private String SoDienThoai;

    @Column(name = "Luong", precision = 10, scale = 2)
    private BigDecimal Luong;

    @OneToMany(mappedBy = "NhanVien", cascade = CascadeType.ALL)
    private List<HoaDon> DanhSachHoaDon;

    @OneToMany(mappedBy = "NhanVien", cascade = CascadeType.ALL)
    private List<PhieuNhap> DanhSachPhieuNhap;

    public NhanVien() {}

    public NhanVien(String MaNhanVien, String TenNhanVien, Date NgaySinh, String DiaChi, String SoDienThoai, BigDecimal Luong) {
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.Luong = Luong;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public BigDecimal getLuong() {
        return Luong;
    }

    public void setLuong(BigDecimal Luong) {
        this.Luong = Luong;
    }

    public List<HoaDon> getDanhSachHoaDon() {
        return DanhSachHoaDon;
    }

    public void setDanhSachHoaDon(List<HoaDon> DanhSachHoaDon) {
        this.DanhSachHoaDon = DanhSachHoaDon;
    }

    public List<PhieuNhap> getDanhSachPhieuNhap() {
        return DanhSachPhieuNhap;
    }

    public void setDanhSachPhieuNhap(List<PhieuNhap> DanhSachPhieuNhap) {
        this.DanhSachPhieuNhap = DanhSachPhieuNhap;
    }
}
