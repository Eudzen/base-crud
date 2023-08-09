package com.example.crud.repository;

import com.example.crud.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface FoodTypeRepo extends JpaRepository<FoodType, Long> {

    boolean existsByName(String name);

}
