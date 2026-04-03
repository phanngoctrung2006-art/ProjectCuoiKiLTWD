package com.cafe.dao;

import com.cafe.model.entity.HoaDon;
import java.sql.Date;
import java.util.List;

/**
 * Interface DAO cho bảng HoaDon.
 */
public interface HoaDonDAO extends GenericDAO<HoaDon, String> {

    /**
     * Tìm hóa đơn theo khoảng ngày lập.
     * @param tuNgay Ngày bắt đầu
     * @param denNgay Ngày kết thúc
     * @return Danh sách hóa đơn trong khoảng ngày
     */
    List<HoaDon> findByNgayLapBetween(Date tuNgay, Date denNgay);

    /**
     * Tìm hóa đơn theo mã khách hàng.
     * @param maKhachHang Mã khách hàng
     * @return Danh sách hóa đơn của khách hàng
     */
    List<HoaDon> findByMaKhachHang(String maKhachHang);

    /**
     * Tìm hóa đơn theo mã nhân viên.
     * @param maNhanVien Mã nhân viên
     * @return Danh sách hóa đơn do nhân viên lập
     */
    List<HoaDon> findByMaNhanVien(String maNhanVien);
}
