package com.cafe.service.implement;

import com.cafe.dao.HoaDonDAO;
import com.cafe.model.entity.HoaDon;
import com.cafe.service.HoaDonService;
import java.math.BigDecimal;
import java.util.List;

/**
 * Lớp triển khai các logic nghiệp vụ cho Hóa Đơn.
 * Kiểm tra tính hợp lệ của dữ liệu (Validation) trước khi gọi DAO để cập nhật CSDL.
 */
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonDAO hoaDonDAO;

    public HoaDonServiceImpl(HoaDonDAO hoaDonDAO) {
        this.hoaDonDAO = hoaDonDAO;
    }

    @Override
    public List<HoaDon> getAll() {
        // Gọi DAO để thực hiện câu Query SELECT * FROM hoadon
        // Trả về danh sách tất cả các thực thể hóa đơn đang có trong database
        return hoaDonDAO.findAll();
    }

    @Override
    public HoaDon getById(String id) {
        // Tìm 1 Record Hóa đơn dựa trên Khóa chính (Primary Key).
        // Nếu không tìm thấy, hệ thống DAO sẽ trả về NULL.
        return hoaDonDAO.findById(id);
    }

    @Override
    public HoaDon create(HoaDon hoaDon) {
        // Kiểm tra tính hợp lệ: Hóa đơn và Mã hóa đơn không được để trống
        if (hoaDon == null || hoaDon.getMaHoaDon() == null || hoaDon.getMaHoaDon().isEmpty()) {
            throw new IllegalArgumentException("Mã hóa đơn không được trống");
        }
        return hoaDonDAO.save(hoaDon);
    }

    @Override
    public HoaDon update(HoaDon hoaDon) {
        // Ràng buộc trước khi cập nhật
        if (hoaDon == null || hoaDon.getMaHoaDon() == null) {
            throw new IllegalArgumentException("Hóa đơn không hợp lệ");
        }
        return hoaDonDAO.update(hoaDon);
    }

    @Override
    public void delete(String id) {
        // Lớp Service đảm nhiệm phần bắt lỗi nghiệp vụ (Business Logic Exception).
        // Đảm bảo không cho phép truyền xuống CSDL một câu truy vấn xóa rỗng, tránh Exception phía Hibernate.
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID không được trống");
        }
        hoaDonDAO.delete(id);
    }

    @Override
    public List<HoaDon> findByKhachHangId(String maKhachHang) {
        if (maKhachHang == null || maKhachHang.isEmpty()) {
            throw new IllegalArgumentException("Mã khách hàng không được trống");
        }
        // Sử dụng Java 8 Stream API để lọc danh sách Hóa Đơn trên Memory
        // Filter: Duyệt qua tất cả hóa đơn (h), kiểm tra KhachHang tồn tại và so sánh mã
        // toList(): Gom các kết quả khớp lại thành 1 List mới trả về
        return hoaDonDAO.findAll().stream()
                .filter(h -> h.getKhachHang() != null && h.getKhachHang().getMaKhachHang().equals(maKhachHang))
                .toList();
    }

    @Override
    public BigDecimal getTotalRevenue() {
        // Tính tổng doanh thu bằng cách cộng dồn cột TongTien của tất cả hóa đơn
        return hoaDonDAO.findAll().stream()
                .map(HoaDon::getTongTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void updateTongTienOnly(String maHoaDon, BigDecimal tongTien) {
        // Ép kiểu về impl cụ thể để dùng JPQL UPDATE, tránh cascade merge
        ((com.cafe.dao.impl.HoaDonDAOImpl) hoaDonDAO).updateTongTien(maHoaDon, tongTien);
    }
}
