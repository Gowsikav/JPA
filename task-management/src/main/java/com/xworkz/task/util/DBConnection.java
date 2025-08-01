package com.xworkz.task.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class DBConnection {

    private static EntityManagerFactory entityManagerFactory;
    private DBConnection()
    {
        System.out.println("DBConnection constructor");
    }

    static {
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
        if(entityManagerFactory.isOpen() && entityManagerFactory!=null)
        {
            entityManagerFactory.close();
            System.out.println("EntityManagerFactory is closed");
        }
    }
}
