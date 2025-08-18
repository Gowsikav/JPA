package com.xworkz.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_register_details")
@NamedQuery(name = "checkExistingEmail",query ="select a.email from RegisterEntity a where a.email=:email and a.isActive=true")
@NamedQuery(name = "checkExistingPhoneNumber",query ="select a.phoneNumber from RegisterEntity a where a.phoneNumber=:phoneNumber and a.isActive=true")
@NamedQuery(name = "getDetailsByEmail",query = "select a from RegisterEntity a where a.email=:email and a.isActive=true")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private Integer registerId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "state")
    private String state;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;

    @Column(name = "is_active")
    private Boolean isActive;
}
