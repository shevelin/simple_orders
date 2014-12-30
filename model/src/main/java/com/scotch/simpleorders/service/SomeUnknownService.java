package com.scotch.simpleorders.service;

import com.scotch.simpleorders.model.entity.Commodity;
import com.scotch.simpleorders.model.entity.Customer;

import java.util.List;

/**
 * Created by sutupin on 29.12.2014.
 */
public interface SomeUnknownService {
    List<Customer> getAllTheCustomersThatHaveOrderedCommodity(Commodity one);
}
