package com.xworkz.blanket.passport.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "passport_info")
public class PassportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private int passportId;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "name")
    private String Name;

    @Column(name = "date_of_birth")
    private LocalDate birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "nationality")
    private String nationality;
}
