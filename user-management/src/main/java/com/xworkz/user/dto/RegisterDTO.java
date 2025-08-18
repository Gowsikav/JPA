package com.xworkz.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RegisterDTO {

    private Integer registerId;

    @NotBlank
    @Size(min = 3)
    private String userName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid phone number")
    private String phoneNumber;

    @Past
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NonNull
    private String gender;

    @NonNull
    private String state;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,}$",
            message = "Password must be at least 5 characters long, contain one uppercase, one lowercase, one digit and one special character"
    )
    private String password;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,}$",
            message = "Password must be at least 5 characters long, contain one uppercase, one lowercase, one digit and one special character"
    )
    private String confirmPassword;

}
