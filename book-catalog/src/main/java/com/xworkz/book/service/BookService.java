package com.xworkz.book.service;

import com.xworkz.book.entity.BookEntity;

import java.util.Optional;

public interface BookService {

    boolean save(BookEntity bookEntity);
    boolean delete(Integer id);
    boolean UpdateById(Integer id,Float price);
    Optional<BookEntity> findByTitle(String bookTitle);
    Optional<BookEntity> findByAuthor(String bookAuthor);
    Optional<BookEntity> findByLanguage(String bookLanguage);

}
