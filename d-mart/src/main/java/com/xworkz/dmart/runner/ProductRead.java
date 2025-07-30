package com.xworkz.dmart.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class ProductRead {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        try {
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            ProductEntity productEntity=entityManager.find(ProductEntity.class,1);
            System.out.println(productEntity);

        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManagerFactory!=null)
            {
                entityManagerFactory.close();
                System.out.println("EntityManagerFactory closed");
            }
            if(entityManager!=null)
            {
                entityManager.close();
                System.out.println("EntityManager closed");
            }
        }
    }
}
