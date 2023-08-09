package com.example.crud.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class FoodUpdateDto {

    @NotNull(message = "Не указан id продукта")
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long id;

    private FoodCreateDto updatedInfo;
}
