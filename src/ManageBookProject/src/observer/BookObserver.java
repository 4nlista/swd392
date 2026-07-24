package observer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import model.Book;

/**
 *
 * @author Admin
 */
public interface BookObserver {
    void onBookListChanged(List<Book> books);
}
