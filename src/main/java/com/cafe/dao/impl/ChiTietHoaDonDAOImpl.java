package com.cafe.dao.impl;

import com.cafe.dao.ChiTietHoaDonDAO;
import com.cafe.model.entity.ChiTietHoaDon;
import com.cafe.model.entity.ChiTietHoaDonId;
import com.cafe.model.entity.HoaDon;
import com.cafe.model.entity.ThucUong;
import com.cafe.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAOImpl extends GenericDAOImpl<ChiTietHoaDon, ChiTietHoaDonId> implements ChiTietHoaDonDAO {

    public ChiTietHoaDonDAOImpl() {
        super(ChiTietHoaDon.class);
    }

    @Override
    public List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT ct FROM ChiTietHoaDon ct " +
                    "LEFT JOIN FETCH ct.ThucUong " +
                    "WHERE ct.HoaDon.MaHoaDon = :maHD";
            return em.createQuery(jpql, ChiTietHoaDon.class)
                    .setParameter("maHD", maHoaDon)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteByMaHoaDon(String maHoaDon) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "DELETE FROM ChiTietHoaDon ct WHERE ct.HoaDon.MaHoaDon = :maHD";
            em.createQuery(jpql).setParameter("maHD", maHoaDon).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Thêm hoặc cập nhật ChiTietHoaDon, sau đó cập nhật TongTien của HoaDon —
     * toàn bộ trong 1 EntityManager, tránh vấn đề detached entity.
     */
    public void addOrUpdateChiTiet(String maHoaDon, String maThucUong, int soLuong) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            ChiTietHoaDonId id = new ChiTietHoaDonId(maHoaDon, maThucUong);
            ChiTietHoaDon existing = em.find(ChiTietHoaDon.class, id);

            if (existing != null) {
                // Cập nhật số lượng nếu đã có món này
                existing.setSoLuong(existing.getSoLuong() + soLuong);
            } else {
                // Thêm mới — dùng getReference để tránh detached entity exception
                HoaDon hd = em.getReference(HoaDon.class, maHoaDon);
                ThucUong tu = em.getReference(ThucUong.class, maThucUong);
                ChiTietHoaDon chiTiet = new ChiTietHoaDon(hd, tu, soLuong);
                em.persist(chiTiet);
            }

            // Flush để chắc chắn dữ liệu ghi xuống trước khi tính tổng
            em.flush();

            // Tính và cập nhật TongTien bằng JPQL — tránh cascade merge
            String sumJpql = "SELECT SUM(tu.Gia * ct.SoLuong) FROM ChiTietHoaDon ct " +
                             "JOIN ct.ThucUong tu WHERE ct.HoaDon.MaHoaDon = :ma";
            BigDecimal tongTien = em.createQuery(sumJpql, BigDecimal.class)
                    .setParameter("ma", maHoaDon)
                    .getSingleResult();
            if (tongTien == null) tongTien = BigDecimal.ZERO;

            em.createQuery("UPDATE HoaDon h SET h.TongTien = :tien WHERE h.MaHoaDon = :ma")
              .setParameter("tien", tongTien)
              .setParameter("ma", maHoaDon)
              .executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm món vào hóa đơn: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Giảm số lượng ChiTietHoaDon đi 1.
     * Nếu số lượng về 0 thì xóa hẳn dòng đó.
     * Sau đó cập nhật TongTien của HoaDon.
     */
    public void decreaseOrRemoveChiTiet(String maHoaDon, String maThucUong) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            ChiTietHoaDonId id = new ChiTietHoaDonId(maHoaDon, maThucUong);
            ChiTietHoaDon existing = em.find(ChiTietHoaDon.class, id);
            if (existing != null) {
                int newQty = existing.getSoLuong() - 1;
                if (newQty <= 0) {
                    em.remove(existing);
                } else {
                    existing.setSoLuong(newQty);
                }
                em.flush();
            }

            // Tính và cập nhật TongTien bằng JPQL
            String sumJpql = "SELECT COALESCE(SUM(tu.Gia * ct.SoLuong), 0) FROM ChiTietHoaDon ct " +
                             "JOIN ct.ThucUong tu WHERE ct.HoaDon.MaHoaDon = :ma";
            BigDecimal tongTien = em.createQuery(sumJpql, BigDecimal.class)
                    .setParameter("ma", maHoaDon)
                    .getSingleResult();
            if (tongTien == null) tongTien = BigDecimal.ZERO;

            em.createQuery("UPDATE HoaDon h SET h.TongTien = :tien WHERE h.MaHoaDon = :ma")
              .setParameter("tien", tongTien)
              .setParameter("ma", maHoaDon)
              .executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi giảm số lượng món: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Xóa ChiTietHoaDon và cập nhật TongTien — toàn bộ trong 1 EntityManager.
     */
    public void deleteChiTietAndRecalculate(String maHoaDon, String maThucUong) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            ChiTietHoaDonId id = new ChiTietHoaDonId(maHoaDon, maThucUong);
            ChiTietHoaDon existing = em.find(ChiTietHoaDon.class, id);
            if (existing != null) {
                em.remove(existing);
                em.flush();
            }

            // Tính và cập nhật TongTien bằng JPQL
            String sumJpql = "SELECT COALESCE(SUM(tu.Gia * ct.SoLuong), 0) FROM ChiTietHoaDon ct " +
                             "JOIN ct.ThucUong tu WHERE ct.HoaDon.MaHoaDon = :ma";
            BigDecimal tongTien = em.createQuery(sumJpql, BigDecimal.class)
                    .setParameter("ma", maHoaDon)
                    .getSingleResult();
            if (tongTien == null) tongTien = BigDecimal.ZERO;

            em.createQuery("UPDATE HoaDon h SET h.TongTien = :tien WHERE h.MaHoaDon = :ma")
              .setParameter("tien", tongTien)
              .setParameter("ma", maHoaDon)
              .executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi xóa món khỏi hóa đơn: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
