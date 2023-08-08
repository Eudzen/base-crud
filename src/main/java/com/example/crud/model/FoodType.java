package com.example.crud.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "food_types", indexes = @Index(name = "type_name", columnList = "name"))
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    private String about;

}
