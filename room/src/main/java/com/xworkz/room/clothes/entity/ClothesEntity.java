package com.xworkz.room.clothes.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clothes_info")
public class ClothesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothes_id")
    private int clothesId;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;

    @Column(name = "material")
    private String material;

}
