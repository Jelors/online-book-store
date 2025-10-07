package com.springm.store.service;

import com.springm.store.dto.BookDto;
import com.springm.store.dto.CreateBookRequestDto;
import com.springm.store.exception.EntityNotFoundException;
import com.springm.store.mapper.BookMapper;
import com.springm.store.model.Book;
import com.springm.store.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        Book book = bookMapper.toModel(bookRequestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book with id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto updateBookById(Long id, BookDto changedBookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " not found!"));

        if (existingBook.isDeleted()) {
            throw new EntityNotFoundException("Can't update deleted book with id: " + id);
        }

        existingBook.setAuthor(changedBookDto.getAuthor());
        existingBook.setTitle(changedBookDto.getTitle());
        existingBook.setPrice(changedBookDto.getPrice());
        existingBook.setDescription(changedBookDto.getDescription());
        existingBook.setCoverImage(changedBookDto.getCoverImage());
        existingBook.setIsbn(changedBookDto.getIsbn());

        Book saved = bookRepository.save(existingBook);

        return bookMapper.toDto(saved);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
