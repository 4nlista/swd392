Họ và tên: Nguyễn Bảo An
Mã sinh viên: HE182167

Môn học: SWD392 - Software Architecture and Design

Đề tài:
J2.S.P0113 - Management Book

Mô tả:
Dự án xây dựng ứng dụng quản lý sách trên nền tảng Java Desktop (Java Swing), áp dụng kiến trúc Model - View - Controller (MVC). Hệ thống đáp ứng đầy đủ các chức năng theo yêu cầu của đề J2.S.P0113.

Kiến trúc sử dụng:
- Model - View - Controller (MVC)
- Observer Pattern (Model tự động notify View khi dữ liệu thay đổi)

Cấu trúc project:
- controller: Điều phối yêu cầu từ View đến Model, xử lý nghiệp vụ.
- model: Quản lý dữ liệu và danh sách sách.
- observer: Chứa interface Observer phục vụ Observer Pattern.
- view: Giao diện người dùng (Java Swing), xử lý hiển thị và kiểm tra dữ liệu đầu vào.
- utils: Chứa các hàm tiện ích dùng để kiểm tra dữ liệu đầu vào.
- main: Khởi tạo chương trình, kết nối các thành phần của hệ thống.

Nguyên tắc thiết kế:
- Áp dụng mô hình MVC để tách biệt giao diện, xử lý và dữ liệu.
- Áp dụng Observer Pattern để View tự động cập nhật khi Model thay đổi.
- Tuân thủ các nguyên tắc SOLID (Single Responsibility, Dependency Injection, Separation of Concerns và Low Coupling).

Chức năng:
- Thêm sách.
- Cập nhật thông tin sách.
- Xóa sách.
- Hiển thị danh sách sách.
- Validate dữ liệu đầu vào trước khi xử lý.

Sơ đồ thiết kế: (XEM TRONG THƯ MỤC "docs" CỦA TẬP TÀI LIỆU)
- MVC Class Diagram.
- MVC Sequence Diagram.
- Observer Pattern Class Diagram.
- Design Pattern Class Diagram.

Ghi chú:
- Project được phát triển bằng Java Swing trên NetBeans.
- Source code đã được tổ chức theo kiến trúc MVC.
- Observer Pattern được sử dụng để Model tự động notify View.
- Validation được thực hiện tại View trước khi chuyển yêu cầu đến Controller.
