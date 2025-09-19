package com.pragma.restaurant.infrastructure.out.jpa.repository;

import com.pragma.restaurant.domain.model.Order;
import com.pragma.restaurant.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> getFirstByIdClientAndStateIsIn(Long idClient, List<Order.OrderState> states);

    Page<OrderEntity> findAllByRestaurantId(Long restaurantId, Pageable pageable);
    Page<OrderEntity> findAllByRestaurantIdAndState(Long restaurantId, Order.OrderState state, Pageable pageable);

    Long id(Long id);
}
