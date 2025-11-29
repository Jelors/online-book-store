package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.category.CategoryDto;
import com.springm.store.dto.category.CreateCategoryRequestDto;
import com.springm.store.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto createCategoryRequestDto);

    void updateCategoryFromDto(
            CreateCategoryRequestDto categoryRequestDto,
            @MappingTarget Category category
    );
}
