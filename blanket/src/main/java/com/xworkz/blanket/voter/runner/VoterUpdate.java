package com.xworkz.blanket.voter.runner;

import com.xworkz.blanket.voter.entity.VoterEntity;

import javax.persistence.*;

public class VoterUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            VoterEntity voterEntity=entityManager.find(VoterEntity.class,1);
            System.out.println("Data: "+voterEntity);
            if(voterEntity!=null)
            {
                voterEntity.setAge(30);
            }
            voterEntity=entityManager.merge(voterEntity);
            System.out.println("updated data: "+voterEntity);
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
