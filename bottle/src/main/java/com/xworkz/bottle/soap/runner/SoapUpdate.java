package com.xworkz.bottle.soap.runner;

import com.xworkz.bottle.soap.entity.SoapEntity;

import javax.persistence.*;

public class SoapUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            SoapEntity soapEntity=entityManager.find(SoapEntity.class,1);
            System.out.println("Data: "+soapEntity);
            if(soapEntity!=null)
            {
                soapEntity.setFragrance("Red rose");
            }
            soapEntity=entityManager.merge(soapEntity);
            System.out.println("updated data: "+soapEntity);
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
