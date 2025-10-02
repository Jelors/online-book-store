package com.springm.store.service;

import com.springm.store.dto.BookDto;
import com.springm.store.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    BookDto findById(Long id);

    List<BookDto> findAll();
}
