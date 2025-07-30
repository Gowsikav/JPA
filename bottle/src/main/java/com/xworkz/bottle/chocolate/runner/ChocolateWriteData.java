package com.xworkz.bottle.chocolate.runner;

import com.xworkz.bottle.chocolate.entity.ChocolateEntity;

import javax.persistence.*;

public class ChocolateWriteData {

    public static void main(String[] args) {

        ChocolateEntity chocolateEntity=new ChocolateEntity();
        chocolateEntity.setIngredientName("Water,sugar");
        chocolateEntity.setCostPerKg(30);
        chocolateEntity.setSupplierName("Kiren");
        chocolateEntity.setQuantityInKg(4);

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(chocolateEntity);
            entityTransaction.commit();
            System.out.println("Inserted done");
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Roll backed data");
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
