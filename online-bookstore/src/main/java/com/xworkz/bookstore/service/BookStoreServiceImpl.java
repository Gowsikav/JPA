package com.xworkz.bookstore.service;

import com.xworkz.bookstore.dto.BookStoreDTO;
import com.xworkz.bookstore.entity.BookStoreEntity;
import com.xworkz.bookstore.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStoreServiceImpl implements BookStoreService{

    @Autowired
    private BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl()
    {
        System.out.println("BookStoreServiceImpl constructor");
    }

    @Override
    public boolean save(BookStoreDTO bookStoreDTO) {
        System.out.println("save method in service");
        System.out.println("service data: "+bookStoreDTO);

        BookStoreEntity bookStoreEntity=new BookStoreEntity();
        bookStoreEntity.setBookName(bookStoreDTO.getBookName());
        bookStoreEntity.setBookAuthor(bookStoreDTO.getBookAuthor());
        bookStoreEntity.setBookCategory(bookStoreDTO.getBookCategory());
        bookStoreEntity.setBookPrice(bookStoreDTO.getBookPrice());

        return bookStoreRepository.save(bookStoreEntity);
    }
}
