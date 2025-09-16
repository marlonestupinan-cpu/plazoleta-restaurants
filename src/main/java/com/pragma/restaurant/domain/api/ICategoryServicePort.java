package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Category;

public interface ICategoryServicePort {
    void createIfNotExist(String categoryName);

    Category getCategoryByName(String categoryName);
}
