-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 01, 2025 lúc 07:07 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dulich`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dattour`
--

CREATE TABLE `dattour` (
  `MaDatTour` varchar(10) NOT NULL,
  `MaKH` varchar(10) NOT NULL,
  `MaTour` varchar(10) NOT NULL,
  `NgayDat` date NOT NULL DEFAULT curdate(),
  `SoLuongNguoi` int(11) NOT NULL CHECK (`SoLuongNguoi` > 0),
  `TongTien` decimal(15,2) NOT NULL,
  `SoTienDaThanhToan` decimal(15,2) NOT NULL DEFAULT 0.00,
  `MaDT` varchar(10) NOT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `TrangThai` text DEFAULT 'Chờ xác nhận'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dattour`
--

INSERT INTO `dattour` (`MaDatTour`, `MaKH`, `MaTour`, `NgayDat`, `SoLuongNguoi`, `TongTien`, `SoTienDaThanhToan`, `MaDT`, `MaNV`, `TrangThai`) VALUES
('DT001', 'KH001', 'T001', '2025-05-20', 2, 5000000.00, 2500000.00, 'DT001', 'NV001', 'Chờ xác nhận'),
('DT002', 'KH002', 'T002', '2025-05-21', 3, 9000000.00, 4500000.00, 'DT002', 'NV002', 'Đã xác nhận'),
('DT003', 'KH003', 'T003', '2025-05-22', 1, 2800000.00, 0.00, 'DT003', 'NV003', 'Chờ xác nhận'),
('DT004', 'KH004', 'T004', '2025-05-23', 4, 14000000.00, 7000000.00, 'DT004', 'NV004', 'Đã xác nhận'),
('DT005', 'KH005', 'T005', '2025-05-24', 2, 6400000.00, 3200000.00, 'DT005', 'NV005', 'Chờ xác nhận'),
('DT006', 'KH006', 'T006', '2025-05-25', 3, 8100000.00, 4050000.00, 'DT006', 'NV006', 'Đã xác nhận'),
('DT007', 'KH007', 'T007', '2025-05-26', 2, 8000000.00, 4000000.00, 'DT007', 'NV007', 'Chờ xác nhận'),
('DT008', 'KH008', 'T008', '2025-05-27', 1, 2900000.00, 0.00, 'DT008', 'NV008', 'Chờ xác nhận'),
('DT009', 'KH009', 'T009', '2025-05-28', 2, 9000000.00, 4500000.00, 'DT009', 'NV009', 'Đã xác nhận'),
('DT010', 'KH010', 'T010', '2025-05-29', 3, 6000000.00, 3000000.00, 'DT010', 'NV010', 'Chờ xác nhận');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doitac`
--

CREATE TABLE `doitac` (
  `MaDT` varchar(10) NOT NULL,
  `TenDT` varchar(255) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `LoaiDoiTac` varchar(50) DEFAULT NULL,
  `GhiChu` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `doitac`
--

INSERT INTO `doitac` (`MaDT`, `TenDT`, `SDT`, `Email`, `DiaChi`, `LoaiDoiTac`, `GhiChu`) VALUES
('DT001', 'Công ty Du lịch Sài Gòn', '0923456701', 'saigontravel@gmail.com', '123 Lê Lợi, TP.HCM', 'Đại lý tour', 'Chuyên tour miền Nam'),
('DT002', 'Hanoi Tourism', '0923456702', 'hanoitourism@gmail.com', '45 Nguyễn Trãi, Hà Nội', 'Đại lý tour', 'Chuyên tour miền Bắc'),
('DT003', 'Đà Nẵng Travel', '0923456703', 'danangtravel@gmail.com', '78 Hùng Vương, Đà Nẵng', 'Đại lý tour', 'Chuyên tour miền Trung'),
('DT004', 'Khách sạn Biển Xanh', '0923456704', 'bluehotel@gmail.com', '12 Trần Phú, Nha Trang', 'Khách sạn', 'Hợp tác cung cấp chỗ ở'),
('DT005', 'Nhà hàng Hương Huế', '0923456705', 'huonghue@gmail.com', '56 Lý Thường Kiệt, Huế', 'Nhà hàng', 'Cung cấp suất ăn'),
('DT006', 'Cần Thơ Tour', '0923456706', 'canthotour@gmail.com', '89 Nguyễn Huệ, Cần Thơ', 'Đại lý tour', 'Chuyên tour miền Tây'),
('DT007', 'Khách sạn Sông Hàn', '0923456707', 'songhanhotel@gmail.com', '34 Phạm Ngũ Lão, Đà Nẵng', 'Khách sạn', 'Hợp tác lâu dài'),
('DT008', 'Du lịch Đà Lạt', '0923456708', 'dalattravel@gmail.com', '67 Lê Đại Hành, Đà Lạt', 'Đại lý tour', 'Chuyên tour cao nguyên'),
('DT009', 'Nhà hàng Hải Sản Vinh', '0923456709', 'vinhseafood@gmail.com', '101 Nguyễn Văn Cừ, Vinh', 'Nhà hàng', 'Cung cấp hải sản tươi'),
('DT010', 'Khách sạn Sài Gòn', '0923456710', 'saigonhotel@gmail.com', '123 Hai Bà Trưng, TP.HCM', 'Khách sạn', 'Hợp tác chỗ ở');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(10) NOT NULL,
  `HoTen` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `HoTen`, `SDT`, `Email`, `NgaySinh`, `DiaChi`) VALUES
('KH001', 'Nguyễn Văn An', '0901234561', 'an.nguyen@gmail.com', '1990-05-15', '123 Nguyễn Trãi, Hà Nội'),
('KH002', 'Trần Thị Bình', '0901234562', 'binh.tran@gmail.com', '1995-08-20', '45 Lê Lợi, TP.HCM'),
('KH003', 'Lê Minh Châu', '0901234563', 'chau.le@gmail.com', '1988-03-10', '78 Hùng Vương, Đà Nẵng'),
('KH004', 'Phạm Quốc Dũng', '0901234564', 'dung.pham@gmail.com', '1992-11-25', '12 Trần Phú, Nha Trang'),
('KH005', 'Hoàng Thị Mai', '0901234565', 'mai.hoang@gmail.com', '1997-07-30', '56 Lý Thường Kiệt, Huế'),
('KH006', 'Đỗ Văn Nam', '0901234566', 'nam.do@gmail.com', '1993-09-12', '89 Nguyễn Huệ, Cần Thơ'),
('KH007', 'Vũ Thị Lan', '0901234567', 'lan.vu@gmail.com', '1990-12-05', '23 Phạm Ngũ Lão, Hà Nội'),
('KH008', 'Ngô Quang Huy', '0901234568', 'huy.ngo@gmail.com', '1985-04-18', '67 Lê Đại Hành, Đà Lạt'),
('KH009', 'Bùi Thị Thảo', '0901234569', 'thao.bui@gmail.com', '1996-06-22', '34 Nguyễn Văn Cừ, Vinh'),
('KH010', 'Trương Văn Long', '0901234570', 'long.truong@gmail.com', '1994-10-10', '101 Hai Bà Trưng, TP.HCM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `HoTen` varchar(100) NOT NULL,
  `DiaChi` text DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` enum('Nam','Nữ') DEFAULT NULL,
  `SDT` varchar(15) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `ChucVu` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `DiaChi`, `NgaySinh`, `GioiTinh`, `SDT`, `Email`, `ChucVu`) VALUES
('NV001', 'Nguyễn Thành Đạt', '12 Nguyễn Huệ, Hà Nội', '1988-02-14', 'Nam', '0912345601', 'dat.nguyen@gmail.com', 'Quản lý'),
('NV002', 'Trần Thị Hương', '45 Lê Lợi, TP.HCM', '1990-06-25', 'Nữ', '0912345602', 'huong.tran@gmail.com', 'Nhân viên'),
('NV003', 'Lê Văn Hùng', '78 Hùng Vương, Đà Nẵng', '1985-09-10', 'Nam', '0912345603', 'hung.le@gmail.com', 'Quản lý'),
('NV004', 'Phạm Thị Ngọc', '23 Trần Phú, Nha Trang', '1992-03-20', 'Nữ', '0912345604', 'ngoc.pham@gmail.com', 'Nhân viên'),
('NV005', 'Hoàng Minh Tuấn', '56 Lý Thường Kiệt, Huế', '1990-11-15', 'Nam', '0912345605', 'tuan.hoang@gmail.com', 'Nhân viên'),
('NV006', 'Đỗ Thị Lan', '89 Nguyễn Huệ, Cần Thơ', '1995-07-30', 'Nữ', '0912345606', 'lan.do@gmail.com', 'Nhân viên'),
('NV007', 'Nông Văn Nguyên', '34 Phạm Ngũ Lão, Hà Nội', '1987-04-12', 'Nam', '0912345607', 'anh.vu@gmail.com', 'Nhân viên'),
('NV008', 'Ngô Thị Mai', '67 Lê Đại Hành, Đà Lạt', '1993-08-05', 'Nữ', '0912345608', 'mai.ngo@gmail.com', 'Nhân viên'),
('NV009', 'Bùi Văn Khánh', '101 Nguyễn Văn Cừ, Vinh', '1989-12-22', 'Nam', '0912345609', 'khanh.bui@gmail.com', 'Nhân viên'),
('NV010', 'Trương Thị Hồng', '123 Hai Bà Trưng, TP.HCM', '1994-05-18', 'Nữ', '0912345610', 'hong.truong@gmail.com', 'Nhân viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` int(11) NOT NULL,
  `TenDangNhap` varchar(50) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `MaNV` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaTK`, `TenDangNhap`, `MatKhau`, `MaNV`) VALUES
(1, 'admin', '123456', 'NV001'),
(2, 'huongtran', 'pass456', 'NV002'),
(3, 'hungle', 'secure789', 'NV003'),
(4, 'ngocpham', 'ngoc2025', 'NV004'),
(5, 'tuanh', 'tuan1234', 'NV005'),
(6, 'lando', 'lan5678', 'NV006'),
(7, 'nguyen', '123123', 'NV007'),
(8, 'maingo', 'mai7890', 'NV008'),
(9, 'khanhbui', 'khanh123', 'NV009'),
(10, 'hongt', 'hong456', 'NV010');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `MaTour` varchar(10) NOT NULL,
  `TenTour` varchar(255) NOT NULL,
  `DiaDiem` varchar(255) NOT NULL,
  `NgayKhoiHanh` date NOT NULL,
  `NgayKetThuc` date DEFAULT NULL,
  `SoNguoi` int(11) NOT NULL,
  `Gia` decimal(10,2) NOT NULL,
  `TrangThai` text DEFAULT NULL,
  `MoTa` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`MaTour`, `TenTour`, `DiaDiem`, `NgayKhoiHanh`, `NgayKetThuc`, `SoNguoi`, `Gia`, `TrangThai`, `MoTa`) VALUES
('T001', 'Tour Hà Nội - Hạ Long', 'Vịnh Hạ Long', '2025-06-01', '2025-06-03', 30, 2500000.00, 'Mở', 'Tham quan Vịnh Hạ Long, chèo thuyền kayak'),
('T002', 'Tour Sài Gòn - Cần Thơ', 'Cần Thơ', '2025-06-05', '2025-06-07', 25, 3000000.00, 'Mở', 'Khám phá chợ nổi Cái Răng'),
('T003', 'Tour Đà Nẵng - Hội An', 'Hội An', '2025-06-10', '2025-06-12', 20, 2800000.00, 'Mở', 'Tham quan phố cổ Hội An, cầu Rồng'),
('T004', 'Tour Nha Trang', 'Nha Trang', '2025-06-15', '2025-06-18', 35, 3500000.00, 'Mở', 'Lặn biển ngắm san hô'),
('T005', 'Tour Huế - Phong Nha', 'Phong Nha', '2025-06-20', '2025-06-22', 15, 3200000.00, 'Mở', 'Khám phá hang động Phong Nha'),
('T006', 'Tour Đà Lạt', 'Đà Lạt', '2025-06-25', '2025-06-27', 25, 2700000.00, 'Mở', 'Tham quan hồ Xuân Hương, đồi chè'),
('T007', 'Tour Phú Quốc', 'Phú Quốc', '2025-07-01', '2025-07-04', 30, 4000000.00, 'Mở', 'Thư giãn tại bãi Sao, làng chài'),
('T008', 'Tour Sapa', 'Sapa', '2025-07-05', '2025-07-07', 20, 2900000.00, 'Mở', 'Khám phá núi Hàm Rồng, bản Cát Cát'),
('T009', 'Tour Côn Đảo', 'Côn Đảo', '2025-07-10', '2025-07-13', 15, 4500000.00, 'Mở', 'Tham quan nhà tù Côn Đảo'),
('T010', 'Tour Hà Nội - Ninh Bình', 'Ninh Bình', '2025-07-15', '2025-07-16', 25, 2000000.00, 'Mở', 'Tham quan Tràng An, chùa Bái Đính');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `dattour`
--
ALTER TABLE `dattour`
  ADD PRIMARY KEY (`MaDatTour`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaTour` (`MaTour`),
  ADD KEY `MaDT` (`MaDT`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `doitac`
--
ALTER TABLE `doitac`
  ADD PRIMARY KEY (`MaDT`),
  ADD UNIQUE KEY `SDT` (`SDT`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`),
  ADD UNIQUE KEY `SDT` (`SDT`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`),
  ADD UNIQUE KEY `SDT` (`SDT`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`MaTour`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTK` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `dattour`
--
ALTER TABLE `dattour`
  ADD CONSTRAINT `dattour_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `dattour_ibfk_2` FOREIGN KEY (`MaTour`) REFERENCES `tour` (`MaTour`),
  ADD CONSTRAINT `dattour_ibfk_3` FOREIGN KEY (`MaDT`) REFERENCES `doitac` (`MaDT`),
  ADD CONSTRAINT `dattour_ibfk_4` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE SET NULL;

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
