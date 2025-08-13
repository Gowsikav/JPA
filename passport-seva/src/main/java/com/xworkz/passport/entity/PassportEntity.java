package com.xworkz.passport.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "passport_register_details")
@NamedQuery(name = "findAll",query = "select a from PassportEntity a")
@NamedQuery(name = "findExistingEmail",query = "select a.email from PassportEntity a where a.email=:email")
@NamedQuery(name = "findExistingPhoneNumber",query = "select a.phoneNumber from PassportEntity a where a.phoneNumber=:phoneNumber")
@NamedQuery(name = "findExistingLoginId",query = "select a.loginId from PassportEntity a where a.loginId=:loginId")
public class PassportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private Integer passportId;

    @Column(name = "passport_office")
    private String passportOffice;

    @Column(name = "name")
    private String name;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "login_same_as_email")
    private String loginSameAsEmail;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;

    @Column(name = "hint_question")
    private String hintQuestion;

    @Column(name = "hint_answer")
    private String hintAnswer;
}
