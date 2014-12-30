package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.CustomerDao;
import com.scotch.simpleorders.model.entity.Customer;

import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaCustomerDao implements CustomerDao {
    @Override
    public Customer add(Customer newCustomer) {
        return null;
    }

    @Override
    public Customer update(Customer dirtyCustomer) {
        return null;
    }

    @Override
    public void remove(Customer commodity) {

    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }
}
