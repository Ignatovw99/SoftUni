package config;

import constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static Connection CONNECTION;

    public static void createConnectionToHost(String username, String password) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        Connector.CONNECTION = DriverManager.getConnection(Connector.CONNECTION_STRING + Constants.DATABASE, props);
    }

    public static Connection getConnection() {
        return Connector.CONNECTION;
    }
}
