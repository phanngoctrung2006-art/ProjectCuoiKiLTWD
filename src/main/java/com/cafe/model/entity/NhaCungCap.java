package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Lớp Entity (Thực thể) mô tả đối tác phân phối Nguyên vật liệu.
 * Tương ứng bảng "NhaCungCap" trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {

    // Mã số nhà cung cấp
    @Id
    @Column(name = "MaNhaCungCap", columnDefinition = "CHAR(5)")
    private String MaNhaCungCap;

    // Tên công ty/đơn vị cung cấp
    @Column(name = "TenNhaCungCap", length = 100)
    private String TenNhaCungCap;

    // Địa chỉ hoạt động của nhà cung cấp
    @Column(name = "DiaChi", length = 255)
    private String DiaChi;

    // Số điện thoại liên hệ nhập hàng
    @Column(name = "SoDienThoai", length = 15)
    private String SoDienThoai;

    // Danh sách các Phiếu Nhập hàng được cung cấp bởi đơn vị này
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
