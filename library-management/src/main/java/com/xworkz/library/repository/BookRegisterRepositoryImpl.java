package com.xworkz.library.repository;

import com.xworkz.library.entity.BookRegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class BookRegisterRepositoryImpl implements BookRegisterRepository{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public BookRegisterRepositoryImpl()
    {
        System.out.println("BookRegisterRepository implementation constructor");
    }

    @Override
    public boolean save(BookRegisterEntity entity) {
        System.out.println("save method in repo");

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
            return true;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("roll backed");
            }
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
    public List<BookRegisterEntity> getAll() {
        System.out.println("getAll method in repository");
        EntityManager entityManager=null;
        List<BookRegisterEntity> entities=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();;
            Query query=entityManager.createNamedQuery("getAll");
            entities=query.getResultList();
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
        return entities;
    }

    @Override
    public BookRegisterEntity getSingleDataById(Integer id) {
        System.out.println("getSingleDataById method in repository");
        EntityManager entityManager=null;
        BookRegisterEntity entity=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entity=(BookRegisterEntity) entityManager.createNamedQuery("getDataById").setParameter("id",id).getSingleResult();
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
    public boolean updateDataById(BookRegisterEntity entity) {
        System.out.println("updateDataById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(entity);
            entityTransaction.commit();
            return true;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("roll backed");
            }
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
    public boolean deleteDataById(Integer id) {
        System.out.println("deleteDataById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int row=entityManager.createNamedQuery("deleteDataById").setParameter("id",id).executeUpdate();
            if(row==1) {
                entityTransaction.commit();
                return true;
            }
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("roll backed");
            }
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return false;
    }
}
