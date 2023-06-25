package com.api.foodapp.controller;

import com.api.foodapp.dto.RestaurantDto;
import com.api.foodapp.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    //create restaurant endpoint
    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {
        return new ResponseEntity<>(restaurantService.addRestaurant(restaurantDto), HttpStatus.CREATED);
    }

    //get all restaurants API
    @GetMapping
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    //get restaurant by id
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    // update restaurant by id
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@Valid @RequestBody RestaurantDto restaurantDto,
                                                          @PathVariable(name = "id") Integer id) {
        RestaurantDto restaurantResponse = restaurantService.updateRestaurant(restaurantDto, id);
        return new ResponseEntity<>(restaurantResponse, HttpStatus.OK);
    }

    //delete restaurant by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable(name = "id") Integer id) {
        restaurantService.deleteRestaurantById(id);
        return new ResponseEntity<>("Restaurant deleted Successfully", HttpStatus.OK);
    }
}
