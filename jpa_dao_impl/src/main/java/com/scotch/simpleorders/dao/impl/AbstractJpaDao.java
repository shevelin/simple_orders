package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.impl.util.ManagerHolder;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sutupin on 31.12.2014.
 */
public abstract class AbstractJpaDao<T> {
    protected EntityManager getEntityManager() {
        return ManagerHolder.getManager();
    }

    public abstract T add(T newT);

    public T update(T dirty) {
        return getEntityManager().merge(dirty);
    }

    public void remove(T one) {
        getEntityManager().remove(one);
    }

    public abstract List<T> getAll() ;

    public T getById(int id) {
        return getEntityManager().find(T.class, id);
    }
}
