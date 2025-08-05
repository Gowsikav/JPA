package com.xworkz.book.repository;

import com.xworkz.book.entity.BookEntity;
import com.xworkz.book.util.DBConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {
    public BookRepositoryImpl() {
        System.out.println("BookRepositoryImpl constructor");
    }

    @Override
    public boolean save(BookEntity bookEntity) {
        System.out.println("save method in repository");

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(bookEntity);
            entityTransaction.commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("delete method in repository");

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        BookEntity bookEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            bookEntity = entityManager.find(BookEntity.class, id);
            if (bookEntity != null) {
                entityManager.remove(bookEntity);
                entityTransaction.commit();
            } else {
                System.out.println("id is not found");
                return false;
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public boolean UpdateById(Integer id, Float price) {
        System.out.println("update by id method in repository");
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        BookEntity bookEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            bookEntity = entityManager.find(BookEntity.class, id);
            if (bookEntity != null) {
                bookEntity.setPrice(price);
                bookEntity = entityManager.merge(bookEntity);
                System.out.println("updated data:" + bookEntity);
                entityTransaction.commit();
            } else {
                System.out.println("id is not found");
                return false;
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public Optional<BookEntity> findByTitle(String bookTitle) {
        System.out.println("find by title method in repository");
        EntityManager entityManager = null;
        BookEntity bookEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            bookEntity = (BookEntity) entityManager.createNamedQuery("getTitle").setParameter("title", bookTitle).getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }

        return bookEntity != null ? Optional.of(bookEntity) : Optional.empty();
    }

    @Override
    public Optional<BookEntity> findByAuthor(String bookAuthor) {
        System.out.println("find by author method in repository");
        EntityManager entityManager = null;
        BookEntity bookEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("getAuthor");
            bookEntity = (BookEntity) query.setParameter("author", bookAuthor).getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return bookEntity != null ? Optional.of(bookEntity) : Optional.empty();
    }

    @Override
    public List<BookEntity> findByLanguage(String bookLanguage) {
        System.out.println("find by language method in repository");
        EntityManager entityManager = null;
        List<BookEntity> list = null;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("getLanguage");
            list = query.setParameter("language", bookLanguage).getResultList();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return list;
    }

    @Override
    public List<BookEntity> findAll() {
        System.out.println("findAll method in repository");
        EntityManager entityManager = null;
        List<BookEntity> list = null;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("findAll");
            list = query.getResultList();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return list;
    }

    @Override
    public BookEntity findById(Integer id) {
        System.out.println("find by id method in repository");
        EntityManager entityManager = null;
        BookEntity bookEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("findById");
            bookEntity = (BookEntity) query.setParameter("id", id).getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return bookEntity;
    }

    @Override
    public BookEntity updatePriceByBookTitle(Integer id, String title, Float price) {
        System.out.println("updatePriceByBookTitle method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        BookEntity bookEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int row=entityManager.createNamedQuery("updatePriceByBookTitle")
                    .setParameter("price",price)
                    .setParameter("id",id)
                    .setParameter("bookTitle",title).executeUpdate();
            System.out.println("Row Affected: "+row);
            entityTransaction.commit();
            bookEntity=entityManager.find(BookEntity.class,id);

            return bookEntity;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
            }
        }finally {
            if (entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return bookEntity;
    }

    @Override
    public BookEntity updateAuthorByBookTitle(Integer id, String title, String author) {
        System.out.println("updateAuthorByBookTitle method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        BookEntity bookEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int row=entityManager.createNamedQuery("updateAuthorByBookTitle")
                    .setParameter("bookTitle",title)
                    .setParameter("id",id)
                    .setParameter("author",author).executeUpdate();
            System.out.println("Row Affected: "+row);
            entityTransaction.commit();
            bookEntity=entityManager.find(BookEntity.class,id);

            return bookEntity;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
            }
        }finally {
            if (entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return bookEntity;
    }

    @Override
    public BookEntity updateLanguageByAuthor(Integer id, String language, String author) {
        System.out.println("updateLanguageByAuthor method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        BookEntity bookEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int row=entityManager.createNamedQuery("updateLanguageByAuthor")
                    .setParameter("id",id)
                    .setParameter("language",language)
                    .setParameter("author",author).executeUpdate();
            System.out.println("Row Affected: "+row);
            entityTransaction.commit();
            bookEntity=entityManager.find(BookEntity.class,id);

            return bookEntity;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
            }
        }finally {
            if (entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return bookEntity;
    }


}
