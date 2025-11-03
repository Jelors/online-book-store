package com.springm.store.service;

import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.book.CreateBookRequestDto;
import com.springm.store.repository.book.BookSearchParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    BookDto findById(Long id);

    Page<BookDto> findAll(Pageable pageable);

    BookDto updateBookById(Long id, CreateBookRequestDto changedBookDto);

    void deleteBookById(Long id);

    Page<BookDto> search(BookSearchParameters parameters, Pageable pageable);
}
