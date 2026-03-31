# ProjectCuoiKi - Hệ Thống Quản Lý Bán Hàng Café

## 🎯 Tổng Quan

**ProjectCuoiKi** là một ứng dụng **Quản Lý Bán Hàng Café** được phát triển theo mô hình kiến trúc **MVC + DAO + Service + JPA/Hibernate**.

Ứng dụng sử dụng:
- **Backend**: Java 17, Hibernate 7.3.0, Jakarta Persistence 3.2
- **Frontend**: Java Swing
- **Database**: MySQL 8.0+
- **Build**: Maven

---

## ✨ Tính Năng Chính

### 1. Quản Lý Hóa Đơn (CRUD)
- ✅ Tạo mới hóa đơn
- ✅ Cập nhật thông tin
- ✅ Xóa hóa đơn
- ✅ Tìm kiếm hóa đơn
- ✅ Liên kết khách hàng tự động

### 2. Quản Lý Khách Hàng
- ✅ Danh sách khách hàng
- ✅ Tìm kiếm theo tên, số điện thoại
- ✅ CRUD khách hàng

### 3. Báo Cáo & Phân Tích (15 JPQL Queries)
- ✅ Tất cả hóa đơn
- ✅ Doanh thu theo khách hàng
- ✅ Doanh thu theo tháng
- ✅ Sản phẩm bán chạy nhất
- ✅ Thống kê: Trung bình, Max, Min, Tổng
- ✅ Khách hàng chưa từng mua
- ✅ Hóa đơn có chi tiết nhiều nhất

### 4. Giao Diện Thân Thiện
- ✅ Tab CRUD: Form + Bảng dữ liệu
- ✅ Tab Báo cáo: Multiple report views
- ✅ Menu bar: File, Help
- ✅ Xác nhận dialog: Tránh xóa nhầm

---

## 📁 Cấu Trúc Dự Án

```
ProjectCuoiKi/
├── pom.xml                                    (Maven config)
├── README.md                                  (File này)
├── HUONG_DAN_SU_DUNG.md                      (Hướng dẫn chi tiết)
├── database/                                 (SQL scripts)
│   ├── create_database.sql
│   ├── create_table.sql
│   └── insert_data.sql
└── src/main/
    ├── java/com/cafe/
    │   ├── AppLauncher.java                  (Entry point) ⭐
    │   ├── controller/
    │   │   └── HoaDonController.java        (MVC Controller)
    │   ├── service/
    │   │   ├── HoaDonService.java
    │   │   ├── KhachHangService.java
    │   │   ├── ReportService.java
    │   │   └── implement/ (3 implementations)
    │   ├── dao/
    │   │   ├── ReportDAO.java
    │   │   ├── (14 other DAOs)
    │   │   └── impl/ (15 implementations)
    │   ├── model/
    │   │   ├── entity/ (15 JPA Entities)
    │   │   └── dto/ (15 DTOs)
    │   ├── view/
    │   │   ├── MainFrame.java
    │   │   └── HoaDonManagementPanel.java
    │   └── util/
    │       └── HibernateUtil.java
    └── resources/
        └── META-INF/
            └── persistence.xml
```

---

## 🚀 Hướng Dẫn Cài Đặt

### 1. Yêu Cầu Hệ Thống
- Java 17+
- MySQL 8.0+
- Maven 3.6+
- IntelliJ IDEA (optional)

### 2. Cài Đặt Database
```bash
# Chạy các file SQL trong MySQL
mysql -u root -p < database/create_database.sql
mysql -u root -p < database/create_table.sql
mysql -u root -p < database/insert_data.sql
```

### 3. Cập Nhật Cấu Hình
Sửa file `src/main/resources/META-INF/persistence.xml`:
```xml
<property name="jakarta.persistence.jdbc.password" value="YOUR_PASSWORD"/>
```

### 4. Build Project
```bash
cd ProjectCuoiKi
mvn clean compile
# hoặc trong IDE: Ctrl + F9
```

