package com.xworkz.bookstore.service;

import com.xworkz.bookstore.dto.BookStoreDTO;

import java.util.List;

public interface BookStoreService {

    boolean save(BookStoreDTO bookStoreDTO);
    List<BookStoreDTO> findAllEntity();
    BookStoreDTO findById(Integer id);
}
