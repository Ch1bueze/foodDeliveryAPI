package com.api.foodapp.controller;

import com.api.foodapp.dto.FoodMenuDto;
import com.api.foodapp.service.FoodMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class FoodMenuController {
    private final FoodMenuService foodMenuService;
    @PostMapping("/restaurants/{restaurantId}/food-menu")
    public ResponseEntity<FoodMenuDto> addMenu(@PathVariable(name = "restaurantId") Integer restaurantId,
                                               @Valid @RequestBody FoodMenuDto foodMenuDto){
        return new ResponseEntity<>(foodMenuService.addMenuItem(restaurantId,foodMenuDto), HttpStatus.CREATED);
    }
    @GetMapping("/restaurants/{restaurantId}/food-menu")
    public List<FoodMenuDto> getMenuByRestaurantId(@PathVariable(name = "restaurantId") Integer restaurantId){
        return foodMenuService.getMenuByRestaurantId(restaurantId);
    }
    @GetMapping("/restaurants/{restaurantId}/food-menu/{id}")
    public ResponseEntity<FoodMenuDto> getMenuById(@PathVariable(name = "restaurantId") Integer restaurantId,
                                                   @PathVariable(name = "id") Integer menuId){
        FoodMenuDto foodMenuDto = foodMenuService.getMenuById(restaurantId, menuId);
        return new ResponseEntity<>(foodMenuDto,HttpStatus.OK);
    }
    @PutMapping("restaurants/{restaurantId}/food-menu/{id}")
    public ResponseEntity<FoodMenuDto> updateMenu(@PathVariable(name = "restaurantId") Integer restaurantId,
                                                  @PathVariable(name = "id") Integer menuId,
                                                  @Valid @RequestBody FoodMenuDto foodMenuDto){
        FoodMenuDto updatedMenu = foodMenuService.updateMenu(restaurantId, menuId, foodMenuDto);
        return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
    }
    @DeleteMapping("restaurants/{restaurantId}/food-menu/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable(name = "restaurantId") Integer restaurantId,
                                                  @PathVariable(name = "id") Integer menuId){
        foodMenuService.deleteMenu(restaurantId, menuId);
        return  new ResponseEntity<>("Menu deleted successfully",HttpStatus.OK);
    }
}
