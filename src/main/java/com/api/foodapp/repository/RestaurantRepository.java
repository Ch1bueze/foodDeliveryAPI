package com.api.foodapp.repository;

import com.api.foodapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
}
