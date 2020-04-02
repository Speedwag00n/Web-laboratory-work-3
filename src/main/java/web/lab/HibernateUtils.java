package web.lab;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {

    private static EntityManagerFactory entityManagerFactory;
    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }


    public static EntityManagerFactory getInstance(){
        return entityManagerFactory;
    }

}
