package com.xworkz.movie.repository;

import com.xworkz.movie.entity.MovieEntity;
import com.xworkz.movie.util.DBConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MovieRepositoryImpl implements MovieRepository {

    public MovieRepositoryImpl() {
        System.out.println("MovieRepository implementation constructor");
    }

    @Override
    public boolean save(MovieEntity movieEntity) {
        System.out.println("save method in Repository");
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            entityManager.persist(movieEntity);
            entityTransaction.commit();

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed data");
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
    public boolean deleteById(Integer id) {
        System.out.println("delete by id in Repository");
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            MovieEntity movie = entityManager.find(MovieEntity.class, id);
            if (movie != null) {
                System.out.println("data is found");
                entityManager.remove(movie);
                entityTransaction.commit();
            } else {
                System.out.println("data is not found");
                return false;
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed data");
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
    public boolean updateById(Integer id, Float ratings) {
        System.out.println("update by id in repository");
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = DBConnection.getEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            MovieEntity movie = entityManager.find(MovieEntity.class, id);
            if (movie != null) {
                System.out.println("data is found");
                movie.setRatings(ratings);
                movie = entityManager.merge(movie);
                entityTransaction.commit();
                System.out.println("Updated entity: " + movie);
            } else {
                System.out.println("data is not found");
                return false;
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Roll backed data");
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
    public Optional<MovieEntity> findByMovieName(String movieName) {
        System.out.println("find by movie name in repository");
        EntityManager entityManager = null;
        MovieEntity movieEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();

            movieEntity = (MovieEntity) entityManager.createNamedQuery("movieName").setParameter("name", movieName).getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                System.out.println("EntityManager is closed");
                entityManager.close();
            }
        }
        return movieEntity != null ? Optional.of(movieEntity) : Optional.empty();
    }

    @Override
    public Optional<MovieEntity> findByDirectorName(String directorName) {
        System.out.println("find by director name in repository");
        EntityManager entityManager = null;
        MovieEntity movieEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();

            Query query = entityManager.createNamedQuery("directorName");
            movieEntity = (MovieEntity) query.setParameter("name", directorName).getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                System.out.println("EntityManager is closed");
                entityManager.close();
            }
        }
        return movieEntity != null ? Optional.of(movieEntity) : Optional.empty();
    }

    @Override
    public List<MovieEntity> findByHeroName(String heroName) {
        System.out.println("find by hero name in repository");
        EntityManager entityManager = null;
        List<MovieEntity> list = null;
        try {
            entityManager = DBConnection.getEntityManager();

            Query query = entityManager.createNamedQuery("heroName");
            list = query.setParameter("name", heroName).getResultList();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                System.out.println("EntityManager is closed");
                entityManager.close();
            }
        }
        return list;
    }

    @Override
    public List<MovieEntity> findAll() {
        System.out.println("findAll in repository");
        EntityManager entityManager = null;
        List<MovieEntity> list = null;
        try {
            entityManager = DBConnection.getEntityManager();

            Query query = entityManager.createNamedQuery("findAll");
            list = query.getResultList();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                System.out.println("EntityManager is closed");
                entityManager.close();
            }
        }
        return list;
    }

    @Override
    public MovieEntity findById(Integer id) {
        System.out.println("find by id method in repository");
        EntityManager entityManager = null;
        MovieEntity movieEntity = null;
        try {
            entityManager = DBConnection.getEntityManager();

            Query query = entityManager.createNamedQuery("findById");
            movieEntity = (MovieEntity) query.setParameter("id", id).getSingleResult();

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                System.out.println("EntityManager is closed");
                entityManager.close();
            }
        }
        return movieEntity;
    }
}
