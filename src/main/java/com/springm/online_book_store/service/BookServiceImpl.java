package com.springm.online_book_store.service;

import com.springm.online_book_store.model.Book;
import com.springm.online_book_store.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
