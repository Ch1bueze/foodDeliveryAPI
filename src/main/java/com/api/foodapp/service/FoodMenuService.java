package com.api.foodapp.service;

import com.api.foodapp.dto.FoodMenuDto;

import java.util.List;

public interface FoodMenuService {
     FoodMenuDto addMenuItem(Integer restaurantId, FoodMenuDto foodMenuDto);
     List<FoodMenuDto> getMenuByRestaurantId(Integer restaurantId);
     FoodMenuDto getMenuById(Integer restaurantId, Integer menuId);
     FoodMenuDto updateMenu(Integer restaurantId, Integer menuId, FoodMenuDto menuRequest);
     void deleteMenu(Integer restaurantId, Integer menuId);
}
