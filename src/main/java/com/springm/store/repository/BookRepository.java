package com.springm.store.repository;

import com.springm.store.dto.BookDto;
import com.springm.store.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book save);

    List<BookDto> findAll();
}
