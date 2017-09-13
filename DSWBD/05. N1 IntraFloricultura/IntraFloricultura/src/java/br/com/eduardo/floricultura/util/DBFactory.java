package br.com.eduardo.floricultura.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eduardo
 */
public class DBFactory {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/floricultura";
                String user = "postgres";
                String password = "postgres";

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
