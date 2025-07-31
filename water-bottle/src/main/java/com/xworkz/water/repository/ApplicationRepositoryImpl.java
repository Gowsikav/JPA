package com.xworkz.water.repository;

import com.xworkz.water.entity.ApplicationEntity;

import javax.persistence.*;
import java.util.Optional;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    public ApplicationRepositoryImpl() {
        System.out.println("ApplicationRepository implementation constructor");
    }

    @Override
    public boolean saveApplication(ApplicationEntity applicationEntity) {
        System.out.println("saveApplication method in ApplicationRepository implementation");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(applicationEntity);
            entityTransaction.commit();
            System.out.println("Insertion done");

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("rollback");
                return false;
            }
        } finally {
            if (entityManagerFactory != null) {
                System.out.println("entityManagerFactory is closed");
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
        return true;
    }

    @Override
    public Optional<ApplicationEntity> findById(int id) {
        System.out.println("findById method in ApplicationRepository implementation");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        ApplicationEntity applicationEntity = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager = entityManagerFactory.createEntityManager();

            applicationEntity = entityManager.find(ApplicationEntity.class, id);
            if (applicationEntity != null) {
                System.out.println("Id is found");
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManagerFactory != null) {
                System.out.println("entityManagerFactory is closed");
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
        return applicationEntity == null ? Optional.empty() : Optional.of(applicationEntity);
    }

    @Override
    public boolean deleteById(int id) {
        System.out.println("deleteById method in ApplicationRepository implementation");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            ApplicationEntity applicationEntity = entityManager.find(ApplicationEntity.class, id);
            if (applicationEntity != null) {
                entityManager.remove(applicationEntity);
                System.out.println("id is removed");
                entityTransaction.commit();
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("rollback");
            }
            return false;
        } finally {
            if (entityManagerFactory != null) {
                System.out.println("entityManagerFactory is closed");
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }

        return true;
    }

    @Override
    public boolean updateById(int id, String company) {
        System.out.println("updateById method in ApplicationRepository implementation");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            ApplicationEntity applicationEntity = entityManager.find(ApplicationEntity.class, id);
            if (applicationEntity != null) {
                applicationEntity.setCompany(company);
                entityManager.merge(applicationEntity);
                entityTransaction.commit();
            } else {
                System.out.println("Id is not found for update");
                return false;
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("rollback");
            }
            return false;
        } finally {
            if (entityManagerFactory != null) {
                System.out.println("entityManagerFactory is closed");
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
        return true;
    }
}

