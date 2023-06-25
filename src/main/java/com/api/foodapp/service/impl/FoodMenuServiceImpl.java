package com.api.foodapp.service.impl;

import com.api.foodapp.dto.FoodMenuDto;
import com.api.foodapp.entity.FoodMenu;
import com.api.foodapp.entity.Restaurant;
import com.api.foodapp.exception.FoodAPIException;
import com.api.foodapp.exception.ResourceNotFoundException;
import com.api.foodapp.repository.FoodMenuRepository;
import com.api.foodapp.repository.RestaurantRepository;
import com.api.foodapp.service.FoodMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodMenuServiceImpl implements FoodMenuService {
    private final FoodMenuRepository foodMenuRepository;
    private final RestaurantRepository restaurantRepository;
    @Override
    public FoodMenuDto addMenuItem(Integer restaurantId, FoodMenuDto foodMenuDto) {
        FoodMenu foodMenu = mapToEntity(foodMenuDto);

        //retrieve restaurant by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new ResourceNotFoundException("Restaurant","id",restaurantId));

        //set restaurant to menu entity(each now menu has unique restaurant set to it)
        foodMenu.setRestaurant(restaurant);

        FoodMenu newMenu = foodMenuRepository.save(foodMenu);
        return mapToDto(newMenu);
    }

    @Override
    public List<FoodMenuDto> getMenuByRestaurantId(Integer restaurantId) {
        //retrieve menu by restaurant id
        List<FoodMenu> menus = foodMenuRepository.findByRestaurantId(restaurantId);
        return menus.stream().map(menu -> mapToDto(menu)).collect(Collectors.toList());
    }

    @Override
    public FoodMenuDto getMenuById(Integer restaurantId, Integer menuId) {
        //retrieve restaurant by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new ResourceNotFoundException("Restaurant","id",restaurantId));
        //retrieve restaurant by id
        FoodMenu menu = foodMenuRepository.findById(menuId).orElseThrow(
                ()-> new ResourceNotFoundException("FoodMenu","id",menuId));

        if (!menu.getRestaurant().getId().equals(restaurant.getId())){
            throw new FoodAPIException(HttpStatus.BAD_REQUEST,"Menu does not belong to this restaurant");
        }
        return mapToDto(menu);
    }

    @Override
    public FoodMenuDto updateMenu(Integer restaurantId, Integer menuId, FoodMenuDto menuRequest) {
        //retrieve restaurant by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new ResourceNotFoundException("Restaurant","id",restaurantId));
        //retrieve restaurant by id
        FoodMenu menu = foodMenuRepository.findById(menuId).orElseThrow(
                ()-> new ResourceNotFoundException("FoodMenu","id",menuId));

        if (!menu.getRestaurant().getId().equals(restaurant.getId())){
            throw new FoodAPIException(HttpStatus.BAD_REQUEST,"Menu does not belong to restaurant");
        }
        menu.setName(menuRequest.getName());
        menu.setPrice(menuRequest.getPrice());

        FoodMenu updatedMenu = foodMenuRepository.save(menu);
        return mapToDto(updatedMenu);
    }

    @Override
    public void deleteMenu(Integer restaurantId, Integer menuId) {
        //retrieve restaurant by id
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new ResourceNotFoundException("Restaurant","id",restaurantId));
        //retrieve restaurant by id
        FoodMenu menu = foodMenuRepository.findById(menuId).orElseThrow(
                ()-> new ResourceNotFoundException("FoodMenu","id",menuId));

        if (!menu.getRestaurant().getId().equals(restaurant.getId())) {
            throw new FoodAPIException(HttpStatus.BAD_REQUEST, "Menu does not belong to the said restaurant");
        }
        foodMenuRepository.delete(menu);
    }

    private FoodMenuDto mapToDto(FoodMenu foodMenu){
        FoodMenuDto foodMenuDto = new FoodMenuDto();
        foodMenuDto.setId(foodMenu.getId());
        foodMenuDto.setName(foodMenu.getName());
        foodMenuDto.setPrice(foodMenu.getPrice());
        return foodMenuDto;
    }
    private FoodMenu mapToEntity(FoodMenuDto foodMenuDto){
        FoodMenu foodMenu = new FoodMenu();
        foodMenu.setId(foodMenuDto.getId());
        foodMenu.setName(foodMenuDto.getName());
        foodMenu.setPrice(foodMenuDto.getPrice());
        return foodMenu;
    }
}
