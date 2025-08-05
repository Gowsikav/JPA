package com.xworkz.task.repository;

import com.xworkz.task.entity.TaskEntity;
import com.xworkz.task.util.DBConnection;
import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository {

    public TaskRepositoryImpl() {
        System.out.println("TaskRepository implementation constructor");
    }

    @Override
    public boolean save(TaskEntity taskEntity) {
        System.out.println("save method in repository");

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(taskEntity);
            entityTransaction.commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("delete method in repository");

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        TaskEntity taskEntity;
        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            taskEntity = entityManager.find(TaskEntity.class, id);
            if (taskEntity != null) {
                System.out.println("id found");
                entityManager.remove(taskEntity);
                entityTransaction.commit();
            } else {
                System.out.println("id not found");
                return false;
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public boolean UpdateById(Integer id, Status status) {
        System.out.println("update by id method in repository");

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        TaskEntity taskEntity;
        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            taskEntity = entityManager.find(TaskEntity.class, id);
            if (taskEntity != null) {
                System.out.println("id found");
                taskEntity.setStatus(Status.COMPLETED);
                entityManager.merge(taskEntity);
                entityTransaction.commit();
                System.out.println("Updated data");
                System.out.println(taskEntity);
            } else {
                System.out.println("id not found");
                return false;
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed");
            }
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return true;
    }

    @Override
    public Optional<TaskEntity> findByTitle(String taskTitle) {
        System.out.println("find by title method in repository");
        EntityManager entityManager = null;
        TaskEntity taskEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            taskEntity = (TaskEntity) entityManager.createNamedQuery("getTitle").setParameter("title", taskTitle).getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity != null ? Optional.of(taskEntity) : Optional.empty();
    }

    @Override
    public List<TaskEntity> findByPriority(Priority priority) {
        System.out.println("find by priority method in repository");
        EntityManager entityManager = null;
        List<TaskEntity> list;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("getPriority");
            list = query.setParameter("priority", priority).getResultList();
            return list;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return null;
    }

    @Override
    public Optional<TaskEntity> findByDueDate(LocalDate taskDueDate) {
        System.out.println("find by Due date method in repository");
        EntityManager entityManager = null;
        TaskEntity taskEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("getDueDate");
            taskEntity = (TaskEntity) query.setParameter("dueDate", taskDueDate).getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity != null ? Optional.of(taskEntity) : Optional.empty();
    }

    @Override
    public List<TaskEntity> findAll() {
        System.out.println("findAll method in repository");
        EntityManager entityManager = null;
        List<TaskEntity> list;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("findAll");
            list = query.getResultList();
            return list;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return Collections.emptyList();
    }

    @Override
    public TaskEntity findById(Integer id) {
        System.out.println("find by id method in repository");
        EntityManager entityManager = null;
        TaskEntity taskEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();
            Query query = entityManager.createNamedQuery("findById");
            taskEntity = (TaskEntity) query.setParameter("id", id).getSingleResult();
            return taskEntity;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return null;
    }

    @Override
    public TaskEntity updateStatusByTitle(Integer id, Status status, String title) {
        System.out.println("updateStatusByTitle method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        TaskEntity taskEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int row=entityManager.createNamedQuery("updateStatusByTitle").setParameter("status",status)
                    .setParameter("title",title)
                    .setParameter("id",id).executeUpdate();
            System.out.println("Row Affected: "+row);
            entityTransaction.commit();
            taskEntity=entityManager.find(TaskEntity.class,id);

            return taskEntity;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
            }
        }finally {
            if (entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity;
    }

    @Override
    public TaskEntity updatePriorityByDueDate(Integer id, Priority priority, LocalDate dueDate) {
        System.out.println("updatePriorityByDueDate method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        TaskEntity taskEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int row=entityManager.createNamedQuery("updatePriorityByDueDate").setParameter("priority",priority)
                    .setParameter("dueDate",dueDate)
                    .setParameter("id",id).executeUpdate();
            System.out.println("Row Affected: "+row);
            entityTransaction.commit();
            taskEntity=entityManager.find(TaskEntity.class,id);

            return taskEntity;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
            }
        }finally {
            if (entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity;
    }

    @Override
    public TaskEntity updateDueDateByStatus(Integer id, Status status, LocalDate dueDate) {
        System.out.println("updateDueDateByStatus method in repository");
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;
        TaskEntity taskEntity=null;
        try{
            entityManager=DBConnection.getEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            int row=entityManager.createNamedQuery("updateDueDateByStatus")
                    .setParameter("status",status)
                    .setParameter("dueDate",dueDate)
                    .setParameter("id",id).executeUpdate();
            System.out.println("Row Affected: "+row);
            entityTransaction.commit();
            taskEntity=entityManager.find(TaskEntity.class,id);

            return taskEntity;
        }catch (PersistenceException e)
        {
            System.out.println(e.getMessage());
            if(entityTransaction!=null)
            {
                entityTransaction.rollback();
            }
        }finally {
            if (entityManager!=null && entityManager.isOpen())
            {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return taskEntity;
    }

}
