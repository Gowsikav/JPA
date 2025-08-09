package com.xworkz.foodorder.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class FoodOrderDTO {

    private Integer foodId;

    @NotBlank
    @Size(min = 3,max = 30)
    private String foodName;

    @NotNull
    @Min(1)
    @Max(15)
    private Integer quantity;

    @NotBlank
    @Size(min = 3,max = 35)
    private String restaurantName;

    @NotBlank
    @Size(min = 5,max = 50)
    private String description;

    @NotNull
    @DecimalMin("10.0")
    @DecimalMax("2000.0")
    private Float price;

}
