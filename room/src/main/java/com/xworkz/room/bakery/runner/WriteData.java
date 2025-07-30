package com.xworkz.room.bakery.runner;

import com.xworkz.room.bakery.entity.BakeryEntity;

import javax.persistence.*;

public class WriteData {

    public static void main(String[] args) {

        BakeryEntity bakeryEntity=new BakeryEntity();
        bakeryEntity.setBakeryName("Ayyankar");
        bakeryEntity.setActive(true);
        bakeryEntity.setOwnerName("Ramesh");
        bakeryEntity.setContactNumber(8294620471L);

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(bakeryEntity);
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
