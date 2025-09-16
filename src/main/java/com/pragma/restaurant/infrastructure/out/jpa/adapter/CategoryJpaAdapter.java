package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.spi.ICategoryPersistencePort;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .map(categoryEntityMapper::toCategory)
                .orElse(null);
    }
}
