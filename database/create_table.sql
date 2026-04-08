USE QuanLyCafe;

-- Loại thức uống
CREATE TABLE LoaiThucUong (
    MaLoai CHAR(5) PRIMARY KEY,
    TenLoaiThucUong VARCHAR(100)
);

-- Thức uống
CREATE TABLE ThucUong (
    MaThucUong CHAR(5) PRIMARY KEY,
    TenThucUong VARCHAR(100) NOT NULL,
    Gia DECIMAL(10,2) NOT NULL,
    MaLoai CHAR(5) NOT NULL,
    Url VARCHAR(50) NOT NULL UNIQUE,
    FOREIGN KEY (MaLoai) REFERENCES 
);

-- Công thức
CREATE TABLE CongThuc (
    MaCongThuc CHAR(5) PRIMARY KEY,
    TenCongThuc VARCHAR(100),
    MaThucUong CHAR(5),
    FOREIGN KEY (MaThucUong) REFERENCES ThucUong(MaThucUong)
);

-- Nguyên liệu
CREATE TABLE NguyenLieu (
    MaNguyenLieu CHAR(5) PRIMARY KEY,
    TenNguyenLieu VARCHAR(100),
    SoLuong INT
);

-- Khách hàng
CREATE TABLE KhachHang (
    MaKhachHang CHAR(5) PRIMARY KEY,
    TenKhachHang VARCHAR(100),
    SoDienThoai VARCHAR(15)
);

-- Bàn
CREATE TABLE Ban (
    MaBan CHAR(5) PRIMARY KEY
);

-- Phương thức thanh toán
CREATE TABLE PhuongThucThanhToan (
    MaThanhToan CHAR(5) PRIMARY KEY,
    TenThanhToan VARCHAR(100)
);

-- Nhân viên
CREATE TABLE NhanVien (
    MaNhanVien CHAR(5) PRIMARY KEY,
    TenNhanVien VARCHAR(100),
    NgaySinh DATE,
    DiaChi VARCHAR(255),
    SoDienThoai VARCHAR(15),
    Luong DECIMAL(10,2)
);

-- Hóa đơn
CREATE TABLE HoaDon (
    MaHoaDon CHAR(5) PRIMARY KEY,
    NgayLap DATE,
    TongTien DECIMAL(10,2),
    MaKhachHang CHAR(5),
    MaBan CHAR(5),
    MaThanhToan CHAR(5),
    MaNhanVien CHAR(5),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan),
    FOREIGN KEY (MaThanhToan) REFERENCES PhuongThucThanhToan(MaThanhToan),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

-- Nhà cung cấp
CREATE TABLE NhaCungCap (
    MaNhaCungCap CHAR(5) PRIMARY KEY,
    TenNhaCungCap VARCHAR(100),
    DiaChi VARCHAR(255),
    SoDienThoai VARCHAR(15)
);

-- Phiếu nhập
CREATE TABLE PhieuNhap (
    MaPhieuNhap CHAR(5) PRIMARY KEY,
    NgayNhap DATE,
    TongTien DECIMAL(10,2),
    MaNhanVien CHAR(5),
    MaNhaCungCap CHAR(5),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaNhaCungCap) REFERENCES NhaCungCap(MaNhaCungCap)
);

-- Hóa đơn - Thức uống
CREATE TABLE ChiTietHoaDon (
    MaHoaDon CHAR(5),
    MaThucUong CHAR(5),
    SoLuong INT,
    PRIMARY KEY (MaHoaDon, MaThucUong),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaThucUong) REFERENCES ThucUong(MaThucUong)
);

-- Công thức - Nguyên liệu
CREATE TABLE ChiTietCongThuc (
    MaCongThuc CHAR(5),
    MaNguyenLieu CHAR(5),
    SoLuong INT,
    PRIMARY KEY (MaCongThuc, MaNguyenLieu),
    FOREIGN KEY (MaCongThuc) REFERENCES CongThuc(MaCongThuc),
    FOREIGN KEY (MaNguyenLieu) REFERENCES NguyenLieu(MaNguyenLieu)
);

-- Phiếu nhập - Nguyên liệu
CREATE TABLE ChiTietPhieuNhap (
    MaPhieuNhap CHAR(5),
    MaNguyenLieu CHAR(5),
    SoLuong INT,
    Gia DECIMAL(10,2),
    PRIMARY KEY (MaPhieuNhap, MaNguyenLieu),
    FOREIGN KEY (MaPhieuNhap) REFERENCES PhieuNhap(MaPhieuNhap),
    FOREIGN KEY (MaNguyenLieu) REFERENCES NguyenLieu(MaNguyenLieu)
);

-- Tài khoản
CREATE TABLE TaiKhoan (
    TenDangNhap VARCHAR(50) PRIMARY KEY,
    MatKhau VARCHAR(50),
    Quyen VARCHAR(50),
    MaNhanVien CHAR(5),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);
