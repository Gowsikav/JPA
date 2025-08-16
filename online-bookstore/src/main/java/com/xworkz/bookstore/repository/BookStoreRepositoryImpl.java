package com.xworkz.bookstore.repository;

import com.xworkz.bookstore.entity.BookStoreEntity;
import com.xworkz.bookstore.util.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class BookStoreRepositoryImpl implements BookStoreRepository{

    @Autowired
    private DBConnection dbConnection;

    public BookStoreRepositoryImpl()
    {
        System.out.println("BookStoreRepositoryImpl constructor");
    }

    @Override
    public boolean save(BookStoreEntity entity) {
        System.out.println("save method in repository");
        System.out.println("Repo data: "+entity);
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null && entityTransaction.isActive())
            {
                entityTransaction.rollback();
                System.out.println("insert operation roll backed");
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
    public List<BookStoreEntity> findAllEntity() {
        System.out.println("findAll entity method in repository");
        EntityManager entityManager=null;
        List<BookStoreEntity> list=null;
        try{
            entityManager= dbConnection.getEntityManager();
            list=entityManager.createNamedQuery("findAllEntity").getResultList();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return list;
    }

    @Override
    public BookStoreEntity findById(Integer id) {
        System.out.println("findAll Entity method in repository");
        EntityManager entityManager=null;
        BookStoreEntity entity=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entity=(BookStoreEntity) entityManager.createNamedQuery("findById").setParameter("id",id).getSingleResult();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return entity;
    }

    @Override
    public Boolean updateBookStoreById(BookStoreEntity entity) {
        System.out.println("updateBookStoreById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("updateBookStoreById")
                    .setParameter("bookName",entity.getBookName())
                    .setParameter("bookAuthor",entity.getBookAuthor())
                    .setParameter("bookCategory",entity.getBookCategory())
                    .setParameter("bookPrice",entity.getBookPrice())
                    .setParameter("bookId",entity.getBookId())
                    .executeUpdate();
            if(rows>0)
            {
                System.out.println("Rows: "+rows);
                entityTransaction.commit();
                return true;
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("roll backed");
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
    public Boolean deleteBookStoreById(Integer id) {
        System.out.println("deleteBookStoreById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("deleteBookStoreById").setParameter("bookId",id).executeUpdate();
            if(rows>0)
            {
                System.out.println("rows: "+rows);
                entityTransaction.commit();
                return true;
            }
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null && entityTransaction.isActive())
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
        return false;
    }

    @Override
    public List<BookStoreEntity> searchBookStoreByBookName(String bookName) {
        System.out.println("searchBookStoreByBookName method in repository");
        EntityManager entityManager=null;
        List<BookStoreEntity> list=null;
        try{
            entityManager= dbConnection.getEntityManager();
            list=entityManager.createNamedQuery("searchBookStoreByBookName").setParameter("bookName",bookName).getResultList();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return list;
    }
}
