package com.example.crud.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class FoodGetDto {

    private Long id;

    private FoodTypeGetDto typeDto;

    private String name;

    private Double proteins;

    private Double fats;

    private Double carbohydrates;

    private Integer calories;

}
