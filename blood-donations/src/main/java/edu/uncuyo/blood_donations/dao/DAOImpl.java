package edu.uncuyo.blood_donations.dao;

import edu.uncuyo.blood_donations.entity.Entity;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author adrian
 */
public class DAOImpl<T extends Entity<U>, U> implements DAO<T, U> {

    protected EntityManagerFactory entityManagerFactory;
    protected Class<T> entityClass;

    public DAOImpl(EntityManagerFactory entityManagerFactory, Class<T> entityClass) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityClass = entityClass;
    }

    @Override
    public void create(T t) {
        var entityManager = entityManagerFactory.createEntityManager();
        var transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(t);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T read(U u) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(entityClass, u);
        }
    }

    @Override
    public Set<T> read() {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            return new HashSet<>(entityManager.createQuery("SELECT t FROM " + entityClass.getSimpleName() + " t", entityClass).getResultList());
        }
    }

    @Override
    public void update(T t) {
        var entityManager = entityManagerFactory.createEntityManager();
        var transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (entityManager.find(entityClass, t.getId()) != null) {
                entityManager.merge(t);
                transaction.commit();
            }
        } catch (Exception exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(U u) {
        var entityManager = entityManagerFactory.createEntityManager();
        var transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T t = entityManager.find(entityClass, u);
            if (t != null) {
                entityManager.remove(t);
                transaction.commit();
            }
        } catch (Exception exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }
}
