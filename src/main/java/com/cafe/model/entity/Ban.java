package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Lớp Entity (Thực thể) đại diện cho bảng "Ban" trong Cơ sở dữ liệu.
 * Dùng để quản lý thông tin các bàn phục vụ trong quán cafe.
 */
@Entity
@Table(name = "Ban")
public class Ban {

    // Mã định danh của bàn, độ dài 5 ký tự (VD: B0001)
    @Id
    @Column(name = "MaBan", columnDefinition = "CHAR(5)")
    private String MaBan;

    // Danh sách các hóa đơn liên kết với bàn này
    @OneToMany(mappedBy = "Ban", cascade = CascadeType.ALL)
    private List<HoaDon> DanhSachHoaDon;

    public Ban() {}

    public Ban(String MaBan) {
        this.MaBan = MaBan;
    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public List<HoaDon> getDanhSachHoaDon() {
        return DanhSachHoaDon;
    }

    public void setDanhSachHoaDon(List<HoaDon> DanhSachHoaDon) {
        this.DanhSachHoaDon = DanhSachHoaDon;
    }
}
