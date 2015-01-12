package com.scotch.simpleorders.dao;

import com.scotch.simpleorders.entity.IndentItem;

import java.util.List;

/**
 * Created by sutupin on 12.01.2015.
 */
public interface IndentItemDao {
    IndentItem add(IndentItem newIndentItem);
    IndentItem update(IndentItem dirtyIndentItem);
    void remove(IndentItem indentItem);
    List<IndentItem> getAll();
    IndentItem getById(Integer id);
}
