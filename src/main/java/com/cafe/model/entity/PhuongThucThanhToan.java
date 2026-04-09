package com.cafe.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Lớp Entity mô tả các Hình thức / Phương thức thanh toán của cửa hàng.
 * Ví dụ: Tiền mặt, Chuyển khoản ngân hàng, Quẹt thẻ...
 */
@Entity
@Table(name = "PhuongThucThanhToan")
public class PhuongThucThanhToan {

    // Mã định danh phương thức thanh toán
    @Id
    @Column(name = "MaThanhToan", columnDefinition = "CHAR(5)")
    private String MaThanhToan;

    // Tên hiển thị loại hình thanh toán
    @Column(name = "TenThanhToan", length = 100)
    private String TenThanhToan;

    // Các hóa đơn sử dụng phương thức này
    @OneToMany(mappedBy = "PhuongThucThanhToan", cascade = CascadeType.ALL)
    private List<HoaDon> DanhSachHoaDon;

    public PhuongThucThanhToan() {}

    public PhuongThucThanhToan(String MaThanhToan, String TenThanhToan) {
        this.MaThanhToan = MaThanhToan;
        this.TenThanhToan = TenThanhToan;
    }

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
