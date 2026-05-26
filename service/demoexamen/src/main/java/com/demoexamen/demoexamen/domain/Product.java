package com.demoexamen.demoexamen.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Limit;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "product")
    private List<RecipePosition> recipePositions;
}
