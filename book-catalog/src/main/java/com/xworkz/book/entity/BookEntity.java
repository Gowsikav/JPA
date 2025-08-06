package com.xworkz.book.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "book_details")
@NamedQuery(name = "getTitle", query = "select a from BookEntity a where a.bookTitle=:title")
@NamedQuery(name = "getAuthor", query = "select a from BookEntity a where a.author=:author")
@NamedQuery(name = "getLanguage", query = "select a from BookEntity a where a.language=:language")
@NamedQuery(name = "findAll", query = "select a from BookEntity a")
@NamedQuery(name = "findById", query = "select a from BookEntity a where a.bookId=:id")
@NamedQuery(name = "updatePriceByBookTitle",
        query = "update BookEntity a set a.price =:price where a.bookTitle =:bookTitle and a.bookId =:id")
@NamedQuery(name = "updateAuthorByBookTitle",
        query = "update BookEntity a set a.author =:author where a.bookTitle =:bookTitle and a.bookId =:id")
@NamedQuery(name = "updateLanguageByAuthor",
        query = "update BookEntity a set a.language =:language where a.author =:author and a.bookId =:id")
@NamedQuery(name = "getAllBookTitle",query = "select a.bookTitle from BookEntity a")
@NamedQuery(name = "getAllPrice",query = "select a.price from BookEntity a")
@NamedQuery(name = "getAllAuthorAndLanguage",query = "select a.author,a.language from BookEntity a")
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
