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
    protected Commodity getByUnique(Commodity entity) {
        return null;
    }

    @Override
    protected String getAllQueryString() {
        return null;
    }

    @Override
    public void removeAllForCategory(Category category) {

    }

    @Override
    public List<Commodity> getAllForCategory(Category category) {
        return null;
    }
}
