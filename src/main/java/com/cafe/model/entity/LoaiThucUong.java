package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Lớp Entity đại diện cho danh mục nhóm thức uống (VD: Cafe, Trà Sữa, Nước Ép...).
 * Ánh xạ với bảng "LoaiThucUong" trong CSDL.
 */
@Entity
@Table(name = "LoaiThucUong")
public class LoaiThucUong {

    // Mã loại nhóm thức uống (VD: L0001)
    @Id
    @Column(name = "MaLoai", columnDefinition = "CHAR(5)")
    private String MaLoai;

    // Tên hiển thị loại thức uống
    @Column(name = "TenLoaiThucUong", length = 100)
    private String TenLoaiThucUong;

    // Danh sách thức uống thuộc danh mục này
    @OneToMany(mappedBy = "LoaiThucUong", cascade = CascadeType.ALL)
    private List<ThucUong> DanhSachThucUong;

    public LoaiThucUong() {
    }

    public LoaiThucUong(String MaLoai, String TenLoaiThucUong) {
        this.MaLoai = MaLoai;
        this.TenLoaiThucUong = TenLoaiThucUong;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoaiThucUong() {
        return TenLoaiThucUong;
    }

    public void setTenLoaiThucUong(String TenLoaiThucUong) {
        this.TenLoaiThucUong = TenLoaiThucUong;
    }

    public List<ThucUong> getDanhSachThucUong() {
        return DanhSachThucUong;
    }

    public void setDanhSachThucUong(List<ThucUong> DanhSachThucUong) {
        this.DanhSachThucUong = DanhSachThucUong;
    }

    @Override
    public String toString() {
        return this.TenLoaiThucUong;
    }
}
