package com.pragma.restaurant.infrastructure.out.jpa.repository;

import com.pragma.restaurant.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
    Optional<RestaurantEntity> findById(Long id);

    Optional<RestaurantEntity> findByIdOwner(Long idOwner);

    Long id(Long id);
}
