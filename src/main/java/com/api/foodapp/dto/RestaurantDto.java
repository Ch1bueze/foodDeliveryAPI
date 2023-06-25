package com.api.foodapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RestaurantDto {
    private Integer id;

    //Name should not be null or empty(validation)
    @NotEmpty
    private String name;
}
