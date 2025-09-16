package com.pragma.restaurant.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "categorias")
@Getter
@Setter
public class CategoryProperties {
    private Map<String, String> map;

    public Map<String, String> getCategories() {
        return map;
    }

    public String getCategoryName(String key) {
        return map.get(key);
    }
}
