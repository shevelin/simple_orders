package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.CommodityDao;
import com.scotch.simpleorders.entity.Category;
import com.scotch.simpleorders.entity.Commodity;

import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaCommodityDao extends AbstractJpaDao<Commodity, Integer> implements CommodityDao {
    @Override
    public void removeAllForCategory(Category category) {
        entityManager.createQuery("DELETE FROM Commodity c WHERE c.category = :categoryId ").setParameter("categoryId", category.getId());
    }

    @Override
    public List<Commodity> getAllForCategory(Category category) {
        return entityManager.createQuery("SELECT c FROM Commodity c WHERE c.category = :categoryId", entityClass)
                .setParameter("categoryId", category.getId()).getResultList();
    }
}
