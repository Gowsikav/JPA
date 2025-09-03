package com.xworkz.onlineexam.repository;

import com.xworkz.onlineexam.entity.OnlineExamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class OnlineExamRepositoryImpl implements OnlineExamRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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
            entityManager=entityManagerFactory.createEntityManager();
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
            entityManager= entityManagerFactory.createEntityManager();
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

    @Override
    public OnlineExamEntity findById(Integer id) {
        System.out.println("findAll Entity method in repository");
        EntityManager entityManager=null;
        OnlineExamEntity entity=null;
        try{
            entityManager= entityManagerFactory.createEntityManager();
            entity=(OnlineExamEntity) entityManager.createNamedQuery("findById").setParameter("id",id).getSingleResult();
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
    public Boolean updateOnlineExamById(OnlineExamEntity entity) {
        System.out.println("updateOnlineExamById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("updateOnlineExamById")
                    .setParameter("subject",entity.getSubject())
                    .setParameter("noOfQuestions",entity.getNoOfQuestions())
                    .setParameter("examDate",entity.getExamDate())
                    .setParameter("totalMarks",entity.getTotalMarks())
                    .setParameter("durationHours",entity.getDurationHours())
                    .setParameter("durationMinutes",entity.getDurationMinutes())
                    .setParameter("examId",entity.getExamId())
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
    public Boolean deleteById(Integer id) {
        System.out.println("deleteById method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Integer rows=entityManager.createNamedQuery("deleteExamById").setParameter("examId",id).executeUpdate();
            if(rows>0)
            {
                System.out.println("Rows : "+rows);
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
    public List<OnlineExamEntity> searchBySubject(String name) {
        System.out.println("searchBySubject method in repository");
        EntityManager entityManager=null;
        List<OnlineExamEntity> list=null;
        try {
            entityManager= entityManagerFactory.createEntityManager();
            list=entityManager.createNamedQuery("searchOnlineExamBySubject").setParameter("subject",name).getResultList();

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
