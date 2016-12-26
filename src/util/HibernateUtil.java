package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by zhenghb on 2016/6/3.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    @SuppressWarnings("deprecation")
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory(); }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
