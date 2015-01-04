package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.impl.util.ManagerHolder;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Abstact DAO to manage entities.
 * @param <E> entity class
 * @param <K> type of primary key
 */
public abstract class AbstractJpaDao<E, K> {
    /**
     * Field to store entity class.
     */
    protected Class<E> entityClass;

    /**
     * Entity Manager.
     */
    protected EntityManager entityManager;

    /**
     * Default constructor that extacts entity type froim class definition.
     */
    public AbstractJpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public E add(E newEntity) {
        add0(newEntity);
        return getByUnique(newEntity);
    }

    private void add0(E newEntity) {
        entityManager.persist(newEntity);
    }
    
    protected abstract E getByUnique(E entity);

    public E update(E dirty) {
        return entityManager.merge(dirty);
    }

    public void remove(E one) {
        entityManager.remove(one);
    }

    protected abstract String getAllQueryString();

    public List<E> getAll() {
        String queryString = getAllQueryString(); // A decision was made to locate all here without creating fields for storage.
        List<E> resultList = entityManager.createQuery(queryString, entityClass)
                .getResultList();
        return resultList;
    }


    public E getById(int id) {
        return entityManager.find(entityClass, id);
    }
    
    public void flash() {
        entityManager.flush();
    }
}
