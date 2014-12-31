package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.CustomerDao;
import com.scotch.simpleorders.dao.impl.util.ManagerHolder;
import com.scotch.simpleorders.entity.Customer;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaCustomerDao implements CustomerDao {
    private EntityManager getEntityManager() {
        return ManagerHolder.getManager();
    }

    @Override
    public Customer add(Customer newCustomer) {
        getEntityManager().persist(newCustomer);
        return getEntityManager().createQuery("SELECT c FROM Customer c WHERE c.name LIKE :custName", Customer.class)
                .setParameter("custName", newCustomer.getName()).getSingleResult();
    }

    @Override
    public Customer update(Customer dirtyCustomer) {
        return getEntityManager().merge(dirtyCustomer);
    }

    @Override
    public void remove(Customer customer) {
        getEntityManager().remove(customer);
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> resultList = getEntityManager().createQuery("Select a From Customer a", Customer.class)
                .getResultList();
        return resultList;
    }

    @Override
    public Customer getById(int id) {
        return getEntityManager().find(Customer.class, id);
    }
}
