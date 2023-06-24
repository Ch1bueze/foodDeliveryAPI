package com.api.foodapp.service;

import com.api.foodapp.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto addRestaurant(RestaurantDto restaurantDto);
    List<RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantById(Integer id);
    RestaurantDto updateRestaurant(RestaurantDto restaurantDto, Integer id);
    void deleteRestaurantById(Integer id);
}
