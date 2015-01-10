package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.entity.Customer;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;

import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;
import org.hamcrest.CoreMatchers;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sutupin on 05.01.2015.
 */
public class JpaCustomerDaoTest extends DBUnitTestCase {

    @Test
    public void testAddNew() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);
        //DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, setupDataSet);

        Customer newCustomer = new Customer();
        newCustomer.setName("Jane Doe");
        newCustomer.setPassword("password");

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCustomerDao customerDao = new JpaCustomerDao();
        customerDao.setEntityManager(entityManager);

        try {
            customerDao.add(newCustomer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("customer");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer_add.xml");
        ITable expectedTable = expectedDataSet.getTable("customer");

        ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
        //Assertion.assertEquals(expectedTable, filteredActualTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testRemove() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCustomerDao customerDao = new JpaCustomerDao();
        customerDao.setEntityManager(entityManager);

        try {
            Customer customer = customerDao.getById(1);
            customerDao.remove(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("customer");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer_remove.xml");
        ITable expectedTable = expectedDataSet.getTable("customer");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdate() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCustomerDao customerDao = new JpaCustomerDao();
        customerDao.setEntityManager(entityManager);

        try {
            Customer customer = customerDao.getById(1);
            customer.setName("VikaIvanova");
            customer.setPassword("vika77");
            customerDao.update(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("customer");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer_update.xml");
        ITable expectedTable = expectedDataSet.getTable("customer");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdateDetached() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCustomerDao customerDao = new JpaCustomerDao();
        customerDao.setEntityManager(entityManager);

        try {
            Customer customer = new Customer();
            customer.setName("VikaIvanova");
            customer.setPassword("vika77");
            customer.setId(1);
            customerDao.update(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("customer");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer_update.xml");
        ITable expectedTable = expectedDataSet.getTable("customer");

        Assertion.assertEquals(expectedTable, actualTable);
    }


    @Test
    public void testGetALL() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCustomerDao customerDao = new JpaCustomerDao();
        customerDao.setEntityManager(entityManager);

        List<Customer> actual;
        try {
            actual = customerDao.getAll();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Customer first = new Customer();
        first.setId(1); first.setName("Serious Sam"); first.setPassword("password");
        Customer second = new Customer();
        second.setId(2); second.setName("Wild Bill"); second.setPassword("password");


        List<Customer> expected = new ArrayList<Customer>();
        expected.add(first);
        expected.add(second);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetById() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/customer.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCustomerDao customerDao = new JpaCustomerDao();
        customerDao.setEntityManager(entityManager);

        Customer actual;
        try {
            actual = customerDao.getById(1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Customer expected = new Customer();
        expected.setId(1); expected.setName("Serious Sam"); expected.setPassword("password");

        Assert.assertEquals(expected, actual);
    }

}
