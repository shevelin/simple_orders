package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.IndentDao;
import com.scotch.simpleorders.model.entity.Commodity;
import com.scotch.simpleorders.model.entity.Customer;
import com.scotch.simpleorders.model.entity.Indent;
import com.scotch.simpleorders.model.entity.IndentStatus;

import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaIndentDao implements IndentDao {
    @Override
    public Indent add(Indent newIndent) {
        return null;
    }

    @Override
    public Indent update(Indent dirtyCategory) {
        return null;
    }

    @Override
    public void remove(Indent category) {

    }

    @Override
    public List<Indent> getAll() {
        return null;
    }

    @Override
    public List<Indent> getAllForCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Indent> getAllForStatus(IndentStatus status) {
        return null;
    }

    @Override
    public List<Indent> getAllForCommodity(Commodity commodity) {
        return null;
    }

    @Override
    public Indent getById(int id) {
        return null;
    }
}
