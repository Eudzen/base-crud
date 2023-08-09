package com.example.crud.service.impl;

import com.example.crud.exception.*;
import com.example.crud.model.*;
import com.example.crud.repository.*;
import com.example.crud.service.*;
import com.example.crud.utils.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class FoodTypeServiceImpl implements FoodTypeService {

    private final FoodTypeRepo foodTypeRepo;

    private final FoodService foodService;

    @Override
    public Long create(FoodType type) {
        checkForExistsByName(type.getName());

        return foodTypeRepo.save(type).getId();
    }

    @Override
    public FoodType getById(Long id) {
        return foodTypeRepo
            .findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(
                "Тип продукта с id=%d не найден", id
            )));
    }

    @Override
    public Page<FoodType> getPage(Pageable pageable) {
        PageValidation.checkPageSize(pageable.getPageSize(), 20);

        return foodTypeRepo.findAll(pageable);
    }

    @Override
    public String update(FoodType type) {
        getById(type.getId());

        foodTypeRepo.save(type);
        return String.format("Продукт с id=%d успешно отредактирован", type.getId());
    }

    @Override
    public String delete(Long id) {
        var type = getById(id);

        foodService.deleteAllByType(type);

        foodTypeRepo.delete(type);
        return String.format("Удаление \"%s\" с id=%d и связанных продуктов успешно",
            type.getName(), type.getId());
    }

    private void checkForExistsByName(String name) {
        if (foodTypeRepo.existsByName(name)) {
            throw new AlreadyExistsException(String.format(
                "Тип продукта с наименованием \"%s\" уже существует", name
            ));
        }
    }

}
