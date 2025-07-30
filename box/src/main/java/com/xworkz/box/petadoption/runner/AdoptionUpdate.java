package com.xworkz.box.petadoption.runner;

import com.xworkz.box.petadoption.entity.PetAdoptionEntity;

import javax.persistence.*;

public class AdoptionUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            PetAdoptionEntity petAdoptionEntity=entityManager.find(PetAdoptionEntity.class,1);
            System.out.println("Data: "+petAdoptionEntity);
            if(petAdoptionEntity!=null)
            {
                petAdoptionEntity.setAdopterName("Sam");
            }
            petAdoptionEntity=entityManager.merge(petAdoptionEntity);
            System.out.println("updated data: "+petAdoptionEntity);
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
