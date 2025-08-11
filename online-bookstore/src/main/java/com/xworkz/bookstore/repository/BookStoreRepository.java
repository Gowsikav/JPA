package com.xworkz.bookstore.repository;

import com.xworkz.bookstore.entity.BookStoreEntity;

import java.util.List;

public interface BookStoreRepository {

    boolean save(BookStoreEntity entity);
    List<BookStoreEntity> findAllEntity();
}
