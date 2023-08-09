package com.example.crud.service;

import com.example.crud.exception.*;
import com.example.crud.model.*;
import com.example.crud.repository.*;
import com.example.crud.utils.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepo foodRepo;

    @Override
    public Long create(Food food) {
        checkForExistsByName(food.getName());
        return foodRepo.save(food).getId();
    }

    @Override
    public Food getById(Long id) {
        return foodRepo
            .findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(
                "Продукт с id=%d не найден", id
            )));
    }

    @Override
    public Page<Food> getPage(Pageable pageable) {
        PageValidation.checkPageSize(pageable.getPageSize(), 20);

        return foodRepo.findAll(pageable);
    }

    @Override
    public void update(Food food) {
        getById(food.getId());

        foodRepo.save(food);
    }

    @Override
    public String delete(Long id) {
        var food = getById(id);

        foodRepo.delete(food);
        return String.format("Удаление \"%s\" с id=%d успешно", food.getName(), food.getId());
    }

    @Override
    public void deleteAllByType(FoodType type) {
        foodRepo.deleteAllByType(type);
    }

    private void checkForExistsByName(String name) {
        if (foodRepo.existsByName(name)) {
            throw new AlreadyExistsException(String.format(
                "Продукт с наименованием \"%s\" уже существует", name
            ));
        }
    }

}
