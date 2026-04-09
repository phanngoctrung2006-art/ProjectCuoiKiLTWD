package com.cafe.dao.impl;

import com.cafe.dao.ReportDAO;
import com.cafe.model.entity.HoaDon;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Lớp DAO phụ trách thực thi các câu truy vấn thống kê (Thống kê Doanh thu, 
 * Sản phẩm bán chạy, Trung bình giá trị...).
 * Sử dụng JPQL thay vì SQL tuần túy để tương tác với Hibernate Object.
 */
public class ReportDAOImpl implements ReportDAO {

    @Override
    public List<HoaDon> q01_getAllHoaDonWithCustomer() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            // Lấy danh sách hóa đơn kết hợp thông tin khách hàng bằng LEFT JOIN FETCH
            // Giúp khắc phục lỗi N+1 Query hiệu quả trong Hibernate
            String jpql = "SELECT DISTINCT h FROM HoaDon h " +
                    "LEFT JOIN FETCH h.KhachHang " +
                    "ORDER BY h.NgayLap DESC";
            return em.createQuery(jpql, HoaDon.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Map<String, Object>> q05_revenueByCustomer() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            // Gom nhóm danh sách Hóa Đơn theo ID khách để tính tổng tiền (SUM) đã mua 
            // Cú pháp 'new map' trả về dữ liệu dưới dạng Map (Key-Value)
            String jpql = "SELECT new map(k.MaKhachHang as maKhachHang, " +
                    "k.TenKhachHang as tenKhachHang, " +
                    "COALESCE(SUM(h.TongTien), 0) as tongDoanhThu) " +
                    "FROM KhachHang k " +
                    "LEFT JOIN k.DanhSachHoaDon h " +
                    "GROUP BY k.MaKhachHang, k.TenKhachHang " +
                    "ORDER BY COALESCE(SUM(h.TongTien), 0) DESC";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> result = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class)
                    .getResultList();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Map<String, Object>> q07_topSellingProducts() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT new map(t.MaThucUong as ma, " +
                    "t.TenThucUong as ten, " +
                    "COALESCE(SUM(c.SoLuong), 0) as tongSoLuong) " +
                    "FROM ThucUong t " +
                    "LEFT JOIN t.DanhSachChiTietHoaDon c " +
                    "GROUP BY t.MaThucUong, t.TenThucUong " +
                    "ORDER BY COALESCE(SUM(c.SoLuong), 0) DESC";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> result = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class)
                    .getResultList();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public long q09_countTotalOrders() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT COUNT(h) FROM HoaDon h";
            Long result = em.createQuery(jpql, Long.class).getSingleResult();
            return result != null ? result : 0L;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            em.close();
        }
    }

    @Override
    public BigDecimal q10_averageOrderValue() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT AVG(h.TongTien) FROM HoaDon h";
            Double result = em.createQuery(jpql, Double.class).getSingleResult();
            return result != null ? BigDecimal.valueOf(result) : BigDecimal.ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        } finally {
            em.close();
        }
    }

    @Override
    public BigDecimal q11_maxOrderValue() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT MAX(h.TongTien) FROM HoaDon h";
            BigDecimal result = em.createQuery(jpql, BigDecimal.class).getSingleResult();
            return result != null ? result : BigDecimal.ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        } finally {
            em.close();
        }
    }

    @Override
    public BigDecimal q12_minOrderValue() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT MIN(h.TongTien) FROM HoaDon h";
            BigDecimal result = em.createQuery(jpql, BigDecimal.class).getSingleResult();
            return result != null ? result : BigDecimal.ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        } finally {
            em.close();
        }
    }

    @Override
    public BigDecimal q13_totalRevenue() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT SUM(h.TongTien) FROM HoaDon h";
            BigDecimal result = em.createQuery(jpql, BigDecimal.class).getSingleResult();
            return result != null ? result : BigDecimal.ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        } finally {
            em.close();
        }
    }

}
