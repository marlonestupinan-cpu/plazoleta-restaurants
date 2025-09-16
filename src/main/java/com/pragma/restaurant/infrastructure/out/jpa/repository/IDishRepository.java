package com.pragma.restaurant.infrastructure.out.jpa.repository;

import com.pragma.restaurant.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Integer> {
}
