package com.xworkz.foodorder.util;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

@Component
public class DBConnection {

    private EntityManagerFactory entityManagerFactory;
    public DBConnection()
    {
        System.out.println("DBConnection constrcutor");
    }

    public EntityManager getEntityManager()
    {
        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }
        return entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    public void closeResource()
    {
        if(entityManagerFactory!=null && entityManagerFactory.isOpen())
        {
            System.out.println("EntityManagerFactory is closed");
            entityManagerFactory.close();
        }
    }
}
