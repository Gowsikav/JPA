package com.xworkz.library.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class BookRegisterDTO {

    private Integer bookId;
    private String bookTitle;
    private String author;
    private String language;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;
    private Float price;
}
