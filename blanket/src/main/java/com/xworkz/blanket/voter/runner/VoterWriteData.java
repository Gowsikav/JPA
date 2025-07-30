package com.xworkz.blanket.voter.runner;

import com.xworkz.blanket.voter.entity.VoterEntity;

import javax.persistence.*;
import java.time.LocalDate;

public class VoterWriteData {

    public static void main(String[] args) {

        VoterEntity voterEntity=new VoterEntity();
        voterEntity.setAge(25);
        voterEntity.setName("Ramesh");
        voterEntity.setVoterIdNumber("V9765");
        voterEntity.setGender("male");
        voterEntity.setRegistrationDate(LocalDate.of(2018,8,30));

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(voterEntity);
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
