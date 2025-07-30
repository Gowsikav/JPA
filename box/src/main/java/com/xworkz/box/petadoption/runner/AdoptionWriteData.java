package com.xworkz.box.petadoption.runner;

import com.xworkz.box.petadoption.entity.PetAdoptionEntity;

import javax.persistence.*;

public class AdoptionWriteData {

    public static void main(String[] args) {

        PetAdoptionEntity petAdoptionEntity=new PetAdoptionEntity();
        petAdoptionEntity.setAdopterName("Shalini");
        petAdoptionEntity.setType("Cat");
        petAdoptionEntity.setName("Minion");
        petAdoptionEntity.setGender("female");
        petAdoptionEntity.setBreed("Persian");

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(petAdoptionEntity);
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
