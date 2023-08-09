package com.example.crud.repository;

import com.example.crud.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {

    boolean existsByName(String name);

    @Modifying
    @Query("update Food set isDeleted = true, type = null " +
           "where type = :type")
    void deleteAllByType(@Param("type") FoodType type);

}
