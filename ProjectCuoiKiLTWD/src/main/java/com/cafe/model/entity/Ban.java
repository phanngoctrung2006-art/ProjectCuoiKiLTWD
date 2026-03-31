package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity map với bảng Ban trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "Ban")
public class Ban {

    @Id
    @Column(name = "MaBan", columnDefinition = "CHAR(5)")
    private String MaBan;

    @OneToMany(mappedBy = "Ban", cascade = CascadeType.ALL)
    private List<HoaDon> DanhSachHoaDon;

    // Constructors
    public Ban() {}

    public Ban(String MaBan) {
        this.MaBan = MaBan;
    }

    // Getters and Setters
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
