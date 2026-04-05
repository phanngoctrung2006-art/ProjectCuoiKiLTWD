package com.cafe.model.enums;

/**
 * Enum đại diện cho vai trò (chức vụ) của nhân viên.
 */
public enum VaiTroNhanVien {
    NHAN_VIEN("Nhân viên"),
    THU_NGAN("Thu ngân"),
    PHA_CHE("Pha chế"),
    PHUC_VU("Phục vụ"),
    QUAN_LY("Quản lý");

    private final String moTa;

    VaiTroNhanVien(String moTa) {
        this.moTa = moTa;
    }

    /**
     * Lấy mô tả tiếng Việt của vai trò.
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * Tìm enum theo mô tả tiếng Việt.
     * @param moTa Chuỗi mô tả
     * @return Enum tương ứng hoặc null nếu không tìm thấy
     */
    public static VaiTroNhanVien fromMoTa(String moTa) {
        for (VaiTroNhanVien vt : values()) {
            if (vt.moTa.equalsIgnoreCase(moTa)) {
                return vt;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return moTa;
    }
}
