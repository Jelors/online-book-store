package com.springm.store.service;

import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.category.CategoryDto;
import com.springm.store.dto.category.CreateCategoryRequestDto;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDto);

    void deleteById(Long id);

    List<BookDto> findAllByCategoryId(Long id);
}
