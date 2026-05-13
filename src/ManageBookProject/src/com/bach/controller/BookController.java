/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bach.controller;

import com.bach.model.Book;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Admin
 */
public class BookController {
    
    // mảng ArrayList lưu trữ dữ liệu
    private ArrayList<Book> listBook;

    public BookController() {
    }

    public BookController(ArrayList<Book> listBook) {
        this.listBook = listBook;
    }
    
    public void loadDataToModel(DefaultListModel<Book> model) {
        model.clear();
        for (Book b : listBook) {
            model.addElement(b);
        }
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
    
    public ArrayList<Book> getList() {
        return listBook;
    }
    
}
