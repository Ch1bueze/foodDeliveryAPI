package com.api.foodapp.repository;

import com.api.foodapp.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodMenuRepository extends JpaRepository<FoodMenu,Integer> {
   List<FoodMenu> findByRestaurantId(Integer restaurantId);
}
