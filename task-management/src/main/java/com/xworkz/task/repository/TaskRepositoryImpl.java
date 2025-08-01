package com.xworkz.task.repository;

import com.xworkz.task.entity.TaskEntity;
import com.xworkz.task.util.DBConnection;
import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository{

    public TaskRepositoryImpl()
    {
        System.out.println("TaskRepository implementation constructor");
    }

    @Override
    public boolean save(TaskEntity taskEntity) {
        System.out.println("save method in repository");

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        try{
            entityManager= DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(taskEntity);
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
    public boolean delete(Integer id) {
        System.out.println("delete method in repository");

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        TaskEntity taskEntity;
        try{
            entityManager= DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            taskEntity=entityManager.find(TaskEntity.class,id);
            if(taskEntity!=null)
            {
                System.out.println("id found");
                entityManager.remove(taskEntity);
                entityTransaction.commit();
            }else {
                System.out.println("id not found");
                return false;
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
        return true;
    }

    @Override
    public boolean UpdateById(Integer id, Status status) {
        System.out.println("update by id method in repository");

        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        TaskEntity taskEntity;
        try{
            entityManager= DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            taskEntity=entityManager.find(TaskEntity.class,id);
            if(taskEntity!=null)
            {
                System.out.println("id found");
                taskEntity.setStatus(Status.COMPLETED);
                entityManager.merge(taskEntity);
                entityTransaction.commit();
                System.out.println("Updated data");
                System.out.println(taskEntity);
            }else {
                System.out.println("id not found");
                return false;
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
        return true;
    }

    @Override
    public Optional<TaskEntity> findByTitle(String taskTitle) {
        System.out.println("find by title method in repository");
        EntityManager entityManager=null;
        TaskEntity taskEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            taskEntity= (TaskEntity) entityManager.createNamedQuery("getTitle").setParameter("title",taskTitle).getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity!=null?Optional.of(taskEntity):Optional.empty();
    }

    @Override
    public Optional<TaskEntity> findByPriority(Priority priority) {
        System.out.println("find by priority method in repository");
        EntityManager entityManager=null;
        TaskEntity taskEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            taskEntity= (TaskEntity) entityManager.createNamedQuery("getPriority").setParameter("priority",priority).getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity!=null?Optional.of(taskEntity):Optional.empty();
    }

    @Override
    public Optional<TaskEntity> findByDueDate(LocalDate taskDueDate) {
        System.out.println("find by Due date method in repository");
        EntityManager entityManager=null;
        TaskEntity taskEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            taskEntity= (TaskEntity) entityManager.createNamedQuery("getDueDate").setParameter("dueDate",taskDueDate).getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity!=null?Optional.of(taskEntity):Optional.empty();
    }

}
