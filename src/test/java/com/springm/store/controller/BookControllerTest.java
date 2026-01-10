package com.springm.store.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.book.CreateBookRequestDto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
@Sql(scripts = {"classpath:database/books/add-items-to-categories-table.sql",
        "classpath:database/books/add-three-items-to-books-table.sql",
        "classpath:database/books/assign-categories-for-books.sql"})
@Sql(scripts = "classpath:database/books/clear-all-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Add a new book")
    void createBook_ValidRequestDto_Success() throws Exception {

        CreateBookRequestDto bookRequestDto = new CreateBookRequestDto();
        bookRequestDto.setTitle("Dune");
        bookRequestDto.setAuthor("Frank Gerbert");
        bookRequestDto.setIsbn("978-0316597011");
        bookRequestDto.setPrice(BigDecimal.valueOf(35.32));
        bookRequestDto.setCategoryIds(Set.of(1L));

        BookDto expected = new BookDto();
        expected.setTitle(bookRequestDto.getTitle());
        expected.setAuthor(bookRequestDto.getAuthor());
        expected.setIsbn(bookRequestDto.getIsbn());
        expected.setPrice(bookRequestDto.getPrice());
        expected.setCategoryIds(bookRequestDto.getCategoryIds());

        String jsonRequest = objectMapper.writeValueAsString(bookRequestDto);

        MvcResult result = mockMvc.perform(
                        post("/books")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();

        BookDto actual = objectMapper.readValue(result.getResponse().getContentAsString(), BookDto.class);

        assertTrue(
                EqualsBuilder.reflectionEquals(expected, actual, "id")
        );
    }

    @Test
    @DisplayName("Find all available books")
    void findAll_ValidItems_ShouldReturnAllBooks() throws Exception {
        MvcResult result = mockMvc.perform(get("/books")
                        .param("page", "0")
                        .param("size", "4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        Map<String, Object> responseMap = objectMapper.readValue(
                jsonResponse,
                new TypeReference<>() {
                });
        List<BookDto> actualDtos = objectMapper.convertValue(
                responseMap.get("content"),
                new TypeReference<List<BookDto>>() {
                }
        );
        assertEquals("Kobzar", actualDtos.get(0).getTitle());
        assertEquals("Harry Potter", actualDtos.get(1).getTitle());
        assertEquals("The Witcher", actualDtos.get(2).getTitle());
        assertEquals(3, responseMap.get("totalElements"));
        assertEquals(3, ((List<?>) responseMap.get("content")).size());
    }

    @Test
    @DisplayName("Returns book with right id")
    void getBookById_ValidItems_ShouldReturnBook() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/books/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        assertTrue(jsonResponse.contains("Kobzar"));
        assertTrue(jsonResponse.contains("Taras Shevchenko"));
        assertTrue(jsonResponse.contains("978-1909156548"));
    }

    @Test
    @DisplayName("Updates book by id")
    void updateBookById_ValidInput_Success() throws Exception {
        CreateBookRequestDto bookRequestDto = new CreateBookRequestDto();
        bookRequestDto.setTitle("The World of Ice and Fire");
        bookRequestDto.setAuthor("George R. R. Martin");
        bookRequestDto.setIsbn("978-0316597101");
        bookRequestDto.setPrice(BigDecimal.valueOf(69.99));
        bookRequestDto.setCategoryIds(Set.of(1L, 2L));

        String jsonRequest = objectMapper.writeValueAsString(bookRequestDto);

        MvcResult result = mockMvc.perform(
                        put("/books/2")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        BookDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                BookDto.class
        );

        BookDto expected = new BookDto();
        expected.setId(2L);
        expected.setTitle(bookRequestDto.getTitle());
        expected.setAuthor(bookRequestDto.getAuthor());
        expected.setIsbn(bookRequestDto.getIsbn());
        expected.setPrice(bookRequestDto.getPrice());
        expected.setCategoryIds(bookRequestDto.getCategoryIds());

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAuthor(), actual.getAuthor());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getCategoryIds(), actual.getCategoryIds());
    }

    @Test
    @DisplayName("Deletes book by id")
    void deleteBookById_ValidInput_Success() throws Exception {
        mockMvc.perform(
                        delete("/books/3")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
