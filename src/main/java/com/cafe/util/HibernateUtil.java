package com.cafe.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Lớp tiện ích quản lý EntityManagerFactory cho JPA/Hibernate.
 */
public class HibernateUtil {
    private static final String PERSISTENCE_UNIT_NAME = "cafePU";
    private static EntityManagerFactory factory;

    /**
     * Lấy EntityManagerFactory.
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    /**
     * Tạo một EntityManager mới.
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    /**
     * Đóng EntityManagerFactory.
     */
    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
