package com.springm.store.dto;

import com.springm.store.validation.Author;
import com.springm.store.validation.Isbn;
import com.springm.store.validation.Title;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookRequestDto {
    @Title
    private String title;
    @Author
    private String author;
    @Isbn
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    private String description;
    private String coverImage;
}
