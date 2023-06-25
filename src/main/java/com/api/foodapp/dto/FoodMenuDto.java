package com.api.foodapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FoodMenuDto {
    private Integer id;

    //Name should not be null or empty(validation)
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //Price should not be null or empty(validation)
    @NotEmpty(message = "Name should not be null or empty")
    private String price;
}
