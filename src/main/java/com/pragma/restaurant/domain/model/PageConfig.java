package com.pragma.restaurant.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageConfig {
    private Integer size;
    private Integer page;
    private String sort;
}
