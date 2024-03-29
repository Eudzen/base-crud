package com.example.crud.mapper;

import com.example.crud.dto.*;
import com.example.crud.model.*;
import org.springframework.stereotype.*;

@Component
public class FoodMapper {

    public Food mapToModel(FoodCreateDto dto, FoodType type) {
        return new Food()
            .setType(type)
            .setName(dto.getName())
            .setProteins(dto.getProteins())
            .setFats(dto.getFats())
            .setCarbohydrates(dto.getCarbohydrates())
            .setCalories(dto.getCalories())
            .setIsDeleted(false);
    }

    public FoodGetDto mapToGetDto(Food food, FoodTypeGetDto typeDto) {
        return new FoodGetDto(
            food.getId(),
            typeDto,
            food.getName(),
            food.getProteins(),
            food.getFats(),
            food.getCarbohydrates(),
            food.getCalories()
        );
    }

    public Food mapToModel(FoodUpdateDto dto, FoodType type) {
        return new Food()
            .setId(dto.getId())
            .setType(type)
            .setName(dto.getUpdatedInfo().getName())
            .setProteins(dto.getUpdatedInfo().getProteins())
            .setFats(dto.getUpdatedInfo().getFats())
            .setCarbohydrates(dto.getUpdatedInfo().getCarbohydrates())
            .setCalories(dto.getUpdatedInfo().getCalories())
            .setIsDeleted(false);
    }

}
