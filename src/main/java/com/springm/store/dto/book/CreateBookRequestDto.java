package com.springm.store.dto.book;

import com.springm.store.validation.book.Author;
import com.springm.store.validation.book.Isbn;
import com.springm.store.validation.book.Title;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
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

    private Set<Long> categoryIds;
}
