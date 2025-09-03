package com.xworkz.foodorder.repository;

import com.xworkz.foodorder.entity.FoodOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class FoodOrderRepositoryImpl implements FoodOrderRepository{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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

    @Override
    public Boolean updateFoodOrderById(FoodOrderEntity entity) {
        System.out.println("updateById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("updateFoodOrderById")
                    .setParameter("foodName",entity.getFoodName())
                    .setParameter("quantity",entity.getQuantity())
                    .setParameter("description",entity.getDescription())
                    .setParameter("restaurantName",entity.getRestaurantName())
                    .setParameter("price",entity.getPrice())
                    .setParameter("foodId",entity.getFoodId())
                    .executeUpdate();
            if(rows>0)
            {
                System.out.println("Rows: "+rows);
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
                return false;
            }
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
    public Boolean deleteById(Integer id) {
        System.out.println("deleteById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("deleteById").setParameter("foodId",id).executeUpdate();
            if(rows>0)
            {
                System.out.println("Rows: "+rows);
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
    public List<FoodOrderEntity> searchByFoodName(String name) {
        System.out.println("searchByFoodName method in repository");
        EntityManager entityManager=null;
        List<FoodOrderEntity> foodOrderEntity=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            foodOrderEntity=entityManager.createNamedQuery("searchByFoodName").setParameter("foodName",name).getResultList();
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
        return foodOrderEntity;
    }
}
