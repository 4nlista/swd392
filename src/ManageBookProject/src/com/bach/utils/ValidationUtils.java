package com.bach.utils;

import com.bach.controller.BookController;

/**
 * Lớp tiện ích chứa các phương thức kiểm tra dữ liệu đầu vào.
 * Tất cả method đều là static — không cần khởi tạo đối tượng.
 * View gọi trực tiếp trước khi ủy quyền xử lý cho Controller.
 */
public class ValidationUtils {

    /**
     * Kiểm tra các chuỗi đầu vào có bị rỗng (hoặc chỉ chứa khoảng trắng) không.
     * Dùng varargs để kiểm tra nhiều trường cùng lúc.
     *
     * @param values Danh sách các chuỗi cần kiểm tra
     * @return true nếu TẤT CẢ đều không rỗng, false nếu có ít nhất 1 trường rỗng
     */
    public static boolean isNotEmpty(String... values) {
        for (String v : values) {
            if (v == null || v.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Kiểm tra chuỗi năm xuất bản có hợp lệ (parse được sang số nguyên) không.
     *
     * @param yearStr Chuỗi năm lấy từ ComboBox (ví dụ: "2016\t")
     * @return true nếu parse thành công, false nếu lỗi
     */
    public static boolean isValidYear(String yearStr) {
        try {
            Integer.parseInt(yearStr.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Kiểm tra mã sách có bị trùng lặp không.
     * - Khi THÊM MỚI: truyền editingIndex = -1, kiểm tra toàn bộ danh sách.
     * - Khi CẬP NHẬT: truyền editingIndex >= 0, bỏ qua chính cuốn sách đang sửa.
     *
     * @param code         Mã sách cần kiểm tra
     * @param ctrl         BookController chứa danh sách hiện tại
     * @param editingIndex Index của cuốn sách đang được sửa (-1 nếu thêm mới)
     * @return true nếu mã đã tồn tại (bị trùng), false nếu hợp lệ
     */
    public static boolean isCodeDuplicated(String code, BookController ctrl, int editingIndex) {
        for (int i = 0; i < ctrl.getList().size(); i++) {
            if (i == editingIndex) {
                continue; // Bỏ qua chính cuốn sách đang được sửa
            }
            if (ctrl.getList().get(i).getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }
}
