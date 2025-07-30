package com.xworkz.bottle.chocolate.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "chocolate_info")
public class ChocolateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chocolate_id")
    private int bakeryId;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "quantity_kg")
    private double quantityInKg;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "cost_kg")
    private double costPerKg;
}
