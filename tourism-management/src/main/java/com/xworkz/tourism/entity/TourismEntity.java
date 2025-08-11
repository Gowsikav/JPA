package com.xworkz.tourism.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tourism_info")
@NamedQuery(name = "getAllEntity",query = "select a from TourismEntity a")
public class TourismEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Integer packageId;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "destination")
    private String destination;

    @Column(name = "days")
    private Integer days;

    @Column(name = "package_price")
    private Double packagePrice;

    @Column(name = "persons_count")
    private Integer personsCount;

}
