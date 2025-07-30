package com.xworkz.box.petadoption.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pet_adoption_info")
public class PetAdoptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_id")
    private int clothesId;

    @Column(name = "pet_name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private String gender;

    @Column(name = "adopter_name")
    private String adopterName;

}
