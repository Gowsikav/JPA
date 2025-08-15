package com.xworkz.tourism.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tourism_info")
@NamedQuery(name = "getAllEntity",query = "select a from TourismEntity a")
@NamedQuery(name = "findById",query = "select a from TourismEntity a where a.packageId=:id")
@NamedQuery(name="updateTourismEntityById", query = "update TourismEntity a set a.packageName=:packageName,a.destination=:destination," +
        "a.days=:days,a.packagePrice=:packagePrice,a.personsCount=:personsCount where a.packageId=:packageId")
@NamedQuery(name = "deleteTourismById",query = "delete from TourismEntity a where a.packageId=:packageId")
@NamedQuery(name = "searchByPackageName",query = "select a from TourismEntity a where a.packageName=:name")
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
