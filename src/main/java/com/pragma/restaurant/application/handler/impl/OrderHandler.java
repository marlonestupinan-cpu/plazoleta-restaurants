package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.application.dto.response.OrderResponseDto;
import com.pragma.restaurant.application.dto.response.UserResponse;
import com.pragma.restaurant.application.handler.IMessageService;
import com.pragma.restaurant.application.handler.IOrderHandler;
import com.pragma.restaurant.application.handler.IUserHandler;
import com.pragma.restaurant.application.mapper.IOrderResponseMapper;
import com.pragma.restaurant.domain.api.IDishServicePort;
import com.pragma.restaurant.domain.api.IOrderServicePort;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.model.Dish;
import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.infrastructure.exception.AlreadyClientOrderActiveException;
import com.pragma.restaurant.infrastructure.exception.NotRestaurantEmployeeException;
import com.pragma.restaurant.infrastructure.exception.OrderAlreadyAssignedException;
import com.pragma.restaurant.infrastructure.exception.OrderNotActiveException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderService;
    private final IRestaurantServicePort restaurantService;
    private final IDishServicePort dishService;
    private final IOrderResponseMapper orderResponseMapper;

    private final IUserHandler userHandler;
    private final IMessageService messageService;

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

    @Override
    public Page<OrderResponseDto> getAllOrders(Long idOwner, Pageable pageable, Integer state) {
        Restaurant restaurant = restaurantService.getOwnerRestaurant(idOwner);

        return orderResponseMapper.toPageResponseList(orderService.getAllRestaurantOrders(restaurant.getId(), pageable, state));
    }

    @Override
    public void assignOrder(Long idOrder, Long idEmployee, Long idOwner) {
        Order order = orderService.getOrderById(idOrder);

        if (!order.getRestaurant().getIdOwner().equals(idOwner)) {
            throw new NotRestaurantEmployeeException();
        }
        if (order.getState() != Order.OrderState.PENDING) {
            throw new OrderAlreadyAssignedException();
        }

        order.setState(Order.OrderState.WORKING);
        order.setIdChef(idEmployee);

        orderService.saveOrder(order);
    }

    @Override
    public void finishOrder(Long idOrder, Long idEmployee) {
        Order order = orderService.getOrderById(idOrder);

        if (!order.getIdChef().equals(idEmployee)) {
            throw new NotRestaurantEmployeeException();
        }
        if (order.getState() != Order.OrderState.WORKING) {
            throw new OrderNotActiveException();
        }

        UserResponse user = userHandler.getUserInfo(order.getIdClient());

        String code = generateSecurityCode();

        order.setState(Order.OrderState.FINISHED);
        order.setSecurityCode(code);

        orderService.saveOrder(order);

        // Send to client the security code
        messageService.sendCode(user.getPhoneNumber(), code);
    }

    public String generateSecurityCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
