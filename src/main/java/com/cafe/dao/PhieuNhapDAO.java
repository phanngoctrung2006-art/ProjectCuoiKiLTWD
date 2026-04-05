package com.cafe.dao;

import com.cafe.model.entity.PhieuNhap;
import java.sql.Date;
import java.util.List;

/**
 * Interface DAO cho bảng PhieuNhap.
 */
public interface PhieuNhapDAO extends GenericDAO<PhieuNhap, String> {

    /**
     * Tìm phiếu nhập theo khoảng ngày nhập.
     * @param tuNgay Ngày bắt đầu
     * @param denNgay Ngày kết thúc
     * @return Danh sách phiếu nhập trong khoảng ngày
     */
    List<PhieuNhap> findByNgayNhapBetween(Date tuNgay, Date denNgay);

    /**
     * Tìm phiếu nhập theo mã nhà cung cấp.
     * @param maNhaCungCap Mã nhà cung cấp
     * @return Danh sách phiếu nhập từ nhà cung cấp
     */
    List<PhieuNhap> findByMaNhaCungCap(String maNhaCungCap);
}
