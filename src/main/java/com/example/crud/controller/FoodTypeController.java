package com.example.crud.controller;

import com.example.crud.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food-type")
public class FoodTypeController {

    private final FoodTypeService foodTypeService;

}
