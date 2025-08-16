package com.xworkz.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "online_book_store")
@NamedQuery(name = "findAllEntity",query = "select a from BookStoreEntity a")
@NamedQuery(name = "findById",query = "select a from BookStoreEntity a where a.bookId=:id")
@NamedQuery(name = "updateBookStoreById",query = "update BookStoreEntity a set a.bookName=:bookName," +
        "a.bookAuthor=:bookAuthor,a.bookCategory=:bookCategory,a.bookPrice=:bookPrice where a.bookId=:bookId")
@NamedQuery(name="deleteBookStoreById",query = "delete from BookStoreEntity a where a.bookId=:bookId")
@NamedQuery(name = "searchBookStoreByBookName",query = "select a from BookStoreEntity a where a.bookName=:bookName")
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
