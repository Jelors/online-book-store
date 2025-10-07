package com.springm.store.repository;

import com.springm.store.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book save);

    Optional<Book> findById(Long id);

    List<Book> findAll();
}
