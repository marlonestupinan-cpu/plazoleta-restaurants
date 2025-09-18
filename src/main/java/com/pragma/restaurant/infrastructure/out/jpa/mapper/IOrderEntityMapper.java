package com.pragma.restaurant.infrastructure.out.jpa.mapper;

import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRestaurantEntityMapper.class}
)
public abstract class IOrderEntityMapper {
    @Autowired
    @Lazy
    protected IOrderDishEntityMapper iOrderDishEntityMapper;

    public abstract OrderEntity toEntity(Order order, @Context CycleAvoidingMappingContext context);

    public abstract Order toOrder(OrderEntity orderEntity, @Context CycleAvoidingMappingContext context);

    public abstract List<Order> toOrderList(List<OrderEntity> orders, @Context CycleAvoidingMappingContext context);

    public Page<Order> toPageOrderList(Page<OrderEntity> page,  @Context CycleAvoidingMappingContext context) {
        if (page == null) {
            return null;
        }

        List<Order> dtoList = toOrderList(page.getContent(), context);

        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
