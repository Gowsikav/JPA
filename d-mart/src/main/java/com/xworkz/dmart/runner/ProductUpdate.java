package com.xworkz.dmart.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.*;

public class ProductUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("Connection established");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            ProductEntity productEntity = entityManager.find(ProductEntity.class, 2);
            System.out.println("ProductEntity for id 2: " + productEntity);
            if (productEntity != null) {
                productEntity.setPrice(80);
                productEntity.setName("Cold Coffee");
                ProductEntity updatedEntity = entityManager.merge(productEntity);
                System.out.println("Updated data: " + updatedEntity);
            }
            entityTransaction.commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Rollback data");
            }
        } finally {
            if (entityManagerFactory != null) {
                System.out.println("entityManagerFactory closed");
                entityManagerFactory.close();
            }
            if (entityManager!=null) {
                System.out.println("entityManager closed");
                entityManager.close();
            }
        }
    }
}
