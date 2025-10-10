package com.springm.store.controller;

import com.springm.store.dto.BookDto;
import com.springm.store.dto.CreateBookRequestDto;
import com.springm.store.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> findAll() {
        return bookService.findAll();
    }

    @GetMapping(value = "/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto bookRequestDto) {
        return bookService.save(bookRequestDto);
    }

    @PutMapping("/{id}")
    public BookDto updateBookById(
            @PathVariable Long id,
            @RequestBody CreateBookRequestDto bookDto
    ) {
        return bookService.updateBookById(id, bookDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
