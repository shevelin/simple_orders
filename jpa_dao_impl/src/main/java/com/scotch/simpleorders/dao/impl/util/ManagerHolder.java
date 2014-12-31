package com.scotch.simpleorders.dao.impl.util;

import javax.persistence.EntityManager;

/**
 * Created by sutupin on 30.12.2014.
 */
public class ManagerHolder {
    private static ThreadLocal<EntityManager> managers;
    public static EntityManager getManager() {
        EntityManager manager = managers.get();
        if(manager == null) {
            manager = FactoryHolder.getEntityManager();
            managers.set(manager);
        }
        return manager;
    }

    public static void removeManager() {
        EntityManager manager = managers.get();
        if(manager != null) {
            manager.close();
            managers.remove();
        }
    }
}
