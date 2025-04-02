package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String HOST_NAME = "localhost" ;
    private static final String DB_NAME = "mydbtest";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";



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
}
