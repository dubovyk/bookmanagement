package com.dubovyk.bookmanager.Persistance;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * This class provides a method to start db sessions.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * @return Creates a session factory for database connections based
     * on config in hibernate.cfg.xml.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Close caches and connection pools
     */
    public static void shutdown() {
        getSessionFactory().close();
    }

}
