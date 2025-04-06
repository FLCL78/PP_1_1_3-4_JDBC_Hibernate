package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    //All
    private static final String HOST_NAME = "localhost";
    private static final String DB_NAME = "mydbtest";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    //Hibernate only
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;


    //JDBC
    public static Connection getMyConnection() {
        String MyUrl = "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MyUrl, LOGIN, PASSWORD);

        } catch (SQLException e) {
            System.err.println("Ошибка подключения, проверь настройки");
            e.printStackTrace();
        }
        return conn;
    }

    //Hibernate
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties setting = new Properties();
                setting.put(Environment.DRIVER, DRIVER);
                setting.put(Environment.URL, "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME);
                setting.put(Environment.USER, LOGIN);
                setting.put(Environment.PASS, PASSWORD);
                setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                setting.put(Environment.SHOW_SQL, "true");
                setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                setting.put(Environment.HBM2DDL_AUTO, "update"); //???????????????

                configuration.setProperties(setting);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Не будет вашего доступа к БД. ХА-ХА-ХА!");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


        public static void shutdown() {
            getSessionFactory().close();
        }


    }


