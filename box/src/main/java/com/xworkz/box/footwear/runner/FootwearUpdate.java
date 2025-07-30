package com.xworkz.box.footwear.runner;

import com.xworkz.box.footwear.entity.FootwearEntity;

import javax.persistence.*;

public class FootwearUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            FootwearEntity footwearEntity=entityManager.find(FootwearEntity.class,1);
            System.out.println("Data: "+footwearEntity);
            if(footwearEntity!=null)
            {
                footwearEntity.setType("Shoe");
            }
            footwearEntity=entityManager.merge(footwearEntity);
            System.out.println("updated data: "+footwearEntity);
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
