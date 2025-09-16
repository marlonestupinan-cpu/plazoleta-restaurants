package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.ICategoryServicePort;
import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
    @Override
    public void createIfNotExist(String name) {
        if (categoryPersistencePort.getCategoryByName(name) == null) {
            Category newCategory = new Category();
            newCategory.setName(name);
            categoryPersistencePort.saveCategory(newCategory);
        }
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryPersistencePort.getCategoryByName(categoryName);
    }
}
