package com.xworkz.passport.repository;

import com.xworkz.passport.entity.PassportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class PassportRepositoryImpl implements PassportRepository{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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

    @Override
    public PassportEntity findByPassportId(Integer id) {
        System.out.println("findByPassportId method in repository");
        EntityManager entityManager=null;
        PassportEntity passport=null;
        try{
            entityManager= entityManagerFactory.createEntityManager();
            passport=(PassportEntity) entityManager.createNamedQuery("findUserId").setParameter("passportId",id).getSingleResult();
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
        return passport;
    }

    @Override
    public Boolean updatePassportEntityById(PassportEntity entity) {
        System.out.println("updatePassportEntityById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("updatePassportEntityById")
                    .setParameter("passportOffice",entity.getPassportOffice())
                    .setParameter("name",entity.getName())
                    .setParameter("surName",entity.getSurName())
                    .setParameter("dateOfBirth",entity.getDateOfBirth())
                    .setParameter("email",entity.getEmail())
                    .setParameter("phoneNumber",entity.getPhoneNumber())
                    .setParameter("loginSameAsEmail",entity.getLoginSameAsEmail())
                    .setParameter("loginId",entity.getLoginId())
                    .setParameter("password",entity.getPassword())
                    .setParameter("confirmPassword",entity.getConfirmPassword())
                    .setParameter("hintQuestion",entity.getHintQuestion())
                    .setParameter("hintAnswer",entity.getHintAnswer())
                    .setParameter("passportId",entity.getPassportId())
                    .executeUpdate();
            if(rows>0)
            {
                System.out.println("rows: "+rows);
                entityTransaction.commit();
                return true;
            }
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
        return false;
    }

    @Override
    public Boolean deleteUserById(Integer id) {
        System.out.println("deleteUserById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("deleteUserById").setParameter("passportId",id).executeUpdate();
            if(rows>0)
            {
                System.out.println("Rows :"+rows);
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
    public List<PassportEntity> searchUserByUserName(String name) {
        System.out.println("searchUserByUserName method in repository");
        EntityManager entityManager=null;
        List<PassportEntity> list=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            list=entityManager.createNamedQuery("searchUserByName").setParameter("name",name).getResultList();
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
        return list;
    }
}
