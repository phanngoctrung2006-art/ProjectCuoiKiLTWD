package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "LoaiThucUong")
public class LoaiThucUong {

    @Id
    @Column(name = "MaLoai", columnDefinition = "CHAR(5)")
    private String MaLoai;

    @Column(name = "TenLoaiThucUong", length = 100)
    private String TenLoaiThucUong;

    @OneToMany(mappedBy = "LoaiThucUong", cascade = CascadeType.ALL)
    private List<ThucUong> DanhSachThucUong;

    public LoaiThucUong() {}

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
}
