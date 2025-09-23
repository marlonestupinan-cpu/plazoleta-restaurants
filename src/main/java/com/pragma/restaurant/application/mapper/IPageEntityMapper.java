package com.pragma.restaurant.application.mapper;

import com.pragma.restaurant.domain.model.PageConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPageEntityMapper {
    default PageRequest toPageable(PageConfig pageable) {
        return PageRequest.of(pageable.getPage(), pageable.getSize(), toSort(pageable.getSort()));
    }


    default PageConfig toPageConfig(Pageable pageable) {
        return new PageConfig(pageable.getPageSize(), pageable.getPageNumber(), toStringSort(pageable.getSort()));
    }

    default String toStringSort(Sort sort) {
        if (sort == null || sort.isUnsorted()) {
            return "";
        }

        return sort.stream()
                .map(order -> order.getProperty() + "," + order.getDirection())
                .collect(Collectors.joining(";"));
    }

    default Sort toSort(String sort) {
        if (!StringUtils.hasText(sort)) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = Arrays.stream(sort.split(";"))
                .map(part -> {
                    String[] parts = part.split(",");
                    if (parts.length == 2) {
                        String property = parts[0];
                        Sort.Direction direction = Sort.Direction.fromString(parts[1]);
                        return new Sort.Order(direction, property);
                    }
                    return null;
                })
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toList());

        return Sort.by(orders);
    }
}
