package com.xworkz.blanket.voter.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "voter_info")
public class VoterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voter_id")
    private int voterId;

    @Column(name = "voter_number")
    private String voterIdNumber;

    @Column(name = "candidate_name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

}
