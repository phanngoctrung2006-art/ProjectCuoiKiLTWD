package com.cafe.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Lớp Utility hỗ trợ khởi tạo và quản lý phiên làm việc với Hibernate (EntityManagerFactory).
 * Design Pattern: Singleton (Khởi tạo kết nối 1 lần để dùng chung cho toàn dự án).
 */
public class HibernateUtil {
    // Tên Unit cấu hình giống khai báo trong persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "cafePU";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
