package com.cafe.model.enums;

/**
 * Enum đại diện cho đơn vị tính của nguyên liệu.
 */
public enum DonViTinh {
    GRAM("gram"),
    KG("kg"),
    ML("ml"),
    LIT("lít"),
    CAI("cái"),
    GOI("gói"),
    HOP("hộp"),
    CHAI("chai"),
    QUA("quả");

    private final String moTa;

    DonViTinh(String moTa) {
        this.moTa = moTa;
    }

    /**
     * Lấy mô tả tiếng Việt của đơn vị tính.
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * Tìm enum theo mô tả tiếng Việt.
     * @param moTa Chuỗi mô tả
     * @return Enum tương ứng hoặc null nếu không tìm thấy
     */
    public static DonViTinh fromMoTa(String moTa) {
        for (DonViTinh dvt : values()) {
            if (dvt.moTa.equalsIgnoreCase(moTa)) {
                return dvt;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return moTa;
    }
}
