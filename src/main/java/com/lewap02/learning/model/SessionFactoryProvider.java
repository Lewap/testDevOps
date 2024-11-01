package com.lewap02.learning.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {

    public static SessionFactory provideSessionFactory() {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        return config.buildSessionFactory();

    }

}
