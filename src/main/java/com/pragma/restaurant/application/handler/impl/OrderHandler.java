package com.pragma.restaurant.application.handler.impl;

import com.pragma.restaurant.application.dto.request.CreateOrderRequestDto;
import com.pragma.restaurant.application.dto.request.LogRequest;
import com.pragma.restaurant.application.dto.response.OrderResponseDto;
import com.pragma.restaurant.application.handler.ILogService;
import com.pragma.restaurant.application.handler.IMessageService;
import com.pragma.restaurant.application.handler.IOrderHandler;
import com.pragma.restaurant.application.mapper.ICreateOrderRequestMapper;
import com.pragma.restaurant.application.mapper.IOrderDishRequestMapper;
import com.pragma.restaurant.application.mapper.IOrderResponseMapper;
import com.pragma.restaurant.application.mapper.IPageEntityMapper;
import com.pragma.restaurant.application.mapper.IPageListMapper;
import com.pragma.restaurant.domain.api.IOrderServicePort;
import com.pragma.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.restaurant.domain.api.IUserServicePort;
import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.domain.model.Restaurant;
import com.pragma.restaurant.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderService;
    private final IRestaurantServicePort restaurantService;
    private final IOrderResponseMapper orderResponseMapper;

    private final IUserServicePort userService;
    private final IMessageService messageService;
    private final ILogService logService;

    private final ICreateOrderRequestMapper createOrderRequestMapper;
    private final IOrderDishRequestMapper orderDishRequestMapper;
    private final IPageEntityMapper pageEntityMapper;
    private final IPageListMapper pageListMapper;

    @Override
    public void saveNewOrder(CreateOrderRequestDto order, Long idClient) {
        Order orderToSave = createOrderRequestMapper.toOrder(order);
        List<OrderDish> orderDishes = order
                .getOrderDishes()
                .stream()
                .map(orderDishRequestMapper::toOrderDish)
                .collect(Collectors.toList());

        Order orderSaved = orderService.saveOrder(orderToSave, orderDishes, idClient);

        logService.createLog(LogRequest.buildLogWithOrder(orderSaved));
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Long idOwner, Pageable pageable, Integer state) {
        Restaurant restaurant = restaurantService.getOwnerRestaurant(idOwner);

        return orderResponseMapper
                .toPageResponseList(pageListMapper
                        .toPage(orderService
                                        .getAllRestaurantOrders(restaurant.getId(),
                                                pageEntityMapper.toPageConfig(pageable),
                                                state),
                                pageable));
    }

    @Override
    public void assignOrder(Long idOrder, Long idEmployee, Long idOwner) {
        Order order = orderService.getOrderById(idOrder);
        orderService.assignOrder(order, idEmployee, idOwner);
        logService.createLog(LogRequest.buildLogWithOrder(order));
    }

    @Override
    public void finishOrder(Long idOrder, Long idEmployee) {
        Order order = orderService.getOrderById(idOrder);
        User user = userService.getUserInfo(order.getIdClient());
        String code = generateSecurityCode();

        orderService.finishOrder(order, idEmployee, user, code);

        // Send to client the security code
        messageService.sendCode(user.getPhoneNumber(), code);
        logService.createLog(LogRequest.buildLogWithOrder(order));
    }

    @Override
    public void deliverOrder(Long idOrder, String securityCode, Long idEmployee) {
        Order order = orderService.getOrderById(idOrder);
        orderService.deliverOrder(order, securityCode, idEmployee);
        logService.createLog(LogRequest.buildLogWithOrder(order));
    }

    @Override
    public void cancelOrder(Long idOrder, Long idClient) {
        Order order = orderService.getOrderById(idOrder);
        orderService.cancelOrder(order, idClient);
        logService.createLog(LogRequest.buildLogWithOrder(order));
    }

    public String generateSecurityCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
