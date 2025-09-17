package com.pragma.restaurant.infrastructure.out.jpa.repository;

import com.pragma.restaurant.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Integer> {
    Optional<DishEntity> findById(Long id);
}
