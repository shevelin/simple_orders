package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.impl.util.FactoryHolder;
import com.scotch.simpleorders.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by sutupin on 26.12.2014.
 */
public class JpaCustomerDaoBak {
    public List<Customer> getAll() throws Exception {
        List<Customer> resultList = null;
        EntityManager manager = FactoryHolder.getEntityManager();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            resultList = manager.createQuery("Select a From Customer a", Customer.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }
        return resultList;
    }

    public Customer create(String name, String password) throws Exception { // todo: figure out
        Customer newCustomer = null;

        EntityManager manager = FactoryHolder.getEntityManager();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            //int count = manager.createQuery("select max(c.id) from Customer c", Integer.class).getSingleResult();

            newCustomer = new Customer();
            //newCustomer.setId(++count);
            newCustomer.setName(name);
            newCustomer.setPassword(password);
            manager.persist(newCustomer);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }

        return newCustomer;
    }

    public Customer update(Customer dirtyCustomer) throws Exception {
        EntityManager manager = FactoryHolder.getEntityManager();

        Customer customer = manager.find(Customer.class, dirtyCustomer.getId());

        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            customer.setName(dirtyCustomer.getName());
            customer.setPassword(dirtyCustomer.getPassword());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }

        return customer;
    }

    public Customer getById(int id) throws Exception {
        Customer result = null;

        EntityManager manager = FactoryHolder.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            result = manager.find(Customer.class, id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }

        if(result == null){
            throw new Exception();
        }

        return result;
    }

    public Customer getByName(String name) throws Exception {
        Customer result = null;

        EntityManager manager = FactoryHolder.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            result =  manager.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :custName",Customer.class)
                    .setParameter("custName", name).getSingleResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }

        return result;
    }

    public void remove(Customer customer) throws Exception {
        EntityManager manager = FactoryHolder.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            manager.remove(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }

    }

}
