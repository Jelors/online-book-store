package com.springm.store.controller;

import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.category.CategoryDto;
import com.springm.store.dto.category.CreateCategoryRequestDto;
import com.springm.store.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new category", description = "Create a new category")
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody @Valid CreateCategoryRequestDto categoryDto
    ) {
        return new ResponseEntity<CategoryDto>(
                categoryService.save(categoryDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get all categories", description = "Fetch all categories")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<List<CategoryDto>>(
                categoryService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get a category by ID", description = "Get a category with specified ID")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<CategoryDto>(
                categoryService.getById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update category by ID",
            description = "Updates category with specified ID"
    )
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CreateCategoryRequestDto categoryDto
    ) {
        return new ResponseEntity<CategoryDto>(
                categoryService.update(id, categoryDto),
                HttpStatus.NO_CONTENT
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete category by ID",
            description = "Deletes category with specified ID"
    )
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(
            summary = "Get all books with specified category",
            description = "Get all books with specified category by id"
    )
    public ResponseEntity<List<BookDto>> getBooksByCategoryId(@PathVariable Long id) {
        return new ResponseEntity<List<BookDto>>(
                categoryService.findAllByCategoryId(id),
                HttpStatus.OK
        );
    }

}
