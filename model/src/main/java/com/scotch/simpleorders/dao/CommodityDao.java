package com.scotch.simpleorders.dao;

import com.scotch.simpleorders.entity.Category;
import com.scotch.simpleorders.entity.Commodity;
import java.util.List;

/**
 * Created by sutupin on 29.12.2014.
 */
interface CommodityDao {
    Commodity add(Commodity newCommodity);
    Commodity update(Commodity dirtyCommodity);
    void remove(Commodity commodity);
    void removeAllForCategory(Category category);
    List<Commodity> getAll();
    List<Commodity> getAllForCategory(Category category);
    Commodity getById(int id);
}
