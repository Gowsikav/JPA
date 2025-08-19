package com.xworkz.user.repository;

import com.xworkz.user.entity.RegisterEntity;
import com.xworkz.user.util.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

@Repository
public class RegisterRepositoryImpl implements RegisterRepository {

    @Autowired
    private DBConnection dbConnection;

    public RegisterRepositoryImpl()
    {
        System.out.println("RegisterRepositoryImpl constructor");
    }

    @Override
    public boolean save(RegisterEntity entity) {
        System.out.println("save method in RegisterRepositoryImpl");
        System.out.println("Repo data: "+entity);

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("roll backed");
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
    public String checkExistingEmail(String email) {
        System.out.println("checkExistingEmail method in repository");
        EntityManager entityManager=null;
        String existingEmail=null;
        try{
            entityManager= dbConnection.getEntityManager();
            existingEmail=(String) entityManager.createNamedQuery("checkExistingEmail").setParameter("email",email).getSingleResult();
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
    public String checkExistingPhoneNumber(String phoneNumber) {
        System.out.println("checkExistingPhoneNumber method in repository");
        EntityManager entityManager=null;
        String existingNumber=null;
        try{
            entityManager= dbConnection.getEntityManager();
            existingNumber=(String) entityManager.createNamedQuery("checkExistingPhoneNumber").setParameter("phoneNumber",phoneNumber).getSingleResult();
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
        return existingNumber;
    }

    @Override
    public RegisterEntity getUserDetailsByEmail(String email) {
        System.out.println("getUserDetailsByEmail method in repository");
        EntityManager entityManager=null;
        RegisterEntity entity=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entity=(RegisterEntity) entityManager.createNamedQuery("getDetailsByEmail")
                    .setParameter("email",email)
                    .getSingleResult();
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
        return entity;
    }

    @Override
    public String getOtp(String email) {
        System.out.println("getOtp method in repository");
        EntityManager entityManager=null;
        String otp=null;
        try{
            entityManager= dbConnection.getEntityManager();
            otp=(String) entityManager.createNamedQuery("getOtp")
                    .setParameter("email",email)
                    .getSingleResult();
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
        return otp;
    }

    @Override
    public boolean setPassword(String email, String password) {
        System.out.println("setPassword method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int rows=entityManager.createNamedQuery("setPassword").setParameter("password",password)
                    .setParameter("email",email).executeUpdate();
            System.out.println("Rows: "+rows);
            if(rows==1)
            {
                entityTransaction.commit();
                return true;
            }
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("roll backed");
            }
            return false;
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return false;
    }

    @Override
    public void mergeLockTime(RegisterEntity entity) {
        System.out.println("mergeLockTime method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(entity);
            entityTransaction.commit();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("roll backed");
            }
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
    }
}
