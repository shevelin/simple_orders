package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.entity.Category;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by S on 10.01.2015.
 */
public class JpaCategoryDaoTest extends DBUnitTestCase {

    @Test
    public void testAddNew() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        Category newCategory = new Category();
        newCategory.setTitle("Sportswear");
        newCategory.setDescription("Sportswear category description");

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCategoryDao categoryDao = new JpaCategoryDao();
        categoryDao.setEntityManager(entityManager);

        try {
            categoryDao.add(newCategory);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("category");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category_add.xml");
        ITable expectedTable = expectedDataSet.getTable("category");

        ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
        //Assertion.assertEquals(expectedTable, filteredActualTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testRemove() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCategoryDao categoryDao = new JpaCategoryDao();
        categoryDao.setEntityManager(entityManager);

        try {
            Category category = categoryDao.getById(1);
            categoryDao.remove(category);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("category");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category_remove.xml");
        ITable expectedTable = expectedDataSet.getTable("category");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdate() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCategoryDao categoryDao = new JpaCategoryDao();
        categoryDao.setEntityManager(entityManager);

        try {
            Category category = categoryDao.getById(1);
            category.setTitle("Sportswear");
            category.setDescription("Sportswear category description");
            categoryDao.update(category);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("category");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category_update.xml");
        ITable expectedTable = expectedDataSet.getTable("category");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdateDetached() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCategoryDao categoryDao = new JpaCategoryDao();
        categoryDao.setEntityManager(entityManager);

        try {
            Category category = new Category();
            category.setTitle("Sportswear");
            category.setDescription("Sportswear category description");
            category.setId(1);
            categoryDao.update(category);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("category");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category_update.xml");
        ITable expectedTable = expectedDataSet.getTable("category");

        Assertion.assertEquals(expectedTable, actualTable);
    }


    @Test
    public void testGetALL() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCategoryDao categoryDao = new JpaCategoryDao();
        categoryDao.setEntityManager(entityManager);

        List<Category> actual;
        try {
            actual = categoryDao.getAll();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Category first = new Category();
        first.setId(1); first.setTitle(""); first.setDescription("");

        Category second = new Category();
        second.setId(2); second.setTitle(""); second.setDescription("");


        List<Category> expected = new ArrayList<Category>();
        expected.add(first);
        expected.add(second);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetById() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/category/category.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaCategoryDao categoryDao = new JpaCategoryDao();
        categoryDao.setEntityManager(entityManager);

        Category actual;
        try {
            actual = categoryDao.getById(1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Category expected = new Category();
        expected.setId(1); expected.setTitle(""); expected.setDescription("");

        Assert.assertEquals(expected, actual);
    }

}
