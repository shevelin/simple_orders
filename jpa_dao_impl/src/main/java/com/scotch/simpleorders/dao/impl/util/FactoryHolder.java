package com.scotch.simpleorders.dao.impl.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sutupin on 29.12.2014.
 */
public class FactoryHolder {
    private static EntityManagerFactory factory;

    static {
        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/param.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = Persistence.createEntityManagerFactory("firstOne", props);
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void closeFactory() {
        factory.close();
    }
}
