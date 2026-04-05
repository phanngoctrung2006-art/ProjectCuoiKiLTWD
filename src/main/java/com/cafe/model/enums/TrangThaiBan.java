package com.cafe.model.enums;

/**
 * Enum đại diện cho trạng thái của bàn trong quán cà phê.
 */
public enum TrangThaiBan {
    TRONG("Trống"),
    DANG_SU_DUNG("Đang sử dụng"),
    DA_DAT("Đã đặt"),
    BAO_TRI("Bảo trì");

    private final String moTa;

    TrangThaiBan(String moTa) {
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
    public static TrangThaiBan fromMoTa(String moTa) {
        for (TrangThaiBan tt : values()) {
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
