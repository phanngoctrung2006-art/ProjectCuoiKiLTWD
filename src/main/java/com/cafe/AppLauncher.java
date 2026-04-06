package com.cafe;

import com.cafe.controller.HoaDonController;
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

        // Service Layer
        HoaDonService hoaDonService = new HoaDonServiceImpl(hoaDonDAO);
        KhachHangService khachHangService = new KhachHangServiceImpl(khachHangDAO);
        ReportService reportService = new ReportServiceImpl(reportDAO);
        ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl(chiTietHoaDonDAO);
        ThucUongService thucUongService = new ThucUongServiceImpl(thucUongDAO);
        NhanVienService nhanVienService = new NhanVienServiceImpl(nhanVienDAO);
        NguyenLieuService nguyenLieuService = new NguyenLieuServiceImpl(nguyenLieuDAO);
        NhaCungCapService nhaCungCapService = new NhaCungCapServiceImpl(nhaCungCapDAO);
        PhieuNhapService phieuNhapService = new PhieuNhapServiceImpl(phieuNhapDAO);

        // Controller
        HoaDonController hoaDonController = new HoaDonController(
                hoaDonService, khachHangService, reportService,
                chiTietHoaDonService, thucUongService,
                (com.cafe.dao.impl.ChiTietHoaDonDAOImpl) chiTietHoaDonDAO);

        // UI
        SwingUtilities.invokeLater(() -> {
            HoaDonManagementPanel hoaDonPanel = new HoaDonManagementPanel(hoaDonController);
            NhanVienPanel nhanVienPanel = new NhanVienPanel(nhanVienService);
            ThucUongPanel thucUongPanel = new ThucUongPanel(thucUongService);
            PhieuNhapPanel phieuNhapPanel = new PhieuNhapPanel(phieuNhapService, nhanVienService, nhaCungCapService);
            NguyenLieuPanel nguyenLieuPanel = new NguyenLieuPanel(nguyenLieuService);
            NhaCungCapPanel nhaCungCapPanel = new NhaCungCapPanel(nhaCungCapService);
            KhachHangPanel khachHangPanel = new KhachHangPanel(khachHangService);

            MainFrame frame = new MainFrame(
                    hoaDonPanel, nhanVienPanel, thucUongPanel,
                    phieuNhapPanel, nguyenLieuPanel, nhaCungCapPanel, khachHangPanel);
            frame.setVisible(true);
        });
    }
}
