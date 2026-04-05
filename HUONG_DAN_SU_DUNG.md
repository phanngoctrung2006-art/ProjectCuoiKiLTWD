# 📖 HƯỚNG DẪN CHI TIẾT SỬ DỤNG PROJECTCUOKI

## 🎯 BƯỚC 1: CHUẨN BỊ DATABASE

### 1.1. Chạy SQL scripts
```sql
-- Bước 1: Tạo database
SOURCE database/create_database.sql;

-- Bước 2: Tạo bảng
SOURCE database/create_table.sql;

-- Bước 3: Thêm dữ liệu mẫu
SOURCE database/insert_data.sql;
```

### 1.2. Kiểm tra dữ liệu
```sql
USE QuanLyCafe;
SELECT * FROM KhachHang;       -- Xem khách hàng
SELECT * FROM ThucUong;        -- Xem thức uống
SELECT * FROM HoaDon;          -- Xem hóa đơn
```

---

## 🔧 BƯỚC 2: CẤU HÌNH PERSISTENCE

### 2.1. Mở file `src/main/resources/META-INF/persistence.xml`

```xml
<property name="jakarta.persistence.jdbc.password" value="123456"/>
```

**Thay đổi nếu cần**:
- `jdbc.user`: Tên user MySQL (mặc định: `root`)
- `jdbc.password`: Password MySQL (mặc định: `123456`)
- `jdbc.url`: Đường dẫn database (mặc định: `localhost:3306/QuanLyCafe`)

---

## 🏗️ BƯỚC 3: BUILD PROJECT

### 3.1. Trong IntelliJ IDEA:
1. **File** → **Project Structure** → **Project**
2. Đặt **Project SDK** = Java 17
3. **Build** → **Build Project** (Ctrl + F9)

### 3.2. Trong Terminal:
```bash
cd D:\LapTrinhWindow\ProjectCuoiKi

# Nếu có Maven:
mvn clean compile

# Hoặc Build Package:
mvn clean package -DskipTests
```

---

## 🚀 BƯỚC 4: CHẠY ỨNG DỤNG

### 4.1. Từ IntelliJ IDEA:
1. Mở file `src/main/java/com/cafe/AppLauncher.java`
2. **Right-click** → **Run 'AppLauncher.main()'**
3. Hoặc nhấn **Shift + F10**

### 4.2. Giao diện sẽ hiển thị:
- Window: **Hệ Thống Quản Lý Bán Hàng Café** (1400x800)
- 2 Tabs: **Quản Lý Hóa Đơn** + **Báo Cáo**

---

## 📝 BƯỚC 5: HƯỚNG DẪN SỬ DỤNG

### Tab 1: QUẢN LÝ HÓA ĐƠN

#### **Form Nhập Liệu**:
```
┌─────────────────────────────────────────────────────┐
│ Mã Hóa Đơn: [HD__] | Ngày Lập: [2026-04-01      ]  │
│ Khách Hàng: [Nguyễn Văn A▼] | Tổng Tiền: [90000 ] │
│ Ghi Chú: [_________________________________        ] │
│                                                      │
│ [Mới] [Lưu] [Cập Nhật] [Xóa] [Làm Mới]            │
└─────────────────────────────────────────────────────┘
```

#### **Tạo Mới Hóa Đơn**:
1. Click **[Mới]** → Form trống
2. Nhập:
   - **Mã Hóa Đơn**: HD01, HD02, ...
   - **Ngày Lập**: 2026-04-01 (format: yyyy-MM-dd)
   - **Khách Hàng**: Chọn từ dropdown
   - **Tổng Tiền**: Nhập số tiền (VD: 90000)
3. Click **[Lưu]**
4. Message: "Lưu thành công"

#### **Cập Nhật Hóa Đơn**:
1. Click vào hóa đơn trong bảng
2. Chỉnh sửa thông tin
3. Click **[Cập Nhật]**

#### **Xóa Hóa Đơn**:
1. Click vào hóa đơn trong bảng
2. Click **[Xóa]**
3. Xác nhận: "Xóa hóa đơn HD01?"
4. Click **Yes**

#### **Bảng Hiển Thị**:
```
┌──────┬───────────┬──────────────┬─────────┬──────────┐
│ Mã   │ Ngày Lập  │ Khách Hàng   │ Tổng TB │ Ghi Chú  │
├──────┼───────────┼──────────────┼─────────┼──────────┤
│ HD01 │ 2026-03-01│ Nguyễn Văn A │ 90000   │ HD01     │
│ HD02 │ 2026-03-02│ Trần Thị B   │ 120000  │ HD02     │
│ HD03 │ 2026-03-03│ Lê Văn C     │ 80000   │ HD03     │
└──────┴───────────┴──────────────┴─────────┴──────────┘
```

