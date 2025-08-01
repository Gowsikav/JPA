package com.xworkz.book.repository;

import com.xworkz.book.entity.BookEntity;
import com.xworkz.book.util.DBConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository{
    public BookRepositoryImpl()
    {
        System.out.println("BookRepositoryImpl constructor");
    }

    @Override
    public boolean save(BookEntity bookEntity) {
        System.out.println("save method in repository");

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(bookEntity);
            entityTransaction.commit();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("delete method in repository");

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        BookEntity bookEntity=null;
        try{
            entityManager= DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            bookEntity=entityManager.find(BookEntity.class,id);
            if(bookEntity!=null)
            {
                entityManager.remove(bookEntity);
                entityTransaction.commit();
            }else {
                System.out.println("id is not found");
                return false;
            }
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public boolean UpdateById(Integer id, Float price) {
        System.out.println("update by id method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        BookEntity bookEntity=null;
        try{
            entityManager= DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            bookEntity=entityManager.find(BookEntity.class,id);
            if(bookEntity!=null)
            {
                bookEntity.setPrice(price);
                bookEntity=entityManager.merge(bookEntity);
                System.out.println("updated data:"+bookEntity);
                entityTransaction.commit();
            }else {
                System.out.println("id is not found");
                return false;
            }
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public Optional<BookEntity> findByTitle(String bookTitle) {
        System.out.println("find by title method in repository");
        EntityManager entityManager=null;
        BookEntity bookEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            bookEntity= (BookEntity) entityManager.createNamedQuery("getTitle").setParameter("title",bookTitle).getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }

        return bookEntity!=null?Optional.of(bookEntity):Optional.empty();
    }

    @Override
    public Optional<BookEntity> findByAuthor(String bookAuthor) {
        System.out.println("find by author method in repository");
        EntityManager entityManager=null;
        BookEntity bookEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            bookEntity= (BookEntity) entityManager.createNamedQuery("getAuthor").setParameter("author",bookAuthor).getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return bookEntity!=null?Optional.of(bookEntity):Optional.empty();
    }

    @Override
    public Optional<BookEntity> findByLanguage(String bookLanguage) {
        System.out.println("find by language method in repository");
        EntityManager entityManager=null;
        BookEntity bookEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            bookEntity= (BookEntity) entityManager.createNamedQuery("getLanguage").setParameter("language",bookLanguage).getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return bookEntity!=null?Optional.of(bookEntity):Optional.empty();
    }

}
