package com.springm.store.service.impl;

import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.book.CreateBookRequestDto;
import com.springm.store.exception.EntityNotFoundException;
import com.springm.store.mapper.BookMapper;
import com.springm.store.model.Book;
import com.springm.store.model.Category;
import com.springm.store.repository.book.BookRepository;
import com.springm.store.repository.book.BookSearchParameters;
import com.springm.store.repository.book.BookSpecificationBuilder;
import com.springm.store.repository.category.CategoryRepository;
import com.springm.store.service.BookService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        Book book = bookMapper.toModel(bookRequestDto);

        Set<Category> categories = bookRequestDto.getCategoryIds().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Category with id "
                                        + id + " not found")))
                .collect(Collectors.toSet());

        book.setCategories(categories);
        bookRepository.save(book);

        return bookMapper.toDto(book);
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book with id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public Page<BookDto> findAll(
            Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);

    }

    @Override
    public BookDto updateBookById(Long id, CreateBookRequestDto changedBookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Book with id: " + id + " not found!"));

        bookMapper.updateBookFromDto(changedBookDto, existingBook);
        bookRepository.save(existingBook);
        return bookMapper.toDto(existingBook);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<BookDto> search(BookSearchParameters parameters, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(parameters);
        return bookRepository.findAll(bookSpecification, pageable)
                .map(bookMapper::toDto);
    }
}
