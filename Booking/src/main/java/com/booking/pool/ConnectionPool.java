package com.booking.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private Integer maxSize;
    private Integer validationConnectionTimeout;

    private final Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private final Set<Connection> usedConnection = new ConcurrentSkipListSet<>(Comparator.comparingInt(Object::hashCode));
    private static final ConnectionPool instance = new ConnectionPool();

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
            LOGGER.info("Connection pool initialized");
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
                        throw new ConnectionPoolException("The database connection number exceed limit");
                    }
                }
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }
        usedConnection.add(connection);
        LOGGER.info("getConnection success");
        return new ConnectionWrapper(connection);
    }

    void freeConnection(Connection connection) {
        try {
            connection.clearWarnings();
            if (usedConnection.remove(connection)) {
                freeConnections.add(connection);
                LOGGER.info("freeConnection success");
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                LOGGER.error(e1);
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
                        LOGGER.error(e);
                    }
                }
                usedConnection.clear();
            }
        }
        LOGGER.info("destroy success");
    }

    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public static ConnectionPool getInstance() {
        return instance;
    }
}
