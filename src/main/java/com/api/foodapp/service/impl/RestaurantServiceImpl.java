package com.api.foodapp.service.impl;

import com.api.foodapp.dto.RestaurantDto;
import com.api.foodapp.entity.Restaurant;
import com.api.foodapp.exception.ResourceNotFoundException;
import com.api.foodapp.repository.RestaurantRepository;
import com.api.foodapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;

    @Override
    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {

        Restaurant restaurant = mapToEntity(restaurantDto);

        Restaurant newRestaurant = repository.save(restaurant);


        RestaurantDto restaurantResponse = mapToDto(newRestaurant);

        return restaurantResponse;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = repository.findAll();
        return restaurants.stream().map(restaurant -> mapToDto(restaurant)).collect(Collectors.toList());
    }

    @Override
    public RestaurantDto getRestaurantById(Integer id) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));
        return mapToDto(restaurant);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, Integer id) {
        // get restaurant by id from database then edit
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));
        restaurant.setName(restaurantDto.getName());

        Restaurant updatedRestaurant = repository.save(restaurant);
        return mapToDto(updatedRestaurant);
    }

    @Override
    public void deleteRestaurantById(Integer id) {
        // get restaurant by id from database then delete
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));
        repository.delete(restaurant);
    }

    //convert entity to DTO
    public RestaurantDto mapToDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName(restaurant.getName());
        return restaurantDto;
    }

    //convert DTO to entity
    public Restaurant mapToEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        return restaurant;
    }
}
