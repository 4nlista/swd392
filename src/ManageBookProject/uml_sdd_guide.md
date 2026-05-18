# Hướng Dẫn Vẽ UML và Làm Tài Liệu Báo Cáo (SRS & SDD)

Dưới đây là các sơ đồ UML nháp được viết bằng cú pháp Mermaid. 
👉 **Cách xem ảnh:** Bạn hãy copy toàn bộ khối chữ nằm trong các dải ````mermaid ... ```` và dán vào trang [mermaid.live](https://mermaid.live) để xem hình ảnh nhé. Bạn dùng hình này làm bản nháp để đối chiếu khi vẽ lại trên Visual Paradigm.

---

## 1. Sơ Đồ Use Case (Use Case Diagram)
*Dán vào phần Đặc tả yêu cầu của tài liệu SRS.*

```mermaid
usecaseDiagram
actor "Thủ thư / Người dùng" as User

rectangle "Hệ thống Manage Book" {
    usecase "Thêm sách mới" as UC1
    usecase "Cập nhật sách" as UC2
    usecase "Xóa sách" as UC3
    usecase "Xem danh sách sách" as UC4
    usecase "Kiểm tra tính hợp lệ (Validate)" as UC5
}

User --> UC1
User --> UC2
User --> UC3
User --> UC4

UC1 ..> UC5 : <<include>>
UC2 ..> UC5 : <<include>>
```

---

## 2. Sơ Đồ Lớp (Class Diagram)
*Dán vào phần Thiết kế cấu trúc tĩnh của tài liệu SDD.*

```mermaid
classDiagram
    direction LR

    class Book {
        -String code
        -String name
        -String author
        -String publisher
        -int year
        -boolean forRent
        +Book()
        +Book(code, name, author, publisher, year, forRent)
        +getCode() String
        +getName() String
        +getAuthor() String
        +getPublisher() String
        +getYear() int
        +isForRent() boolean
        +toString() String
    }

    class BookController {
        -ArrayList~Book~ listBook
        +BookController()
        +addBook(Book b) void
        +updateBook(int index, Book b) void
        +removeBook(int index) void
        +isCodeExisted(String code) boolean
        +getList() ArrayList~Book~
        +getBook(int index) Book
        +getSampleData() void
    }

    class ValidationUtils {
        <<Utility>>
        +isNotEmpty(String... values) boolean$
        +isValidYear(String year) boolean$
        +isCodeDuplicated(String code, BookController ctrl, int editingIndex) boolean$
    }

    class BookGUI {
        -BookController controller
        -DefaultListModel~Book~ bookListModel
        +BookGUI(BookController controller)
        -displayBook(Book b) void
        -clearForm() void
        -refreshList() void
        -btnSaveActionPerformed(evt) void
        -btnNewActionPerformed(evt) void
        -btnRemoveActionPerformed(evt) void
        -listBooksValueChanged(evt) void
    }

    class Main {
        +main(String[] args)$
    }

    BookController "1" o-- "*" Book : Quản lý >
    BookGUI --> BookController : Gọi nghiệp vụ >
    BookGUI ..> ValidationUtils : Sử dụng >
    Main ..> BookController : Khởi tạo >
    Main ..> BookGUI : Khởi tạo & Inject >
```

---

## 3. Sơ Đồ Tuần Tự (Sequence Diagram) - Luồng Thêm Sách
*Dán vào phần Thiết kế hành vi động của tài liệu SDD.*

```mermaid
sequenceDiagram
    autonumber
    actor User as Người Dùng
    participant GUI as BookGUI (View)
    participant Utils as ValidationUtils
    participant Ctrl as BookController
    
    User->>GUI: Nhập thông tin & Nhấn "Save"
    activate GUI
    
    GUI->>Utils: isNotEmpty(code, name, author, pub)
    activate Utils
    Utils-->>GUI: true (Dữ liệu không rỗng)
    deactivate Utils
    
    GUI->>Utils: isCodeDuplicated(code, ctrl, -1)
    activate Utils
    Utils-->>GUI: false (Không trùng mã)
    deactivate Utils
    
    GUI->>Ctrl: addBook(new Book(...))
    activate Ctrl
    Ctrl-->>GUI: void (Lưu vào ArrayList thành công)
    deactivate Ctrl
    
    GUI->>Ctrl: getList()
    activate Ctrl
    Ctrl-->>GUI: ArrayList<Book>
    deactivate Ctrl
    
    GUI->>GUI: refreshList() (Cập nhật JList)
    GUI-->>User: Hiển thị popup "Thêm mới thành công!"
    deactivate GUI
```

---

## 📚 Dàn Ý Copy-Paste Cho Tài Liệu Báo Cáo Của Bạn

### PHẦN 1: SRS (Software Requirements Specification)

**1.1. Mục đích dự án:**
Phần mềm **Manage Book** là ứng dụng Desktop (Java Swing) giúp thủ thư quản lý thông tin các đầu sách trong thư viện (Mã, tên, tác giả, nhà xuất bản, năm, tình trạng cho thuê).

**1.2. Yêu cầu chức năng (Functional):**
*   **Quản lý danh sách:** Xem toàn bộ danh sách sách ở khung bên trái. Bấm vào sách sẽ hiển thị chi tiết sang form bên phải.
*   **Thêm sách mới:** Khởi tạo form trống, nhập liệu, kiểm tra ràng buộc (không rỗng, đúng định dạng năm, không trùng mã sách) và lưu vào hệ thống.
*   **Cập nhật sách:** Chỉnh sửa thông tin sách đang chọn và lưu lại. Tự động bỏ qua lỗi trùng mã nếu mã không thay đổi.
*   **Xóa sách:** Xóa sách đang được chọn trên lưới.

**1.3. Yêu cầu phi chức năng (Non-Functional):**
*   Kiến trúc: **MVC (Model - View - Controller)**.
*   Công nghệ: Java Core, Swing UI. Không sử dụng Framework/Database ngoài. Dữ liệu được quản lý in-memory bằng `ArrayList`.

---

### PHẦN 2: SDD (Software Design Document)

**2.1. Kiến trúc hệ thống (Architecture Design):**
*   Hệ thống phân tách nghiêm ngặt thành 3 tầng: 
    *   **Model (`Book`):** POJO chứa cấu trúc dữ liệu thuần.
    *   **View (`BookGUI`):** Giao diện Swing, chỉ nhận sự kiện và gọi Controller. KHÔNG chứa logic nghiệp vụ.
    *   **Controller (`BookController`):** Nhận dữ liệu từ View, thực thi nghiệp vụ (lưu trữ, sửa, xóa) trên tập hợp Model (`ArrayList`). Hoàn toàn không phụ thuộc vào thư viện giao diện (`javax.swing`).
*   **Bootstrap (`Main`):** Điểm khởi chạy duy nhất, tiêm (inject) Controller vào View để giảm coupling.
*   **Utility (`ValidationUtils`):** Tập trung toàn bộ logic kiểm tra tính hợp lệ dữ liệu.

**2.2. Sơ đồ thiết kế (UML Diagrams):**
*(Tại phần này, bạn xuất file ảnh từ Visual Paradigm và dán vào tài liệu Word, viết vài dòng giải thích bên dưới mỗi ảnh)*.
