package com.pragma.restaurant.domain.spi;

import com.pragma.restaurant.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);

    Category getCategoryByName(String categoryName);

    Category getCategoryById(Long id);
}
