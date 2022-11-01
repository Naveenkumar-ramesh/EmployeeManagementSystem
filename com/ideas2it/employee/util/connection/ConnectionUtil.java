package com.ideas2it.employee.util.connection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Makes connection between database and the application .
 * @author Naveenkumar
 */
public class ConnectionUtil {

    private static SessionFactory sessionFactory;

    private ConnectionUtil() {
    }

    /**
     * Creates connection between the database and application.
     */
    public static SessionFactory getSessionFactory() {
        try {
            if(sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
         return sessionFactory;
    }

    /**
     * Closes the connection between the database and application.
     */
    public void closeConnection() {
        try {
            if(sessionFactory != null) {
                sessionFactory.close();
            }
        } catch(HibernateException e) {
            e.printStackTrace();
        }
    }
}