package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Lớp Entity (Thực thể) đại diện cho bảng "NhanVien" trong CSDL.
 * Dùng để quản lý thông tin nhân sự làm việc trong quán (Họ tên, mức lương, SĐT...).
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien {

    // Mã định danh của nhân viên
    @Id
    @Column(name = "MaNhanVien", columnDefinition = "CHAR(5)")
    private String MaNhanVien;

    // Tên đầy đủ
    @Column(name = "TenNhanVien", length = 100)
    private String TenNhanVien;

    // Ngày sinh
    @Column(name = "NgaySinh")
    private Date NgaySinh;

    // Địa chỉ thường trú
    @Column(name = "DiaChi", length = 255)
    private String DiaChi;

    // Số điện thoại liên lạc
    @Column(name = "SoDienThoai", length = 15)
    private String SoDienThoai;

    // Mức lương cơ bản
    @Column(name = "Luong", precision = 10, scale = 2)
    private BigDecimal Luong;

    // Các hóa đơn do nhân viên này lập
    @OneToMany(mappedBy = "NhanVien", cascade = CascadeType.ALL)
    private List<HoaDon> DanhSachHoaDon;

    // Lịch sử các phiếu nhập hàng phần mềm do NV này thực hiện
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
