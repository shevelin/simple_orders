package com.scotch.simpleorders.dao;

/*import com.scotch.simpleorders.dao.CategoryDao;
import com.scotch.simpleorders.entity.Category;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;*/

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.scotch.simpleorders.entity.Category;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by S on 10.01.2015.
 */
@ContextConfiguration
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public class CategoryDaoTestWithSpring extends AbstractJUnit4SpringContextTests {

    @Resource
    private CategoryDao categoryDao;


/*
    @After
    public void resetSequence() throws DatabaseUnitException, SQLException {
        String[] tables = dataSet.getTableNames();
        Statement statement = connection.getConnection().createStatement();
        for (String table : tables) {
            int startWith = dataSet.getTable(table).getRowCount() + 1;
            statement.execute("ALTER SEQUENCE " + table + "_id_seq RESTART WITH " + startWith);

        }
    }


*/
    @Test
    @DatabaseSetup("category.xml")
    @ExpectedDatabase(value = "category_add.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testAddNew() throws Exception {
//        DatabaseOperation databaseOperation = new CompositeOperation(DatabaseOperation.CLEAN_INSERT, DBUnitTestCase.SEQUENCE_RESETTER);
//        databaseOperation.execute(dbunitConnection, setupDataSet);

        Category newCategory = new Category();
        newCategory.setTitle("Sportswear");
        newCategory.setDescription("Sportswear category description");

        categoryDao.add(newCategory);

    }
/*
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
    public void testGetAll() throws Exception {
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

        Assert.assertEquals(actual.size(), 5);
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
        expected.setTitle("fruit");
        expected.setDescription("Some description of fruit category");

        Assert.assertEquals(expected, actual);
    }
*/

}
