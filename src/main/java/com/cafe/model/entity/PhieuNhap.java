package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Lớp Entity chứa thông tin của một lần nhập hàng (Vật tư, Nguyên liệu) từ Nhà Cung Cấp.
 * Ánh xạ với bảng "PhieuNhap" trong Database.
 */
@Entity
@Table(name = "PhieuNhap")
public class PhieuNhap {

    // Mã số hệ thống của đợt nhập hàng này
    @Id
    @Column(name = "MaPhieuNhap", columnDefinition = "CHAR(5)")
    private String MaPhieuNhap;

    // Ngày thực hiện tạo phiếu nhập vào kho
    @Column(name = "NgayNhap")
    private Date NgayNhap;

    // Tổng số chi phí phải trả cho phiếu nhập này
    @Column(name = "TongTien", precision = 10, scale = 2)
    private BigDecimal TongTien;

    // Thông tin nhân viên phụ trách nhận/lấy hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien")
    private NhanVien NhanVien;

    // Thông tin công ty/đơn vị cung cấp lô hàng này
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhaCungCap")
    private NhaCungCap NhaCungCap;

    // Chi tiết số lượng và giá từng loại mặt hàng được nhập trong phiếu
    @OneToMany(mappedBy = "PhieuNhap", cascade = CascadeType.ALL)
    private List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap;

    public PhieuNhap() {}

    public PhieuNhap(String MaPhieuNhap, Date NgayNhap, BigDecimal TongTien, NhanVien NhanVien, NhaCungCap NhaCungCap) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
        this.NhanVien = NhanVien;
        this.NhaCungCap = NhaCungCap;
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    public NhanVien getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(NhanVien NhanVien) {
        this.NhanVien = NhanVien;
    }

    public NhaCungCap getNhaCungCap() {
        return NhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap NhaCungCap) {
        this.NhaCungCap = NhaCungCap;
    }

    public List<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap() {
        return DanhSachChiTietPhieuNhap;
    }

    public void setDanhSachChiTietPhieuNhap(List<ChiTietPhieuNhap> DanhSachChiTietPhieuNhap) {
        this.DanhSachChiTietPhieuNhap = DanhSachChiTietPhieuNhap;
    }
}
