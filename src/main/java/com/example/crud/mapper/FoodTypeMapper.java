package com.example.crud.mapper;

import com.example.crud.dto.*;
import com.example.crud.model.*;
import org.springframework.stereotype.*;

@Component
public class FoodTypeMapper {

    public FoodType mapToModel(FoodTypeCreateDto dto) {
        return new FoodType()
            .setName(dto.getName())
            .setAbout(dto.getAbout());
    }

    public FoodTypeGetDto mapToGetDto(FoodType type) {
        return new FoodTypeGetDto(
            type.getId(),
            type.getName(),
            type.getAbout()
        );
    }

    public FoodType mapToModel(FoodTypeUpdateDto dto) {
        return new FoodType()
            .setId(dto.getId())
            .setName(dto.getName())
            .setAbout(dto.getAbout());
    }

}
