package com.Service.Catalog.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    private Long id;
    private String name;
    private String code;
    private String brand;
    private Double price;
    private int inStock;
    private String warranty;
    private String condition;

    public Product(String name, String code, String brand, Double price, Integer inStock, String warranty, String condition) {
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.price = price;
        this.inStock = inStock;
        this.warranty = warranty;
        this.condition = condition;
    }
}
