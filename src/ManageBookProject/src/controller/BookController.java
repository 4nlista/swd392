/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Book;
import model.BookListModel;
import observer.BookObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller trong MVC. Lop nay nhan yeu cau tu View va uy quyen xu ly du lieu
 * cho Model.
 */
public class BookController {

    private final BookListModel bookListModel;

    public BookController() {
        this.bookListModel = new BookListModel();
    }

    public BookController(ArrayList<Book> listBook) {
        this.bookListModel = new BookListModel(listBook);
    }

    public void addObserver(BookObserver observer) {
        bookListModel.addObserver(observer);
    }

    public void removeObserver(BookObserver observer) {
        bookListModel.removeObserver(observer);
    }

    /**
     * Tra ve mot cuon sach theo index. View dung method nay de doc du lieu khi
     * can hien thi.
     */
    public Book getBook(int index) {
        return bookListModel.getBook(index);
    }

    /**
     * Nap du lieu mau vao Model.
     */
    public void getSampleData() {
        bookListModel.addBook(new Book("DBI202", "Core Java 01", "Author A", "Publisher X", 2016, true));
        bookListModel.addBook(new Book("PRO192", "C#. Net", "Author B", "Publisher Y", 2017, false));
    }

    public void addBook(Book book) {
        bookListModel.addBook(book);
    }

    public void updateBook(int index, Book book) {
        bookListModel.updateBook(index, book);
    }

    public void removeBook(int index) {
        bookListModel.removeBook(index);
    }

    public boolean isCodeExisted(String code) {
        return bookListModel.isCodeExisted(code);
    }

    public List<Book> getList() {
        return bookListModel.getBooks();
    }

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
