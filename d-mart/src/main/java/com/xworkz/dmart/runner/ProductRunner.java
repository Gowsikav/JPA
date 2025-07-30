package com.xworkz.dmart.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.*;

public class ProductRunner {
    public static void main(String[] args) {

        ProductEntity productEntity=new ProductEntity();
        productEntity.setName("Tea");
        productEntity.setManuDate("28-07-2025");
        productEntity.setPrice(25);
        productEntity.setCompany("Tea company");

       try{
           EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
           // load,register and get connection
           System.out.println("Connection established: "+entityManagerFactory);
           EntityManager entityManager= entityManagerFactory.createEntityManager();
           // handle DCL(insert,update,delete) and DQL(select)
           EntityTransaction entityTransaction=entityManager.getTransaction();
           // handle TCL(set autocommit,commit,rollback)

           entityTransaction.begin();
           entityManager.persist(productEntity);
           entityTransaction.commit();
       }catch(PersistenceException e)
       {
           System.out.println(e.getMessage());
       }

    }
}
