package org.phstudy;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.phstudy.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * Created by study on 11/14/14.
 */
public class SeparateDatabaseApp {
    private static String DATABASE_JDBC_URL = "jdbc:h2:~/SeparateDatabase";
    private static String DATABASE_JDBC_URL2 = "jdbc:h2:~/SeparateDatabase2";
    private static String DATABASE_USERNAME = "sa";
    private static String DATABASE_PASSWORD = "";
    private static String PU = "pu-tenant1";
    private static String PU2 = "pu-tenant2";


    public static void main(String[] args) {
        // use Persistence Unit#1
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PU, getProperties(DATABASE_JDBC_URL));
        EntityManager em = factory.createEntityManager();
        em.find(Product.class, 1L);
        em.close();

        // use Persistence Unit#2
        EntityManagerFactory factory2 = Persistence.createEntityManagerFactory(PU2, getProperties(DATABASE_JDBC_URL2));
        EntityManager em2 = factory2.createEntityManager();
        em2.find(Product.class, 1L);
        em2.close();
    }

    private static Properties getProperties(String jdbcUrl) {
        Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        properties.put("javax.persistence.database-major-version", "1");
        properties.put("javax.persistence.database-minor-version", "4");
        properties.put("javax.persistence.jdbc.url", jdbcUrl);
        properties.put("javax.persistence.jdbc.user", DATABASE_USERNAME);
        properties.put("javax.persistence.jdbc.password", DATABASE_PASSWORD);
        properties.put("javax.persistence.database-product-name", "H2");
        properties.put("javax.persistence.schema-generation.database.action", "create");
        properties.put("javax.persistence.schema-generation.create-database-schemas", "true");
        return properties;
    }
}
