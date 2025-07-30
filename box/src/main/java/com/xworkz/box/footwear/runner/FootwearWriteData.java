package com.xworkz.box.footwear.runner;

import com.xworkz.box.footwear.entity.FootwearEntity;

import javax.persistence.*;

public class FootwearWriteData {

    public static void main(String[] args) {

        FootwearEntity footwearEntity=new FootwearEntity();
        footwearEntity.setBrandName("Nike");
        footwearEntity.setType("Slipper");
        footwearEntity.setSize("7");
        footwearEntity.setPrice(450);
        footwearEntity.setColor("Black");


        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(footwearEntity);
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
