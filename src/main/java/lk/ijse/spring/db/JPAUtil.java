package lk.ijse.spring.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JPAUtil {

    private static EntityManagerFactory emf = buildEntityManageFactory();

    private static EntityManagerFactory buildEntityManageFactory() {
//        File file = new File("resources/application.properties");
        Properties properties = new Properties();

        try {
//            properties.load(new FileInputStream(file));
//            properties.load(new FileReader("application.properties"));
            properties.load(JPAUtil.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Persistence.createEntityManagerFactory("DEP",properties);
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
}
