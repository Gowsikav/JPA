package com.xworkz.box.footwear.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "footwear_details")
public class FootwearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "footwear_id")
    private int footwearId;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;
}
