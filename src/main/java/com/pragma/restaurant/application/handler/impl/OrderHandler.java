package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.application.handler.IOrderHandler;
import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.api.IOrderServicePort;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.infrastructure.exception.AlreadyClientOrderActiveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderService;
    private final IRestaurantServicePort restaurantService;
    private final IDishServicePort  dishService;
    @Override
    public void saveNewOrder(CreateOrderRequestDto order, Long idClient) {
        Restaurant restaurant = restaurantService.getRestaurantById(order.getIdRestaurant());

        Order activeOrder = orderService.getClientActiveOrder(idClient);
        if (activeOrder != null) {
            throw new AlreadyClientOrderActiveException();
        }
        Order orderToSave = Order.builder()
                .date(new Date())
                .state(Order.OrderState.PENDING)
                .idClient(idClient)
                .restaurant(restaurant)
                .build();

        List<OrderDish> orderDishes = order.getOrderDishes().stream().map(orderRequest -> {
            Dish dish = dishService.getById(orderRequest.getIdDish());
            return OrderDish.builder()
                    .dish(dish)
                    .order(orderToSave)
                    .quantity(orderRequest.getQuantity())
                    .build();
        }).collect(Collectors.toList());

        orderToSave.setOrderDishes(orderDishes);
        orderService.saveOrder(orderToSave);
    }
}
