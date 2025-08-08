package com.xworkz.bookstore.repository;

import com.xworkz.bookstore.entity.BookStoreEntity;

public interface BookStoreRepository {

    boolean save(BookStoreEntity entity);
}
