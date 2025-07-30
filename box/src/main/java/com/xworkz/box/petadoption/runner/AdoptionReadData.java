package com.xworkz.box.petadoption.runner;


import com.xworkz.box.petadoption.entity.PetAdoptionEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class AdoptionReadData {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();

             PetAdoptionEntity petAdoptionEntity =entityManager.find(PetAdoptionEntity.class,1);
            System.out.println(petAdoptionEntity);

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        finally {
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
