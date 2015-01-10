package com.scotch.simpleorders.dao.impl;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sutupin on 06.01.2015.
 */
public abstract class DBUnitTestCase {
    private static final String DRIVER_CLASS = "org.hsqldb.jdbcDriver";
    private static final String CONNECTION_URL = "jdbc:hsqldb:mem:scotchtestdb";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private static EntityManagerFactory entityManagerFactory;
    protected static DatabaseConnection dbunitConnection;
    protected EntityManager entityManager;

    @BeforeClass
    public static void init() throws Exception {
        Map<String, String> persistenceProperties = new HashMap<>();

        persistenceProperties.put("javax.persistence.jdbc.driver", DRIVER_CLASS);
        persistenceProperties.put("javax.persistence.jdbc.url", CONNECTION_URL);
        persistenceProperties.put("javax.persistence.jdbc.user", USERNAME);
        persistenceProperties.put("javax.persistence.jdbc.password", PASSWORD);
        persistenceProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        persistenceProperties.put("hibernate.show_sql", "true");
        persistenceProperties.put("hibernate.format_sql", "true");
        persistenceProperties.put("hibernate.hbm2ddl.auto", "create");

        entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit", persistenceProperties);

        Class.forName("org.hsqldb.jdbcDriver");
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        dbunitConnection = new DatabaseConnection(connection);
    }

    @Before
    public void setEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void closeEntityManager() {
        if(entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @AfterClass
    public static void close() throws SQLException {
        entityManagerFactory.close();

        if (dbunitConnection != null) {
            dbunitConnection.close();
            dbunitConnection = null;
        }
    }

    protected DatabaseConnection getConnection(){
        return dbunitConnection;
    }

    protected IDataSet getDataSet(String resourceName) throws Exception {
//        return new XmlDataSet(DBUnitTestCase.class.getResourceAsStream(resourceName));

        return new XmlDataSet(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(resourceName));
    }



    /*
    public static final DatabaseOperation SEQUENCE_RESETTER_POSTGRES = new DatabaseOperation() {
        @Override
        public void execute(IDatabaseConnection connection, IDataSet dataSet)
                throws DatabaseUnitException, SQLException {
            String[] tables = dataSet.getTableNames();
            Statement statement = connection.getConnection().createStatement();
            for (String table : tables) {
                int startWith = dataSet.getTable(table).getRowCount() + 1;
                statement.execute("ALTER SEQUENCE " + table + "_id_seq RESTART WITH "+ startWith);

            }
        }
    };
*/


/*
    private static String dropCreateSqlQueryString = null;

    private String getDropCreateSqlQueryString() throws IOException {
        if(dropCreateSqlQueryString == null) {
            StringWriter sw = new StringWriter();
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("com/scotch/simpleorders/dao/impl/drop_create.sql");
            InputStreamReader isr = new InputStreamReader(is);
            int c;
            while ((c = isr.read()) != -1) {
                sw.write(c);
            }
            dropCreateSqlQueryString = sw.toString();
        }
        return dropCreateSqlQueryString;
    }
*/

}
