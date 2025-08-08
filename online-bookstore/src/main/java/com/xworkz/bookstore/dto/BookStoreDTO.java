package com.xworkz.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class BookStoreDTO {

    private Integer bookId;
    @NotBlank
    @Size(min = 2,max = 30)
    private String bookName;

    @NotBlank
    @Size(min = 3,max = 30)
    private String bookAuthor;

    @NotBlank
    @Size(min = 3,max = 20)
    private String bookCategory;

    @Positive
    @NotNull
    private Float bookPrice;
}
