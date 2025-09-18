package com.pragma.restaurant.infrastructure.out.jpa.mapper;

import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.infrastructure.out.jpa.entity.OrderDishEntity;
import com.pragma.restaurant.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IDishEntityMapper.class, IOrderEntityMapper.class}
)
public abstract class IOrderDishEntityMapper {
    protected IOrderEntityMapper orderEntityMapper;
    public abstract OrderDishEntity toEntity(OrderDish orderDish, @Context CycleAvoidingMappingContext context);

    public abstract OrderDish toOrderDish(OrderEntity orderEntity, @Context CycleAvoidingMappingContext context);

}
