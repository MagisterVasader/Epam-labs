package com.booking.connection;

import com.booking.Printable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private DBConnection() {
    }

    public static Connection getDbConnection() {
        String dbHost = "localhost";
        String dbPort = "3306";
        String dbUser = "root";
        String dbPass = "asaa220708";
        String dbName = "web_app_epam?serverTimezone=Europe/Moscow&useSSL=false&allowPublicKeyRetrieval=true";

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            Printable.printInfo("Connected successfully!");
        } catch (SQLException e) {
            Printable.printError(e.getLocalizedMessage());
        }
        return dbConnection;
    }

}
