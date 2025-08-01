package com.xworkz.water.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class JPAConnection {

    private JPAConnection()
    {
        System.out.println("Jpa Connection constructor");
    }
   private static EntityManagerFactory entityManagerFactory = null;
    static{
        try{
           entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static EntityManager getEntityManager()
    {
       return entityManagerFactory.createEntityManager();
    }

    public static void closeResource()
    {
        if(entityManagerFactory!=null && entityManagerFactory.isOpen())
        {
            entityManagerFactory.close();
            System.out.println("entityManagerFactory is closed");
        }
    }
}
