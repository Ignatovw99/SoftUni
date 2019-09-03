package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static Configuration CONFIGURATION;
    private static SessionFactory SESSION_FACTORY;

    public static void setupHibernate() {
        CONFIGURATION = new Configuration();
        CONFIGURATION.configure();
        SESSION_FACTORY = CONFIGURATION.buildSessionFactory();
    }

    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }
}
