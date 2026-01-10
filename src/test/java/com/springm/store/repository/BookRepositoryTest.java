package com.springm.store.repository;

import com.springm.store.model.Book;
import com.springm.store.model.Category;
import com.springm.store.repository.book.BookRepository;
import com.springm.store.repository.category.CategoryRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Find all books that belongs to category with ID: 1")
    void findAllByCategoryId_EqualsOne_ReturnsListWithOneBook() {
        Category category = new Category();
        category.setName("Fantastic");
        category.setDescription("Fantastic books");
        categoryRepository.save(category);

        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("J. K. Rowling");
        book.setPrice(BigDecimal.TEN);
        book.setIsbn("978-0547928213");
        book.setDescription("Such a good book about a boy that survived");
        book.setCoverImage("harryPotter.png");
        book.setCategories(Set.of(category));
        bookRepository.save(book);

        List<Book> actual = bookRepository.findAllByCategories_Id(1L);

        assertEquals(1, actual.size());
    }

    @Test
    @DisplayName("Returns empty list, when tries to find by non-existing category.")
    void findAllByCategoryId_EqualsNonExistingId_ReturnsEmptyList() {
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("J. K. Rowling");
        book.setPrice(BigDecimal.TEN);
        book.setIsbn("978-0547928213");
        book.setDescription("Such a good book about a boy that survived");
        book.setCoverImage("harryPotter.png");
        book.setCategories(Set.of());
        bookRepository.save(book);

        List<Book> actual = bookRepository.findAllByCategories_Id(2L);

        assertEquals(0, actual.size());
    }

}
