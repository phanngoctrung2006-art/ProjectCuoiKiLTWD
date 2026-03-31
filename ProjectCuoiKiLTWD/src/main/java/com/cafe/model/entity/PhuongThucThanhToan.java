package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity map với bảng PhuongThucThanhToan trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "PhuongThucThanhToan")
public class PhuongThucThanhToan {

    @Id
    @Column(name = "MaThanhToan", columnDefinition = "CHAR(5)")
    private String MaThanhToan;

    @Column(name = "TenThanhToan", length = 100)
    private String TenThanhToan;

    @OneToMany(mappedBy = "PhuongThucThanhToan", cascade = CascadeType.ALL)
    private List<HoaDon> DanhSachHoaDon;

    // Constructors
    public PhuongThucThanhToan() {}

    public PhuongThucThanhToan(String MaThanhToan, String TenThanhToan) {
        this.MaThanhToan = MaThanhToan;
        this.TenThanhToan = TenThanhToan;
    }

    // Getters and Setters
    public String getMaThanhToan() {
        return MaThanhToan;
    }

    public void setMaThanhToan(String MaThanhToan) {
        this.MaThanhToan = MaThanhToan;
    }

    public String getTenThanhToan() {
        return TenThanhToan;
    }

    public void setTenThanhToan(String TenThanhToan) {
        this.TenThanhToan = TenThanhToan;
    }

    public List<HoaDon> getDanhSachHoaDon() {
        return DanhSachHoaDon;
    }

    public void setDanhSachHoaDon(List<HoaDon> DanhSachHoaDon) {
        this.DanhSachHoaDon = DanhSachHoaDon;
    }
}
