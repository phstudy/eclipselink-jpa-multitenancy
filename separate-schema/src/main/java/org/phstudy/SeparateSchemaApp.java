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
public class SeparateSchemaApp {
    private static String DATABASE_JDBC_URL = "jdbc:h2:~/SeparateSchema";
    private static String DATABASE_USERNAME = "sa";
    private static String DATABASE_PASSWORD = "";
    private static String PU = "pu-eclipselink";

    public static void main(String[] args) {

        /*
            ----- Caveat -----
            Separate Schema do not support DDL generation, and it must be created manually.
            The schema definition is in resources/sql/init.sql file.
         */

        // use default tenant which defined in persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PU, getProperties());
        EntityManager em = factory.createEntityManager();

        em.find(Product.class, 1L);
        em.close();

        // change tenant in EntityManagerFactory Level
        Properties properties = getProperties();
        properties.put(PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT, "JCConf");
        properties.put(PersistenceUnitProperties.SESSION_NAME, "multi-tenant-JCConf");
        factory = Persistence.createEntityManagerFactory(PU, properties);
        em = factory.createEntityManager();
        em.find(Product.class, 1L);

        em.close();
    }



    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        properties.put("javax.persistence.database-major-version", "1");
        properties.put("javax.persistence.database-minor-version", "4");
        properties.put("javax.persistence.jdbc.url", DATABASE_JDBC_URL);
        properties.put("javax.persistence.jdbc.user", DATABASE_USERNAME);
        properties.put("javax.persistence.jdbc.password", DATABASE_PASSWORD);
        properties.put("javax.persistence.database-product-name", "H2");
        properties.put("javax.persistence.schema-generation.database.action", "create");
        properties.put("javax.persistence.schema-generation.create-database-schemas", "true");
        properties.put("javax.persistence.sql-load-script-source", "sql/init.sql");
        return properties;
    }
}
