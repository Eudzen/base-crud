package com.example.crud.service;

import com.example.crud.model.*;
import com.example.crud.repository.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class FoodTypeServiceImpl implements FoodTypeService {

    private final FoodTypeRepo foodTypeRepo;

    @Override
    public Long add(FoodType type) {
        return foodTypeRepo.save(type).getId();
    }

    @Override
    public FoodType getById(Long id) {
        return foodTypeRepo.findById(id).orElseThrow();//TODO: Доб. исключение
    }

    @Override
    public Page<FoodType> getPage(Pageable pageable) {
        return foodTypeRepo.findAll(pageable);
    }

    @Override
    public String delete(Long id) {
        var type = getById(id);
        if (type == null) {
            throw new NullPointerException("Object with id=" + id.toString());//TODO: Надо свое искл.
        }

        foodTypeRepo.delete(type);
        return String.format("%s with id=%d successfully deleted", type.getName(), type.getId());
    }

}
