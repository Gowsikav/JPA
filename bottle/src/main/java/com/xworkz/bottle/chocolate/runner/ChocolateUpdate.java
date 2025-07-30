package com.xworkz.bottle.chocolate.runner;


import com.xworkz.bottle.chocolate.entity.ChocolateEntity;

import javax.persistence.*;

public class ChocolateUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            ChocolateEntity chocolateEntity=entityManager.find(ChocolateEntity.class,1);
            System.out.println("Data: "+chocolateEntity);
            if(chocolateEntity!=null)
            {
                chocolateEntity.setCostPerKg(50);
            }
            chocolateEntity=entityManager.merge(chocolateEntity);
            System.out.println("updated data: "+chocolateEntity);
            entityTransaction.commit();

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
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
