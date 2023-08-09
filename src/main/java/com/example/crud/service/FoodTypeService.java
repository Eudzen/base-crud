package com.example.crud.service;

import com.example.crud.model.*;
import org.springframework.data.domain.*;

public interface FoodTypeService {

    Long create(FoodType type);

    FoodType getById(Long id);

    Page<FoodType> getPage(Pageable pageable);

    void update (FoodType type);

    String delete(Long id);

}
