package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.CustomerDao;
import com.scotch.simpleorders.entity.Customer;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaCustomerDao extends AbstractJpaDao<Customer, Integer> implements CustomerDao {
}
