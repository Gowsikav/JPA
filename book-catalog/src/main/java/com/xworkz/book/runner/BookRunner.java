package com.xworkz.book.runner;

import com.xworkz.book.entity.BookEntity;
import com.xworkz.book.service.BookService;
import com.xworkz.book.service.BookServiceImpl;
import com.xworkz.book.util.DBConnection;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookRunner {

    public static void main(String[] args) {

        BookService bookService = new BookServiceImpl();
//        BookEntity bookEntity=new BookEntity();
//        bookEntity.setBookTitle("To Kill a Mockingbird");
//        bookEntity.setAuthor("Harper Lee");
//        bookEntity.setISBN("978-0-06-112008-4");
//        bookEntity.setPrice(250.20f);
//        bookEntity.setLanguage("Chinese");
//        bookEntity.setPublishedDate(LocalDate.of(2016,7,11));
//
//        if(bookService.save(bookEntity))
//        {
//            System.out.println("Data is saved");
//        }else {
//            System.out.println("Data is not saved");
//        }
//
//        Integer id = 3;
//        if (bookService.delete(id)) {
//            System.out.println("Data is deleted");
//        } else {
//            System.out.println("Data is not deleted");
//        }
//
//        Integer updateId=4;
//        Float price=300f;
//        if(bookService.UpdateById(updateId,price))
//        {
//            System.out.println("updated data");
//        }else {
//            System.out.println("Data not updated");
//        }
//
//        String title="Atomic Habits";
//        Optional<BookEntity> optionalBook1=bookService.findByTitle(title);
//        if(optionalBook1.isPresent())
//        {
//            System.out.println("value found");
//            System.out.println(optionalBook1.get());
//        }else {
//            System.out.println("Data not found");
//        }

//        String author="George Orwell";
//        Optional<BookEntity> optionalBook2=bookService.findByAuthor(author);
//        if(optionalBook2.isPresent())
//        {
//            System.out.println("value found");
//            System.out.println(optionalBook2.get());
//        }else {
//            System.out.println("Data not found");
//        }
//
//        String language="English";
//        List<BookEntity> list=bookService.findByLanguage(language);
//        if(!list.isEmpty())
//        {
//            System.out.println("value found");
//            list.forEach(System.out::println);
//        }else {
//            System.out.println("Data not found");
//        }
//
//        BookEntity entity=bookService.finById(4);
//        if (entity!=null)
//        {
//            System.out.println("Data is found");
//            System.out.println(entity);
//        }else {
//            System.out.println("Data is not found");
//        }

//        List<BookEntity> list1 = bookService.findAll();
//        if (!list1.isEmpty()) {
//            System.out.println("Data is found");
//            list1.forEach(System.out::println);
//        } else {
//            System.out.println("Data is not found");
//        }
//
//        BookEntity bookEntity=null;
//        bookEntity  = bookService.updateAuthorByBookTitle(4, "To Kill a Mockingbird", "Loe john");
//        System.out.println(bookEntity);
//        bookEntity = bookService.updatePriceByBookTitle(5, "George Orwell", 350f);
//        System.out.println(bookEntity);
//        bookEntity = bookService.updateLanguageByAuthor(2, "Russian", "James Clear");
//        System.out.println(bookEntity);

        List<String> title=bookService.getAllBookTitle();
        System.out.println("title list");
        title.forEach(System.out::println);

        List<Float> price=bookService.getAllPrice();
        System.out.println("price list");
        price.forEach(System.out::println);

        List<Object[]> stringList=bookService.getAllAuthorAndLanguage();
        System.out.println("Author and Language list");
        stringList.stream().map(e->e[0]+" : "+e[1]).forEach(System.out::println);

        DBConnection.closeResource();

    }
}
