package com.scotch.test.jpa.hibernate.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by sutupin on 29.12.2014.
 */
public class FactoryHolder {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("firstOne");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void closeFactory() {
        factory.close();
    }
}
