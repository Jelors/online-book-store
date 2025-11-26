package com.springm.store.repository.category;

import com.springm.store.model.Book;
import com.springm.store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Book> findAllByCategoryId(Long categoryId);

}
