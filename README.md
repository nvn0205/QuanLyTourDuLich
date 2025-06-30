# Phần Mềm Quản Lý Tour Du Lịch 🌍✈️

## Giới thiệu
Phần mềm quản lý tour du lịch được xây dựng bằng Java Swing, giúp quản lý các hoạt động của công ty du lịch một cách hiệu quả và chuyên nghiệp.

## Tính năng chính 🎯
1. **Quản lý Tour**
   - Thêm, sửa, xóa thông tin tour
   - Quản lý lịch trình, giá cả
   - Theo dõi trạng thái tour

2. **Quản lý Đặt Tour**
   - Đặt tour cho khách hàng
   - Theo dõi thanh toán
   - Quản lý trạng thái đặt tour

3. **Quản lý Khách Hàng**
   - Thông tin khách hàng
   - Lịch sử đặt tour
   - Quản lý liên hệ

4. **Quản lý Nhân Viên**
   - Thông tin nhân viên
   - Phân quyền người dùng
   - Quản lý chức vụ

5. **Quản lý Đối Tác**
   - Thông tin đối tác
   - Phân loại đối tác
   - Quản lý hợp tác

## Công nghệ sử dụng 🛠️
- **Ngôn ngữ:** Java
- **GUI Framework:** Java Swing
- **Database:** MySQL
- **IDE:** NetBeans
- **Build Tool:** Ant
- **Java Version:** JDK 23

## Cấu trúc project 📁
```
src/
├── BLL/         # Business Logic Layer
├── DAL/         # Data Access Layer
├── Entities/    # Entity Classes
└── View/        # User Interface
    ├── component/   # Reusable Components
    └── Login/       # Login Related Views
```

## Phân quyền người dùng 👥
1. **Quản lý**
   - Truy cập tất cả chức năng
   - Thêm/sửa/xóa tất cả dữ liệu
   - Quản lý nhân viên

2. **Nhân viên**
   - Xem tất cả thông tin (trừ quản lý nhân viên)
   - Thêm/sửa/xóa đặt tour và khách hàng
   - Chỉ xem thông tin tour và đối tác

## Hướng dẫn cài đặt 📥
1. **Yêu cầu hệ thống**
   - JDK 23 trở lên
   - MySQL Server
   - NetBeans IDE (khuyến nghị)

2. **Các bước cài đặt**
   ```bash
   # Clone project
   git clone [repository-url]

   # Import database
   mysql -u root -p < database/quanlytourdulich.sql

   # Mở project trong NetBeans
   File > Open Project > Chọn thư mục project

   # Build và chạy
   Click Run Project hoặc nhấn F6
   ```

3. **Tài khoản mặc định**
   - Admin: admin/123456
   - Nhân viên: nguyen/123123

