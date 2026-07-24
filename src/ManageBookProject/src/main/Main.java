package main;

import controller.BookController;
import view.BookGUI;

/**
 * Diem khoi chay cua ung dung (Bootstrap Layer).
 *
 * Nhiem vu duy nhat cua lop nay:
 *   1. Khoi tao Controller (chua du lieu in-memory)
 *   2. Nap du lieu mau vao Controller
 *   3. Khoi tao View va tiem Controller vao (Dependency Injection)
 *   4. Hien thi giao dien
 *
 * Khong chua bat ky business logic nao.
 */
public class Main {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            // B1: Khởi tạo Controller
            BookController controller = new BookController();

            // B2: Nạp dữ liệu mẫu
            controller.getSampleData();

            // B3: Tạo View
            BookGUI view = new BookGUI(controller);

            // B4: Đăng ký Observer  <-- THÊM DÒNG NÀY
            controller.addObserver(view);

            // B5: Hiển thị
            view.setVisible(true);
        });
    }
}

