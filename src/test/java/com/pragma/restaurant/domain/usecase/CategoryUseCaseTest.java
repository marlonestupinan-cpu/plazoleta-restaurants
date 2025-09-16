package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.model.Category;
import com.pragma.restaurant.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryUseCaseTest {

    private ICategoryPersistencePort categoryPersistencePort;
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        categoryPersistencePort = mock(ICategoryPersistencePort.class);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @Test
    void shouldSaveCategoryWhenItDoesNotExist() {
        String categoryName = "Desayuno";
        when(categoryPersistencePort.getCategoryByName(categoryName)).thenReturn(null);

        categoryUseCase.createIfNotExist(categoryName);

        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryPersistencePort).saveCategory(categoryCaptor.capture());
        assertEquals(categoryName, categoryCaptor.getValue().getName());
    }

    @Test
    void shouldNotSaveCategoryWhenItAlreadyExists() {
        String categoryName = "Cena";
        when(categoryPersistencePort.getCategoryByName(categoryName)).thenReturn(new Category());

        categoryUseCase.createIfNotExist(categoryName);

        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void shouldReturnCategoryWhenFoundById() {
        Long categoryId = 1L;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        expectedCategory.setName("Desayuno");
        when(categoryPersistencePort.getCategoryById(categoryId)).thenReturn(expectedCategory);

        Category result = categoryUseCase.getCategoryById(categoryId);

        assertEquals(expectedCategory, result);
        verify(categoryPersistencePort).getCategoryById(categoryId);
    }
}