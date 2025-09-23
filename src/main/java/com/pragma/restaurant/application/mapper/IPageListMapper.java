package com.pragma.restaurant.application.mapper;

import com.pragma.restaurant.domain.model.PageList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPageListMapper {
    default <T> PageList<T> toPageList(Page<T> pages) {
        return new PageList<>(
                pages.getContent(),
                pages.getNumber(),
                pages.getSize(),
                pages.getTotalPages(),
                pages.getTotalElements());
    }

    default <T> Page<T> toPage(PageList<T> pages, Pageable pageable) {
        return new PageImpl<>(pages.getContent(), pageable, pages.getTotalCount());
    }
}
