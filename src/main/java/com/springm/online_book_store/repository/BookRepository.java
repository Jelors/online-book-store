package com.springm.online_book_store.repository;

import com.springm.online_book_store.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book save);

    List<Book> findAll();
}
