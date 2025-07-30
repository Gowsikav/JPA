package com.xworkz.room.clothes.runner;

import com.xworkz.room.clothes.entity.ClothesEntity;

import javax.persistence.*;

public class ClothesWriteData {

    public static void main(String[] args) {

        ClothesEntity clothesEntity=new ClothesEntity();
        clothesEntity.setBrand("Twin birds");
        clothesEntity.setCategory("Pants");
        clothesEntity.setPrice(200);
        clothesEntity.setColor("Blue");
        clothesEntity.setMaterial("Cotton");

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(clothesEntity);
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
