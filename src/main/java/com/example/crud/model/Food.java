package com.example.crud.model;

import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted=false")
@SQLDelete(sql = "update food set is_deleted = true where id=?")
@Table(name = "food", indexes = @Index(name = "food_type", columnList = "type_id"))
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    @ToString.Exclude
    private FoodType type;

    @Column(length = 50)
    private String name;

    @Column(scale = 2)
    private Double proteins;

    @Column(scale = 2)
    private Double fats;

    @Column(scale = 2)
    private Double carbohydrates;

    private Integer calories;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

}