### 5. Chạy Ứng Dụng
```bash
# Từ IDE: Right-click AppLauncher.java → Run
# Hoặc: Shift + F10

# Hoặc từ Terminal:
mvn exec:java -Dexec.mainClass="com.cafe.AppLauncher"
```

---

## 📊 15 JPQL Queries

### Nhóm A: Tra Cứu (Q1-Q4)
```
Q1: SELECT h FROM HoaDon h LEFT JOIN FETCH h.KhachHang ...
Q2: SELECT h FROM HoaDon h WHERE h.MaHoaDon = :ma
Q3: SELECT h FROM HoaDon h WHERE LOWER(k.TenKhachHang) LIKE LOWER(:name)
Q4: SELECT h FROM HoaDon h WHERE h.NgayLap BETWEEN :from AND :to
```

### Nhóm B: Sản Phẩm (Q5-Q8)
```
Q5: SELECT new map(...) FROM KhachHang k LEFT JOIN k.DanhSachHoaDon h 
    GROUP BY k.MaKhachHang
Q6: SELECT new map(...) FROM HoaDon h 
    GROUP BY YEAR(h.NgayLap), MONTH(h.NgayLap)
Q7: SELECT new map(...) FROM ThucUong t LEFT JOIN t.DanhSachChiTietHoaDon c 
    GROUP BY t.MaThucUong
Q8: SELECT new map(...) FROM ThucUong t 
    GROUP BY t.MaThucUong, SUM(c.SoLuong * t.Gia)
```

### Nhóm C: Thống Kê (Q9-Q13)
```
Q9:  COUNT(h) FROM HoaDon h
Q10: AVG(h.TongTien) FROM HoaDon h
Q11: MAX(h.TongTien) FROM HoaDon h
Q12: MIN(h.TongTien) FROM HoaDon h
Q13: SUM(h.TongTien) FROM HoaDon h
```

### Nhóm D: Phân Tích (Q14-Q15)
```
Q14: SELECT k FROM KhachHang k WHERE k NOT IN (SELECT DISTINCT h.KhachHang FROM HoaDon h)
Q15: SELECT new map(h.MaHoaDon, COUNT(c)) FROM HoaDon h LEFT JOIN h.DanhSachChiTietHoaDon c 
     GROUP BY h.MaHoaDon ORDER BY COUNT(c) DESC
```

---

## 🏗️ Kiến Trúc MVC

```
┌──────────────────────┐
│   View (Swing)       │
│ MainFrame, Panel     │
└──────────────┬───────┘
               │
┌──────────────▼───────┐
│ Controller           │
│ HoaDonController     │
└──────────────┬───────┘
               │
┌──────────────▼───────┐
│ Service              │
│ HoaDonService        │
│ KhachHangService     │
│ ReportService        │
└──────────────┬───────┘
               │
┌──────────────▼───────┐
│ DAO                  │
│ HoaDonDAOImpl         │
│ ReportDAOImpl         │
└──────────────┬───────┘
               │
┌──────────────▼───────┐
│ Hibernate/JPA        │
│ EntityManager        │
└──────────────┬───────┘
               │
┌──────────────▼───────┐
│ MySQL Database       │
│ QuanLyCafe           │
└──────────────────────┘
```

---

## 📋 Database Schema

### 15 Bảng:
1. **LoaiThucUong** - Loại đồ uống
2. **ThucUong** - Sản phẩm
3. **KhachHang** - Khách hàng
4. **HoaDon** - Hóa đơn
5. **ChiTietHoaDon** - Chi tiết hóa đơn
6. **NhanVien** - Nhân viên
7. **Ban** - Bàn
8. **PhuongThucThanhToan** - Phương thức thanh toán
9. **CongThuc** - Công thức pha chế
10. **ChiTietCongThuc** - Chi tiết công thức
11. **NguyenLieu** - Nguyên liệu
12. **NhaCungCap** - Nhà cung cấp
13. **PhieuNhap** - Phiếu nhập
14. **ChiTietPhieuNhap** - Chi tiết phiếu nhập
15. **Ban** - Bàn

