package web.lab;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.faces.context.FacesContext;
import java.io.File;

public class HibernateUtils {

    private static SessionFactory instance;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Point.class);
            instance = configuration.configure().buildSessionFactory();
        } catch (Exception e) {

        }
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }

}
