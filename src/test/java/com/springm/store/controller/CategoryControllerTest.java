package com.springm.store.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springm.store.dto.category.CategoryDto;
import com.springm.store.dto.category.CreateCategoryRequestDto;
import java.util.List;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
@Sql(scripts = {"classpath:database/books/categories/add-five-items-to-categories-table.sql",
        "classpath:database/books/add-three-items-to-books-table.sql",
        "classpath:database/books/assign-categories-for-books.sql"})
@Sql(scripts = "classpath:database/books/clear-all-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Create category")
    void createCategory_ValidInput_Success() throws Exception {
        CreateCategoryRequestDto categoryRequestDto = new CreateCategoryRequestDto();
        categoryRequestDto.setName("Novels");
        categoryRequestDto.setDescription("novels");

        CategoryDto expected = new CategoryDto();
        expected.setName(categoryRequestDto.getName());
        expected.setDescription(categoryRequestDto.getDescription());

        String jsonRequest = objectMapper.writeValueAsString(categoryRequestDto);

        MvcResult result = mockMvc.perform(
                        post("/categories")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        CategoryDto actual = objectMapper.readValue(result.getResponse().getContentAsString(), CategoryDto.class);

        assertTrue(reflectionEquals(expected, actual, "id"));
    }

    @Test
    @DisplayName("Get all existing categories")
    void getAllCategories_ValidItems_ShouldReturnAllCategories() throws Exception {
        MvcResult result = mockMvc.perform(get("/categories")
                        .param("page", "0")
                        .param("size", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        List<CategoryDto> actualList = objectMapper.readValue(
                jsonResponse,
                new TypeReference<List<CategoryDto>>() {
                }
        );
        assertThat(actualList)
                .extracting(CategoryDto::getName)
                .containsExactly("Fantasy", "Classic",
                        "Poetry", "History", "Fantastic");
    }

    @Test
    @DisplayName("Return category by id")
    void getCategoryById_ValidId_Success() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/categories/4")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        assertTrue(jsonResponse.contains("History"));
    }

    @Test
    @DisplayName("Update category by id")
    void updateCategory_ValidInput_Success() throws Exception {
        CreateCategoryRequestDto categoryRequestDto = new CreateCategoryRequestDto();
        categoryRequestDto.setName("Military");
        categoryRequestDto.setDescription("Category about military things");

        String jsonRequest = objectMapper.writeValueAsString(categoryRequestDto);

        MvcResult result = mockMvc.perform(
                        put("/categories/3")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        CategoryDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                CategoryDto.class
        );

        CategoryDto expected = new CategoryDto();
        expected.setId(3L);
        expected.setName(categoryRequestDto.getName());
        expected.setDescription(categoryRequestDto.getDescription());

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    @DisplayName("Delete category by id")
    void deleteCategoryById_ValidId_Success() throws Exception {
        mockMvc.perform(
                        delete("/categories/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Get all books that belong to specific category")
    void getBooksByCategoryId_ValidInput_Success() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/categories/1/books")
                                .param("page", "0")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        List<CategoryDto> actual = objectMapper.readValue(
                jsonResponse,
                new TypeReference<List<CategoryDto>>() {
                }
        );

        assertEquals(3, actual.size());
    }

}
