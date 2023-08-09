package com.example.crud.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class FoodTypeUpdateDto {

    @NotNull(message = "Не указан id типа продукта")
    @Min(value = 1, message = "Id не может быть меньше 1")
    private Long id;

    @Size(max = 50, message = "Поле \"name\" ограничено 50 символами")
    @NotBlank(message = "Поле \"name\" должно иметь хотя бы один символ (не пробел)")
    private String name;

    @Size(max = 255, message = "Поле \"about\" ограничено 255 символами")
    private String about;

}
