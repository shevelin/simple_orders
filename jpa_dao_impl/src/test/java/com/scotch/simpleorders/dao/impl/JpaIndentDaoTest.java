package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.entity.Indent;
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
public class JpaIndentDaoTest extends DBUnitTestCase {

/*
    @Test
    public void testAddNew() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);
        //DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, setupDataSet);

        Indent newIndent = new Indent();
        newIndent.setName("Jane Doe");
        newIndent.setPassword("password");

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaIndentDao indentDao = new JpaIndentDao();
        indentDao.setEntityManager(entityManager);

        try {
            indentDao.add(newIndent);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("indent");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent_add.xml");
        ITable expectedTable = expectedDataSet.getTable("indent");

        ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
        //Assertion.assertEquals(expectedTable, filteredActualTable);
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testRemove() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaIndentDao indentDao = new JpaIndentDao();
        indentDao.setEntityManager(entityManager);

        try {
            Indent indent = indentDao.getById(1);
            indentDao.remove(indent);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("indent");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent_remove.xml");
        ITable expectedTable = expectedDataSet.getTable("indent");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdate() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaIndentDao indentDao = new JpaIndentDao();
        indentDao.setEntityManager(entityManager);

        try {
            Indent indent = indentDao.getById(1);
            indent.setName("VikaIvanova");
            indent.setPassword("vika77");
            indentDao.update(indent);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("indent");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent_update.xml");
        ITable expectedTable = expectedDataSet.getTable("indent");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdateDetached() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaIndentDao indentDao = new JpaIndentDao();
        indentDao.setEntityManager(entityManager);

        try {
            Indent indent = new Indent();
            indent.setName("VikaIvanova");
            indent.setPassword("vika77");
            indent.setId(1);
            indentDao.update(indent);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("indent");

        IDataSet expectedDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent_update.xml");
        ITable expectedTable = expectedDataSet.getTable("indent");

        Assertion.assertEquals(expectedTable, actualTable);
    }


    @Test
    public void testGetALL() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaIndentDao indentDao = new JpaIndentDao();
        indentDao.setEntityManager(entityManager);

        List<Indent> actual;
        try {
            actual = indentDao.getAll();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Indent first = new Indent();
        first.setId(1); first.setName("Serious Sam"); first.setPassword("password");
        Indent second = new Indent();
        second.setId(2); second.setName("Wild Bill"); second.setPassword("password");


        List<Indent> expected = new ArrayList<Indent>();
        expected.add(first);
        expected.add(second);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetById() throws Exception {
        IDataSet setupDataSet = getDataSet("com/scotch/simpleorders/dao/impl/indent/indent.xml");

        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
        databaseOperation.execute(dbunitConnection, setupDataSet);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        JpaIndentDao indentDao = new JpaIndentDao();
        indentDao.setEntityManager(entityManager);

        Indent actual;
        try {
            actual = indentDao.getById(1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        }

        Indent expected = new Indent();
        expected.setId(1); expected.setName("Serious Sam"); expected.setPassword("password");

        Assert.assertEquals(expected, actual);
    }
*/

}
