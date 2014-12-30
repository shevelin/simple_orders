package com.scotch.simpleorders.dao;

import com.scotch.simpleorders.model.entity.Category;

import java.util.List;

/**
 * Created by sutupin on 29.12.2014.
 */
public interface CategoryDao {
    Category add(Category newCategory);
    Category update(Category dirtyCategory);
    void remove(Category category);
    List<Category> getAll();
    Category getById(int id);
}
