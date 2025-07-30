package com.xworkz.bottle.soap.runner;


import com.xworkz.bottle.soap.entity.SoapEntity;

import javax.persistence.*;
import java.time.LocalDate;

public class SoapWriteData {

    public static void main(String[] args) {

        SoapEntity soapEntity=new SoapEntity();
        soapEntity.setBrandName("Lux");
        soapEntity.setFragrance("Rose");
        soapEntity.setColor("pink");
        soapEntity.setPrice(50);
        soapEntity.setManufactureDate(LocalDate.of(2021,9,18));

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(soapEntity);
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
