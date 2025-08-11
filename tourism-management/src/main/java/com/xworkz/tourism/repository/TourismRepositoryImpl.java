package com.xworkz.tourism.repository;

import com.xworkz.tourism.entity.TourismEntity;
import com.xworkz.tourism.util.DBConnection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class TourismRepositoryImpl implements TourismRepository{
    public TourismRepositoryImpl()
    {
        System.out.println("TourismRepositoryImpl constructor");
    }

    @Override
    public boolean save(TourismEntity entity) {
        System.out.println("save method in repository");
        System.out.println("Repo data: "+entity);

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= DBConnection.getEntityManager();
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
                return false;
            }
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("entityManager is closed");
            }
        }
        return true;
    }

    @Override
    public List<TourismEntity> getAllEntity() {
        System.out.println("getAllEntity method in repository");

        EntityManager entityManager=null;
        List<TourismEntity> list=null;
        try{
            entityManager=DBConnection.getEntityManager();
            list =entityManager.createNamedQuery("getAllEntity").getResultList();
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
