package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.entity.Customer;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;


import com.scotch.simpleorders.dao.impl.util.FactoryHolder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by sutupin on 05.01.2015.
 */
public class JpaCustomerDaoTest extends DBUnitTestCase {
    public JpaCustomerDaoTest(String name) {
        super(name);

    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(
                                "com/scotch/simpleorders/dao/impl/customer/CustomerTestDataSet.xml"));
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
            customerDao.add(newCustomer);
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
