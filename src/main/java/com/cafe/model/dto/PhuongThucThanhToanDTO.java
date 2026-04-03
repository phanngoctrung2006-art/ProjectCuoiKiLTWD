package com.cafe.model.dto;

import com.cafe.model.entity.PhuongThucThanhToan;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng PhuongThucThanhToan.
 * Chứa thông tin cơ bản của phương thức thanh toán, không chứa danh sách hóa đơn.
 */
public class PhuongThucThanhToanDTO implements Serializable {

    private String MaThanhToan;
    private String TenThanhToan;

    // Constructors
    public PhuongThucThanhToanDTO() {}

    public PhuongThucThanhToanDTO(String MaThanhToan, String TenThanhToan) {
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

    /**
     * Chuyển đổi từ Entity sang DTO.
     */
    public static PhuongThucThanhToanDTO fromEntity(PhuongThucThanhToan entity) {
        if (entity == null) return null;
        PhuongThucThanhToanDTO dto = new PhuongThucThanhToanDTO();
        dto.setMaThanhToan(entity.getMaThanhToan());
        dto.setTenThanhToan(entity.getTenThanhToan());
        return dto;
    }

    /**
     * Chuyển đổi từ DTO sang Entity.
     */
    public PhuongThucThanhToan toEntity() {
        PhuongThucThanhToan entity = new PhuongThucThanhToan();
        entity.setMaThanhToan(this.MaThanhToan);
        entity.setTenThanhToan(this.TenThanhToan);
        return entity;
    }
}