---

### Tab 2: BÁO CÁO

#### **Nút Báo Cáo**:
```
[Tất Cả Hóa Đơn] [Doanh Thu Khách] [Sản Phẩm Bán Chạy] [Thống Kê]
```

#### **1. Tất Cả Hóa Đơn** (Q1: getAllHoaDonWithCustomer)
```
┌──────┬───────────┬──────────────┬─────────┐
│ Mã   │ Ngày      │ Khách Hàng   │ Tổng    │
├──────┼───────────┼──────────────┼─────────┤
│ HD01 │ 2026-03-01│ Nguyễn Văn A │ 90000   │
│ HD02 │ 2026-03-02│ Trần Thị B   │ 120000  │
└──────┴───────────┴──────────────┴─────────┘
```
**Query**: SELECT h FROM HoaDon h LEFT JOIN FETCH h.KhachHang ...

#### **2. Doanh Thu Khách Hàng** (Q5: revenueByCustomer)
```
┌──────────┬──────────────┬────────────────┐
│ Mã KH    │ Tên KH       │ Tổng Doanh Thu │
├──────────┼──────────────┼────────────────┤
│ KH01     │ Nguyễn Văn A │ 200000         │
│ KH02     │ Trần Thị B   │ 150000         │
│ KH03     │ Lê Văn C     │ 80000          │
└──────────┴──────────────┴────────────────┘
```
**Query**: GROUP BY k.MaKhachHang, SUM(h.TongTien)

#### **3. Sản Phẩm Bán Chạy** (Q7: topSellingProducts)
```
┌──────┬──────────────────┬────────────────┐
│ Mã   │ Tên Sản Phẩm     │ Số Lượng Bán   │
├──────┼──────────────────┼────────────────┤
│ TU01 │ Cà phê sữa đá    │ 10             │
│ TU05 │ Matcha đá xay    │ 8              │
│ TU02 │ Cà phê đen đá    │ 5              │
└──────┴──────────────────┴────────────────┘
```
**Query**: GROUP BY t.MaThucUong, SUM(c.SoLuong)

#### **4. Thống Kê** (Q9-Q13)
```
┌──────────────────────────────┬────────────┐
│ Thống Kê                     │ Giá Trị    │
├──────────────────────────────┼────────────┤
│ Tổng số hóa đơn              │ 5          │
│ Giá trị TB hóa đơn           │ 108000     │
│ Giá trị max                  │ 150000     │
│ Giá trị min                  │ 80000      │
│ Tổng doanh thu               │ 540000     │
└──────────────────────────────┴────────────┘
```
**Queries**:
- COUNT(h): Đếm số lượng
- AVG(h.TongTien): Trung bình
- MAX/MIN(h.TongTien): Max/Min
- SUM(h.TongTien): Tổng

---

## 💾 BƯỚC 6: DỮ LIỆU MẪU

### Khách Hàng:
| Mã | Tên | SĐT |
|---|---|---|
| KH01 | Nguyễn Văn A | 0900000001 |
| KH02 | Trần Thị B | 0900000002 |
| KH03 | Lê Văn C | 0900000003 |
| KH04 | Phạm Thị D | 0900000004 |
| KH05 | Hoàng Văn E | 0900000005 |

### Thức Uống (Sản Phẩm):
| Mã | Tên | Giá |
|---|---|---|
| TU01 | Cà phê sữa đá | 30,000 |
| TU02 | Cà phê đen đá | 25,000 |
| TU03 | Trà đào cam sả | 35,000 |
| TU05 | Matcha đá xay | 40,000 |

### Hóa Đơn:
| Mã | Ngày | Khách | Tổng |
|---|---|---|---|
| HD01 | 2026-03-01 | KH01 | 90,000 |
| HD02 | 2026-03-02 | KH02 | 120,000 |
| HD03 | 2026-03-03 | KH03 | 80,000 |
| HD04 | 2026-03-04 | KH04 | 150,000 |
| HD05 | 2026-03-05 | KH05 | 110,000 |

---

## 🐛 BƯỚC 7: TROUBLESHOOTING

