package com.springm.store.dto;

import java.math.BigDecimal;
import com.springm.store.validation.Author;
import com.springm.store.validation.Isbn;
import com.springm.store.validation.Title;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Long id;
    @Title
    private String title;
    @Author
    private String author;
    @Isbn
    private String isbn;
    @Min(0)
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    private String coverImage;
}
