package com.xworkz.bottle.soap.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "soap_info")
public class SoapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soap_id")
    private int soapId;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "fragrance")
    private String fragrance;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;

    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

}
