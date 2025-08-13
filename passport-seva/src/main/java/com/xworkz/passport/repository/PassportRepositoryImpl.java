package com.xworkz.passport.repository;

import com.xworkz.passport.entity.PassportEntity;
import com.xworkz.passport.util.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class PassportRepositoryImpl implements PassportRepository{

    @Autowired
    private DBConnection dbConnection;

    public PassportRepositoryImpl()
    {
        System.out.println("PassportRepositoryImpl constructor");
    }

    @Override
    public boolean save(PassportEntity passport) {

        System.out.println("save method in repository");
        System.out.println("Repo Data: "+passport);
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(passport);
            entityTransaction.commit();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }

        return true;
    }

    @Override
    public List<PassportEntity> findAll() {
        System.out.println("findAll method in repository");
        EntityManager entityManager=null;
        List<PassportEntity> list=null;
        try {
            entityManager= dbConnection.getEntityManager();
            list=entityManager.createNamedQuery("findAll").getResultList();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        } finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return list;
    }

    @Override
    public String findExistingEmail(String email) {
        System.out.println("findExistingEmail method in repository");
        EntityManager entityManager=null;
        String existingEmail=null;
        try{
            entityManager= dbConnection.getEntityManager();
            existingEmail=(String)entityManager.createNamedQuery("findExistingEmail").setParameter("email",email).getSingleResult();

        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return existingEmail;
    }

    @Override
    public Long findExistingPhoneNumber(Long phoneNumber) {
        System.out.println("findExistingPhoneNumber method in repository");
        EntityManager entityManager=null;
        Long existingPhoneNumber=null;
        try{
            entityManager= dbConnection.getEntityManager();
            existingPhoneNumber=(Long)entityManager.createNamedQuery("findExistingPhoneNumber").setParameter("phoneNumber",phoneNumber).getSingleResult();

        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return existingPhoneNumber;
    }

    @Override
    public String findExistingLoginId(String loginId) {
        System.out.println("findExistingLoginId method in repository");
        EntityManager entityManager=null;
        String findExistingLoginId=null;
        try{
            entityManager= dbConnection.getEntityManager();
            findExistingLoginId=(String)entityManager.createNamedQuery("findExistingLoginId").setParameter("loginId",loginId).getSingleResult();

        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return findExistingLoginId;
    }
}