**Dữ liệu mẫu**: 5 khách, 10 sản phẩm, 5 hóa đơn, ...

---

## 🎓 Công Nghệ Sử Dụng

| Công Nghệ | Phiên Bản | Mục Đích |
|-----------|-----------|---------|
| Java | 17 | Ngôn ngữ lập trình |
| Hibernate ORM | 7.3.0 | Object-Relational Mapping |
| Jakarta Persistence | 3.2 | JPA Standard |
| MySQL Connector | 9.6.0 | Database Driver |
| Maven | 3.6+ | Build Tool |
| Swing | Built-in | GUI Framework |

---

## 💡 Ví Dụ Sử Dụng

### Tạo Hóa Đơn Mới
```java
HoaDon hoaDon = new HoaDon();
hoaDon.setMaHoaDon("HD06");
hoaDon.setNgayLap(Date.valueOf("2026-04-01"));
hoaDon.setTongTien(new BigDecimal("95000"));
hoaDon.setKhachHang(khachHangService.getById("KH01"));

HoaDon saved = hoaDonService.create(hoaDon);
System.out.println("Tạo thành công: " + saved.getMaHoaDon());
```

### Báo Cáo Doanh Thu
```java
List<Map<String, Object>> revenue = reportService.q05_revenueByCustomer();
for (Map<String, Object> row : revenue) {
    System.out.println(row.get("tenKhachHang") + 
                      ": " + row.get("tongDoanhThu"));
}
```

### Tìm Kiếm Hóa Đơn
```java
List<HoaDon> orders = reportService.q03_findHoaDonByCustomerName("Nguyễn");
for (HoaDon hd : orders) {
    System.out.println(hd.getMaHoaDon() + " - " + hd.getTongTien());
}
```

---

## 🐛 Gỡ Lỗi

### Lỗi Phổ Biến

| Lỗi | Giải Pháp |
|-----|----------|
| `java.sql.SQLException: Unknown database` | Chạy `create_database.sql` |
| `Access denied for user 'root'` | Cập nhật password trong `persistence.xml` |
| `EntityManager is closed` | Kiểm tra kết nối database |
| `Cannot find main AppLauncher` | Rebuild project (Ctrl + F9) |
| `Hibernate showing 'javax' errors` | Normalize codebase, ignore warning |

**Xem chi tiết**: [HUONG_DAN_SU_DUNG.md](HUONG_DAN_SU_DUNG.md)

---

## 📈 Mở Rộng Tương Lai

- [ ] Thêm LoginFrame + Phân quyền
- [ ] Thêm ThucUongManagementPanel
- [ ] Xuất hóa đơn PDF
- [ ] Biểu đồ doanh thu
- [ ] REST API
- [ ] Web UI (Spring Boot)
- [ ] Báo cáo nâng cao
- [ ] Mobile App

---

## 👥 Tác Giả

- **Phát triển**: 01/04/2026
- **Mô hình tham khảo**: Sales MIS (file Word)
- **Mục đích**: Project cuối kỳ - Quản Lý Café

---

## 📄 Giấy Phép

MIT License - Tự do sử dụng, sửa đổi, phân phối

---

## 📞 Hỗ Trợ

Gặp vấn đề? 
1. Kiểm tra log trong console
2. Xem [HUONG_DAN_SU_DUNG.md](HUONG_DAN_SU_DUNG.md)
3. Verify database connection
4. Rebuild project

---

## 🎉 Chúc Mừng!

Bạn đã thiết lập thành công **ProjectCuoiKi**! 

Hãy khám phá ứng dụng:
1. ✅ Tạo hóa đơn mới
2. ✅ Cập nhật khách hàng
3. ✅ Xem báo cáo doanh thu
4. ✅ Phân tích dữ liệu

**Chúc bạn có một trải nghiệm tuyệt vời! 🚀**


