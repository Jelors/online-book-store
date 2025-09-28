package com.springm.online_book_store.service;

import com.springm.online_book_store.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
