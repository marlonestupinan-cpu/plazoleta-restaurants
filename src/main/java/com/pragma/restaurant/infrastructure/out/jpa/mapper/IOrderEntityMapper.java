package com.pragma.restaurant.infrastructure.out.jpa.mapper;

import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

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
}
