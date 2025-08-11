package com.xworkz.onlineexam.repository;

import com.xworkz.onlineexam.entity.OnlineExamEntity;
import com.xworkz.onlineexam.util.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.List;

@Repository
public class OnlineExamRepositoryImpl implements OnlineExamRepository {

    @Autowired
    private DBConnection dbConnection;

    public OnlineExamRepositoryImpl()
    {
        System.out.println("OnlineExamRepositoryImpl constructor");
    }

    @Override
    public boolean save(OnlineExamEntity entity) {
        System.out.println("save method in repository");
        System.out.println("Repo data: "+entity);
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager=dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();
        }catch (PersistenceException e)
        {
            if(entityTransaction!=null && entityTransaction.isActive())
            {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("Entity manager is closed");
            }
        }

        return true;
    }

    @Override
    public List<OnlineExamEntity> findAllEntity() {
        System.out.println("findAll entity method in repository");
        EntityManager entityManager=null;
        List<OnlineExamEntity> list=null;
        try{
            entityManager= dbConnection.getEntityManager();
            list=entityManager.createNamedQuery("findAllEntity").getResultList();
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("Entity Manager is closed");
            }
        }
        return list;
    }
}
