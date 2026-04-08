package com.cafe;

import com.cafe.controller.*;
import com.cafe.dao.*;
import com.cafe.dao.impl.*;
import com.cafe.service.*;
import com.cafe.service.implement.*;
import com.cafe.view.*;
import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {

        // DAO Layer
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

        // Service Layer (chứa validation)
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

        // Controller Layer (cầu nối Service ↔ View, không chứa validation)
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

        // Quan trọng: Khởi tạo controller cho menu
        MenuController menuController = new MenuController(menuPanel, thucUongService, loaiThucUongDAO);

        // View Layer (chỉ hiển thị giao diện, gọi controller)
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(taiKhoanController, () -> {
                HoaDonManagementPanel hoaDonPanel = new HoaDonManagementPanel(hoaDonController);
                NhanVienPanel nhanVienPanel = new NhanVienPanel(nhanVienController);
                // ThucUongPanel thucUongPanel = new ThucUongPanel(thucUongController);
                PhieuNhapPanel phieuNhapPanel = new PhieuNhapPanel(phieuNhapController);
                NguyenLieuPanel nguyenLieuPanel = new NguyenLieuPanel(nguyenLieuController);
                NhaCungCapPanel nhaCungCapPanel = new NhaCungCapPanel(nhaCungCapController);
                KhachHangPanel khachHangPanel = new KhachHangPanel(khachHangController);

                MainFrame frame = new MainFrame(
                        hoaDonPanel, nhanVienPanel, menuPanel,
                        phieuNhapPanel, nguyenLieuPanel, nhaCungCapPanel, khachHangPanel);
                frame.setVisible(true);
            });
            loginFrame.setVisible(true);
        });
    }
}