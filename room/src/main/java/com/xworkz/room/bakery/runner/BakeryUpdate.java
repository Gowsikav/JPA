package com.xworkz.room.bakery.runner;

import com.xworkz.room.bakery.entity.BakeryEntity;

import javax.persistence.*;

public class BakeryUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            BakeryEntity bakeryEntity=entityManager.find(BakeryEntity.class,1);
            System.out.println("Data: "+bakeryEntity);
            if(bakeryEntity!=null)
            {
                bakeryEntity.setOwnerName("Rajesh");
            }
            bakeryEntity=entityManager.merge(bakeryEntity);
            System.out.println("updated data: "+bakeryEntity);
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
