package com.xworkz.room.bakery.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bakery_info")
public class BakeryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bakery_id")
    private int bakeryId;

    @Column(name = "bakery_name")
    private String bakeryName;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "contact_number")
    private long contactNumber;
}
