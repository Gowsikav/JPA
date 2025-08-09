package com.xworkz.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "online_book_store")
public class BookStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_category")
    private String bookCategory;

    @Column(name = "book_price")
    private Float bookPrice;

}
