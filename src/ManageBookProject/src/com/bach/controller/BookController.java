/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bach.controller;

import com.bach.model.Book;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BookController {
    
    // Model: danh sách sách trong bộ nhớ (in-memory)
    private ArrayList<Book> listBook;

    public BookController() {
        this.listBook = new ArrayList<>();
    }

    public BookController(ArrayList<Book> listBook) {
        this.listBook = listBook;
    }
    
    /**
     * Trả về một cuốn sách theo index.
     * View dùng method này để đọc dữ liệu khi cần hiển thị.
     */
    public Book getBook(int index) {
        if (index >= 0 && index < listBook.size()) {
            return listBook.get(index);
        }
        return null;
    }

    /**
     * Nạp dữ liệu mẫu vào danh sách.
     * Được gọi từ Main, không để trong View.
     */
    public void getSampleData() {
        listBook.add(new Book("DBI202", "Core Java 01", "Author A", "Publisher X", 2016, true));
        listBook.add(new Book("PRO192", "C#. Net", "Author B", "Publisher Y", 2017, false));
    }
    
    public void addBook(Book b){
        listBook.add(b);
    }
    
    public void updateBook(int index, Book b) {
        if (index >= 0 && index < listBook.size()) {
            listBook.set(index, b);
        }
    }
    
    public void removeBook(int index){
        if (index >= 0 && index < listBook.size()) {
            listBook.remove(index);
        }
    }
    
    public boolean isCodeExisted(String code) {
        for (Book b : listBook) {
            if (b.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Book> getList() {
        return listBook;
    }
    
}
