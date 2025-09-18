package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IOrderDishServicePort;
import com.pragma.restaurant.domain.exception.DomainException;
import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.domain.spi.IOrderDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDishUseCase implements IOrderDishServicePort {
    private final IOrderDishPersistencePort orderDishPersistencePort;
    @Override
    public void saveOrderDish(OrderDish orderDish) {
        if (orderDish.getQuantity() < 0) {
            throw new DomainException("La cantidad debe ser un entero positivo");
        }
        orderDishPersistencePort.saveOrderDish(orderDish);
    }
}
