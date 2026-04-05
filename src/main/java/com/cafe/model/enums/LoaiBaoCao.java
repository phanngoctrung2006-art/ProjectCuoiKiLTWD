package com.cafe.model.enums;

/**
 * Enum đại diện cho loại báo cáo thống kê.
 */
public enum LoaiBaoCao {
    DOANH_THU_THEO_NGAY("Doanh thu theo ngày"),
    DOANH_THU_THEO_THANG("Doanh thu theo tháng"),
    DOANH_THU_THEO_NAM("Doanh thu theo năm"),
    SAN_PHAM_BAN_CHAY("Sản phẩm bán chạy"),
    KHACH_HANG_THAN_THIET("Khách hàng thân thiết"),
    NHAP_HANG("Nhập hàng"),
    TON_KHO("Tồn kho");

    private final String moTa;

    LoaiBaoCao(String moTa) {
        this.moTa = moTa;
    }

    /**
     * Lấy mô tả tiếng Việt của loại báo cáo.
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * Tìm enum theo mô tả tiếng Việt.
     * @param moTa Chuỗi mô tả
     * @return Enum tương ứng hoặc null nếu không tìm thấy
     */
    public static LoaiBaoCao fromMoTa(String moTa) {
        for (LoaiBaoCao lbc : values()) {
            if (lbc.moTa.equalsIgnoreCase(moTa)) {
                return lbc;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return moTa;
    }
}
