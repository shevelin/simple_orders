package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.entity.Category;
import com.scotch.simpleorders.entity.Commodity;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by S on 10.01.2015.
 */
public class JpaCommodityDaoTest extends DBUnitTestCase{

    @Test
    public void testAddNew() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Commodity newCommodity = new Commodity();
        newCommodity.setTitle("topinambour");
        //newCommodity.setDescription("password");

        JpaCategoryDao categoryDao = new JpaCategoryDao();
        categoryDao.setEntityManager(entityManager);
        Category category = categoryDao.getById(2);

        newCommodity.setCategory(category);
        newCommodity.setPrice(new BigDecimal(12.0));

        JpaCommodityDao commodityDao = new JpaCommodityDao();
        commodityDao.setEntityManager(entityManager);

        try {
            commodityDao.add(newCommodity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("commodity");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity_add.xml");
        ITable expectedTable = expectedDataSet.getTable("commodity");

        Assertion.assertEquals(expectedTable, actualTable);
    }
/*
    @Test
    public void testRemove() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCommodityDao commodityDao = new JpaCommodityDao();
        commodityDao.setEntityManager(entityManager);

        try {
            Commodity commodity = commodityDao.getById(1);
            commodityDao.remove(commodity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("commodity");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity_remove.xml");
        ITable expectedTable = expectedDataSet.getTable("commodity");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdate() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCommodityDao commodityDao = new JpaCommodityDao();
        commodityDao.setEntityManager(entityManager);

        try {
            Commodity commodity = commodityDao.getById(1);
            commodity.setName("VikaIvanova");
            commodity.setPassword("vika77");
            commodityDao.update(commodity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("commodity");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity_update.xml");
        ITable expectedTable = expectedDataSet.getTable("commodity");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdateDetached() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCommodityDao commodityDao = new JpaCommodityDao();
        commodityDao.setEntityManager(entityManager);

        try {
            Commodity commodity = new Commodity();
            commodity.setName("VikaIvanova");
            commodity.setPassword("vika77");
            commodity.setId(1);
            commodityDao.update(commodity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("commodity");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity_update.xml");
        ITable expectedTable = expectedDataSet.getTable("commodity");

        Assertion.assertEquals(expectedTable, actualTable);
    }


    @Test
    public void testGetALL() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCommodityDao commodityDao = new JpaCommodityDao();
        commodityDao.setEntityManager(entityManager);

        List<Commodity> actual;
        try {
            actual = commodityDao.getAll();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Commodity first = new Commodity();
        first.setId(1); first.setName("Serious Sam"); first.setPassword("password");
        Commodity second = new Commodity();
        second.setId(2); second.setName("Wild Bill"); second.setPassword("password");


        List<Commodity> expected = new ArrayList<Commodity>();
        expected.add(first);
        expected.add(second);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetById() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCommodityDao commodityDao = new JpaCommodityDao();
        commodityDao.setEntityManager(entityManager);

        Commodity actual;
        try {
            actual = commodityDao.getById(1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Commodity expected = new Commodity();
        expected.setId(1); expected.setName("Serious Sam"); expected.setPassword("password");

        Assert.assertEquals(expected, actual);
    }
*/

}
