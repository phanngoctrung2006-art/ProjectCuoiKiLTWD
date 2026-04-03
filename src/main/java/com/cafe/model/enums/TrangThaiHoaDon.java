package com.cafe.model.enums;

/**
 * Enum đại diện cho trạng thái của hóa đơn.
 */
public enum TrangThaiHoaDon {
    CHO_XAC_NHAN("Chờ xác nhận"),
    DANG_XU_LY("Đang xử lý"),
    DA_THANH_TOAN("Đã thanh toán"),
    DA_HUY("Đã hủy");

    private final String moTa;

    TrangThaiHoaDon(String moTa) {
        this.moTa = moTa;
    }

    /**
     * Lấy mô tả tiếng Việt của trạng thái.
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * Tìm enum theo mô tả tiếng Việt.
     * @param moTa Chuỗi mô tả
     * @return Enum tương ứng hoặc null nếu không tìm thấy
     */
    public static TrangThaiHoaDon fromMoTa(String moTa) {
        for (TrangThaiHoaDon tt : values()) {
            if (tt.moTa.equalsIgnoreCase(moTa)) {
                return tt;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return moTa;
    }
}
