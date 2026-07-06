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
            // Thiet lap giao dien theo he dieu hanh dang dung
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            // Buoc 1: Khoi tao Controller
            BookController controller = new BookController();

            // Buoc 2: Nap du lieu mau (data seeding nam o day, khong phai trong View)
            controller.getSampleData();

            // Buoc 3: Tao View va tiem Controller vao
            BookGUI view = new BookGUI(controller);

            // Buoc 4: Hien thi
            view.setVisible(true);
        });
    }
}

