package com.xworkz.book.service;

import com.xworkz.book.entity.BookEntity;
import com.xworkz.book.repository.BookRepository;
import com.xworkz.book.repository.BookRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository=new BookRepositoryImpl();

    public BookServiceImpl()
    {
        System.out.println("BookServiceImpl constructor");
    }

    private boolean validateDetails(BookEntity bookEntity)
    {
        System.out.println("validateDetails method in service");
        if(bookEntity!=null)
        {
            System.out.println("book entity is valid");
        }else {
            System.out.println("book entity is not valid");
            return false;
        }

        String title= bookEntity.getBookTitle();
        if(title!=null && title.length()>3 && title.length()<50)
        {
            System.out.println("title is valid");
        }else {
            System.out.println("title is not valid");
            return false;
        }

        String author= bookEntity.getAuthor();
        if(author!=null && author.length()>3 && author.length()<25)
        {
            System.out.println("author is valid");
        }else {
            System.out.println("author is not valid");
            return false;
        }

        String isbn=bookEntity.getISBN();
        if(isbn!=null && isbn.length()==17)
        {
            if(isbn.startsWith("978") || isbn.startsWith("979"))
            {
                System.out.println("isbn number is valid");
            }else {
                System.out.println("isbn number is not valid");
                return false;
            }
        }else {
            System.out.println("isbn is not valid");
            return false;
        }

        if(bookEntity.getPublishedDate()!=null)
        {
            System.out.println("published date is valid");
        }else {
            System.out.println("published date is not valid");
            return false;
        }

        String language=bookEntity.getLanguage();
        if(language!=null && language.length()>3 && language.length()<20)
        {
            System.out.println("language is valid");
        }else {
            System.out.println("language is not valid");
            return false;
        }

        Float price= bookEntity.getPrice();
        if(price!=null && price>0)
        {
            System.out.println("price is valid");
        }else {
            System.out.println("price is not valid");
            return false;
        }
        System.out.println("All details are valid");
        return true;
    }

    @Override
    public boolean save(BookEntity bookEntity) {
        System.out.println("save method in service");
        if(bookEntity!=null)
        {
            if(validateDetails(bookEntity))
            {
                 return bookRepository.save(bookEntity);
            }
        }else {
            System.out.println("book entity is null");
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("delete method in service");
        if(id!=null && id>0)
        {
            System.out.println("id is valid");
            return bookRepository.delete(id);
        }else {
            System.out.println("id is not valid");
        }
        return false;
    }

    @Override
    public boolean UpdateById(Integer id, Float price) {
        System.out.println("update by id method in service");
        if(id!=null && id>0)
        {
            System.out.println("id is valid");
            if(price!=null && price>0)
            {
                System.out.println("price is valid");
               return bookRepository.UpdateById(id, price);
            }else {
                System.out.println("price is not valid");
                return false;
            }
        }
        return false;
    }

    @Override
    public Optional<BookEntity> findByTitle(String bookTitle) {
        System.out.println("find by title method in service");
        if(bookTitle!=null && bookTitle.length()>3 && bookTitle.length()<50)
        {
            System.out.println("title is valid");
            Optional<BookEntity> optionalBookEntity=bookRepository.findByTitle(bookTitle);
            optionalBookEntity.ifPresent(this::validateDetails);
            return optionalBookEntity;
        }else {
            System.out.println("title is not valid");
        }
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> findByAuthor(String bookAuthor) {
        System.out.println("find by author method in service");
        if(bookAuthor!=null && bookAuthor.length()>3 && bookAuthor.length()<25)
        {
            System.out.println("author is valid");
            Optional<BookEntity> optionalBookEntity=bookRepository.findByAuthor(bookAuthor);
            optionalBookEntity.ifPresent(this::validateDetails);
            return optionalBookEntity;
        }else {
            System.out.println("author is not valid");
        }
        return Optional.empty();
    }

    @Override
    public List<BookEntity> findByLanguage(String bookLanguage) {
        System.out.println("find by language method in service");
        if(bookLanguage!=null && bookLanguage.length()>3 && bookLanguage.length()<25)
        {
            System.out.println("language is valid");
            return bookRepository.findByLanguage(bookLanguage);
        }else {
            System.out.println("language is not valid");
        }
        return null;
    }

    @Override
    public List<BookEntity> findAll() {
        System.out.println("find all method in service");

        return bookRepository.findAll();
    }

    @Override
    public BookEntity finById(Integer id) {
        System.out.println("find by id method in service");
        if(id!=null && id>0)
        {
            System.out.println("id is valid");
            return bookRepository.findById(id);
        }else {
            System.out.println("id is not valid");
        }
        return null;
    }

    @Override
    public BookEntity updatePriceByBookTitle(Integer id, String title, Float price) {
        System.out.println("updatePriceByBookTitle method in service");

        return bookRepository.updatePriceByBookTitle(id,title,price);
    }

    @Override
    public BookEntity updateAuthorByBookTitle(Integer id, String title, String author) {
        System.out.println("updateAuthorByBookTitle method in service");

        return bookRepository.updateAuthorByBookTitle(id,title,author);
    }

    @Override
    public BookEntity updateLanguageByAuthor(Integer id, String language, String author) {
        System.out.println("updateLanguageByAuthor method in service");

        return bookRepository.updateLanguageByAuthor(id,language,author);

    }

    @Override
    public List<String> getAllBookTitle() {
        System.out.println("getAllBookTitle method in service");
        return bookRepository.getAllBookTitle();
    }

    @Override
    public List<Float> getAllPrice() {
        System.out.println("getAllPrice method in service");
        return bookRepository.getAllPrice();
    }

    @Override
    public List<Object[]> getAllAuthorAndLanguage() {
        System.out.println("getAllAuthorAndLanguage method in service");
        return bookRepository.getAllAuthorAndLanguage();
    }
}
