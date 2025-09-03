package com.xworkz.user.repository;

import com.xworkz.user.entity.RegisterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class RegisterRepositoryImpl implements RegisterRepository {

    private static final Logger log= LoggerFactory.getLogger(RegisterRepositoryImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public RegisterRepositoryImpl()
    {
        System.out.println("RegisterRepositoryImpl constructor");
        log.info("RegisterRepositoryImpl constructor");
    }

    @Override
    public boolean save(RegisterEntity entity) {
        System.out.println("save method in RegisterRepositoryImpl");
        System.out.println("Repo data: "+entity);

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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

    @Override
    public boolean updateUserDetails(RegisterEntity entity) {
        System.out.println("updateUserDetails method in repository");
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        RegisterEntity existingEntity;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            existingEntity = getUserDetailsByEmail(entity.getEmail());
            if (existingEntity == null) {
                System.out.println("User not found");
                return false;
            }
            if (entity.getProfilePath() == null || entity.getProfilePath().isEmpty()) {
                entity.setProfilePath(existingEntity.getProfilePath());
            }
            if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
                entity.setPassword(existingEntity.getPassword());
            }
            if (entity.getLockTime() == null) {
                entity.setLockTime(existingEntity.getLockTime());
            }
            entity.setLoginCount(existingEntity.getLoginCount());
            entity.setIsActive(true);
            RegisterEntity mergedEntity = entityManager.merge(entity);

            if (mergedEntity != null) {
                entityTransaction.commit();
                System.out.println("User updated successfully: " + mergedEntity);
                return true;
            }
        } catch (PersistenceException e) {
            System.out.println("Error while updating user: " + e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Transaction rolled back");
            }
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return false;
    }

    @Override
    public boolean updateOTPByEmail(String email,String otp) {
        System.out.println("updateOTPByEmail method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int rows=entityManager.createNamedQuery("setOTP")
                    .setParameter("otp",otp)
                    .setParameter("email",email)
                    .executeUpdate();
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
            if(entityManager!=null&& entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return false;
    }

    @Override
    public List<String> getAllEmail() {
        System.out.println("getAll Email from repository");
        EntityManager entityManager=null;
        List<String> list=null;
        try{
            entityManager= dbConnection.getEntityManager();
            list=entityManager.createNamedQuery("getAllEmail").getResultList();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("Entity manager is closed");
            }
        }
        return list;
    }
}
