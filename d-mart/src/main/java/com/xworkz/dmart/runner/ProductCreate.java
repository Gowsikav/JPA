package com.xworkz.dmart.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.*;

public class ProductCreate {

    public static void main(String[] args) {

        ProductEntity productEntity=new ProductEntity();
        productEntity.setName("Laptop");
        productEntity.setPrice(40000);
//        productEntity.setManuDate("20-07-2025");
        productEntity.setCompany("Dell");

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
           entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
           entityManager=entityManagerFactory.createEntityManager();
           entityTransaction= entityManager.getTransaction();

           System.out.println("Transaction began");
           entityTransaction.begin();
           entityManager.persist(productEntity);// error throw in this line
           System.out.println("Insert into db");
           entityTransaction.commit();
           System.out.println("Insert done");

        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null) {
                entityTransaction.rollback();
                System.out.println("Roll backed insert");
            }
        }
        finally {
            if(entityManagerFactory!=null) {
                entityManagerFactory.close();
                System.out.println("EntityManagerFactory closed");
            }
            if(entityManager!=null) {
                entityManager.close();
                System.out.println("EntityManager closed");
            }
        }

    }
}
