package com.xworkz.tourism.repository;

import com.xworkz.tourism.entity.TourismEntity;
import com.xworkz.tourism.util.DBConnection;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<TourismEntity> findById(Integer id) {
        System.out.println("Find by id in repository");
        EntityManager entityManager=null;
        Optional<TourismEntity> optionalTourismEntity=Optional.empty();
        try{
            entityManager=DBConnection.getEntityManager();
            TourismEntity tourism=(TourismEntity) entityManager.createNamedQuery("findById").setParameter("id",id).getSingleResult();
            if(tourism!=null)
            {
                return Optional.of(tourism);
            }
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
        return optionalTourismEntity;
    }

    @Override
    public Boolean updateTourismEntityById(TourismEntity tourismEntity) {
        System.out.println("updateTourismEntityById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("updateTourismEntityById")
                    .setParameter("packageName",tourismEntity.getPackageName())
                    .setParameter("destination",tourismEntity.getDestination())
                    .setParameter("days",tourismEntity.getDays())
                    .setParameter("packagePrice",tourismEntity.getPackagePrice())
                    .setParameter("personsCount",tourismEntity.getPersonsCount())
                    .setParameter("packageId",tourismEntity.getPackageId())
                    .executeUpdate();
            if(rows>0)
            {
                entityTransaction.commit();
                return true;
            }
        }catch (QueryException | NoResultException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
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
    public Boolean deleteTourismEntityById(Integer id) {
        System.out.println("deleteTourismEntityById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer row=entityManager.createNamedQuery("deleteTourismById").setParameter("packageId",id).executeUpdate();
            if(row>0)
            {
                entityTransaction.commit();
                return true;
            }
        }catch (NoResultException | QueryException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
                System.out.println("Roll backed");
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
}
