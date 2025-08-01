package com.xworkz.book.runner;

import com.xworkz.book.entity.BookEntity;
import com.xworkz.book.service.BookService;
import com.xworkz.book.service.BookServiceImpl;
import com.xworkz.book.util.DBConnection;

import java.time.LocalDate;
import java.util.Optional;

public class BookRunner {

    public static void main(String[] args) {

        BookService bookService = new BookServiceImpl();
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBookTitle("To Kill a Mockingbird");
        bookEntity.setAuthor("Harper Lee");
        bookEntity.setISBN("978-0-06-112008-4");
        bookEntity.setPrice(250.20f);
        bookEntity.setLanguage("Chinese");
        bookEntity.setPublishedDate(LocalDate.of(2016,7,11));

        if(bookService.save(bookEntity))
        {
            System.out.println("Data is saved");
        }else {
            System.out.println("Data is not saved");
        }

        Integer id = 3;
        if (bookService.delete(id)) {
            System.out.println("Data is deleted");
        } else {
            System.out.println("Data is not deleted");
        }

        Integer updateId=4;
        Float price=300f;
        if(bookService.UpdateById(updateId,price))
        {
            System.out.println("updated data");
        }else {
            System.out.println("Data not updated");
        }

        String title="Atomic Habits";
        Optional<BookEntity> optionalBook1=bookService.findByTitle(title);
        if(optionalBook1.isPresent())
        {
            System.out.println("value found");
            System.out.println(optionalBook1.get());
        }else {
            System.out.println("Data not found");
        }

        String author="George Orwell";
        Optional<BookEntity> optionalBook2=bookService.findByAuthor(author);
        if(optionalBook2.isPresent())
        {
            System.out.println("value found");
            System.out.println(optionalBook2.get());
        }else {
            System.out.println("Data not found");
        }

        String language="Chinese";
        Optional<BookEntity> optionalBook3=bookService.findByLanguage(language);
        if(optionalBook3.isPresent())
        {
            System.out.println("value found");
            System.out.println(optionalBook3.get());
        }else {
            System.out.println("Data not found");
        }

        DBConnection.closeResource();

    }
}
