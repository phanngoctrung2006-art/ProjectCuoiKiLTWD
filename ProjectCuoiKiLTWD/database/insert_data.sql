USE quanlycafe;

-- Loại thức uống
INSERT INTO LoaiThucUong VALUES
('L01','Cà phê'),
('L02','Trà'),
('L03','Đá xay'),
('L04','Nước ép'),
('L05','Trà sữa');

-- Thức uống
INSERT INTO ThucUong VALUES
('TU01','Cà phê sữa đá',30000,'L01'),
('TU02','Cà phê đen đá',25000,'L01'),
('TU03','Trà đào cam sả',35000,'L02'),
('TU04','Trà vải',32000,'L02'),
('TU05','Matcha đá xay',40000,'L03'),
('TU06','Socola đá xay',42000,'L03'),
('TU07','Nước ép cam',30000,'L04'),
('TU08','Nước ép dưa hấu',28000,'L04'),
('TU09','Trà sữa trân châu',45000,'L05'),
('TU10','Trà sữa matcha',47000,'L05');

-- Công thức
INSERT INTO CongThuc VALUES
('CT01','CF sữa đá','TU01'),
('CT02','CF đen đá','TU02'),
('CT03','Trà đào','TU03'),
('CT04','Trà vải','TU04'),
('CT05','Matcha xay','TU05');

-- Nguyên liệu
INSERT INTO NguyenLieu VALUES
('NL01','Cà phê',200),
('NL02','Sữa đặc',150),
('NL03','Trà',180),
('NL04','Đào',100),
('NL05','Vải',90),
('NL06','Matcha',120),
('NL07','Socola',130),
('NL08','Cam',140),
('NL09','Dưa hấu',110),
('NL10','Trân châu',160);

-- Khách hàng
INSERT INTO KhachHang VALUES
('KH01','Nguyễn Văn A','0900000001'),
('KH02','Trần Thị B','0900000002'),
('KH03','Lê Văn C','0900000003'),
('KH04','Phạm Thị D','0900000004'),
('KH05','Hoàng Văn E','0900000005');

-- Bàn
INSERT INTO Ban VALUES
('B01'),('B02'),('B03'),('B04'),('B05');

-- Thanh toán
INSERT INTO PhuongThucThanhToan VALUES
('TT01','Tiền mặt'),
('TT02','Chuyển khoản'),
('TT03','Momo'),
('TT04','ZaloPay'),
('TT05','Thẻ');

-- Nhân viên
INSERT INTO NhanVien VALUES
('NV01','Nguyễn Văn Nam','1998-01-01','Hà Nội','0911111111',6000000),
('NV02','Trần Thị Lan','1999-02-02','HCM','0922222222',6500000),
('NV03','Lê Văn Bình','2000-03-03','Đà Nẵng','0933333333',5500000),
('NV04','Phạm Thị Hoa','1997-04-04','Hải Phòng','0944444444',7000000),
('NV05','Hoàng Minh','1996-05-05','Cần Thơ','0955555555',7200000);

-- Hóa đơn
INSERT INTO HoaDon VALUES
('HD01','2025-03-01',90000,'KH01','B01','TT01','NV01'),
('HD02','2025-03-02',120000,'KH02','B02','TT02','NV02'),
('HD03','2025-03-03',80000,'KH03','B03','TT03','NV03'),
('HD04','2025-03-04',150000,'KH04','B04','TT04','NV04'),
('HD05','2025-03-05',110000,'KH05','B05','TT05','NV05');

-- Nhà cung cấp
INSERT INTO NhaCungCap VALUES
('NCC01','NCC Cà phê Việt','Hà Nội','0966666666'),
('NCC02','NCC Trà sạch','HCM','0977777777'),
('NCC03','NCC Trái cây','Đà Nẵng','0988888888'),
('NCC04','NCC Nguyên liệu','Hải Phòng','0999999999'),
('NCC05','NCC Tổng hợp','Cần Thơ','0901234567');

-- Phiếu nhập
INSERT INTO PhieuNhap VALUES
('PN01','2025-03-01',200000,'NV01','NCC01'),
('PN02','2025-03-02',300000,'NV02','NCC02'),
('PN03','2025-03-03',250000,'NV03','NCC03'),
('PN04','2025-03-04',400000,'NV04','NCC04'),
('PN05','2025-03-05',350000,'NV05','NCC05');

-- Chi tiết hóa đơn
INSERT INTO ChiTietHoaDon VALUES
('HD01','TU01',2),
('HD01','TU03',1),
('HD02','TU05',2),
('HD03','TU02',2),
('HD04','TU06',3),
('HD05','TU09',2);

-- Chi tiết công thức
INSERT INTO ChiTietCongThuc VALUES
('CT01','NL01',10),
('CT01','NL02',5),
('CT02','NL01',8),
('CT03','NL03',10),
('CT03','NL04',5),
('CT04','NL05',6),
('CT05','NL06',7);

-- Chi tiết phiếu nhập
INSERT INTO ChiTietPhieuNhap VALUES
('PN01','NL01',50,1000),
('PN01','NL02',40,2000),
('PN02','NL03',60,1500),
('PN03','NL04',30,2500),
('PN04','NL06',45,3000),
('PN05','NL08',70,2000);