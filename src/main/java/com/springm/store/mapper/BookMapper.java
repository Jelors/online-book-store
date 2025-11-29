package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.book.BookDto;
import com.springm.store.dto.book.BookDtoWithoutCategoryIds;
import com.springm.store.dto.book.CreateBookRequestDto;
import com.springm.store.model.Book;
import com.springm.store.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto createBookRequestDto);

    void updateBookFromDto(
            CreateBookRequestDto changedBookDto,
            @MappingTarget Book book
    );

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> ids = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());

        bookDto.setCategoryIds(ids);
    }
}
