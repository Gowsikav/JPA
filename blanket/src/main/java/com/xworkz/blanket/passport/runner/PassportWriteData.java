package com.xworkz.blanket.passport.runner;

import com.xworkz.blanket.passport.entity.PassportEntity;

import javax.persistence.*;
import java.time.LocalDate;

public class PassportWriteData {

    public static void main(String[] args) {

        PassportEntity passportEntity = new PassportEntity();
        passportEntity.setPassportNumber("M67998");
        passportEntity.setName("Ram");
        passportEntity.setNationality("Indian");
        passportEntity.setGender("male");
        passportEntity.setBirthDate(LocalDate.of(2004, 6, 19));

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(passportEntity);
            entityTransaction.commit();
            System.out.println("Inserted done");
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed data");
            }
        } finally {
            if (entityManagerFactory != null) {
                System.out.println("entityManagerFactory is closed");
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }
}
