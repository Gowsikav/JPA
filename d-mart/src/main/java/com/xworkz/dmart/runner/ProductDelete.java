package com.xworkz.dmart.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.*;

public class ProductDelete {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            ProductEntity productEntity=entityManager.find(ProductEntity.class,5);
            System.out.println("product details to delete: "+productEntity);
            if(productEntity!=null) {
                entityManager.remove(productEntity);
                entityTransaction.commit();
                System.out.println("product removed");
            }

        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Rollback");
            }
        }finally {
            if(entityManagerFactory!=null)
            {
                System.out.println("entityManagerFactory is closed");
                entityManagerFactory.close();
            }
            if(entityManager!=null)
            {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }
}
