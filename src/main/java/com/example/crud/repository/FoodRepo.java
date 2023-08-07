package com.example.crud.repository;

import com.example.crud.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {

}
