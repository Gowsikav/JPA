package com.xworkz.passport.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PassportDTO {

    private Integer passportId;

    @NotBlank
    private String passportOffice;

    @NotBlank
    @Size(min = 3,max = 30)
    private String name;

    @NotBlank
    @Size(min = 1,max = 30)
    private String surName;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Email
    private String email;

    @Min(10)
    private Long phoneNumber;

    @NotBlank
    private String loginSameAsEmail;

    @NotBlank
    private String loginId;

    @NotBlank
    @Size(min = 8,max = 20)
    private String password;

    @NotBlank
    @Size(min = 8,max = 20)
    private String confirmPassword;

    @NotBlank
    private String hintQuestion;

    @NotBlank
    @Size(min = 2,max = 30)
    private String hintAnswer;

}
