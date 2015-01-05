package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.CustomerDao;
import com.scotch.simpleorders.dao.impl.util.ManagerHolder;
import com.scotch.simpleorders.entity.Customer;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaCustomerDao extends AbstractJpaDao<Customer, Integer> implements CustomerDao {
/*
    @Override
    protected String getAllQueryString() {
        return "Select a From Customer a";
    }
*/

/*
    @Override
    protected Customer getByUnique(Customer customer) {
        return entityManager.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :custName", entityClass)
                .setParameter("custName", customer.getName()).getSingleResult();
    }
*/
}
