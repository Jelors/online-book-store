package com.springm.store.service;

import com.springm.store.dto.category.CategoryDto;
import com.springm.store.dto.category.CreateCategoryRequestDto;
import com.springm.store.mapper.CategoryMapper;
import com.springm.store.model.Category;
import com.springm.store.repository.category.CategoryRepository;
import com.springm.store.service.impl.CategoryServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryServiceTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @MockitoBean
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Saves category with valid input")
    void save_ValidInput_Success() {
        CreateCategoryRequestDto categoryRequestDto = new CreateCategoryRequestDto();
        categoryRequestDto.setName("Poetry");

        Category category = new Category();
        category.setId(0L);
        category.setName("Poetry");

        CategoryDto expected = new CategoryDto();
        expected.setId(1L);
        expected.setName("Poetry");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto actual = categoryService.save(categoryRequestDto);

        assertTrue(reflectionEquals(expected, actual, "id"));
    }

    @Test
    @DisplayName("Find category by id, returns CategoryDto")
    void findById_ValidInput_Success() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Fiction");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryDto expected = new CategoryDto();
        expected.setId(1L);
        expected.setName("Fiction");

        CategoryDto actual = categoryService.findById(1L);

        assertTrue(reflectionEquals(expected, actual, "id"));
    }

    @Test
    @DisplayName("Returns all categories that created")
    void findAll_ReturnsAllCategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Poetry");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Fiction");

        List<Category> categories = List.of(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> actualList = categoryService.findAll();

        assertThat(actualList)
                .extracting(CategoryDto::getName)
                .containsExactly("Poetry", "Fiction");
    }

    @Test
    @DisplayName("Receives category id that must be updated and new name and description for replace")
    void update_ValidInput_Success() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Fiction");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CreateCategoryRequestDto categoryRequestDto = new CreateCategoryRequestDto();
        categoryRequestDto.setName("History");

        CategoryDto expected = new CategoryDto();
        expected.setName("History");

        CategoryDto actual = categoryService.update(1L, categoryRequestDto);

        assertTrue(reflectionEquals(expected, actual, "id"));
    }

    @Test
    @DisplayName("Deletes category by id")
    void deleteById_ValidInput_Success() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteById(1L);

        verify(categoryRepository).deleteById(1L);
    }

}
