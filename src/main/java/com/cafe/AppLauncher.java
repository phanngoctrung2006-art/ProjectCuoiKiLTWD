package com.cafe;

import com.cafe.controller.*;
import com.cafe.dao.*;
import com.cafe.dao.impl.*;
import com.cafe.service.*;
import com.cafe.service.implement.*;
import com.cafe.view.*;
import javax.swing.*;

/**
 * Lớp khởi động chính của ứng dụng (Entry Point).
 * Áp dụng mô hình Dependency Injection thủ công (Manual DI):
 * Khởi tạo toàn bộ các DAO -> Truyền vào Service -> Truyền vào Controller -> Truyền vào View.
 */
public class AppLauncher {
    public static void main(String[] args) {

        // --- Tầng DAO Layer (Tương tác cơ sở dữ liệu) ---
        HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();
        KhachHangDAO khachHangDAO = new KhachHangDAOImpl();
        ReportDAO reportDAO = new ReportDAOImpl();
        ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAOImpl();
        ThucUongDAO thucUongDAO = new ThucUongDAOImpl();
        NhanVienDAO nhanVienDAO = new NhanVienDAOImpl();
        NguyenLieuDAO nguyenLieuDAO = new NguyenLieuDAOImpl();
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAOImpl();
        PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAOImpl();
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAOImpl();

        // --- Tầng Service Layer (Chứa logic nghiệp vụ, validation xử lý lỗi) ---
        HoaDonService hoaDonService = new HoaDonServiceImpl(hoaDonDAO);
        KhachHangService khachHangService = new KhachHangServiceImpl(khachHangDAO);
        ReportService reportService = new ReportServiceImpl(reportDAO);
        ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl(chiTietHoaDonDAO);
        ThucUongService thucUongService = new ThucUongServiceImpl(thucUongDAO);
        NhanVienService nhanVienService = new NhanVienServiceImpl(nhanVienDAO);
        NguyenLieuService nguyenLieuService = new NguyenLieuServiceImpl(nguyenLieuDAO);
        NhaCungCapService nhaCungCapService = new NhaCungCapServiceImpl(nhaCungCapDAO);
        PhieuNhapService phieuNhapService = new PhieuNhapServiceImpl(phieuNhapDAO);
        TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl(taiKhoanDAO);

        // --- Tầng Controller Layer (Nhận tương tác từ View, điều phối cho Service) ---
        HoaDonController hoaDonController = new HoaDonController(
                hoaDonService, khachHangService, reportService,
                chiTietHoaDonService, thucUongService,
                (ChiTietHoaDonDAOImpl) chiTietHoaDonDAO);
        NhanVienController nhanVienController = new NhanVienController(nhanVienService);
        ThucUongController thucUongController = new ThucUongController(thucUongService);
        NhaCungCapController nhaCungCapController = new NhaCungCapController(nhaCungCapService);
        NguyenLieuController nguyenLieuController = new NguyenLieuController(nguyenLieuService);
        PhieuNhapController phieuNhapController = new PhieuNhapController(phieuNhapService, nhanVienService,
                nhaCungCapService);
        KhachHangController khachHangController = new KhachHangController(khachHangService);
        TaiKhoanController taiKhoanController = new TaiKhoanController(taiKhoanService);

        LoaiThucUongDAO loaiThucUongDAO = new LoaiThucUongDAOImpl();
        MenuPanel menuPanel = new MenuPanel();
        MenuController menuController = new MenuController(menuPanel, thucUongService, loaiThucUongDAO);

        // View Layer (chỉ hiển thị giao diện, điều phối qua controller)
        // Sử dụng SwingUtilities.invokeLater để đảm bảo GUI chạy trên Event Dispatch Thread (EDT)
        // giúp tránh lỗi giật lag hay treo frame giao diện của Java Swing.
        SwingUtilities.invokeLater(() -> {
            // Khởi tạo LoginFrame, truyền vào callback để kích hoạt MainFrame sau khi đăng nhập đúng
            LoginFrame loginFrame = new LoginFrame(taiKhoanController, () -> {
                HoaDonManagementPanel hoaDonPanel = new HoaDonManagementPanel(hoaDonController);
                NhanVienPanel nhanVienPanel = new NhanVienPanel(nhanVienController);
                // ThucUongPanel thucUongPanel = new ThucUongPanel(thucUongController);
                PhieuNhapPanel phieuNhapPanel = new PhieuNhapPanel(phieuNhapController);
                NguyenLieuPanel nguyenLieuPanel = new NguyenLieuPanel(nguyenLieuController);
                NhaCungCapPanel nhaCungCapPanel = new NhaCungCapPanel(nhaCungCapController);
                KhachHangPanel khachHangPanel = new KhachHangPanel(khachHangController);

                // Khởi tạo MainFrame chứa toàn bộ các Panel quản lý đã được setup Controller
                MainFrame frame = new MainFrame(
                        hoaDonPanel, nhanVienPanel, menuPanel,
                        phieuNhapPanel, nguyenLieuPanel, nhaCungCapPanel, khachHangPanel);
                
                // Hiển thị giao diện chính lên màn hình
                frame.setVisible(true);
            });
            // Bắt đầu ứng dụng bằng cách hiển thị form Đăng nhập đầu tiên
            loginFrame.setVisible(true);
        });
    }
}