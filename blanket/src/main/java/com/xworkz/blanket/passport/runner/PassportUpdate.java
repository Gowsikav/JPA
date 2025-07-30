package com.xworkz.blanket.passport.runner;

import com.xworkz.blanket.passport.entity.PassportEntity;

import javax.persistence.*;

public class PassportUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            PassportEntity passportEntity=entityManager.find(PassportEntity.class,1);
            if(passportEntity!=null)
            {
                passportEntity.setPassportNumber("K682");
            }
            passportEntity=entityManager.merge(passportEntity);
            System.out.println("updated data: "+passportEntity);
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
