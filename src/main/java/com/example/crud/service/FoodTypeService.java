package com.example.crud.service;

import com.example.crud.model.*;
import org.springframework.data.domain.*;

public interface FoodTypeService {

    Long add(FoodType type);

    FoodType getById(Long id);

    Page<FoodType> getPage(Pageable pageable);

    String delete(Long id);

}
