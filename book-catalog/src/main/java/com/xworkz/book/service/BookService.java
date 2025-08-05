package com.xworkz.book.service;

import com.xworkz.book.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    boolean save(BookEntity bookEntity);
    boolean delete(Integer id);
    boolean UpdateById(Integer id,Float price);
    Optional<BookEntity> findByTitle(String bookTitle);
    Optional<BookEntity> findByAuthor(String bookAuthor);
    List<BookEntity> findByLanguage(String bookLanguage);
    List<BookEntity> findAll();
    BookEntity finById(Integer id);
    BookEntity updatePriceByBookTitle(Integer id,String title,Float price);
    BookEntity updateAuthorByBookTitle(Integer id,String title,String author);
    BookEntity updateLanguageByAuthor(Integer id,String language,String author);

}
