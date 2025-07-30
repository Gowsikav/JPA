package com.xworkz.room.clothes.runner;

import com.xworkz.room.clothes.entity.ClothesEntity;

import javax.persistence.*;

public class ClothesUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            ClothesEntity clothesEntity=entityManager.find(ClothesEntity.class,1);
            System.out.println("Data: "+clothesEntity);
            if(clothesEntity!=null)
            {
                clothesEntity.setBrand("Puma");
            }
            clothesEntity=entityManager.merge(clothesEntity);
            System.out.println("updated data: "+clothesEntity);
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
