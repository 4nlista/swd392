/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model chinh quan ly danh sach Book. Lop nay dong vai tro Subject trong
 * Observer Pattern.
 */
public class BookListModel {

    private final List<Book> books;
    private final List<BookObserver> observers;

    public BookListModel() {
        this.books = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public BookListModel(List<Book> books) {
        this.books = books;
        this.observers = new ArrayList<>();
    }

    public void addObserver(BookObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(BookObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        List<Book> readOnlyBooks = Collections.unmodifiableList(books);
        for (BookObserver observer : observers) {
            observer.onBookListChanged(readOnlyBooks);
        }
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
            notifyObservers();
        }
    }

    public void updateBook(int index, Book book) {
        if (book != null && index >= 0 && index < books.size()) {
            books.set(index, book);
            notifyObservers();
        }
    }

    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            notifyObservers();
        }
    }

    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        }
        return null;
    }

    public boolean isCodeExisted(String code) {
        for (Book book : books) {
            if (book.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public List<Book> getBooks() {
        return books;
    }
}
