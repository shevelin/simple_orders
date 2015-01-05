package com.scotch.simpleorders.dao;

import com.scotch.simpleorders.entity.Commodity;
import com.scotch.simpleorders.entity.Customer;
import com.scotch.simpleorders.entity.Indent;
import com.scotch.simpleorders.entity.IndentStatus;

import java.util.List;

/**
 * Created by sutupin on 29.12.2014.
 */
public interface IndentDao {
    Indent add(Indent newIndent);
    Indent update(Indent dirtyCategory);
    void remove(Indent category);
    List<Indent> getAll();
    List<Indent> getAllForCustomer(Customer customer);
    List<Indent> getAllForStatus(IndentStatus status);
    List<Indent> getAllForCommodity(Commodity commodity);
    Indent getById(Integer id);
}
