package com.pragma.restaurant.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageList<T> {
    List<T> content;
    Integer page;
    Integer pageSize;
    Integer totalPages;
    Long totalCount;
}
