package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {

    @Id
    @Column(name = "MaNhaCungCap", columnDefinition = "CHAR(5)")
    private String MaNhaCungCap;

    @Column(name = "TenNhaCungCap", length = 100)
    private String TenNhaCungCap;

    @Column(name = "DiaChi", length = 255)
    private String DiaChi;

    @Column(name = "SoDienThoai", length = 15)
    private String SoDienThoai;

    @OneToMany(mappedBy = "NhaCungCap", cascade = CascadeType.ALL)
    private List<PhieuNhap> DanhSachPhieuNhap;

    public NhaCungCap() {}

    public NhaCungCap(String MaNhaCungCap, String TenNhaCungCap, String DiaChi, String SoDienThoai) {
        this.MaNhaCungCap = MaNhaCungCap;
        this.TenNhaCungCap = TenNhaCungCap;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
    }

    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public void setMaNhaCungCap(String MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String TenNhaCungCap) {
        this.TenNhaCungCap = TenNhaCungCap;
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

    public List<PhieuNhap> getDanhSachPhieuNhap() {
        return DanhSachPhieuNhap;
    }

    public void setDanhSachPhieuNhap(List<PhieuNhap> DanhSachPhieuNhap) {
        this.DanhSachPhieuNhap = DanhSachPhieuNhap;
    }
}
