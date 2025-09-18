package com.pragma.restaurant.infrastructure.out.jpa.adapter;

import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.domain.spi.IOrderDishPersistencePort;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.CycleAvoidingMappingContext;
import com.pragma.restaurant.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.restaurant.infrastructure.out.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Override
    public void saveOrderDish(OrderDish orderDish) {
        orderDishRepository.save(orderDishEntityMapper.toEntity(orderDish, new CycleAvoidingMappingContext()));
    }
}