### Lỗi 1: "Cannot resolve symbol 'javax'"
**Giải pháp**:
- ✅ Đây là warning từ IDE, project vẫn chạy được
- Click **Build** → **Rebuild Project**
- Hoặc **File** → **Invalidate Caches**

### Lỗi 2: "java.sql.SQLException: Unknown database"
**Giải pháp**:
- ✅ Kiểm tra database `QuanLyCafe` có tồn tại không
- ✅ Chạy lại `create_database.sql`
- ✅ Kiểm tra MySQL đang chạy

### Lỗi 3: "Access denied for user 'root'@'localhost'"
**Giải pháp**:
- ✅ Cập nhật password trong `persistence.xml`:
```xml
<property name="jakarta.persistence.jdbc.password" value="YOUR_REAL_PASSWORD"/>
```

### Lỗi 4: "Cannot find main AppLauncher"
**Giải pháp**:
- ✅ Rebuild project: Ctrl + F9
- ✅ Check package structure: `com.cafe.AppLauncher`
- ✅ Chạy trực tiếp từ file AppLauncher.java

### Lỗi 5: "EntityManager is closed"
**Giải pháp**:
- ✅ Đảm bảo database kết nối thành công
- ✅ Kiểm tra JDBC URL trong persistence.xml
- ✅ Restart ứng dụng

---

## 📚 BƯỚC 8: TÌM HIỂU KIẾN TRÚC

### File quan trọng:
1. **AppLauncher.java** - Entry point
2. **HoaDonController.java** - Logic CRUD + Báo cáo
3. **HoaDonManagementPanel.java** - Giao diện
4. **ReportDAOImpl.java** - 15 JPQL queries
5. **persistence.xml** - Hibernate config

### Luồng dữ liệu:
```
User nhấp button
    ↓
HoaDonManagementPanel (View)
    ↓
HoaDonController (Controller)
    ↓
HoaDonService / ReportService (Service)
    ↓
HoaDonDAOImpl / ReportDAOImpl (DAO)
    ↓
HibernateUtil → EntityManager
    ↓
MySQL Database
```

---

## 🔄 BƯỚC 9: THÊM DỮ LIỆU MỚI

### Cách 1: Từ Giao Diện:
1. Click **[Mới]**
2. Nhập Mã: HD06
3. Nhập Ngày: 2026-04-01
4. Chọn Khách: KH01
5. Nhập Tổng: 95000
6. Click **[Lưu]**

### Cách 2: Từ SQL:
```sql
INSERT INTO HoaDon VALUES
('HD06', '2026-04-01', 95000, 'KH01', 'B01', 'TT01', 'NV01');

INSERT INTO ChiTietHoaDon VALUES
('HD06', 'TU01', 3);
```

---

## 📊 BƯỚC 10: HIỂU 15 JPQL QUERIES

### Q1-Q4: Tra cứu
- Q1: Tất cả hóa đơn
- Q2: Hóa đơn theo mã
- Q3: Hóa đơn theo tên khách
- Q4: Hóa đơn theo ngày

### Q5-Q8: Sản phẩm
- Q5: Doanh thu theo khách
- Q6: Doanh thu theo tháng
- Q7: Sản phẩm bán chạy
- Q8: Sản phẩm doanh thu cao

### Q9-Q15: Thống kê
- Q9: Đếm số hóa đơn
- Q10: Trung bình
- Q11: Max
- Q12: Min
- Q13: Tổng
- Q14: Khách chưa mua
- Q15: Hóa đơn nhiều chi tiết

---

## ✨ MẸO & KINH NGHIỆM

1. **Backup Database**:
   ```sql
   mysqldump -u root -p QuanLyCafe > backup.sql
   ```

2. **Xem SQL được chạy**:
   - `hibernate.show_sql = true` đã bật
   - Log SQL hiển thị trong console

3. **Thêm Entity mới**:
   - Tạo @Entity class
   - Tạo DAO interface + Impl
   - Tạo Service interface + Impl
   - Thêm vào persistence.xml

4. **Tối ưu Performance**:
   - Dùng `LEFT JOIN FETCH` để eager load
   - Giới hạn kết quả: `.setMaxResults(10)`
   - Index bảng lớn

---

## 📞 HỖ TRỢ

Nếu gặp lỗi:
1. Check log trong console
2. Verify database connection
3. Rebuild project
4. Restart IDE
5. Clear cache: `File` → `Invalidate Caches`

---

**Chúc bạn sử dụng ứng dụng thành công! 🎉**


