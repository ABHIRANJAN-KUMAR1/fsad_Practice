package com.klu.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("SessionFactory creation failed!");
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}