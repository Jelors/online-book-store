package com.springm.store.dto.category;

import com.springm.store.validation.category.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequestDto {
    @NotNull
    @Category
    private String name;

    private String description;
}
