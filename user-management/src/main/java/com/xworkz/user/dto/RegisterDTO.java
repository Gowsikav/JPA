package com.xworkz.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;

    @NonNull
    private String gender;

    @NonNull
    private String state;

    @NonNull
    private String district;

    @NonNull
    private String pincode;

    @NotBlank
    private String address;

    private Integer loginCount;

    private MultipartFile profilePic;

    private String profilePath;
}
