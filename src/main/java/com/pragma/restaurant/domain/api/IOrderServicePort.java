package com.pragma.restaurant.domain.api;

import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderServicePort {
    Order saveOrder(Order order);

    Order saveOrder(Order order, List<OrderDish> orderDishes, Long idClient);

    Order getClientActiveOrder(Long idClient);

    Page<Order> getAllRestaurantOrders(Long idRestaurant, Pageable pageable, Integer state);

    Order getOrderById(Long idOrder);

    void assignOrder(Order order, Long idEmployee, Long idOwner);

    void finishOrder(Order order, Long idEmployee, User user, String code);

    void deliverOrder(Order order, String securityCode, Long idEmployee);

    void cancelOrder(Order order, Long idClient);
}
