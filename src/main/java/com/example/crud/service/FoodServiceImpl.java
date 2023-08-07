package com.example.crud.service;

import com.example.crud.model.*;
import com.example.crud.repository.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepo foodRepo;

    @Override
    public Long add(Food food) {
        return foodRepo.save(food).getId();
    }

    @Override
    public Food getById(Long id) {
        return foodRepo.findById(id).orElseThrow();//TODO: Доб. исключение
    }

    @Override
    public Page<Food> getPage(Pageable pageable) {
        return foodRepo.findAll(pageable);
    }

    @Override
    public String delete(Long id) {
        var food = getById(id);
        if (food == null) {
            throw new NullPointerException("Object with id=" + id.toString());//TODO: Надо свое искл.
        }

        foodRepo.delete(food);
        return String.format("%s with id=%d successfully deleted", food.getName(), food.getId());
    }

}
