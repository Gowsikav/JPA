package com.xworkz.water.repository;

import com.xworkz.water.entity.ApplicationEntity;
import com.xworkz.water.util.JPAConnection;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

public class ApplicationRepositoryImpl implements ApplicationRepository {


    public ApplicationRepositoryImpl() {
        System.out.println("ApplicationRepository implementation constructor");
    }

    @Override
    public boolean saveApplication(ApplicationEntity applicationEntity) {
        System.out.println("saveApplication method in ApplicationRepository implementation");

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = JPAConnection.getEntityManager();
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

        EntityManager entityManager = null;
        ApplicationEntity applicationEntity = null;

        try {
            entityManager = JPAConnection.getEntityManager();

            applicationEntity = entityManager.find(ApplicationEntity.class, id);
            if (applicationEntity != null) {
                System.out.println("Id is found");
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
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

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = JPAConnection.getEntityManager();
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

        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = JPAConnection.getEntityManager();
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
            if (entityManager != null) {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
        return true;
    }

    @Override
    public void findByApplicationName(String applicationName) {
        System.out.println("findByApplicationName method in Repo implementation");
        EntityManager entityManager = null;
        ApplicationEntity applicationEntity;
        try {
            entityManager = JPAConnection.getEntityManager();
            applicationEntity = (ApplicationEntity) entityManager.createNamedQuery("applicationName").setParameter("name", applicationName).getSingleResult();
            if (applicationEntity != null) {
                System.out.println("Result: " + applicationEntity);
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(entityManager!=null)
            {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }

    @Override
    public void findByApplicationSize(String applicationSize) {
        System.out.println("findByApplicationSize method in Repo implementation");
        EntityManager entityManager = null;
        ApplicationEntity applicationEntity;
        try {
            entityManager = JPAConnection.getEntityManager();
            applicationEntity = (ApplicationEntity) entityManager.createNamedQuery("applicationSize").setParameter("size", applicationSize).getSingleResult();
            if (applicationEntity != null) {
                System.out.println("Result: " + applicationEntity);
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null)
            {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }

    @Override
    public void findByApplicationCompany(String applicationCompany) {
        System.out.println("findByApplicationCompany method in Repo implementation");
        EntityManager entityManager = null;
        ApplicationEntity applicationEntity;
        try {
            entityManager =JPAConnection.getEntityManager();
            applicationEntity = (ApplicationEntity) entityManager.createNamedQuery("applicationCompany").setParameter("company", applicationCompany).getSingleResult();
            if (applicationEntity != null) {
                System.out.println("Result: " + applicationEntity);
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null)
            {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }

    @Override
    public void findByApplicationUsersCount(Integer applicationUsersCount) {
        System.out.println("findByApplicationUsersCount method in Repo implementation");
        EntityManager entityManager = null;
        ApplicationEntity applicationEntity;
        try {
            entityManager = JPAConnection.getEntityManager();
            applicationEntity = (ApplicationEntity) entityManager.createNamedQuery("applicationUsers").setParameter("usersCount", applicationUsersCount).getSingleResult();
            if (applicationEntity != null) {
                System.out.println("Result: " + applicationEntity);
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null)
            {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }

    @Override
    public void findByApplicationRatings(Float applicationRatings) {
        System.out.println("findByApplicationRatings method in Repo implementation");
        EntityManager entityManager = null;
        ApplicationEntity applicationEntity;
        try {
            entityManager = JPAConnection.getEntityManager();
            applicationEntity = (ApplicationEntity) entityManager.createNamedQuery("applicationRatings").setParameter("ratings", applicationRatings).getSingleResult();
            if (applicationEntity != null) {
                System.out.println("Result: " + applicationEntity);
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null)
            {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }

    @Override
    public void findByApplicationLaunchDate(LocalDate applicationLaunchDate) {
        System.out.println("findByApplicationLaunchDate method in Repo implementation");
        EntityManager entityManager = null;
        ApplicationEntity applicationEntity;
        try {
            entityManager = JPAConnection.getEntityManager();
            applicationEntity = (ApplicationEntity) entityManager.createNamedQuery("applicationLaunchDate").setParameter("launchDate", applicationLaunchDate).getSingleResult();
            if (applicationEntity != null) {
                System.out.println("Result: " + applicationEntity);
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }finally {
            if(entityManager!=null)
            {
                System.out.println("entityManager is closed");
                entityManager.close();
            }
        }
    }
}

