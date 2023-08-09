package com.example.crud.service;

import com.example.crud.model.*;
import org.springframework.data.domain.*;

public interface FoodService {

    Long create(Food food);

    Food getById(Long id);

    Page<Food> getPage(Pageable pageable);

    String update(Food food);

    String delete(Long id);

    void deleteAllByType(FoodType type);

}
