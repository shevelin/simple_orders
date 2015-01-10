package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.entity.Customer;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;

import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by sutupin on 05.01.2015.
 */
public class JpaCustomerDaoTest extends DBUnitTestCase {

    @Test
    public void testAddNewCustomer() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/CustomerTestDataSet.xml");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, setupDataSet);

        Customer newCustomer = new Customer();
        newCustomer.setName("Serious Sam");
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

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/customer/CustomerTestDataSet_Expected.xml");
        ITable expectedTable = expectedDataSet.getTable("customer");

        ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredActualTable);
    }

    public void testDeleteCustomer() throws Exception {

    }
}
