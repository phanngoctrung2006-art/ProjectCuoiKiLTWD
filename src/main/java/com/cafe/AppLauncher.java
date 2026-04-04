package com.cafe;

import com.cafe.controller.HoaDonController;
import com.cafe.dao.*;
import com.cafe.dao.impl.*;
import com.cafe.service.HoaDonService;
import com.cafe.service.KhachHangService;

import com.cafe.service.ReportService;
import com.cafe.service.implement.HoaDonServiceImpl;
import com.cafe.service.implement.KhachHangServiceImpl;
import com.cafe.service.implement.ReportServiceImpl;
import com.cafe.view.HoaDonManagementPanel;
import com.cafe.view.MainFrame;
import javax.swing.*;

/**
 * Entry point cho ứng dụng Quản Lý Bán Hàng Café
 */
public class AppLauncher {
    public static void main(String[] args) {
        // Khởi tạo DAO Layer
        HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();
        KhachHangDAO khachHangDAO = new KhachHangDAOImpl();
        ReportDAO reportDAO = new ReportDAOImpl();
        ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAOImpl();
        com.cafe.dao.ThucUongDAO thucUongDAO = new com.cafe.dao.impl.ThucUongDAOImpl();

        // Khởi tạo Service Layer
        HoaDonService hoaDonService = new HoaDonServiceImpl(hoaDonDAO);
        KhachHangService khachHangService = new KhachHangServiceImpl(khachHangDAO);
        ReportService reportService = new ReportServiceImpl(reportDAO);
        com.cafe.service.ChiTietHoaDonService chiTietHoaDonService = new com.cafe.service.implement.ChiTietHoaDonServiceImpl(chiTietHoaDonDAO);
        com.cafe.service.ThucUongService thucUongService = new com.cafe.service.implement.ThucUongServiceImpl(thucUongDAO);

        // Khởi tạo Controller
        HoaDonController hoaDonController = new HoaDonController(
            hoaDonService,
            khachHangService,
            reportService,
            chiTietHoaDonService,
            thucUongService
        );

        // Khởi tạo UI
        SwingUtilities.invokeLater(() -> {
            HoaDonManagementPanel panel = new HoaDonManagementPanel(hoaDonController);
            MainFrame frame = new MainFrame(panel);
            frame.setVisible(true);
        });
    }
}
