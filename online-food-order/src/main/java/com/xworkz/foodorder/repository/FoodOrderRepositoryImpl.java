package com.xworkz.foodorder.repository;

import com.xworkz.foodorder.entity.FoodOrderEntity;
import com.xworkz.foodorder.util.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class FoodOrderRepositoryImpl implements FoodOrderRepository{

    @Autowired
    private DBConnection dbConnection;

    public FoodOrderRepositoryImpl()
    {
        System.out.println("FoodOrderRepositoryImpl constructor");
    }

    @Override
    public boolean save(FoodOrderEntity foodOrderEntity) {
        System.out.println("save method in repository");
        System.out.println("Repo data: "+foodOrderEntity);
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try
        {
            entityManager= dbConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(foodOrderEntity);
            entityTransaction.commit();

        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
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
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public List<FoodOrderEntity> findAllEntity() {
        System.out.println("findAll Entity method in repository");
        EntityManager entityManager=null;
        List<FoodOrderEntity> list=null;
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
                System.out.println("EntityManager is closed");
            }
        }
        return list;
    }

    @Override
    public FoodOrderEntity findById(Integer id) {
        System.out.println("findAll Entity method in repository");
        EntityManager entityManager=null;
        FoodOrderEntity entity=null;
        try{
            entityManager= dbConnection.getEntityManager();
            entity=(FoodOrderEntity) entityManager.createNamedQuery("findById").setParameter("id",id).getSingleResult();
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
}
