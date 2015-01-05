package com.scotch.simpleorders.dao;

import com.scotch.simpleorders.entity.Customer;
import java.util.List;

/**
 * Created by sutupin on 29.12.2014.
 */
public interface CustomerDao {
    Customer add(Customer newCustomer);
    Customer update(Customer dirtyCustomer);
    void remove(Customer commodity);
    List<Customer> getAll();
    Customer getById(Integer id);
}
