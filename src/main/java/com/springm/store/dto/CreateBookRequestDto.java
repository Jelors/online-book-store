package com.springm.store.dto;

import com.springm.store.validation.Author;
import com.springm.store.validation.Isbn;
import com.springm.store.validation.Title;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive
    private BigDecimal price;
    private String description;
    private String coverImage;
}
