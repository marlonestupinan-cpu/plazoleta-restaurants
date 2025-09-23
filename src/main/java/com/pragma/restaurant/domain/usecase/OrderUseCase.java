package com.pragma.restaurant.domain.usecase;

import com.pragma.restaurant.domain.api.IOrderServicePort;
import com.pragma.restaurant.domain.exception.AlreadyClientOrderActiveException;
import com.pragma.restaurant.domain.exception.CannotCancelOrderException;
import com.pragma.restaurant.domain.exception.InvalidSecurityCodeException;
import com.pragma.restaurant.domain.exception.NotOrderClientException;
import com.pragma.restaurant.domain.exception.NotRestaurantEmployeeException;
import com.pragma.restaurant.domain.exception.OrderAlreadyAssignedException;
import com.pragma.restaurant.domain.exception.OrderNotActiveException;
import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.domain.model.OrderDish;
import com.pragma.restaurant.domain.model.PageConfig;
import com.pragma.restaurant.domain.model.User;
import com.pragma.restaurant.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    @Override
    public Order saveOrder(Order order) {
        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public Order saveOrder(Order order, List<OrderDish> orderDishes, Long idClient) {
        Order activeOrder = getClientActiveOrder(idClient);
        if (activeOrder != null) {
            throw new AlreadyClientOrderActiveException();
        }

        order.setState(Order.OrderState.PENDING);
        order.setIdClient(idClient);

        orderDishes.forEach(orderDish -> orderDish.setOrder(order));
        order.setOrderDishes(orderDishes);

        return saveOrder(order);
    }

    @Override
    public Order getClientActiveOrder(Long idClient) {
        return orderPersistencePort.getClientActiveOrder(idClient);
    }

    @Override
    public Page<Order> getAllRestaurantOrders(Long idRestaurant, PageConfig pageable, Integer state) {
        return orderPersistencePort.getAllRestaurantOrders(idRestaurant, pageable, state);
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderPersistencePort.getOrderById(idOrder);
    }

    @Override
    public void assignOrder(Order order, Long idEmployee, Long idOwner) {

        if (!order.getRestaurant().getIdOwner().equals(idOwner)) {
            throw new NotRestaurantEmployeeException();
        }
        if (order.getState() != Order.OrderState.PENDING) {
            throw new OrderAlreadyAssignedException();
        }

        order.setState(Order.OrderState.WORKING);
        order.setIdChef(idEmployee);

        saveOrder(order);
    }

    @Override
    public void finishOrder(Order order, Long idEmployee, User user, String code) {
        if (!order.getIdChef().equals(idEmployee)) {
            throw new NotRestaurantEmployeeException();
        }
        if (order.getState() != Order.OrderState.WORKING) {
            throw new OrderNotActiveException();
        }

        order.setState(Order.OrderState.FINISHED);
        order.setSecurityCode(code);

        saveOrder(order);
    }

    @Override
    public void deliverOrder(Order order, String securityCode, Long idEmployee) {
        if (!order.getIdChef().equals(idEmployee)) {
            throw new NotRestaurantEmployeeException();
        }
        if (order.getState() != Order.OrderState.FINISHED) {
            throw new OrderNotActiveException();
        }
        if (!order.getSecurityCode().equals(securityCode)) {
            throw new InvalidSecurityCodeException();
        }

        order.setState(Order.OrderState.DELIVERED);
        order.setSecurityCode(null);

        saveOrder(order);
    }

    @Override
    public void cancelOrder(Order order, Long idClient) {
        if (!order.getIdClient().equals(idClient)) {
            throw new NotOrderClientException();
        }
        if (order.getState() != Order.OrderState.PENDING) {
            throw new CannotCancelOrderException();
        }

        order.setState(Order.OrderState.CANCELED);
        saveOrder(order);
    }

}
