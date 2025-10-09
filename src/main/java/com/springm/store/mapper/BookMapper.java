package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.BookDto;
import com.springm.store.dto.CreateBookRequestDto;
import com.springm.store.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto createBookRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateBookFromDto(BookDto changedBookDto, @MappingTarget Book book);
}
