package com.springm.store.controller;

import com.springm.store.dto.BookDto;
import com.springm.store.dto.CreateBookRequestDto;
import com.springm.store.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @GetMapping
    public List<Book> getAll() {
        return List.of();
    }

    @GetMapping(value = "{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto bookRequestDto) {
        return null;
    }

}
