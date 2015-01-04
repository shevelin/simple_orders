package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.CategoryDao;
import com.scotch.simpleorders.entity.Category;

import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaCategoryDao extends AbstractJpaDao<Category, Integer> implements CategoryDao {
    @Override
    protected Category getByUnique(Category category) {
        return entityManager.createQuery("SELECT c FROM Category c WHERE c.title LIKE :categoryTitle", entityClass)
                .setParameter("categoryTitle", category.getTitle()).getSingleResult();
    }

    @Override
    protected String getAllQueryString() {
        return "Select c From Category c";
    }
}
