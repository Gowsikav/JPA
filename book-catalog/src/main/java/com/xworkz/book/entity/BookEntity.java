package com.xworkz.book.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "book_details")
@NamedQuery(name = "getTitle",query = "select a from BookEntity a where a.bookTitle=:title")
@NamedQuery(name = "getAuthor",query = "select a from BookEntity a where a.author=:author")
@NamedQuery(name = "getLanguage",query = "select a from BookEntity a where a.language=:language")
@NamedQuery(name = "findAll",query = "select a from BookEntity a")
@NamedQuery(name = "findById",query = "select a from BookEntity a where a.bookId=:id")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "language")
    private String language;

    @Column(name = "price")
    private Float price;
}
