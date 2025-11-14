package com.springm.store.controller;

import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.book.CreateBookRequestDto;
import com.springm.store.repository.book.BookSearchParameters;
import com.springm.store.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Get all books", description = "Get a list of all available books")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<BookDto> findAll(@ParameterObject Pageable pageable) {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        String email = springUser.getUsername();

        Collection<? extends GrantedAuthority> authorities = springUser.getAuthorities();

        System.out.println("User email: " + email);
        authorities.forEach(auth -> System.out.println("Role: " + auth.getAuthority()));

        return bookService.findAll(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a book by ID", description = "Get a book with specified ID")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Create a new book", description = "Create a new book")
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return bookService.save(createBookRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Update book by ID", description = "Updates book with specified ID")
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto updateBookById(@PathVariable Long id,
                                  @RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return bookService.updateBookById(id, createBookRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by ID", description = "Deletes book with specified ID")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    @Operation(summary = "Get books with parameters",
            description = "Returns a list of books with specified search parameters")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<BookDto> searchBooks(@ParameterObject BookSearchParameters searchParameters,
                                     @ParameterObject Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

}
