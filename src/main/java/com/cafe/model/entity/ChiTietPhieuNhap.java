package com.cafe.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Lớp Entity (Thực thể) mô tả chi tiết danh mục hàng hóa được nhập trong một Phiếu Nhập.
 * Ánh xạ với bảng "ChiTietPhieuNhap" chứa thông tin số lượng, giá tiền từng loại linh kiện/vật tư.
 */
@Entity
@Table(name = "ChiTietPhieuNhap")
public class ChiTietPhieuNhap {

    // Khóa chính phức hợp (Gồm mã Phiếu nhập và mã Nguyên liệu)
    @EmbeddedId
    private ChiTietPhieuNhapId Id;

    // Phiếu nhập trỏ chiếu đến chi tiết này
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaPhieuNhap")
    @JoinColumn(name = "MaPhieuNhap", columnDefinition = "CHAR(5)")
    private PhieuNhap PhieuNhap;

    // Nguyên vật liệu được nhập trong lô này
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("MaNguyenLieu")
    @JoinColumn(name = "MaNguyenLieu", columnDefinition = "CHAR(5)")
    private NguyenLieu NguyenLieu;

    // Số lượng mặt hàng thực tế nhập vào kho
    @Column(name = "SoLuong")
    private Integer SoLuong;

    // Đơn giá nhập của mặt hàng
    @Column(name = "Gia", precision = 10, scale = 2)
    private BigDecimal Gia;

    public ChiTietPhieuNhap() {}

    public ChiTietPhieuNhap(PhieuNhap PhieuNhap, NguyenLieu NguyenLieu, Integer SoLuong, BigDecimal Gia) {
        this.PhieuNhap = PhieuNhap;
        this.NguyenLieu = NguyenLieu;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.Id = new ChiTietPhieuNhapId(PhieuNhap.getMaPhieuNhap(), NguyenLieu.getMaNguyenLieu());
    }

    public ChiTietPhieuNhapId getId() {
        return Id;
    }

    public void setId(ChiTietPhieuNhapId Id) {
        this.Id = Id;
    }

    public PhieuNhap getPhieuNhap() {
        return PhieuNhap;
    }

    public void setPhieuNhap(PhieuNhap PhieuNhap) {
        this.PhieuNhap = PhieuNhap;
    }

    public NguyenLieu getNguyenLieu() {
        return NguyenLieu;
    }

    public void setNguyenLieu(NguyenLieu NguyenLieu) {
        this.NguyenLieu = NguyenLieu;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }
}
