package com.pragma.restaurant.infrastructure.out.jpa.repository;

import com.pragma.restaurant.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
}
