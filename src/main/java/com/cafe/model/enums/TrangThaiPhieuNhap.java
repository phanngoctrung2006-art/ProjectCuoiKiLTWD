package com.cafe.model.enums;

/**
 * Enum đại diện cho trạng thái của phiếu nhập nguyên liệu.
 */
public enum TrangThaiPhieuNhap {
    CHO_DUYET("Chờ duyệt"),
    DA_NHAP("Đã nhập"),
    DA_HUY("Đã hủy");

    private final String moTa;

    TrangThaiPhieuNhap(String moTa) {
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
    public static TrangThaiPhieuNhap fromMoTa(String moTa) {
        for (TrangThaiPhieuNhap tt : values()) {
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
