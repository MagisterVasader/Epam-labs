package com.booking.pool;

import com.booking.Printable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Queue;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public final class ConnectionPool {
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private Integer maxSize;
    private Integer validationConnectionTimeout;

    private Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnection = new ConcurrentSkipListSet<>(Comparator.comparingInt(Object::hashCode));

    private ConnectionPool() {
    }

    public void init(String driverClass, String jdbcUrl, String jdbcUser, String jdbcPassword, Integer minSize, Integer maxSize, Integer validationConnectionTimeout) throws ConnectionPoolException {
        try {
            destroy();
            Class.forName(driverClass);
            this.jdbcUrl = jdbcUrl + TimeZone.getDefault().getID();
            this.jdbcUser = jdbcUser;
            this.jdbcPassword = jdbcPassword;
            this.maxSize = maxSize;
            this.validationConnectionTimeout = validationConnectionTimeout;
            for (int i = 0; i < minSize; i++) {
                freeConnections.add(newConnection());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        while (connection == null) {
            try {
                connection = freeConnections.poll();
                if (connection != null) {
                    if (!connection.isValid(validationConnectionTimeout)) {
                        connection.close();
                        connection = null;
                    }
                } else {
                    if (usedConnection.size() < maxSize) {
                        connection = newConnection();
                    } else {
                        throw new ConnectionPoolException("The database connection number exceed limit.");
                    }
                }
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }
        usedConnection.add(connection);
        return new ConnectionWrapper(connection);
    }

    void freeConnection(Connection connection) {
        try {
            connection.clearWarnings();
            if (usedConnection.remove(connection)) {
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                Printable.printError(e1.getLocalizedMessage(),e1);
            }
        }
    }

    public void destroy() {
        synchronized (freeConnections) {
            synchronized (usedConnection) {
                usedConnection.addAll(freeConnections);
                freeConnections.clear();
                for (Connection connection : usedConnection) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        Printable.printError(e.getLocalizedMessage(),e);
                    }
                }
                usedConnection.clear();
            }
        }

    }

    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }
}
