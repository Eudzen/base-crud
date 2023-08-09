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
@RequestMapping("/api/food")
@Tag(name = "Продукты", description = "\"Создание, удаление, редактирование " +
                                      "и получение информации о продукте(-ах) \")\n")
public class FoodController {

    private final FoodService foodService;

    private final FoodMapper foodMapper;

    private final FoodTypeService foodTypeService;

    private final FoodTypeMapper foodTypeMapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавить продукт",
        description = "Добавление нового продукта с указанными параметрами. " +
                      "Обязательно указывать тип продукта и его наименование."
    )
    public Long createFood(@Valid @RequestBody FoodCreateDto dto) {
        var type = foodTypeService.getById(dto.getTypeId());
        var food = foodMapper.mapToModel(dto, type);

        return foodService.create(food);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о продукте",
        description = "Получить информацию о продукте по id. " +
                      "Информация о типе продукта включительно."
    )
    public FoodGetDto getFoodById(
        @PathVariable @Valid @Min(value = 1, message = "Id не может быть меньше 1") Long id
    ) {
        var food = foodService.getById(id);
        var typeDto = foodTypeMapper.mapToGetDto(food.getType());

        return foodMapper.mapToGetDto(food, typeDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о продуктах ",
        description = "Получить страницу с информацией о продуктах. " +
                      "Ограничение на кол-во объектов на страницу 20. " +
                      "Информация о типе продукта включительно."
    )
    public Page<FoodGetDto> getFood(@ParameterObject Pageable pageable) {
        var foodPage = foodService.getPage(pageable);

        var dtos = foodPage.stream()
            .map(food -> {
                var typeDto = foodTypeMapper.mapToGetDto(food.getType());
                return foodMapper.mapToGetDto(food, typeDto);
            })
            .toList();

        return new PageImpl<>(dtos, foodPage.getPageable(), foodPage.getTotalElements());
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Отредактировать информацию о продукте",
        description = "В информации о продукте будут заменены поля переданными параметрами."
    )
    public String update(@Valid @RequestBody FoodUpdateDto dto) {
        var type = foodTypeService.getById(dto
            .getUpdatedInfo()
            .getTypeId());

        return foodService.update(foodMapper.mapToModel(dto, type));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удаление продукта",
        description = "Удаление продукта по id."
    )
    public String deleteFood(@PathVariable Long id) {
        return foodService.delete(id);
    }

}
