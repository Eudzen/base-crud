package com.example.crud.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class FoodCreateDto {

    @NotNull(message = "Не указан id типа продукта")
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long typeId;

    @Size(max = 50, message = "Поле \"name\" ограничено 50 символами")
    @NotBlank(message = "Поле \"name\" должно иметь хотя бы один символ (не пробел)")
    private String name;

    @DecimalMin(value = "0.00", message = "Белков на 100гр не может быть меньше нуля")
    @DecimalMax(value = "99.99", message = "Белков на 100гр не может быть равно 100 и больше")
    @Digits(integer = 2, fraction = 2)
    private Double proteins;

    @DecimalMin(value = "0.00", message = "Жиров на 100гр не может быть меньше нуля")
    @DecimalMax(value = "99.99", message = "Жиров на 100гр не может быть равно 100 и больше")
    @Digits(integer = 2, fraction = 2)
    private Double fats;

    @DecimalMin(value = "0.00", message = "Углеводов на 100гр не может быть меньше нуля")
    @DecimalMax(value = "99.99", message = "Углеводов на 100гр не может быть равно 100 и больше")
    @Digits(integer = 2, fraction = 2)
    private Double carbohydrates;

    @NotNull(message = "Не указано кол-во калорий на 100гр продукта")
    @Min(value = 1, message = "Значение не может быть меньше 1")
    private Integer calories;

}
