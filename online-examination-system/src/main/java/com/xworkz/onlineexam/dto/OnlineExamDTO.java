package com.xworkz.onlineexam.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class OnlineExamDTO {

    private Integer examId;

    @NotBlank
    @Size(min = 3,max = 30)
    private String subject;

    @NotNull
    @Min(1)
    @Max(500)
    private Integer noOfQuestions;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Date should be future")
    private LocalDate examDate;

    @NotNull
    @Min(5)
    @Max(500)
    private Integer totalMarks;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer durationHours;

    @NotNull
    @Min(0)
    @Max(59)
    private Integer durationMinutes;
}
