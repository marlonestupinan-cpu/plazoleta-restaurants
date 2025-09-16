package com.pragma.restaurant.infrastructure.input.bootstrap;

import com.pragma.restaurant.domain.api.ICategoryServicePort;
import com.pragma.restaurant.infrastructure.configuration.CategoryProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDataInitializer implements CommandLineRunner {

    private final ICategoryServicePort categoryService;
    private final CategoryProperties categoryProperties;

    @Override
    public void run(String... args) {
        categoryProperties
                .getCategories()
                .values()
                .forEach(categoryService::createIfNotExist);
    }
}
