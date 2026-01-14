package com.springm.store.service;

import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.book.CreateBookRequestDto;
import com.springm.store.mapper.BookMapper;
import com.springm.store.model.Book;
import com.springm.store.model.Category;
import com.springm.store.repository.book.BookRepository;
import com.springm.store.repository.book.BookSpecificationBuilder;
import com.springm.store.repository.category.CategoryRepository;
import com.springm.store.service.impl.BookServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookServiceTest {
    @Autowired
    private BookServiceImpl bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @MockitoBean
    private CategoryRepository categoryRepository;

    @MockitoBean
    private BookMapper bookMapper;

    @MockitoBean
    private BookSpecificationBuilder bookSpecificationBuilder;

    @Test
    @DisplayName("Save book with valid input")
    void save_ValidInput_Success() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Poetry");

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("Kobzar");
        requestDto.setAuthor("Taras Shevchenko");
        requestDto.setPrice(BigDecimal.valueOf(24));
        requestDto.setCategoryIds(Set.of(1L));

        Book bookEntity = new Book();
        bookEntity.setTitle("Kobzar");
        bookEntity.setAuthor("Taras Shevchenko");
        bookEntity.setPrice(BigDecimal.valueOf(24));
        bookEntity.setCategories(Set.of(category));

        BookDto expected = new BookDto();
        expected.setTitle(bookEntity.getTitle());
        expected.setAuthor(bookEntity.getAuthor());
        expected.setPrice(bookEntity.getPrice());
        expected.setCategoryIds(requestDto.getCategoryIds());

        when(bookMapper.toModel(any(CreateBookRequestDto.class)))
                .thenReturn(bookEntity);

        when(bookRepository.save(any(Book.class)))
                .thenReturn(bookEntity);

        when(bookMapper.toDto(any(Book.class)))
                .thenReturn(expected);
        BookDto actual = bookService.save(requestDto);

        assertTrue(reflectionEquals(expected, actual, "id"));

    }

    @Test
    @DisplayName("Find book by id, returns BookDto")
    void findById_ValidInput_Success() {

        Book bookEntity = new Book();
        bookEntity.setId(1L);
        bookEntity.setTitle("Kobzar");
        bookEntity.setAuthor("Taras Shevchenko");

        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Kobzar");
        bookDto.setAuthor("Taras Shevchenko");

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(bookEntity));

        when(bookMapper.toDto(bookEntity))
                .thenReturn(bookDto);

        BookDto actual = bookService.findById(1L);

        assertTrue(reflectionEquals(bookDto, actual, "id"));
    }

    @Test
    @DisplayName("Find all books")
    void findAll_Pageable_ReturnsAllBooks() {
        Pageable pageable = PageRequest.of(0, 1);

        Book book = new Book();
        book.setId(1L);

        Page<Book> bookPage =
                new PageImpl<>(List.of(book), pageable, 2);

        when(bookRepository.findAll(pageable))
                .thenReturn(bookPage);

        when(bookMapper.toDto(book))
                .thenReturn(new BookDto());

        Page<BookDto> result = bookService.findAll(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(2, result.getTotalElements());
    }

    @Test
    @DisplayName("Updates book with valid id and input dto")
    void updateBookById_ValidInput_Success() {
        Book bookEntity = new Book();
        bookEntity.setId(1L);
        bookEntity.setTitle("Harry Potter");
        bookEntity.setAuthor("J. K. Rowling");

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(bookEntity));

        CreateBookRequestDto changedBookDto = new CreateBookRequestDto();
        changedBookDto.setTitle("The World of Ice and Fire");
        changedBookDto.setAuthor("George R. R. Martin");

        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("The World of Ice and Fire");
        bookDto.setAuthor("George R. R. Martin");

        when(bookMapper.toDto(bookEntity))
                .thenReturn(bookDto);

        BookDto actual = bookService.updateBookById(1L, changedBookDto);

        assertTrue(reflectionEquals(bookDto, actual, "id"));
    }

    @Test
    @DisplayName("Deletes books with valid id")
    void deleteBookById_ValidInput_Success() {
        doNothing()
                .when(bookRepository)
                .deleteById(1L);

        bookService.deleteBookById(1L);

        verify(bookRepository)
                .deleteById(1L);
    }

}
