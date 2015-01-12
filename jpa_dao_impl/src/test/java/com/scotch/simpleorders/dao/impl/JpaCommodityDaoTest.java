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
        newCommodity.setDescription("");

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

        IDataSet databaseDataSet = dbunitConnection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("commodity");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/commodity/commodity_add.xml");
        ITable expectedTable = expectedDataSet.getTable("commodity");

        Assertion.assertEquals(expectedTable, actualTable);
    }

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
            commodity.setTitle("earth apple");
            JpaCategoryDao categoryDao = new JpaCategoryDao();
            categoryDao.setEntityManager(entityManager);
            Category category = categoryDao.getById(2);
            commodity.setCategory(category);
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

        Commodity commodity;
        Category category;

        try {
            commodity = commodityDao.getById(1);
            JpaCategoryDao categoryDao = new JpaCategoryDao();
            categoryDao.setEntityManager(entityManager);
            category = categoryDao.getById(2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        commodity.setTitle("earth apple");
        commodity.setCategory(category);

        tx = entityManager.getTransaction();
        tx.begin();

        try {
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
    public void testGetAll() throws Exception {
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

        Assert.assertEquals(actual.size(), 25);
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
        Category actualCategory;
        try {
            actual = commodityDao.getById(1);
            actualCategory = actual.getCategory(); //!!!!!!!!
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Assert.assertEquals(1, actual.getId());
        Assert.assertEquals("pear", actual.getTitle());
        Assert.assertEquals("", actual.getDescription());


        Category expectedCategory = new Category();
        expectedCategory.setTitle("fruit");

        Assert.assertEquals(expectedCategory, actualCategory); //todo: figure out what happening

        Assert.assertEquals(new BigDecimal(1.0), actual.getPrice());
    }

}
