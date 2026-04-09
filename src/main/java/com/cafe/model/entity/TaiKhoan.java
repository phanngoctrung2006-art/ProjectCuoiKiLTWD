package com.cafe.model.entity;

import jakarta.persistence.*;

/**
 * Lớp Entity lưu trữ thông tin đăng nhập và phân quyền của hệ thống.
 * Ánh xạ với bảng "TaiKhoan" trong CSDL.
 */
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {

    // Tên đăng nhập của người dùng (Username)
    @Id
    @Column(name = "TenDangNhap", length = 50)
    private String tenDangNhap;

    // Mật khẩu truy cập hệ thống (Password)
    @Column(name = "MatKhau", length = 255)
    private String matKhau;

    // Vai trò phân quyền (VD: ADMIN, STAFF)
    @Column(name = "Quyen", length = 50)
    private String quyen;

    // Liên kết với Nhân viên sở hữu tài khoản này
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    public TaiKhoan() {}

    public TaiKhoan(String tenDangNhap, String matKhau, String quyen, NhanVien nhanVien) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.nhanVien = nhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}
