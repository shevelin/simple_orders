package com.scotch.simpleorders.dao.impl;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sutupin on 06.01.2015.
 */
public abstract class DBUnitTestCase extends DBTestCase {
    public static final DatabaseOperation SEQUENCE_RESETTER_POSTGRES = new DatabaseOperation() {
        @Override
        public void execute(IDatabaseConnection connection, IDataSet dataSet)
                throws DatabaseUnitException, SQLException {
            String[] tables = dataSet.getTableNames();
            Statement statement = connection.getConnection().createStatement();
            for (String table : tables) {
                int startWith = dataSet.getTable(table).getRowCount() + 1;
                statement.execute("alter sequence " + table + "_PK_SEQ RESTART WITH "+ startWith);

            }
        }
    };

    public DBUnitTestCase(String name) {
        super(name);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:postgresql://localhost:5432/scotchtestdb");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "postgres");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "gosha");
        //System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        //return DatabaseOperation.REFRESH;
        //return DatabaseOperation.CLEAN_INSERT;

        return new CompositeOperation(DatabaseOperation.CLEAN_INSERT, SEQUENCE_RESETTER_POSTGRES);
    }

    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
    }

/*
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        IDatabaseConnection iDBConnection = getConnection();
        Connection connection  = iDBConnection.getConnection();

        DatabaseMetaData metaData = connection.getMetaData();


        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        String sqlString = getDropCreateSqlQueryString();

        try {
            statement.execute(sqlString);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }


    }

*/
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

}
