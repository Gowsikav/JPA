package com.xworkz.dmart.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "product_info")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_mfd")
    private String manuDate;

    @Column(name = "product_company")
    private String company;
}
