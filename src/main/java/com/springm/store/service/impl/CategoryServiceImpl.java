package com.springm.store.service.impl;

import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.category.CategoryDto;
import com.springm.store.dto.category.CreateCategoryRequestDto;
import com.springm.store.exception.EntityNotFoundException;
import com.springm.store.mapper.BookMapper;
import com.springm.store.mapper.CategoryMapper;
import com.springm.store.model.Category;
import com.springm.store.repository.book.BookRepository;
import com.springm.store.repository.category.CategoryRepository;
import com.springm.store.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category with id ["
                                + id + "] not found!"));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryMapper.toModel(createCategoryRequestDto);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto changedCategoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category with id: "
                                + id + " not found!"));

        categoryMapper.updateCategoryFromDto(changedCategoryDto, existingCategory);
        categoryRepository.save(existingCategory);
        return categoryMapper.toDto(existingCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<BookDto> findAllByCategoryId(Long id) {
        return bookRepository.findAllByCategories_Id(id).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
