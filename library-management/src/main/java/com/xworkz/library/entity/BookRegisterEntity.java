package com.xworkz.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "library_book_register")
@NamedQuery(name = "getAll",query = "select a from BookRegisterEntity a")
@NamedQuery(name = "getDataById", query = "select a from BookRegisterEntity a where a.bookId=:id")
@NamedQuery(name = "deleteDataById",query = "delete from BookRegisterEntity a where a.bookId=:id")
public class BookRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "language")
    private String language;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "price")
    private Float price;
}
