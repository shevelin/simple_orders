package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.entity.Customer;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;


import com.scotch.simpleorders.dao.impl.util.FactoryHolder;
import org.dbunit.operation.DatabaseOperation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by sutupin on 05.01.2015.
 */
public class JpaCustomerDaoTest extends DBTestCase {
    public JpaCustomerDaoTest(String name) {
        super(name);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:postgresql://localhost:5432/scotchtestdb");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "postgres");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "gosha");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(
                                "com/scotch/simpleorders/dao/impl/customer/CustomerTestDataSet.xml"));
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.DELETE;
    }

    public void testAddNewCustomer() throws Exception {

        Customer newCustomer = new Customer();
        newCustomer.setName("Serious Sam");
        newCustomer.setPassword("password");
        //newCustomer.setId(6);

        EntityManager entityManager = FactoryHolder.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();


        JpaCustomerDao customerDao = new JpaCustomerDao();
        customerDao.setEntityManager(entityManager);

        try {
            customerDao.add(newCustomer); // todo:

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            entityManager.close();
        }


        IDataSet databaseDataSet = getConnection().createDataSet();

        ITable actualTable = databaseDataSet.getTable("customer");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/scotch/simpleorders/dao/impl/customer/CustomerTestDataSet_Expected.xml"));

        ITable expectedTable = expectedDataSet.getTable("customer");
        ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

        Assertion.assertEquals(expectedTable, filteredActualTable);
        }
}
