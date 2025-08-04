package com.xworkz.book.repository;

import com.xworkz.book.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    boolean save(BookEntity bookEntity);
    boolean delete(Integer id);
    boolean UpdateById(Integer id,Float price);
    Optional<BookEntity> findByTitle(String bookTitle);
    Optional<BookEntity> findByAuthor(String bookAuthor);
    List<BookEntity> findByLanguage(String bookLanguage);
    List<BookEntity> findAll();
    BookEntity findById(Integer id);

}
