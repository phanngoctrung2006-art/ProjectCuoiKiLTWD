package com.cafe.dao.impl;
import com.cafe.dao.ReportDAO;
import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.KhachHang;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Implementation của ReportDAO - 15 câu truy vấn JPQL.
 */
public class ReportDAOImpl implements ReportDAO {

    @Override
    public List<HoaDon> q01_getAllHoaDonWithCustomer() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
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
    public HoaDon q02_findHoaDonByMa(String ma) {
        if (ma == null || ma.trim().isEmpty()) {
            return null;
        }

        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT h FROM HoaDon h " +
                    "LEFT JOIN FETCH h.KhachHang " +
                    "WHERE h.MaHoaDon = :ma";
            return em.createQuery(jpql, HoaDon.class)
                    .setParameter("ma", ma.trim())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Không tìm thấy
        } catch (NonUniqueResultException e) {
            // Nếu có nhiều kết quả, lấy kết quả đầu tiên
            List<HoaDon> results = em.createQuery(
                "SELECT h FROM HoaDon h WHERE h.MaHoaDon = :ma", HoaDon.class)
                .setParameter("ma", ma.trim())
                .getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<HoaDon> q03_findHoaDonByCustomerName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return q01_getAllHoaDonWithCustomer();
        }

        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT h FROM HoaDon h " +
                    "LEFT JOIN FETCH h.KhachHang k " +
                    "WHERE LOWER(k.TenKhachHang) LIKE LOWER(:name) " +
                    "ORDER BY h.NgayLap DESC";
            return em.createQuery(jpql, HoaDon.class)
                    .setParameter("name", "%" + name.trim() + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<HoaDon> q04_findHoaDonBetweenDates(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null) {
            return q01_getAllHoaDonWithCustomer();
        }

        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT h FROM HoaDon h " +
                    "LEFT JOIN FETCH h.KhachHang " +
                    "WHERE h.NgayLap BETWEEN :fromDate AND :toDate " +
                    "ORDER BY h.NgayLap DESC";
            return em.createQuery(jpql, HoaDon.class)
                    .setParameter("fromDate", fromDate)
                    .setParameter("toDate", toDate)
                    .getResultList();
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
            String jpql = "SELECT new map(k.MaKhachHang as maKhachHang, " +
                    "k.TenKhachHang as tenKhachHang, " +
                    "COALESCE(SUM(h.TongTien), 0) as tongDoanhThu) " +
                    "FROM KhachHang k " +
                    "LEFT JOIN k.DanhSachHoaDon h " +
                    "GROUP BY k.MaKhachHang, k.TenKhachHang " +
                    "ORDER BY COALESCE(SUM(h.TongTien), 0) DESC";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> result = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class).getResultList();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Map<String, Object>> q06_revenueByMonth() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            // Sử dụng cách khác thay vì FUNCTION() để tương thích với MySQL
            String jpql = "SELECT new map(" +
                    "YEAR(h.NgayLap) as nam, " +
                    "MONTH(h.NgayLap) as thang, " +
                    "SUM(h.TongTien) as tongDoanhThu) " +
                    "FROM HoaDon h " +
                    "GROUP BY YEAR(h.NgayLap), MONTH(h.NgayLap) " +
                    "ORDER BY YEAR(h.NgayLap) DESC, MONTH(h.NgayLap) DESC";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> result = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class).getResultList();
            return result;
        } catch (Exception e) {
            // Fallback: thử với FUNCTION()
            try {
                String jpql = "SELECT new map(" +
                        "FUNCTION('YEAR', h.NgayLap) as nam, " +
                        "FUNCTION('MONTH', h.NgayLap) as thang, " +
                        "SUM(h.TongTien) as tongDoanhThu) " +
                        "FROM HoaDon h " +
                        "GROUP BY FUNCTION('YEAR', h.NgayLap), FUNCTION('MONTH', h.NgayLap) " +
                        "ORDER BY FUNCTION('YEAR', h.NgayLap) DESC, FUNCTION('MONTH', h.NgayLap) DESC";
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> result = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class).getResultList();
                return result;
            } catch (Exception e2) {
                e2.printStackTrace();
                return new ArrayList<>();
            }
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
            List<Map<String, Object>> result = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class).getResultList();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Map<String, Object>> q08_topRevenueProducts() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT new map(t.MaThucUong as ma, " +
                    "t.TenThucUong as ten, " +
                    "t.Gia as gia, " +
                    "COALESCE(SUM(c.SoLuong * t.Gia), 0) as tongDoanhThu) " +
                    "FROM ThucUong t " +
                    "LEFT JOIN t.DanhSachChiTietHoaDon c " +
                    "GROUP BY t.MaThucUong, t.TenThucUong, t.Gia " +
                    "ORDER BY COALESCE(SUM(c.SoLuong * t.Gia), 0) DESC";
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> result = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class).getResultList();
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

    @Override
    public List<KhachHang> q14_customersWithoutOrders() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT k FROM KhachHang k " +
                    "WHERE k NOT IN (SELECT DISTINCT h.KhachHang FROM HoaDon h) " +
                    "ORDER BY k.TenKhachHang";
            return em.createQuery(jpql, KhachHang.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public Map<String, Object> q15_orderWithMostDetails() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT new map(h.MaHoaDon as ma, " +
                    "COUNT(c) as soDongChiTiet) " +
                    "FROM HoaDon h " +
                    "LEFT JOIN h.DanhSachChiTietHoaDon c " +
                    "GROUP BY h.MaHoaDon " +
                    "ORDER BY COUNT(c) DESC";
            List<Map<String, Object>> results = (List<Map<String, Object>>) (List<?>) em.createQuery(jpql, Map.class)
                    .setMaxResults(1)
                    .getResultList();
            return results.isEmpty() ? new HashMap<>() : results.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        } finally {
            em.close();
        }
    }
}
