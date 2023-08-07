package com.example.crud.service;

import com.example.crud.model.*;
import org.springframework.data.domain.*;

public interface FoodService {

    Long add(Food food);

    Food getById(Long id);

    Page<Food> getPage(Pageable pageable);

    String delete(Long id);

}
