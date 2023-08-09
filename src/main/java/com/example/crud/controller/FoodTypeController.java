package com.example.crud.controller;

import com.example.crud.dto.*;
import com.example.crud.mapper.*;
import com.example.crud.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springdoc.api.annotations.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food-type")
@Tag(name = "Типы продуктов", description = "\"Создание, удаление, редактирование " +
                                            "и получение информации о типе(-ах) продуктов \")\n")
public class FoodTypeController {

    private final FoodTypeService foodTypeService;

    private final FoodTypeMapper foodTypeMapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить тип продуктов",
        description = "Добавление нового типа продукта с указанными параметрами. " +
                      "Обязательно указывать наименование типа продукта."
    )
    public Long createFoodType(@Valid @RequestBody FoodTypeCreateDto dto) {
        var type = foodTypeMapper.mapToModel(dto);

        return foodTypeService.create(type);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о типе продуктов",
        description = "Получить информацию о типе продукта по id."
    )
    public FoodTypeGetDto getFoodTypeById(
        @PathVariable @Valid @Min(value = 1, message = "Id не может быть меньше 1") Long id
    ) {
        var type = foodTypeService.getById(id);

        return foodTypeMapper.mapToGetDto(type);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о типах продуктов ",
        description = "Получить страницу с информацией о типах продуктов. " +
                      "Ограничение на кол-во объектов на страницу 20."
    )
    public Page<FoodTypeGetDto> getFood(@ParameterObject Pageable pageable) {
        var typePage = foodTypeService.getPage(pageable);

        var dtos = typePage.stream()
            .map(foodTypeMapper::mapToGetDto)
            .toList();

        return new PageImpl<>(dtos, typePage.getPageable(), typePage.getTotalElements());
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Отредактировать информацию о типе продуктов",
        description = "В информации о типе продуктов будут заменены поля переданными параметрами."
    )
    public String update(@Valid @RequestBody FoodTypeUpdateDto dto) {
        return foodTypeService.update(foodTypeMapper.mapToModel(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удаление типа продуктов.",
        description = "Удаление типа продуктов по id. " +
                      "Также производится удаление связанных с типов продуктов."
    )
    public String deleteCity(@PathVariable Long id) {
        return foodTypeService.delete(id);
    }

}
