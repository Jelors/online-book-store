package com.springm.store.repository.book;

import com.springm.store.model.Book;
import com.springm.store.repository.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {

    private static final String AUTHOR_KEY = "author";
    private static final String TITLE_KEY = "title";
    private final BookSpecificationProviderManager bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.unrestricted();
        if (searchParameters != null && searchParameters.titles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE_KEY)
                    .getSpecification(searchParameters.titles()));
        }
        if (searchParameters != null && searchParameters.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR_KEY)
                    .getSpecification(searchParameters.authors()));
        }
        return spec;
    }
}
