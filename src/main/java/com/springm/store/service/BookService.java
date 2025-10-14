package com.springm.store.service;

import com.springm.store.dto.BookDto;
import com.springm.store.dto.CreateBookRequestDto;
import com.springm.store.repository.book.BookSearchParameters;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    BookDto findById(Long id);

    List<BookDto> findAll();

    BookDto updateBookById(Long id, CreateBookRequestDto changedBookDto);

    void deleteBookById(Long id);

    List<BookDto> search(BookSearchParameters parameters);
}
